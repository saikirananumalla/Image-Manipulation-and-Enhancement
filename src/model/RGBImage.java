package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * The RGB image class. Has pixels which have three channels representing the three color channels.
 * All the pixels are stored in a grid of pixels, making up the image. Information regarding the
 * image height and the width is also stored and used in multiple methods of the RGB Image.
 */
public class RGBImage implements Image {

  private final int height;

  private final int width;

  private final Pixel[][] pixelGrid;

  /**
   * The constructor method of the RGB Image. This method takes in the image source path as an
   * input. This loads image data from a specified file location. Location should include the image
   * name as well and stores the pixel information in the grid.
   */
  private RGBImage(int height, int width, Pixel[][] pixelGrid) {
    this.height = height;
    this.width = width;
    this.pixelGrid = pixelGrid;
  }

  /**
   * The constructor method of the RGB Image which takes in two inputs, one for height and the
   * other for the width of the desired image.
   *
   * @param height The height of the image.
   * @param width  The width of the image.
   */
  private RGBImage(int height, int width) {
    pixelGrid = new Pixel[height][width];
    this.height = height;
    this.width = width;
  }

  /**
   * Returns the loader aka the builder instance for RGB Image instance.
   *
   * @return new ImageLoader used to load an image
   */
  public static ImageLoader getLoader() {
    return new ImageLoader();
  }

  /**
   * A static builder class to load the RGBImage class from various files and types like ppm, jpg,
   * png or even a buffered image.
   */
  public static class ImageLoader {

    private int height;
    private int width;
    private Pixel[][] pixelGrid;

    private ImageLoader() {

    }

    /**
     * Load the image from a ppm file format by fetching the rgb values.
     *
     * @param filename file path for the file to be fetched
     * @return new RGBImage model with values from given file
     * @throws IOException if file reading produces exception
     */
    public Image loadFromPPM(String filename) throws IOException {
      Scanner sc;

      sc = new Scanner(new FileInputStream(filename));
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      }

      //now set up the scanner to read from the string we just built
      sc = new Scanner(builder.toString());

      String token;

      token = sc.next();
      if (!token.equals("P3")) {
        System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      }

