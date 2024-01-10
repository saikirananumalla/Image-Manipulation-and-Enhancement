package controller;

import org.junit.Before;
import org.junit.Test;

import model.GUIImageEditorImpl;

import static org.junit.Assert.assertEquals;

/**
 * A Junit class to unit test the verify exception is not thrown, and we show proper error text from
 * image controller by entering many error commands.
 */
public class GUIControllerNegativeTest {

  private GUIController guiController;
  StringBuilder viewLog;

  @Before
  public void setUp() {

    viewLog = new StringBuilder();
    GUIControllerTest.MockJFrameView mockView = new GUIControllerTest.MockJFrameView(viewLog);
    guiController = new GUIController(new GUIImageEditorImpl(), mockView);
  }

  @Test
  public void loadImagePathNotExisting() {

    guiController.load("/nonExisting.png");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display file error called"
            + System.getProperty("line.separator")
            + "Display file error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void loadInvalidImageExtension() {

    guiController.load("/nonExisting.pnggg");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display file error called"
            + System.getProperty("line.separator")
            + "Display file error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void undoEditNoImageLoaded() {

    guiController.undo();
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void revertToOriginalNoImageLoaded() {
    guiController.revertToOriginal();
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void saveWithoutLoad() {

    guiController.save("/some/image.png");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void saveInvalidImageExtension() {

    guiController.load("test/images/16px.ppm");
    guiController.save("/some/image.pncggg");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display file error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void saveInvalidImagePath() {
    guiController.load("test/images/16px.ppm");
    guiController.save("/nonExisting/image.png");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display file error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testCompressImageNotLoaded() {
    guiController.compress("50");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testCompressNullArgument() {
    guiController.load("test/images/16px.ppm");
    guiController.compress(null);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void testCompressEmptyArgument() {
    guiController.load("test/images/16px.ppm");
    guiController.compress("");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void testCompressInvalidArgument() {
    guiController.load("test/images/16px.ppm");
    guiController.compress("50%");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void testCompressInvalidInputLt0() {
    guiController.load("test/images/16px.ppm");
    guiController.compress("-1");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void testCompressInvalidInputGt100() {

    guiController.load("test/images/16px.ppm");
    guiController.compress("102");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void redCompImageNotLoaded() {

    guiController.redComponent();
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void greenCompImageNotLoaded() {
    guiController.greenComponent();
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void blueCompImageNotLoaded() {
    guiController.blueComponent();
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void hFlipImageNotLoaded() {
    guiController.horizontalFlip();
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void vFlipImageNotLoaded() {
    guiController.verticalFlip();
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void levelsAdjustmentImageNotLoaded() {
    guiController.levelsAdjust("1", "2", "3", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void levelsAdjustmentNoInput() {
    guiController.load("test/images/16px.ppm");
    guiController.levelsAdjust("", "", "3", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void levelsAdjustmentNullInput() {
    guiController.load("test/images/16px.ppm");
    guiController.levelsAdjust(null, "", "3", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void levelsAdjustmentInvalidInput() {
    guiController.load("test/images/16px.ppm");
    guiController.levelsAdjust("1", "$", "3", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void levelsAdjustmentInvalidInputLt0() {
    guiController.load("test/images/16px.ppm");
    guiController.levelsAdjust("2", "-1", "3", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void levelsAdjustmentParametersInvalidInputGT255() {

    guiController.load("test/images/16px.ppm");
    guiController.levelsAdjust("2", "40", "256", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void levelsAdjustmentParametersInvalidInputNonAscending1() {

    guiController.load("test/images/16px.ppm");
    guiController.levelsAdjust("21", "10", "254", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void levelsAdjustmentParametersInvalidInputNonAscending2() {
    guiController.load("test/images/16px.ppm");
    guiController.levelsAdjust("21", "100", "80", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void levelsAdjustmentParametersInvalidInputNonAscending3() {
    guiController.load("test/images/16px.ppm");
    guiController.levelsAdjust("210", "100", "50", false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void lumaCompImageNotLoaded() {
    guiController.lumaComponent(false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void blurImageNotLoaded() {
    guiController.blurImage(true);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }


  @Test
  public void sharpenImageNotLoaded() {
    guiController.sharpenImage(true);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void colorCorrectSplitImageNotLoaded() {
    guiController.colorCorrect(false);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void sepiaSourceImageNotLoaded() {
    guiController.toSepia(true);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display load image called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void splitNullInput() {
    guiController.load("test/images/16px.ppm");
    guiController.getSplitView(null);
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void splitInvalidEmptyInput() {
    guiController.load("test/images/16px.ppm");
    guiController.getSplitView("");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());

  }

  @Test
  public void splitInvalidInput() {
    guiController.load("test/images/16px.ppm");
    guiController.getSplitView("$");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void splitInvalidInputLt0() {
    guiController.load("test/images/16px.ppm");
    guiController.getSplitView("-1");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

  @Test
  public void splitInvalidInputGt100() {
    guiController.load("test/images/16px.ppm");
    guiController.getSplitView("101");
    String viewOutput = "Add action listener called"
            + System.getProperty("line.separator") +
            "Display Image is called"
            + System.getProperty("line.separator") +
            "Display error called"
            + System.getProperty("line.separator");
    assertEquals(viewOutput, viewLog.toString());
  }

}
