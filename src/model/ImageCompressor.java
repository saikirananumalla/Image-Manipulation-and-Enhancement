package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A class to compress the image provided by using the haar wavelet transform methods. We first
 * generate double arrays of rgb pixel value arrays using haar transform then compress the image
 * using the percent provided, remove data, then convert the image back using inverse haar
 * transform resulting in a lossy compression. Generates a new image after compression.
 */
public class ImageCompressor {

  private final Image image;

  /**
   * Create a new ImageCompressor instance using the image object passed.
   *
   * @param image Image object to be compressed
   */
  public ImageCompressor(Image image) {
    this.image = image;
  }

  /**
   * Compresses the image by given percentage.
   *
   * @param percent The percentage which needs to be compressed.
   * @return The new compressed image.
   */
  public Image compress(int percent) {

    int maxSide = Math.max(image.getHeight(), image.getWidth());
    int powerRequired = ((int) Math.ceil(Math.log(maxSide) / Math.log(2.00)));
    int gridSize = (int) Math.pow(2, powerRequired);

    // This method will set the grid appropriately and return the grid of doubles.
    double[][] redGrid = getValueGridFromImage(0, gridSize);
    double[][] greenGrid = getValueGridFromImage(1, gridSize);
    double[][] blueGrid = getValueGridFromImage(2, gridSize);


    double[][] transformedRedGrid = haarTransform(redGrid, redGrid.length);
    double[][] transformedGreenGrid = haarTransform(greenGrid, greenGrid.length);
    double[][] transformedBlueGrid = haarTransform(blueGrid, blueGrid.length);

    double threshold = getThreshold(transformedRedGrid, transformedGreenGrid, transformedBlueGrid,
            percent);

    double[][] compressedRedGrid = compressGridByPercentage(transformedRedGrid,
            threshold);
    double[][] compressedGreenGrid = compressGridByPercentage(transformedGreenGrid,
            threshold);
    double[][] compressedBlueGrid = compressGridByPercentage(transformedBlueGrid,
            threshold);

    double[][] inverseTransformRedGrid = inverseHaarTransform(compressedRedGrid, redGrid.length);
    double[][] inverseTransformGreenGrid = inverseHaarTransform(compressedGreenGrid,
            greenGrid.length);
    double[][] inverseTransformBlueGrid = inverseHaarTransform(compressedBlueGrid, blueGrid.length);

    return getImageFromGrid(inverseTransformRedGrid, inverseTransformGreenGrid,
            inverseTransformBlueGrid);

  }

  /**
   * Generates a new image given a grid of pixel values in the specific channel.
   *
   * @param redGrid   The grid for red values.
   * @param greenGrid The grid for green values.
   * @param blueGrid  The grid for blue values.
   * @return The new image object with the specified grid valued present at the specified channel.
   */
  private Image getImageFromGrid(double[][] redGrid, double[][] greenGrid, double[][] blueGrid) {

    BufferedImage bufferedImage = new BufferedImage(image.getWidth(),
            image.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {

        int r = (int) Math.round(redGrid[i][j]);
        int g = (int) Math.round(greenGrid[i][j]);
        int b = (int) Math.round(blueGrid[i][j]);

        int rgb = (r << 16) | (g << 8) | b;

        bufferedImage.setRGB(j, i, rgb);
      }
    }

    return RGBImage.getLoader().loadFromBufferedImage(bufferedImage);
  }

