package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.ImageEditorImpl;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A Junit class to unit test the verify exception is not thrown, and we show proper error text from
 * image controller by entering many error commands.
 */
public class ImageControllerNegativeTest {

  private ImageController imageController;

  private StringBuffer out;

  private TextView view;

  @Before
  public void setUp() {

    out = new StringBuffer();
    view = new TextView(out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNull() {
    imageController = new ImageController(null,
            null, null);
  }

  @Test
  public void loadNoImage() {
    execute("load noImage.png n \n quit \n");
    assertEquals("Error : file does not exist at : noImage.png"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void loadIllegalNumberArgs() {
    execute("load n \n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  //load invalid extension
  @Test
  public void loadInvalidImageExtension() {
    execute("load n.exe n \n quit \n");
    assertEquals("Error : Invalid file extension"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }


  //save invalid extension
  @Test
  public void saveInvalidImageExtension() {
    execute("save n.exe n \n quit \n");
    assertEquals("Error : Invalid file extension"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //save alias name doesn't exist
  @Test
  public void saveNoImage() {
    execute("save n.png n \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //red comp alias name doesn't exist
  @Test
  public void redCompSourceImageNotExists() {
    execute("red-component a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }


  //green comp alias name doesn't exist
  @Test
  public void greenCompSourceImageNotExists() {
    execute("green-component a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //blue comp alias name doesn't exist
  @Test
  public void blueCompSourceImageNotExists() {
    execute("blue-component a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //value comp alias name doesn't exist
  @Test
  public void valueCompSourceImageNotExists() {
    execute("value-component a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //intensity comp alias name doesn't exist
  @Test
  public void intensityCompSourceImageNotExists() {
    execute("intensity-component a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //luma comp alias name doesn't exist
  @Test
  public void lumaCompSourceImageNotExists() {
    execute("luma-component a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //horizontal-flip alias name doesn't exist
  @Test
  public void hFlipSourceImageNotExists() {
    execute("horizontal-flip a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //vertical-flip alias name doesn't exist
  @Test
  public void vFlipSourceImageNotExists() {
    execute("vertical-flip a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //blur comp alias name doesn't exist
  @Test
  public void blurSourceImageNotExists() {
    execute("blur a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //sharpen comp alias name doesn't exist
  @Test
  public void sharpenSourceImageNotExists() {
    execute("sharpen a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //sepia comp alias name doesn't exist
  @Test
  public void sepiaSourceImageNotExists() {
    execute("sepia a b \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //rgb split alias name doesn't exist
  @Test
  public void rgbSourceImageNotExists() {
    execute("rgb-split a b c d \n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //rgb combine r,g,b alias doesn't exist
  @Test
  public void rgbCombineSourceImageNotExists() {
    execute("rgb-combine a b c d\n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  //invalid rgb combine arg count
  @Test
  public void rgbCombineInvalidArgCount() {
    execute("rgb-combine a b c\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  //brighten is integer 2nd command arg
  @Test
  public void brightenInvalidInteger() {
    execute("brighten a b c\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  //invalid arg count for brighten
  @Test
  public void brightenInvalidArgCount() {
    execute("brighten 10 b c d\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  //run without file name
  @Test
  public void runWithoutFileName() {
    execute("run\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  //run with invalid filename
  @Test
  public void runWithInvalidFileName() {
    execute("run fn.png\n quit \n");
    assertEquals("File fn.png not found!" + System.getProperty("line.separator") +
            "Exiting the application" + System.getProperty("line.separator"), out.toString());
  }

  //run with more args
  @Test
  public void runWithInvalidArgs() {
    execute("run a.txt b\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }


  //enter some random command
  @Test
  public void testInvalidCommand() {
    execute("hi there\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  //case sensitive
  @Test
  public void testMixedCaseSensitive() {
    execute("loaD test/images/koala.ppm k\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testCaseSensitive() {
    execute("LOAD test/images/koala.ppm k\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  //only quit exits the application
  @Test
  public void testQuit() {
    execute("exit\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testCommandStripsSpaces() {
    execute("  hi there   \n   load    test/images/16px.ppm  k  \n           quit     \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting" +
            " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testBlurSplitNoInput() {

    execute("blur k kBlur split" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testBlurSplitInvalidInput() {

    execute("blur k kBlur split a" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testBlurSplitInvalidInputLessThan0() {

    execute("blur k kBlur split -2" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testBlurSplitInvalidInputGreaterThan100() {

    execute("blur k kBlur split 102" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testSharpenSplitNoInput() {

    execute("sharpen k kSharp split" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testSharpenSplitInvalidInput() {

    execute("sharpen k kSharp split a" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testSharpenSplitInvalidInputLt0() {

    execute("sharpen k kSharp split -43" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testSharpenSplitInvalidInputGt100() {

    execute("sharpen k kSharp split 243" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testSepiaSplitNoInput() {

    execute("sepia k kSepia split" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testSepiaSplitInvalidInput() {

    execute("sepia k kSepia split a" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testSepiaSplitInvalidInputLt0() {

    execute("sepia k kSepia split -23" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testSepiaSplitInvalidInputGt100() {

    execute("sepia k kSepia split 123" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectSplitNoInput() {

    execute("color-correct k kGrey split" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectSplitInvalidInput() {

    execute("color-correct k kGrey split a" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectSplitInvalidInputLt0() {

    execute("color-correct k kGrey split -1" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectSplitInvalidInputGt100() {

    execute("color-correct k kGrey split 23543" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustmentSplitNoInput() {

    execute("levels-adjust 10 100 200 k kGrey split" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustmentSplitInvalidInput() {

    execute("levels-adjust 10 100 20 k kGrey split a" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustmentSplitInvalidInputLt0() {

    execute("levels-adjust 10 100 20 k kGrey split -23" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustmentSplitInvalidInputGt100() {

    execute("levels-adjust 10 100 20 k kGrey split 101" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLumaSplitNoInput() {

    execute("greyscale k kGrey split" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLumaSplitInvalidInput() {

    execute("greyscale k kGrey split a" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLumaSplitInvalidInputLt0() {

    execute("greyscale k kGrey split -2" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLumaSplitInvalidInputGt100() {

    execute("greyscale k kGrey split 123" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testHistogramInvalidInput() {

    execute("histogram k " +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testHistogramInvalidInputSourceNotFound() {

    execute("histogram k1 kHist " +
            "\n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectInvalidInput() {

    execute("color-correct k " +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectInvalidInputSourceNotFound() {

    execute("color-correct k1 kCC " +
            "\n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectInvalidSplit() {

    execute("color-correct k1 kCC split" +
            "\n quit \n");
    assertEquals("Invalid command"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectInvalidSplitLt0() {

    execute("color-correct k1 kCC split -2" +
            "\n quit \n");
    assertEquals("Invalid command"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testColorCorrectInvalidSplitGt100() {

    execute("color-correct k1 kCC split 222" +
            "\n quit \n");
    assertEquals("Invalid command"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustmentInvalidInput() {

    execute("levels-adjust 20 101 201 k " +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustmentInvalidInputSourceNotFound() {

    execute("levels-adjust 20 101 201 k1 kCC " +
            "\n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }


  @Test
  public void testLevelsAdjustmentInvalidBWHValues() {

    execute("levels-adjust 20 101 257 k1 kCC " +
            "\n quit \n");
    assertEquals("Invalid command"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustmentInvalidBWHValuesLt0() {

    execute("levels-adjust -1 101 254 k1 kCC " +
            "\n quit \n");
    assertEquals("Invalid command"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustBWHValuesNotAsc() {

    execute("levels-adjust 123 52 200 k1 kCC " +
            "\n quit \n");
    assertEquals("Invalid command"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testLevelsAdjustmentInvalidNumberOfArguments() {

    execute("levels-adjust 101 201 k kCC " +
            "\n quit \n");
    assertEquals("Invalid command"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testCompressInvalidInput() {

    execute("compress 10 k " +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testCompressInvalidInputSourceNotFound() {

    execute("compress 10 k1 kCC " +
            "\n quit \n");
    assertEquals("Error : The source image name does not exist"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testCompressInvalidArgument() {

    execute("compress a k kCC " +
            "\n quit \n");
    assertEquals("Invalid command"
            + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testCompressInvalidInputLt0() {

    execute("compress -1 k kc" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  @Test
  public void testCompressInvalidInputGt100() {

    execute("compress 101 k kc" +
            "\n quit \n");
    assertEquals("Invalid command" + System.getProperty("line.separator") + "Exiting"
            + " the application" + System.getProperty("line.separator"), out.toString());
  }

  private void execute(String s) {
    Readable in = new StringReader(s);
    imageController = new ImageController(view, in, new ImageEditorImpl());
    try {
      imageController.start();
    } catch (IOException e) {
      fail("exception thrown");
    }
  }

}
