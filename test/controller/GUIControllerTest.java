package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.GUIImageEditorImpl;
import model.Image;
import model.RGBImage;
import view.JFrameView;

import static org.junit.Assert.assertEquals;

/**
 * The test suite for the GUI Controller. This suite contains the methods to test load, save,
 * generating 3 color components, sepia, blur, sharpen, compress, color correct, levels adjust
 * methods. This suite also mocks both the GUIImageEditor and the JFrameView for conducting
 * unit testing.
 */
public class GUIControllerTest {

  private GUIController guiController;

  private StringBuilder editorLog;
  private StringBuilder viewLog;

  private static final String ADD_ACTION_LISTENER_CALLED = "Add action listener called";
  private static final BufferedImage testBufferedImage;

  static {
    try {
      testBufferedImage = ImageIO.read(new File("test/images/16px.ppm"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static final Image testImage;

  static {
    try {
      testImage = RGBImage.getLoader().loadFromPPM("test/images/16px.ppm");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * The mock class for the GUI editor. This class has a running log which writes the input
   * parameters it receives for testing.
   *
   */
  public static class MockGUIImageEditorImpl extends GUIImageEditorImpl {

    private final StringBuilder log;

    public MockGUIImageEditorImpl(StringBuilder log) {

      this.log = log;
    }

    @Override
    public Image load(String srcPath) {

      log.append("Load input is ").append(srcPath)
              .append(System.getProperty("line.separator"));
      return testImage;
    }

    @Override
    public Image getCopy(Image image) {

      return image;
    }

    @Override
    public BufferedImage getBufferedImage(Image image) {

      return testBufferedImage;
    }

    @Override
    public Image getHistogram(Image image) {

      log.append("Getting the histogram of ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public void saveAsPPM(Image image, String destinationPath) {

      log.append("Save called on PPM Object ").append(image.toString()).append(" ")
              .append(destinationPath).append(System.getProperty("line.separator"));
    }

    @Override
    public Image toSepia(Image image) {

      log.append("sepia of the image ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image sharpen(Image image) {

      log.append("sharpen the image ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image getSplitView(Image image1, Image imag2, int split) {
      log.append("split view called with ").append(split)
              .append(System.getProperty("line.separator"));
      return testImage;
    }

    @Override
    public Image compress(Image image, int percent) {

      log.append("compress the image ").append(percent).append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image colorCorrect(Image image) {
      log.append("color correct the image ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image levelsAdjust(Image image, int b, int w, int h) {
      log.append("levels adjust the image with ").append(b).append(" ").append(w).append(" ").
              append(h).append(image.toString()).append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public void saveAsPNG(Image image, String destinationPath) {

      log.append("Save called on PNG Object ").append(image.toString()).append(" ")
              .append(destinationPath).append(System.getProperty("line.separator"));
    }

    @Override
    public void saveAsJPG(Image image, String destinationPath) {

      log.append("Save called on JPG Object ").append(image.toString()).append(" ")
              .append(destinationPath).append(System.getProperty("line.separator"));
    }

    @Override
    public Image redComponent(Image image) {

      log.append("Getting the red component of ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image greenComponent(Image image) {

      log.append("Getting the green component of ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image blueComponent(Image image) {

      log.append("Getting the blue component of ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image lumaComponent(Image image) {

      log.append("Getting the luma component of ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image horizontalFlip(Image image) {

      log.append("Horizontally flipping of ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image verticalFlip(Image image) {

      log.append("Vertically flipping of ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

    @Override
    public Image blur(Image image) {

      log.append("Blurring of ").append(image.toString())
              .append(System.getProperty("line.separator"));
      return image;
    }

  }


  /**
   *  View Mock class.
   */
  public static class MockJFrameView extends JFrameView {

    private final StringBuilder log;

    /**
     * The constructor for the Mock view class.
     * @param log The log to which the mock object writes the input parameters and other
     *           information.
     */
    public MockJFrameView(StringBuilder log) {

      setVisible(false);
      setEnabled(false);
      this.log = log;
    }

    @Override
    public void displayImage(BufferedImage image, BufferedImage histogram) {

      log.append("Display Image is called").append(System.getProperty("line.separator"));
    }

    @Override
    public void setUpButtonsAndListener(GUIActionListener actionListener) {

      log.append(ADD_ACTION_LISTENER_CALLED).append(System.getProperty("line.separator"));
    }

    @Override
    public void displayError() {
      log.append("Display error called").append(System.getProperty("line.separator"));
    }

    @Override
    public void displayLoadImage() {
      log.append("Display load image called").append(System.getProperty("line.separator"));
    }

    @Override
    public void displayFileError() {
      log.append("Display file error called").append(System.getProperty("line.separator"));
    }

  }

  @Before
  public void setUp() {

    editorLog = new StringBuilder();
    viewLog = new StringBuilder();
    MockGUIImageEditorImpl mockEditor = new MockGUIImageEditorImpl(editorLog);
    MockJFrameView mockView = new MockJFrameView(viewLog);
    guiController = new GUIController(mockEditor, mockView);
    guiController.load("test/images/16px.ppm");
  }

  @Test
  public void testLoad() {

    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testSavePPM() {

    guiController.save("test/images/16px.ppm");
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Save called on PPM Object " + testImage.toString() + " test/images/16px.ppm"
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testSavePNG() {

    guiController.save("test/images/16px.png");
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Save called on PNG Object " + testImage.toString() + " test/images/16px.png"
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testSaveJPG() {

    guiController.save("test/images/16px.jpg");
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Save called on JPG Object " + testImage.toString() + " test/images/16px.jpg"
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testRedComponent() {

    guiController.redComponent();
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the red component of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testGreenComponent() {

    guiController.greenComponent();
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the green component of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testBlueComponent() {

    guiController.blueComponent();
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the blue component of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testLumaComponentPreview() {

    guiController.lumaComponent(false);
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the luma component of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testLumaComponent() {

    guiController.lumaComponent(true);
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the luma component of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testHorizontalFlip() {

    guiController.horizontalFlip();
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Horizontally flipping of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testVerticalFlip() {

    guiController.verticalFlip();
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Vertically flipping of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testBlurImagePreview() {

    guiController.blurImage(false);
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Blurring of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testBlurImage() {

    guiController.blurImage(true);
    String editorOutput = "Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Blurring of " + testImage.toString()
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString()
            + System.getProperty("line.separator");
    String viewOutput = ADD_ACTION_LISTENER_CALLED
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator");
    Assert.assertEquals(editorOutput, editorLog.toString());
    Assert.assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testSharpenImagePreview() {

    guiController.sharpenImage(false);
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "sharpen the image " + testImage.toString() +
            System.getProperty("line.separator"), editorLog.toString());
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
            "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testSharpenImage() {

    guiController.sharpenImage(true);
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "sharpen the image " + testImage.toString() +
            System.getProperty("line.separator") + "Getting the histogram of " +
            testImage.toString() + System.getProperty("line.separator"), editorLog.toString());

    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
            "Display Image is called" + System.getProperty("line.separator") +
            "Display Image is called" + System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testToSepiaPreview() {

    guiController.toSepia(false);
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "sepia of the image " + testImage.toString() +
            System.getProperty("line.separator"), editorLog.toString());
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }


  @Test
  public void testToSepia() {

    guiController.toSepia(true);
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "sepia of the image " + testImage.toString() +
            System.getProperty("line.separator") + "Getting the histogram of " +
            testImage.toString() + System.getProperty("line.separator"), editorLog.toString());
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testCompress() {

    guiController.compress("50");
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "compress the image 50" + testImage.toString() +
            System.getProperty("line.separator") + "Getting the histogram of " +
            testImage.toString() + System.getProperty("line.separator"), editorLog.toString());
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testColorCorrectPreview() {

    guiController.colorCorrect(false);
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "color correct the image " +
            testImage.toString() +
            System.getProperty("line.separator"), editorLog.toString());
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testColorCorrect() {

    guiController.colorCorrect(true);
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "color correct the image "
            + testImage.toString() +
            System.getProperty("line.separator") + "Getting the histogram of " +
            testImage.toString() + System.getProperty("line.separator"), editorLog.toString());
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testLevelsAdjustPreview() {

    guiController.levelsAdjust("10", "120", "200", false);
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "levels adjust the image with 10 120 200" +
            testImage.toString() + System.getProperty("line.separator"), editorLog.toString());
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testLevelsAdjust() {

    guiController.levelsAdjust("10", "120", "200", true);
    assertEquals("Load input is test/images/16px.ppm" + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "levels adjust the image with 10 120 200" +
            testImage.toString() + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator"), editorLog.toString());

    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testRevertToOriginal() {

    guiController.revertToOriginal();
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testGetSplitView() {

    guiController.getSplitView("50");
    assertEquals("Load input is test/images/16px.ppm"
            + System.getProperty("line.separator") +
            "Getting the histogram of " + testImage.toString() +
            System.getProperty("line.separator") + "split view called with 50" +
            System.getProperty("line.separator") + "Getting the histogram of " +
            testImage.toString() + System.getProperty("line.separator"), editorLog.toString());

    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

  @Test
  public void testUndo() {

    guiController.undo();
    assertEquals(ADD_ACTION_LISTENER_CALLED + System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator") +
                    "Display Image is called" +  System.getProperty("line.separator"),
            viewLog.toString());
  }

}
