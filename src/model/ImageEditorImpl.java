package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import static util.Constants.JPG;
import static util.Constants.MAX_PIXEL_VALUE;
import static util.Constants.PNG;
import static util.Constants.PPM;

/**
 * A class to edit the load, edit and save images by implementing the ImageEditor interface.
 */
public class ImageEditorImpl implements ImageEditor {

  @Override
  public Image load(String srcPath) throws IOException {
    int lastDot = srcPath.lastIndexOf('.');
    String ext = srcPath.substring(lastDot + 1);

    if (ext.equals(PPM)) {
      return RGBImage.getLoader().loadFromPPM(srcPath);
    }
    return RGBImage.getLoader().loadFromPngOrJpg(srcPath);
  }

  @Override
  public void saveAsPNG(Image image, String destinationPath) throws IOException {
    saveImage(image, destinationPath, PNG);
  }

  @Override
  public void saveAsJPG(Image image, String destinationPath) throws IOException {
    saveImage(image, destinationPath, JPG);
  }

  @Override
  public void saveAsPPM(Image image, String destinationPath) throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath))) {
      // Write the PPM header
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write(MAX_PIXEL_VALUE + "\n");

      // Write the pixel data in ascii format
      for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++) {
          Pixel p = image.getPixel(x, y);
          int r = p.getRed();
          int g = p.getGreen();
          int b = p.getBlue();
          writer.write(r + "\n");
          writer.write(g + "\n");
          writer.write(b + "\n");
        }
      }
    }
  }

  @Override
  public Image redComponent(Image image) {
    return image.redComponent();
  }

  @Override
  public Image greenComponent(Image image) {
    return image.greenComponent();
  }

  @Override
  public Image blueComponent(Image image) {
    return image.blueComponent();
  }

  @Override
  public Image valueComponent(Image image) {
    return image.valueComponent();
  }

  @Override
  public Image intensityComponent(Image image) {
    return image.intensityComponent();
  }

  @Override
  public Image lumaComponent(Image image) {
    return image.lumaComponent();
  }

  @Override
  public Image horizontalFlip(Image image) {
    return image.horizontalFlip();
  }

  @Override
  public Image verticalFlip(Image image) {
    return image.verticalFlip();
  }

  @Override
  public Image brighten(Image image, int brightnessValue) {
    return image.brighten(brightnessValue);
  }

  @Override
  public Image blur(Image image) {
    return image.blur();
  }

  @Override
  public Image sharpen(Image image) {
    return image.sharpen();
  }

  @Override
  public Image toSepia(Image image) {
    return image.toSepia();
  }

  @Override
  public Image rgbCombine(Image redImage, Image greenImage, Image blueImage) {
    return redImage.rgbCombine(redImage, greenImage, blueImage);
  }

  @Override
  public Image getHistogram(Image image) {

    int[] redValues = new int[256];
    int[] greenValues = new int[256];
    int[] blueValues = new int[256];

    int maxCount = 0;

    for (int x = 0; x < image.getHeight(); x++) {
      for (int y = 0; y < image.getWidth(); y++) {
        Pixel p = image.getPixel(x, y);
        redValues[p.getRed()]++;
        greenValues[p.getGreen()]++;
        blueValues[p.getBlue()]++;

        maxCount = getMaxCount(maxCount, redValues[p.getRed()], greenValues[p.getGreen()],
                blueValues[p.getBlue()]);
      }
    }

    BufferedImage histogram = new BufferedImage(256, 256,
            BufferedImage.TYPE_INT_RGB);

    Graphics g = histogram.getGraphics();

    drawHistogram(g, redValues, Color.RED, maxCount);
    drawHistogram(g, greenValues, Color.GREEN, maxCount);
    drawHistogram(g, blueValues, Color.BLUE, maxCount);

    g.dispose();

    return RGBImage.getLoader().loadFromBufferedImage(histogram);
  }


  @Override
  public Image levelsAdjust(Image image, int b, int m, int w) {
    return image.levelsAdjust(b, m, w);
  }


  @Override
  public Image compress(Image image, int percent) {
    return new ImageCompressor(image).compress(percent);
  }

  @Override
  public Image colorCorrect(Image image) {
    return image.colorCorrect();
  }

  @Override
  public Image getSplitView(Image image1, Image image2, int percent) {
    return image2.getSplitView(image1, percent);
  }

  protected BufferedImage getBufferedImageUtil(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Logger.getLogger("javax.imageio").setLevel(Level.OFF);

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        Pixel p = image.getPixel(x, y);
        int r = p.getRed();
        int g = p.getGreen();
        int b = p.getBlue();
        // Combine the RGB values into a single integer
        int rgb = (r << 16) | (g << 8) | b;
        // Set the pixel in the BufferedImage
        bufferedImage.setRGB(y, x, rgb);
      }
    }
    return bufferedImage;
  }

  private static void drawHistogram(Graphics g, int[] histogram, Color color, int maxHeight) {
    for (int i = 0; i < histogram.length - 1; i++) {
      int y1 = (int) (((double) histogram[i] / (double) maxHeight) * (double) 256);
      int x2 = i + 1;
      int y2 = (int) (((double) histogram[i + 1] / (double) maxHeight) * (double) 256);

      g.setColor(color);
      g.drawLine(i, 256 - y1, x2, 256 - y2);
    }
  }

  private void saveImage(Image image, String destinationPath, String format) throws IOException {

    BufferedImage bufferedImage = getBufferedImageUtil(image);

    File output = new File(destinationPath);
    ImageIO.write(bufferedImage, format, output);
  }

  private int getMaxCount(int maxCount, int red, int green, int blue) {
    return Math.max(maxCount, Math.max(red, Math.max(green, blue)));
  }
}
