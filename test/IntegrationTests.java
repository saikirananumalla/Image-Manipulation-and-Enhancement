import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageController;
import model.Image;
import model.ImageEditorImpl;
import model.Pixel;
import model.RGBImage;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A Junit class to test the working of the application, we input the commands the verify the result
 * images are same as the expected results we have, also checks for scenarios like invalid commands
 * and exception handling.
 */
public class IntegrationTests {

  private StringBuffer out;
  private TextView textView;

  @Before
  public void setUp() {
    out = new StringBuffer();
    textView = new TextView(out);
  }

  @Test
  public void testInvalidCommand() {

    String input = "hi there";

    String exc = "Invalid command"
            + System.getProperty("line.separator")
            + "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testRunScriptFile() {

    String input = "run test/images/script.txt \n quit";

    String exc = "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
    assertTrue(arePPMImagesEqual("test/images/black-test.ppm", "test/images/black.ppm"));
  }

  @Test
  public void testLoadAndSavePPM() {

    String input = "load test/images/16px.ppm k \n save test/images/16px-save.ppm k";

    String exc = "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm", "test/images/16px-save.ppm"));
  }


  @Test
  public void testLoadAndSavePPMInvalidPath() {

    String input = "load test/images/16px.ppm k \n save test/images1/k.ppm k";

    String exc = "Error : test/images1/k.ppm (No such file or directory)" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testLoadAndSavePPMInvalidAlias() {

    String input = "load test/images/16px.ppm k \n save test/images/k.ppm k12";

    String exc = "Error : The source image name does not exist"
            + System.getProperty("line.separator")
            + "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testLoadPNGAndSavePPM() {

    String input = "load test/images/16px.ppm ms \n save test/images/16px-save.ppm ms";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm", "test/images/16px-save.ppm"));
  }

  @Test
  public void testLoadAndSavePNG() {

    String input = "load test/images/16px.png ms \n save test/images/16px-save.png ms";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(areImagesEqual("test/images/16px.png", "test/images/16px-save.png"));
  }


  @Test
  public void testLoadAndSavePNGInvalidAlias() {

    String input = "load test/images/16px.png ms \n save test/images/ms.png ms1";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testLoadAndSaveJPG() {

    String input = "load test/images/16px.jpg ms \n save test/images/16px-save.jpg ms";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(areJPGImagesEqual("test/images/16px.jpg", "test/images/16px-save.jpg"));
  }

  @Test
  public void testLoadAndSaveJPGInvalidAlias() {

    String input = "load test/images/16px.jpg ms \n save test/images/m.jpg ms1";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // red component.

  @Test
  public void testRedComponentOfImage() {

    String input = "load test/images/16px.png ms \n" +
            " red-component ms ms1 \n" +
            " save test/images/16px-red-test.png ms1";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(areImagesEqual("test/images/16px-red.png",
            "test/images/16px-red-test.png"));
  }

  // red component source name invalid.

  @Test
  public void testRedComponentOfImageInvalidSource() {

    String input = "load test/images/16px.png ms \n red-component ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // red component no destination name.

  @Test
  public void testRedComponentOfImageNoArguments() {

    String input = "load test/images/16px.png ms \n red-component";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testRedComponentOfImageNoDestination() {

    String input = "load test/images/16px.png ms \n red-component ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // green component.

  @Test
  public void testGreenComponentOfImage() {

    String input = "load test/images/16px.png ms \n green-component ms ms2" +
            " \n save test/images/16px-green-test.png ms2";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(areImagesEqual("test/images/16px-green.png",
            "test/images/16px-green-test.png"));
  }

  // green component source name invalid.

  @Test
  public void testGreenComponentOfImageInvalidSource() {

    String input = "load test/images/16px.png ms \n green-component ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // green component no destination name.

  @Test
  public void testGreenComponentOfImageNoDestination() {

    String input = "load test/images/16px.png ms \n green-component ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // blue component.

  @Test
  public void testBlueComponentOfImage() {

    String input = "load test/images/16px.png ms \n blue-component ms ms3" +
            " \n save test/images/16px-blue-test.png ms3";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(areImagesEqual("test/images/16px-blue.png",
            "test/images/16px-blue-test.png"));
  }

  // blue component source name invalid.

  @Test
  public void testBlueComponentOfImageInvalidSource() {

    String input = "load test/images/16px.png ms \n blue-component ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // blue component no destination name.

  @Test
  public void testBlueComponentOfImageNoDestination() {

    String input = "load test/images/16px.png ms \n blue-component ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }


  @Test
  public void testLumaComponentOfImage() {

    String input = "load test/images/16px.png ms \n luma-component ms ms4" +
            " \n save test/images/16px-luma-test.ppm ms4";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-luma-test.ppm",
            "test/images/16px-luma-split-100-ref.ppm"));
  }

  @Test
  public void testLumaComponentOfImageSplitZero() {

    String input = "load test/images/16px.ppm test" +
            " \n luma-component test testCC split 0" +
            " \n save test/images/16px-luma-split-0-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm",
            "test/images/16px-luma-split-0-test.ppm"));
  }

  @Test
  public void testLumaComponentOfImageSplit() {

    String input = "load test/images/16px.ppm test" +
            " \n luma-component test testCC split 60" +
            " \n save test/images/16px-luma-split-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-luma-split-ref.ppm",
            "test/images/16px-luma-split-test.ppm"));
  }

  @Test
  public void testLumaComponentOfImageSplit100() {

    String input = "load test/images/16px.ppm test" +
            " \n luma-component test testCC split 100" +
            " \n save test/images/16px-luma-split-100-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-luma-split-100-ref.ppm",
            "test/images/16px-luma-split-100-test.ppm"));
  }

  // luma component source name invalid.

  @Test
  public void testLumaComponentOfImageInvalidSource() {

    String input = "load test/images/16px.ppm ms \n luma-component ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // luma component no destination name.

  @Test
  public void testLumaComponentOfImageNoDestination() {

    String input = "load test/images/16px.ppm ms \n luma-component ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testIntensityComponentOfImage() {

    String input = "load test/images/16px.ppm ms \n intensity-component ms ms4" +
            " \n save test/images/16px-intensity-test.ppm ms4";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-intensity-test.ppm",
            "test/images/16px-intensity-ref.ppm"));
  }

  // luma component source name invalid.

  @Test
  public void testIntensityComponentOfImageInvalidSource() {

    String input = "load test/images/16px.ppm ms \n intensity-component ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // luma component no destination name.

  @Test
  public void testIntensityComponentOfImageNoDestination() {

    String input = "load test/images/16px.ppm ms \n intensity-component ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // horizontal flip component.

  @Test
  public void testHorizontalFlipOfImage() {

    String input = "load test/images/16px.ppm ms \n horizontal-flip ms ms5" +
            " \n save test/images/16px-horizontal-test.png ms5";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(areImagesEqual("test/images/16px-horizontal-test.png",
            "test/images/16px-hFlip.png"));
  }

  @Test
  public void testHorizontalTwiceFlipOfImage() {

    String input = "load test/images/16px.ppm ms \n horizontal-flip ms ms5 \n" +
            " horizontal-flip ms5 ms6 \n save test/images/16px-tw-hFlip.ppm ms6";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-tw-hFlip.ppm",
            "test/images/16px.ppm"));
  }

  @Test
  public void testHorizontalVerticalFlipOfImage() {

    String input = "load test/images/16px.ppm ms \n horizontal-flip ms ms5 \n" +
            " vertical-flip ms5 ms6 \n save test/images/16px-horizontal-vertical-test" +
            ".ppm ms6";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-horizontal-vertical-ref.ppm",
            "test/images/16px-horizontal-vertical-test.ppm"));
  }

  // horizontal flip component source name invalid.

  @Test
  public void testHorizontalFlipOfImageInvalidSource() {

    String input = "load test/images/16px.ppm ms \n horizontal-flip ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // horizontal flip no destination name.

  @Test
  public void testHorizontalFlipOfImageNoDestination() {

    String input = "load test/images/16px.ppm ms \n horizontal-flip ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // vertical flip component.

  @Test
  public void testVerticalFlipOfImage() {

    String input = "load test/images/16px.ppm ms \n vertical-flip ms ms6" +
            " \n save test/images/16px-vFlip-test.png ms6";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(areImagesEqual("test/images/16px-vFlip-test.png",
            "test/images/16px-vFlip.png"));
  }

  @Test
  public void testVerticalHorizontalFlipOfImage() {

    String input = "load test/images/16px.ppm ms \n vertical-flip ms ms5 \n" +
            " horizontal-flip ms5 ms6 \n save" +
            " test/images/16px-vertical-horizontal-test" +
            ".ppm ms6";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-vertical-horizontal-ref.ppm",
            "test/images/16px-vertical-horizontal-test.ppm"));
  }

  @Test
  public void testVerticalTwiceFlipOfImage() {

    String input = "load test/images/16px.ppm ms \n vertical-flip ms ms5 \n" +
            " vertical-flip ms5 ms6 \n save test/images/16px-test.ppm ms6";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm",
            "test/images/16px-test.ppm"));
  }

  // vertical flip component source name invalid.

  @Test
  public void testVerticalFlipOfImageInvalidSource() {

    String input = "load test/images/16px.ppm ms \n vertical-flip ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // vertical flip no destination name.

  @Test
  public void testVerticalFlipOfImageNoDestination() {

    String input = "load test/images/16px.ppm ms \n vertical-flip ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Brighten normal.

  @Test
  public void testBrighteningOfImage() {

    String input = "load test/images/16px.ppm ms \n brighten 50 ms ms7" +
            " \n save test/images/16px-bright-test.ppm ms7";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-bright-ref.ppm",
            "test/images/16px-bright-test.ppm"));
  }

  // Brighten invalid value.

  @Test
  public void testBrighteningOfImageInvalidValue() {

    String input = "load test/images/16px.ppm ms \n brighten XYZ ms ms2";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Brighten invalid source name.

  @Test
  public void testBrighteningOfImageInvalidSource() {

    String input = "load test/images/16px.ppm ms \n brighten 50 ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Brighten no destination name.

  @Test
  public void testBrighteningOfImageNoDestination() {

    String input = "load test/images/16px.ppm ms \n brighten 10 ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Darken an image.

  @Test
  public void testDarkeningOfImage() {

    String input = "load test/images/16px.ppm ms \n brighten -50 ms ms8" +
            " \n save test/images/16px-dark-test.ppm ms8";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-dark-ref.ppm",
            "test/images/16px-dark-test.ppm"));
  }

  // Blur image.

  /**
   * Implementation error. Not significant difference between the reference and the
   * calculated image.
   */
  @Test
  public void testBlurringOfImage() {

    String input = "load test/images/16px.ppm ms \n blur ms ms9" +
            " \n save test/images/16px-blur-test.ppm ms9";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-blur-test.ppm",
            "test/images/16px-blur-split-100-ref.ppm"));
  }

  @Test
  public void testBlurringOfImageSplit0() {

    String input = "load test/images/16px.ppm test" +
            " \n blur test testCC split 0" +
            " \n save test/images/16px-blur-split-0-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm",
            "test/images/16px-blur-split-0-test.ppm"));
  }

  @Test
  public void testBlurringOfImageSplit() {

    String input = "load test/images/16px.ppm test" +
            " \n blur test testCC split 60" +
            " \n save test/images/16px-blur-split-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-blur-split-ref.ppm",
            "test/images/16px-blur-split-test.ppm"));
  }

  @Test
  public void testBlurringOfImageSplit100() {

    String input = "load test/images/16px.ppm test" +
            " \n blur test testCC split 100" +
            " \n save test/images/16px-blur-split-100-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-blur-split-100-ref.ppm",
            "test/images/16px-blur-split-100-test.ppm"));
  }

  // Blur image source name invalid.

  @Test
  public void testBlurringOfImageInvalidSource() {

    String input = "load test/images/16px.ppm ms \n blur ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Blur image no destination name.

  @Test
  public void testBlurringOfImageNoDestination() {

    String input = "load test/images/16px.ppm ms \n blur ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Sharpen image.

  /**
   * Implementation error.
   */
  @Test
  public void testSharpeningOfImage() {

    String input = "load test/images/16px.ppm ms \n sharpen ms ms10" +
            " \n save test/images/16px-sharpen-test.ppm ms10";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-sharpen-test.ppm",
            "test/images/16px-sharpen-split-100-ref.ppm"));
  }

  @Test
  public void testSharpeningOfImageSplit0() {

    String input = "load test/images/16px.ppm test" +
            " \n sharpen test testCC split 0" +
            " \n save test/images/16px-sharpen-split-0-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm",
            "test/images/16px-sharpen-split-0-test.ppm"));
  }

  @Test
  public void testSharpeningOfImageSplit() {

    String input = "load test/images/16px.ppm test" +
            " \n sharpen test testCC split 60" +
            " \n save test/images/16px-sharpen-split-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-sharpen-split-ref.ppm",
            "test/images/16px-sharpen-split-test.ppm"));
  }


  @Test
  public void testSharpeningOfImageSplit100() {

    String input = "load test/images/16px.ppm test" +
            " \n sharpen test testCC split 100" +
            " \n save test/images/16px-sharpen-split-100-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-sharpen-split-100-ref.ppm",
            "test/images/16px-sharpen-split-100-test.ppm"));
  }

  // Sharpen image source name invalid.

  @Test
  public void testSharpeningOfImageInvalidSource() {

    String input = "load test/images/16px.ppm ms \n sharpen ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Sharpen image no destination name.

  @Test
  public void testSharpeningOfImageNoDestination() {

    String input = "load test/images/16px.ppm ms \n sharpen ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Sepia image.

  @Test
  public void testSepiaOfImage() {

    String input = "load test/images/16px.ppm ms \n sepia ms ms11" +
            " \n save test/images/16px-sepia-test.ppm ms11";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-sepia-test.ppm",
            "test/images/16px-sepia-split-100.ppm"));
  }

  @Test
  public void testSepiaOfImageSplitZero() {

    String input = "load test/images/16px.ppm test" +
            " \n sepia test testCC split 0" +
            " \n save test/images/16px-sepia-split-zero-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm",
            "test/images/16px-sepia-split-zero-test.ppm"));
  }

  @Test
  public void testSepiaOfImageSplit() {

    String input = "load test/images/16px.ppm test" +
            " \n sepia test testCC split 60" +
            " \n save test/images/16px-sepia-split-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-sepia-split-ref.ppm",
            "test/images/16px-sepia-split-test.ppm"));
  }

  @Test
  public void testSepiaOfImageSplit100() {

    String input = "load test/images/16px.ppm test" +
            " \n sepia test testCC split 100" +
            " \n save test/images/16px-sepia-split-100-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-sepia-split-100.ppm",
            "test/images/16px-sepia-split-100-test.ppm"));
  }

  // Sepia image source name invalid.

  @Test
  public void testSepiaOfImageInvalidSource() {

    String input = "load test/images/16px.ppm ms \n sepia ms1 ms2";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // Sepia image no destination name.

  @Test
  public void testSepiaOfImageNoDestination() {

    String input = "load test/images/16px.ppm ms \n sepia ms";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  // RGB combine normal.

  @Test
  public void testRGBCombineOfImage() {

    String input = "load test/images/16px-red.png msRed \n " +
            "load test/images/16px-green.png msGreen \n" +
            "load test/images/16px-blue.png msBlue \n" +
            "rgb-combine msResult msRed msGreen msBlue \n" +
            "save test/images/16px-test.ppm msResult";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm",
            "test/images/16px-test.ppm"));
  }

  // RGB combine invalid source name.

  @Test
  public void testRGBCombineOfImageInvalidSourceNames() {

    String input = "load test/images/16px-red.png msRed \n " +
            "load test/images/16px-green.png msGreen \n" +
            "load test/images/16px-blue.png msBlue \n" +
            "rgb-combine msResult msRed1 msGreen1 msBlue1";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";

    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testRGBSplitOfImage() {

    String input = "load test/images/16px.ppm ms \n " +
            "rgb-split ms red green blue \n" +
            "save test/images/16px-red-test.png red \n" +
            "save test/images/16px-green-test.png green \n" +
            "save test/images/16px-blue-test.png blue \n";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(areImagesEqual("test/images/16px-red.png",
            "test/images/16px-red-test.png"));
    assertTrue(areImagesEqual("test/images/16px-green.png",
            "test/images/16px-green-test.png"));
  }

  @Test
  public void testLevelsAdjustmentOfImage() {

    String input = "load test/images/16px.ppm test" +
            " \n levels-adjust 71 203 235 test testLa" +
            " \n save test/images/16px-level-adjust-test.ppm testLa";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-level-adjust-test.ppm",
            "test/images/16px-level-adjust-ref.ppm"));
  }

  @Test
  public void testLevelsAdjustmentOfImageSplitZero() {

    String input = "load test/images/16px.ppm test" +
            " \n levels-adjust 71 203 235 test testLa split 0" +
            " \n save test/images/16px-level-adjust-zero-split-test.ppm testLa";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-level-adjust-zero-split-test.ppm",
            "test/images/16px.ppm"));
  }

  @Test
  public void testLevelsAdjustmentOfImageSplit() {

    String input = "load test/images/16px.ppm test" +
            " \n levels-adjust 71 203 235 test testLa split 60" +
            " \n save test/images/16px-level-adjust-split-test.ppm testLa";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-level-adjust-split-test.ppm",
            "test/images/16px-level-adjust-split-ref.ppm"));
  }

  @Test
  public void testLevelsAdjustmentOfImageSplit100() {

    String input = "load test/images/16px.ppm test" +
            " \n levels-adjust 71 203 235 test testLa split 100" +
            " \n save test/images/16px-level-adjust-split-100-test.ppm testLa";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-level-adjust-split-100-test.ppm",
            "test/images/16px-level-adjust-ref.ppm"));
  }

  @Test
  public void testLevelsAdjustmentInvalidCommand() {

    String input = "load test/images/16px.ppm test" +
            " \n levels-adjust 203 235 test testLa";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";
    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testColorCorrection() {

    String input = "load test/images/16px.ppm test" +
            " \n color-correct test testCC" +
            " \n save test/images/16px-color-correct-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-color-correct-ref.ppm",
            "test/images/16px-color-correct-test.ppm"));
  }

  @Test
  public void testColorCorrectionSplitZero() {

    String input = "load test/images/16px.ppm test" +
            " \n color-correct test testCC split 0" +
            " \n save test/images/16px-color-correct-zero-split-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px.ppm",
            "test/images/16px-color-correct-zero-split-test.ppm"));
  }

  @Test
  public void testColorCorrectionSplit() {

    String input = "load test/images/16px.ppm test" +
            " \n color-correct test testCC split 60" +
            " \n save test/images/16px-color-correct-split-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-color-correct-split-ref.ppm",
            "test/images/16px-color-correct-split-test.ppm"));
  }

  @Test
  public void testColorCorrectionSplit100() {

    String input = "load test/images/16px.ppm test" +
            " \n color-correct test testCC split 100" +
            " \n save test/images/16px-color-correct-100-split-test.ppm testCC";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-color-correct-ref.ppm",
            "test/images/16px-color-correct-100-split-test.ppm"));
  }

  @Test
  public void testColorCorrectionInvalidCommand() {

    String input = "load test/images/16px.ppm test" +
            " \n color-correct test1 testCC";

    String exc = "Error : The source image name does not exist" +
            System.getProperty("line.separator") +
            "Exiting the application";
    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  @Test
  public void testHistogram() {

    String input = "load test/images/16px.ppm test" +
            " \n histogram test testHist" +
            " \n save test/images/16px-hist-test.ppm testHist";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-hist-test.ppm",
            "test/images/16px-hist-ref.ppm"));
  }

  @Test
  public void testCompression() {

    String input = "load test/images/16px.ppm test" +
            " \n compress 60 test testCompress" +
            " \n save test/images/16px-compress-test.ppm testCompress";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-compress-test.ppm",
            "test/images/16px-compress-ref.ppm"));
  }

  @Test
  public void testCompressionZero() {

    String input = "load test/images/16px.ppm test" +
            " \n compress 0 test testCompress" +
            " \n save test/images/16px-compress-zero-test.ppm testCompress";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-compress-zero-test.ppm",
            "test/images/16px.ppm"));
  }

  @Test
  public void testCompression100() {

    String input = "load test/images/16px.ppm test" +
            " \n compress 100 test testCompress" +
            " \n save test/images/16px-compress-100-test.ppm testCompress";

    String output = executeCommand(input);
    assertEquals("Exiting the application", output);
    assertTrue(arePPMImagesEqual("test/images/16px-compress-100-test.ppm",
            "test/images/black.ppm"));
  }

  @Test
  public void testCompressionInvalidCommand() {

    String input = "load test/images/16px.ppm test" +
            " \n compress x test1 testCompress";

    String exc = "Invalid command" +
            System.getProperty("line.separator") +
            "Exiting the application";
    String output = executeCommand(input);
    assertEquals(exc, output);
  }

  private String executeCommand(String input) {

    // Set up the input stream with the current input
    Reader in = new StringReader(input + "\n" + "quit" + "\n");

    ImageController imageController = new ImageController(textView, in, new ImageEditorImpl());

    try {
      imageController.start();
    } catch (IOException e) {
      fail("command execution failed");
    }

    // return the output stream as a string
    return out.toString().trim();
  }

  /**
   * Check if two images are equal.
   * @param imagePath1 image 1 path.
   * @param imagePath2 image 2 path.
   * @return if equal true.
   */
  public static boolean areImagesEqual(String imagePath1, String imagePath2) {
    try {
      // Read the two images
      Image image1 = RGBImage.getLoader().loadFromPngOrJpg(imagePath1);
      Image image2 = RGBImage.getLoader().loadFromPngOrJpg(imagePath2);

      // Check if the dimensions are the same
      return checkImagesEqual(image1, image2);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Check if two images are equal.
   * @param image1 image 1.
   * @param image2 image 2.
   * @return if equal true.
   */
  public static boolean checkImagesEqual(Image image1, Image image2) {
    if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
      return false;
    }

    // Compare the pixel values
    for (int x = 0; x < image1.getWidth(); x++) {

      for (int y = 0; y < image1.getHeight(); y++) {
        if (!image1.getPixel(x, y).equals(image2.getPixel(x, y))) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Check if two jpg images are equal.
   * @param imagePath1 image 1 path.
   * @param imagePath2 image 2 path.
   * @return if equal true.
   */
  public static boolean areJPGImagesEqual(String imagePath1, String imagePath2) {
    try {
      // Read the two images
      Image image1 = RGBImage.getLoader().loadFromPngOrJpg(imagePath1);
      Image image2 = RGBImage.getLoader().loadFromPngOrJpg(imagePath2);

      // Check if the dimensions are the same
      if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
        return false;
      }

      // Compare the pixel values
      for (int x = 0; x < image1.getWidth(); x++) {
        for (int y = 0; y < image1.getHeight(); y++) {
          Pixel p1 = image1.getPixel(x, y);
          Pixel p2 = image2.getPixel(x, y);

          if (Math.abs(p1.getBlue() - p2.getBlue()) > 6 || Math.abs(p1.getRed() - p2.getRed()) > 6
                  || Math.abs(p1.getGreen() - p2.getGreen()) > 6) {
            return false;
          }
        }
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Check if two ppm images are equal.
   * @param imagePath1 image 1 path.
   * @param imagePath2 image 2 path.
   * @return if equal true.
   */
  public static  boolean arePPMImagesEqual(String imagePath1, String imagePath2) {
    try {

      Scanner sc1;
      Scanner sc2;

      //input the image as file stream
      sc1 = new Scanner(new FileInputStream(imagePath1));
      sc2 = new Scanner(new FileInputStream(imagePath2));

      //check if headers are equal
      String token1;
      String token2;

      token1 = sc1.next();
      token2 = sc2.next();

      if (!token1.equals(token2)) {
        return false;
      }

      int width1 = sc1.nextInt();
      int width2 = sc2.nextInt();

      int height1 = sc1.nextInt();
      int height2 = sc2.nextInt();

      int maxValue1 = sc1.nextInt();
      int maxValue2 = sc2.nextInt();

      if (width1 != width2 || height1 != height2 || maxValue1 != maxValue2) {
        return false;
      }

      //check if each pixel is equal
      for (int i = 0; i < height1; i++) {
        for (int j = 0; j < width1; j++) {
          int r1 = sc1.nextInt();
          int g1 = sc1.nextInt();
          int b1 = sc1.nextInt();

          int r2 = sc2.nextInt();
          int g2 = sc2.nextInt();
          int b2 = sc2.nextInt();

          if (r1 != r2 || g1 != g2 || b1 != b2) {
            return false;
          }
        }
      }

      //return true if nothing above fails
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}