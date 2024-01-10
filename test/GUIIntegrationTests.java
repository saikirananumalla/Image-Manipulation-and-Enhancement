import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import controller.GUIController;
import model.GUIImageEditor;
import model.GUIImageEditorImpl;
import model.Image;
import model.RGBImage;
import view.JFrameView;

import static org.junit.Assert.assertTrue;

/**
 * A JUnit class to test the working of GUI controller has tests for all the features supported
 * by the program.
 */
public class GUIIntegrationTests {

  public static final String PX_PPM = "test/images/16px.ppm";
  private GUIController controller;
  private MockView jFrameView;

  @Before
  public void setUp() {
    jFrameView = new MockView();
    GUIImageEditor editor = new GUIImageEditorImpl();

    controller = new GUIController(editor, jFrameView);
    controller.load(PX_PPM);
  }

  @Test
  public void savePPM() {
    controller.save("test/images/16px-save.ppm");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram, "test/images/16px-hist.ppm"));
    assertTrue(IntegrationTests.arePPMImagesEqual("test/images/16px-save.ppm", PX_PPM));
  }

  @Test
  public void saveJPG() {
    controller.save("test/images/16px-save.jpg");
    assertTrue(IntegrationTests.areJPGImagesEqual("test/images/16px-save.jpg",
            "test/images/16px.jpg"));
  }

  @Test
  public void savePNG() {
    controller.save("test/images/16px-save.png");
    assertTrue(IntegrationTests.areImagesEqual("test/images/16px-save.png",
            "test/images/16px.png"));
  }

  @Test
  public void redComponent() {
    controller.redComponent();
    assertTrue(isBufferedImageEqual(jFrameView.image, "test/images/16px-red.png"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram, "test/images/16px-red-hist.ppm"));
  }

  @Test
  public void greenComponent() {
    controller.greenComponent();
    assertTrue(isBufferedImageEqual(jFrameView.image, "test/images/16px-green.png"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-green-hist.ppm"));
  }

  @Test
  public void blueComponent() {
    controller.blueComponent();
    assertTrue(isBufferedImageEqual(jFrameView.image, "test/images/16px-blue.png"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-blue-hist.ppm"));
  }

  @Test
  public void lumaComponent() {
    controller.lumaComponent(true);
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-luma-split-100-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-luma-split-100-ref-hist.ppm"));
  }

  @Test
  public void getlumaComponentSplitView() {
    controller.lumaComponent(false);
    controller.getSplitView("60");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-luma-split-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-luma-split-ref-hist.ppm"));
  }

  @Test
  public void getlumaComponentSplitView0() {
    controller.lumaComponent(false);
    controller.getSplitView("0");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hist.ppm"));
  }

  @Test
  public void getlumaComponentSplitView100() {
    controller.lumaComponent(false);
    controller.getSplitView("100");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-luma-split-100-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-luma-split-100-ref-hist.ppm"));
  }

  @Test
  public void horizontalFlip() {
    controller.horizontalFlip();
    assertTrue(isBufferedImageEqual(jFrameView.image, "test/images/16px-hFlip.png"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hFlip-hist.ppm"));
  }

  @Test
  public void verticalFlip() {
    controller.verticalFlip();
    assertTrue(isBufferedImageEqual(jFrameView.image, "test/images/16px-vFlip.png"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-vFlip-hist.ppm"));
  }

  @Test
  public void blurImage() {
    controller.blurImage(true);
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-blur-split-100-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-blur-split-100-ref-hist.ppm"));
  }

  @Test
  public void getBlurImageSplitView() {
    controller.blurImage(false);
    controller.getSplitView("60");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-blur-split-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-blur-split-ref-hist.ppm"));
  }

  @Test
  public void getBlurImageSplitView0() {
    controller.blurImage(false);
    controller.getSplitView("0");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hist.ppm"));
  }

  @Test
  public void getBlurImageSplitView100() {
    controller.blurImage(false);
    controller.getSplitView("100");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-blur-split-100-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-blur-split-100-ref-hist.ppm"));
  }

  @Test
  public void sharpenImage() {
    controller.sharpenImage(true);
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-sharpen-split-100-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-sharpen-split-100-ref-hist.ppm"));
  }

  @Test
  public void getSharpenImageSplitView() {
    controller.sharpenImage(false);
    controller.getSplitView("60");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-sharpen-split-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-sharpen-split-ref-hist.ppm"));
  }

  @Test
  public void getSharpenImageSplitView0() {
    controller.sharpenImage(false);
    controller.getSplitView("0");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hist.ppm"));
  }

  @Test
  public void getSharpenImageSplitView100() {
    controller.sharpenImage(false);
    controller.getSplitView("100");
    assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-sharpen-split-100-ref.ppm"));
    assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-sharpen-split-100-ref-hist.ppm"));
  }

  @Test
  public void toSepia() {

    controller.toSepia(true);
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-sepia-split-100.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-sepia-hist.ppm"));
  }

  @Test
  public void getSepiaSplitView() {

    controller.toSepia(false);
    controller.getSplitView("60");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-sepia-split-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-sepia-split-60-hist.ppm"));
  }

  @Test
  public void getSepiaSplitView0() {

    controller.toSepia(false);
    controller.getSplitView("0");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hist-ref.ppm"));
  }

  @Test
  public void getsSepiaSplitView100() {

    controller.toSepia(false);
    controller.getSplitView("100");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-sepia-split-100.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-sepia-hist.ppm"));
  }

  @Test
  public void compress() {

    controller.compress("60");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-compress-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-compress-60-hist.ppm"));
  }

  @Test
  public void compress0() {

    controller.compress("0");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hist-ref.ppm"));
  }

  @Test
  public void compress100() {

    controller.compress("100");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/black.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/black-hist.ppm"));
  }

  @Test
  public void colorCorrect() {

    controller.colorCorrect(true);
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-color-correct-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-color-correct-hist.ppm"));
  }

  @Test
  public void getColorCorrectSplitView() {

    controller.colorCorrect(false);
    controller.getSplitView("60");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-color-correct-split-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-color-correct-split-60-hist.ppm"));
  }

  @Test
  public void getColorCorrectSplitView0() {

    controller.colorCorrect(false);
    controller.getSplitView("0");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hist-ref.ppm"));
  }

  @Test
  public void getColorCorrectSplitView100() {

    controller.colorCorrect(false);
    controller.getSplitView("100");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-color-correct-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-color-correct-hist.ppm"));
  }

  @Test
  public void levelsAdjust() {

    controller.levelsAdjust("71", "203", "235", true);
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-level-adjust-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-levels-adjust-hist.ppm"));
  }

  @Test
  public void getLevelsAdjustSplitView() {

    controller.levelsAdjust("71", "203", "235", false);
    controller.getSplitView("60");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-level-adjust-split-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-levels-adjust-split-60-hist.ppm"));
  }

  @Test
  public void getLevelsAdjustSplitView0() {

    controller.levelsAdjust("71", "203", "235", false);
    controller.getSplitView("0");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hist-ref.ppm"));
  }

  @Test
  public void getLevelsAdjustSplitView100() {

    controller.levelsAdjust("71", "203", "235", false);
    controller.getSplitView("100");
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-level-adjust-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-levels-adjust-hist.ppm"));
  }

  @Test
  public void revertToOriginal() {

    controller.compress("50");
    controller.revertToOriginal();
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-hist-ref.ppm"));
  }

  @Test
  public void undo() {

    controller.compress("60");
    controller.toSepia(true);
    controller.undo();
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.image,
            "test/images/16px-compress-ref.ppm"));
    Assert.assertTrue(isBufferedImageEqualToPPM(jFrameView.histogram,
            "test/images/16px-compress-60-hist.ppm"));
  }

  private boolean isBufferedImageEqual(BufferedImage image1, String filePath) {
    try {

      BufferedImage image2 = ImageIO.read(new File(filePath));

      // Check if the dimensions are the same
      if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
        return false;
      }

      // Compare the pixel values
      for (int x = 0; x < image1.getWidth(); x++) {
        for (int y = 0; y < image1.getHeight(); y++) {
          if (image1.getRGB(x,y) != image2.getRGB(x,y)) {
            return false;
          }
        }
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private boolean isBufferedImageEqualToPPM(BufferedImage image1, String filePath) {
    try {

      Image img1  = RGBImage.getLoader().loadFromBufferedImage(image1);
      Image img2 = RGBImage.getLoader().loadFromPPM(filePath);

      return IntegrationTests.checkImagesEqual(img1, img2);
    } catch (Exception e) {
      return false;
    }
  }


  static class MockView extends JFrameView {
    BufferedImage image;
    BufferedImage histogram;

    @Override
    public void displayImage(BufferedImage image, BufferedImage histogram) {
      this.image = image;
      this.histogram = histogram;
    }
  }
}