      this.width = sc.nextInt();
      this.height = sc.nextInt();
      int maxValue = sc.nextInt();
      pixelGrid = new Pixel[height][width];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          pixelGrid[i][j] = new Pixel(r, g, b);
        }
      }

      return new RGBImage(height, width, pixelGrid);
    }

    /**
     * Load the image from png or jpg file format by fetching the rgb values.
     *
     * @param filename file path for the file to be fetched
     * @return new RGBImage model with values from given file
     * @throws IOException if file reading produces exception
     */
    public Image loadFromPngOrJpg(String filename) throws IOException {
      BufferedImage image = ImageIO.read(new File(filename));
      return getRgbImageFromBufferedImage(image);
    }

    /**
     * Load the image from a buffered image by fetching the rgb values.
     *
     * @param bufferedImage buffered image object to be read from
     * @return new RGBImage model with values from given file
     */
    public Image loadFromBufferedImage(BufferedImage bufferedImage) {
      return getRgbImageFromBufferedImage(bufferedImage);
    }

    private Image getRgbImageFromBufferedImage(BufferedImage image) {
      this.width = image.getWidth();
      this.height = image.getHeight();
      pixelGrid = new Pixel[height][width];

      for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++) {
          int rgb = image.getRGB(y, x);
          Pixel p = new Pixel((rgb >> 16) & 0xFF,
                  (rgb >> 8) & 0xFF,
                  rgb & 0xFF);
          pixelGrid[x][y] = p;
        }
      }

      return new RGBImage(height, width, pixelGrid);
    }
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public Pixel getPixel(int i, int j) {
    if (invalidIndices(i, j)) {
      return new Pixel(0, 0, 0);
    }
    return getPixelUtil(i, j);
  }

  @Override
  public Image redComponent() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        newImage.setPixel(i, j, getPixelUtil(i, j).getPixelWithRed());
      }
    }
    return newImage;
  }

  @Override
  public Image greenComponent() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        newImage.setPixel(i, j, getPixelUtil(i, j).getPixelWithGreen());
      }
    }
    return newImage;
  }

  @Override
  public Image blueComponent() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        newImage.setPixel(i, j, getPixelUtil(i, j).getPixelWithBlue());
      }
    }
    return newImage;
  }

  @Override
  public Image valueComponent() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        newImage.setPixel(i, j, getPixelUtil(i, j).getPixelWithValueComp());
      }
    }
    return newImage;
  }

  @Override
  public Image intensityComponent() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        newImage.setPixel(i, j, getPixelUtil(i, j).getPixelWithIntensityComp());
      }
    }
    return newImage;
  }

  @Override
  public Image lumaComponent() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        newImage.setPixel(i, j, getPixelUtil(i, j).getPixelWithLumaComp());
      }
    }
    return newImage;
  }

  @Override
  public Image horizontalFlip() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = width - 1; j >= 0; j--) {
        newImage.setPixel(i, width - 1 - j, getPixelUtil(i, j));
      }
    }
    return newImage;
  }

  @Override
  public Image verticalFlip() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = height - 1; i >= 0; i--) {
      for (int j = 0; j < width; j++) {
        newImage.setPixel(height - 1 - i, j, getPixelUtil(i, j));
      }
    }
    return newImage;
  }

  @Override
  public Image brighten(int brightnessValue) {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        newImage.setPixel(i, j, getPixelUtil(i, j).brighten(brightnessValue));
      }
    }
    return newImage;
  }

  @Override
  public Image blur() {

    double[][] kernel;
    kernel = new double[][]{{0.0625, 0.125, 0.0625},
                            {0.125, 0.25, 0.125},
                            {0.0625, 0.125, 0.0625}};
    return this.getConvolutedImageOfThis(kernel);
  }

  @Override
  public Image sharpen() {

    double[][] kernel;
    kernel = new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
                            {-0.125, 0.25, 0.25, 0.25, -0.125},
                            {-0.125, 0.25, 1, 0.25, -0.125},
                            {-0.125, 0.25, 0.25, 0.25, -0.125},
                            {-0.125, -0.125, -0.125, -0.125, -0.125}};
    return this.getConvolutedImageOfThis(kernel);
  }

  @Override
  public Image toSepia() {
    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        newImage.setPixel(i, j, getPixelUtil(i, j).toSepia());
      }
    }
    return newImage;
  }

  @Override
  public Image rgbCombine(Image redImage, Image greenImage, Image blueImage) {
    RGBImage newImage = new RGBImage(getMax(redImage.getHeight(), greenImage.getHeight(),
            blueImage.getHeight()), getMax(redImage.getWidth(), greenImage.getWidth(),
            blueImage.getWidth()));

    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        Pixel p = new Pixel(redImage.getPixel(i, j).getRed(),
                greenImage.getPixel(i, j).getGreen(), blueImage.getPixel(i, j).getBlue());
        newImage.setPixel(i, j, p);
      }
    }

    return newImage;
  }

  @Override
  public Image levelsAdjust(int b, int m, int w) {

    double ao = (b * b * (m - w)) - (b * (m * m - w * w)) + (w * m * m) - (m * w * w);
    double aa = (b * 127) + (128 * w) - (255 * m);
    double ab = (b * b * (-127)) + (255 * m * m) - (128 * w * w);
    double ac = (b * b * (255 * m - 128 * w)) - (b * (255 * m * m - 128 * w * w));

    double p = aa / ao;
    double q = ab / ao;
    double r = ac / ao;

    return getLevelsAdjustedImage(p, q, r);
  }

  @Override
  public Image colorCorrect() {

    int a = 0;
    int b = 0;
    int c = 0;

    int redMax = 0;
    int greenMax = 0;
    int blueMax = 0;

    int[] redValues = new int[256];
    int[] greenValues = new int[256];
    int[] blueValues = new int[256];

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        Pixel p = getPixelUtil(x, y);
        redValues[p.getRed()]++;
        greenValues[p.getGreen()]++;
        blueValues[p.getBlue()]++;

        if (isMeaningfulPeak(p.getRed()) && redValues[p.getRed()] > redMax) {
          redMax = redValues[p.getRed()];
          a = p.getRed();
        }
        if (isMeaningfulPeak(p.getGreen()) && greenValues[p.getGreen()] > greenMax) {
          greenMax = greenValues[p.getGreen()];
          b = p.getGreen();
        }
        if (isMeaningfulPeak(p.getBlue()) && blueValues[p.getBlue()] > blueMax) {
          blueMax = blueValues[p.getBlue()];
          c = p.getBlue();
        }
      }
    }

    int avg = (a + b + c) / 3;


    return offset(avg - a, avg - b, avg - c);
  }

  @Override
  public Image getSplitView(Image image, int split) {
    RGBImage rgbImage = new RGBImage(height, width);
    int boundary = (split * width / 100);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        if (j >= boundary) {
          rgbImage.setPixel(i, j, image.getPixel(i, j));
        } else {
          rgbImage.setPixel(i, j, this.getPixelUtil(i, j));
        }
      }
    }
    return rgbImage;
  }


  /**
   * Runs through the image and generates the new levels adjusted image.
   *
   * @param p The x square coefficient in the transformation.
   * @param q The x coefficient in the transformation.
   * @param r The constant factor in the transformation.
   * @return new image with the levels adjusted accordingly.
   */
  private Image getLevelsAdjustedImage(double p, double q, double r) {

    RGBImage newImage = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {

        newImage.setPixel(i, j, getPixelUtil(i, j).levelsAdjust(p, q, r));
      }
    }
    return newImage;
  }

  /**
   * Helper method to determine whether a specific position is inside the image boundaries.
   *
   * @param i The position of the object row-wise.
   * @param j The position of the object column-wise.
   * @return Boolean result. True if the position is inside the image boundaries. False if no.
   */
  private boolean invalidIndices(int i, int j) {
    return i < 0 || i >= height || j < 0 || j >= width;
  }

  /**
   * Helper method to retrieve the pixel at a position in the image.
   *
   * @param i The position of the pixel along the row.
   * @param j The position of the pixel along the column.
   * @return The pixel object present at the position.
   */
  private Pixel getPixelUtil(int i, int j) {
    return pixelGrid[i][j];
  }

  /**
   * Method to generate a new image which is convoluted with a kernel.
   *
   * @param kernel The kernel which should be convoluted on the image.
   * @return The new image object.
   */
  private Image getConvolutedImageOfThis(double[][] kernel) {

    RGBImage resultImage = new RGBImage(height, width);

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {

        Pixel p = multiplyKernelAndPixel(x, y, kernel);
        resultImage.setPixel(x, y, p);
      }
    }

    return resultImage;
  }

  /**
   * Multiplies the kernel values with the pixel values.
   *
   * @param i      The row of the pixel.
   * @param j      The column of the pixel.
   * @param kernel The kernel used in the convolution.
   * @return The new pixel with the convoluted values.
   */
  private Pixel multiplyKernelAndPixel(int i, int j, double[][] kernel) {

    int kernelHeight = kernel.length;
    int factor = (kernelHeight - 1) / 2;
    double cnv_red_double = 0.00;
    double cnv_green_double = 0.00;
    double cnv_blue_double = 0.00;

    for (int p = 0; p < kernelHeight; p++) {

      for (int q = 0; q < kernelHeight; q++) {

        Pixel px = this.getPixel(i - factor + p, j - factor + q);
        cnv_red_double += px.getRed() * kernel[p][q];
        cnv_green_double += px.getGreen() * kernel[p][q];
        cnv_blue_double += px.getBlue() * kernel[p][q];
      }
    }
    return new Pixel((int) cnv_red_double, (int) cnv_green_double, (int) cnv_blue_double);
  }

  private Image offset(int r, int g, int b) {
    RGBImage image = new RGBImage(height, width);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        image.setPixel(i, j, getPixel(i, j).offset(r, g, b));
      }
    }
    return image;
  }

  private boolean isMeaningfulPeak(int value) {

    return value > 10 && value < 245;
  }

  private int getMax(int a, int b, int c) {
    return Math.max(a, Math.max(b, c));
  }


  private void setPixel(int i, int j, Pixel p) {
    if (invalidIndices(i, j)) {
      return;
    }
    pixelGrid[i][j] = p;
  }
}