  /**
   * This method compresses the grid by removing the smallest values which is governed by the
   * percentage that the base image should be compressed by.
   *
   * @param grid      The grid of values.
   * @param threshold The percentage by which the image should be compressed by.
   * @return The new grid object where the smallest 'n' percentage of values are removed.
   */
  private double[][] compressGridByPercentage(double[][] grid, double threshold) {

    int h = grid.length;
    double[][] res = new double[h][h];

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < h; j++) {
        if (Math.abs(grid[i][j]) <= threshold) {
          res[i][j] = 0;
        } else {
          res[i][j] = grid[i][j];
        }
      }
    }

    return res;

  }

  /**
   * This helper method transforms the given grid of values using the haar transform.
   *
   * @param grid   The grid of values.
   * @param length The length of the grid, This will be a square grid so, the sides are the same.
   * @return The transformed grid object.
   */
  private double[][] haarTransform(double[][] grid, int length) {

    double[][] res = new double[length][length];

    for (int i = 0; i < length; i++) {
      System.arraycopy(grid[i], 0, res[i], 0, length);
    }

    class HaarTransform {
      void transformRow(int row, int mLength) {
        for (int p = 0, q = 0, r = mLength / 2; p <= mLength - 2; p += 2, q++, r++) {
          double a = grid[row][p];
          double b = grid[row][p + 1];

          res[row][q] = getNormalizedAverage(a, b);
          res[row][r] = getNormalizedDifference(a, b);
        }
      }

      void transformColumn(int col, int mLength) {
        for (int p = 0, q = 0, r = mLength / 2; p <= mLength - 2; p += 2, q++, r++) {
          double a = grid[p][col];
          double b = grid[p + 1][col];

          res[q][col] = getNormalizedAverage(a, b);
          res[r][col] = getNormalizedDifference(a, b);
        }
      }
    }

    int m = length;

    HaarTransform haarTransform = new HaarTransform();

    while (m > 1) {

      for (int i = 0; i < m; i++) {

        haarTransform.transformRow(i, m);
      }

      for (int i = 0; i < length; i++) {
        System.arraycopy(res[i], 0, grid[i], 0, length);
      }

      for (int j = 0; j < m; j++) {

        haarTransform.transformColumn(j, m);
      }

      for (int i = 0; i < length; i++) {
        System.arraycopy(res[i], 0, grid[i], 0, length);
      }

      m = m / 2;
    }
    return res;
  }

  /**
   * This helper method inverse transforms the given grid of values using the inverse step of the
   * haar transform process.
   *
   * @param grid   The grid of values.
   * @param length The length of the grid, This will be a square grid so, the sides are the same.
   * @return The transformed grid object.
   */
  private double[][] inverseHaarTransform(double[][] grid, int length) {

    double[][] res = new double[length][length];

    for (int i = 0; i < length; i++) {
      System.arraycopy(grid[i], 0, res[i], 0, length);
    }

    class InverseTransform {
      void transformRow(int i, int length) {
        for (int p = 0, q = length / 2, r = 0; r <= length - 2; p++, q++, r = r + 2) {
          double a = grid[i][p];
          double b = grid[i][q];

          res[i][r] = getNormalizedAverage(a, b);
          res[i][r + 1] = getNormalizedDifference(a, b);
        }
      }

      void transformCol(int j, int length) {
        for (int p = 0, q = length / 2, r = 0; r <= length - 2; p++, q++, r = r + 2) {
          double a = grid[p][j];
          double b = grid[q][j];

          res[r][j] = getNormalizedAverage(a, b);
          res[r + 1][j] = getNormalizedDifference(a, b);
        }
      }
    }

    int c = 2;

    InverseTransform inverseTransform = new InverseTransform();

    while (c <= length) {

      for (int j = 0; j < c; j++) {
        inverseTransform.transformCol(j, c);
      }
      for (int i = 0; i < length; i++) {
        System.arraycopy(res[i], 0, grid[i], 0, length);
      }

      for (int i = 0; i < c; i++) {
        inverseTransform.transformRow(i, c);
      }

      for (int i = 0; i < length; i++) {
        System.arraycopy(res[i], 0, grid[i], 0, length);
      }

      c *= 2;
    }

    return res;
  }

  private double getNormalizedAverage(double a, double b) {

    return (a + b) / (Math.sqrt(2));
  }

  private double getNormalizedDifference(double a, double b) {

    return (a - b) / (Math.sqrt(2));
  }


  private double getThreshold(double[][] redGrid, double[][] greenGrid, double[][] blueGrid,
                              int percent) {

    Set<Double> doubleSet = new TreeSet<>();
    int l = redGrid.length;

    for (int i = 0; i < l; i++) {
      for (int j = 0; j < l; j++) {
        doubleSet.add(Math.abs(redGrid[i][j]));
        doubleSet.add(Math.abs(greenGrid[i][j]));
        doubleSet.add(Math.abs(blueGrid[i][j]));
      }
    }

    List<Double> list = new ArrayList<>(doubleSet);

    if (percent == 100) {
      return list.get(list.size() - 1);
    }

    return getKthSmallest(doubleSet,
            (int) ((double) percent * (double) doubleSet.size() / (double) 100));
  }

  /**
   * Returns the grid with the pixel values in a specific channel.
   *
   * @param channel The channel which should be retrieved, 0 for red, 1 from green, 2 for blue.
   * @return The grid with the pixel values as a double.
   */
  private double[][] getValueGridFromImage(int channel, int gridSize) {


    double[][] resultGrid = new double[gridSize][gridSize];

    for (int i = 0; i < gridSize; i++) {

      for (int j = 0; j < gridSize; j++) {

        resultGrid[i][j] = getValueFromChannel(channel, image.getPixel(i, j));
      }
    }

    return resultGrid;
  }

  private double getValueFromChannel(int channel, Pixel pixel) {
    if (channel == 0) {
      return pixel.getRed();
    } else if (channel == 1) {
      return pixel.getGreen();
    }
    return pixel.getBlue();
  }

  private static Double getKthSmallest(Set<Double> treeSet, int k) {

    int count = 0;
    for (Double element : treeSet) {
      count++;
      if (count == k) {
        return element;
      }
    }

    return (double) 0;
  }

}
