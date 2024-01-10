package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.Image;
import model.ImageEditorImpl;
import model.RGBImage;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A Junit class to unit test the working of image controller and verify its interactions with the
 * image editor class.
 */
public class ImageControllerTest {

  StringBuffer out = new StringBuffer();
  TextView testView = new TextView(out);
  Readable in;
  ImageController imageController;

  static Image testImage;

  static {
    try {
      testImage = RGBImage.getLoader().loadFromPPM("test/images/16px.ppm");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static class MockEditorImpl extends ImageEditorImpl {

    private final StringBuilder log;

    public MockEditorImpl(StringBuilder log) {

      this.log = log;
    }

    @Override
    public Image load(String srcPath) {

      log.append("Load input is ").append(srcPath)
              .append(System.getProperty("line.separator"));
      return testImage;
    }

    @Override
    public void saveAsPNG(Image image, String destinationPath) {

      log.append("save PNG input is ").append(image.toString()).append(" ")
              .append(destinationPath)
              .append(System.getProperty("line.separator"));
    }

    @Override
    public void saveAsJPG(Image image, String destinationPath) {

      log.append("save JPG input is ").append(image.toString()).append(" ")
              .append(destinationPath)
              .append(System.getProperty("line.separator"));
    }

    @Override
    public void saveAsPPM(Image image, String destinationPath) {

      log.append("save PPM input is ").append(image.toString()).append(" ")
              .append(destinationPath)
              .append(System.getProperty("line.separator"));
    }

    @Override
    public Image redComponent(Image image) {

      log.append("Red Input is ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image greenComponent(Image image) {

      log.append("Green Input is ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image blueComponent(Image image) {

      log.append("Blue Input is ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image valueComponent(Image image) {

      log.append("Value Input is ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image intensityComponent(Image image) {

      log.append("Intensity Input is ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image lumaComponent(Image image) {

      log.append("Luma Input is ").append(image.toString()).append(" ")
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image horizontalFlip(Image image) {

      log.append("HFlip Input is ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image verticalFlip(Image image) {

      log.append("VFlip Input is ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image brighten(Image image, int brightnessValue) {

      log.append("Brighten Input is ").append(image.toString()).append(" ").append(brightnessValue)
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image blur(Image image) {

      log.append("Blur Input is ").append(image.toString()).append(" ")
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image sharpen(Image image) {

      log.append("Sharpen Input is ").append(image.toString()).append(" ")
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image toSepia(Image image) {

      log.append("Sepia Input is ").append(image.toString()).append(" ")
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image rgbCombine(Image redImage, Image greenImage, Image blueImage) {

      log.append("Rgb Combine Input is ").append(testImage.toString())
              .append(System.getProperty("line.separator"));
      return testImage;
    }

    @Override
    public Image getHistogram(Image image) {

      log.append("Get Histogram input is ").append(testImage.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image levelsAdjust(Image image, int b, int m, int w) {

      log.append("Levels adjusted called with ").append(image.toString()).append(", ")
              .append("b: ").append(b).append(", ").append("m: ").append(m).append(", ")
              .append("w: ").append(w).append(", ")
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image compress(Image image, int percent) {

      log.append("Compress called with ").append(image.toString()).append(", ")
              .append("percent: ").append(percent).append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image colorCorrect(Image image) {

      log.append("Color Correct called with ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }
  }

  @Test
  public void testLoadMock() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testSavePPMMock() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n"
            + " save test/images/k.ppm k \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "save PPM input is " + testImage.toString() + " test/images/k.ppm"
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testRedComponentMock() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n red-component k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Red Input is " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testGreenComponentMock() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n green-component k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Green Input is " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testBlueComponentMock() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n blue-component k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Blue Input is " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testValueComponentMock() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n value-component k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Value Input is " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testIntensityComponentMock() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n intensity-component"
            + " k kRed \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Intensity Input is " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testLumaComponentMock() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n luma-component k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Luma Input is " + testImage.toString() + " "
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testHorizontalFlip() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n horizontal-flip k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "HFlip Input is " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testVerticalFlip() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n vertical-flip k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "VFlip Input is " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testBlur() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n blur k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Blur Input is " + testImage.toString() + " "
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testSharpen() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n sharpen k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Sharpen Input is " + testImage.toString() + " "
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testSepia() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n sepia k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Sepia Input is " + testImage.toString() + " "
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testBrighten() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n brighten 10 k kRed"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Brighten Input is " + testImage.toString() + " 10"
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testRGBCombine() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n"
            + " rgb-combine kr k k k \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Rgb Combine Input is " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testBlurSplit() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n blur k kRed split 50"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Blur Input is " + testImage.toString() + " "
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testSharpenSplit() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n sharpen k kRed split 50"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Sharpen Input is " + testImage.toString() + " "
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testSepiaSplit() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n sepia k kRed split 50"
            + " \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Sepia Input is " + testImage.toString() + " "
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testLumaSplit() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n luma-component k kRed" +
            " split 50 \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Luma Input is " + testImage.toString() + " "
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testColorCorrectionSplit() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n color-correct k kRed" +
            " split 50 \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Color Correct called with " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testLevelsAdjustmentSplit() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n levels-adjust" +
            " 20 100 200 k kRed split 50 \n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Levels adjusted called with " + testImage.toString() + ", b: 20, m: 100," +
            " w: 200, " + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testHistogram() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n histogram k kRed " +
            "\n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Get Histogram input is " + testImage.toString() +
            System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testColorCorrect() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n color-correct k kRed" +
            "\n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Color Correct called with " + testImage.toString()
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testLevelsAdjustment() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n levels-adjust" +
            " 20 101 201 k kRed\n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Levels adjusted called with " + testImage.toString() + ", b: 20, m: 101," +
            " w: 201, " + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testCompress() {

    StringBuilder log = getOutput("load test/images/16px.ppm k \n compress 10 " +
            "k kRed\n quit \n");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Compress called with " + testImage.toString() + ", percent: 10"
            + System.getProperty("line.separator"), log.toString());
  }

  @Test
  public void testRunScriptFileParsing() {

    StringBuilder log = getOutput("run test/images/script.txt \n quit");

    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator")
            + "Compress called with " + testImage.toString() + ", percent: 100"
            + System.getProperty("line.separator")
            + "Red Input is " + testImage.toString()
            + System.getProperty("line.separator")
            + "Brighten Input is " + testImage.toString() + " 30"
            + System.getProperty("line.separator")
            + "save PPM input is " + testImage.toString() + " test/images/black-test.ppm"
            + System.getProperty("line.separator"), log.toString());
  }

  private StringBuilder getOutput(String s) {
    in = new StringReader(s);
    StringBuilder log = new StringBuilder();
    MockEditorImpl mockEditor = new MockEditorImpl(log);
    imageController = new ImageController(testView, in, mockEditor);
    try {
      imageController.start();
    } catch (IOException io) {
      fail("Exception occurred");
    }
    return log;
  }

}