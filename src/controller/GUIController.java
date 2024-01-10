package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import model.GUIImageEditor;
import model.Image;
import view.JFrameView;

/**
 * The class which acts as the controller for the GUI part of the Image editor. This controller
 * maintains a deque to support the undo and the revert to original operations. Other operations
 * which are supported are the load, save, color components, greyscale, flipping, blurring,
 * sharpening, sepia, compress, color correction, levels adjust methods. The preview mode images
 * (Split view images) are not stored in the queue and are for viewing purposes only.
 */
public class GUIController extends AbstractController implements Features {
  private Image original;
  private Image image;
  private Image previewImage;
  private Deque<Image> imageDeque;
  private final JFrameView view;
  private final GUIImageEditor editor;
  private boolean isImageLoaded;


  /**
   * Constructor for the controller. Takes an GUI editor and a  JFrameView as inputs. This
   * constructor also initialises the required images in focus and starts up the queue.
   *
   * @param editor The GUI editor object. Has the functionality to implement the operations on the
   *               images.
   * @param view   The JFrameView object. This the UI of the Image Editor application.
   */
  public GUIController(GUIImageEditor editor, JFrameView view) {

    if (view == null || editor == null) {
      throw new IllegalArgumentException("null not allowed");
    }

    this.editor = editor;
    this.view = view;
    view.setUpButtonsAndListener(new GUIActionListener(this));
    original = null;
    image = null;
    isImageLoaded = false;
    previewImage = null;
    refreshQueue();
  }

  @Override
  public void load(String srcPath) {

    if (inValidFile(srcPath)) {
      view.displayFileError();
      return;
    }

    try {
      original = editor.load(srcPath);
    } catch (IOException e) {
      view.displayFileError();
      return;
    }
    image = editor.getCopy(original);
    refreshQueue();
    displayAndCommitImage();
    isImageLoaded = true;
  }

  @Override
  public void save(String saveDestinationPath) {
    if (checkIsImageLoaded()) {
      return;
    }
    if (inValidExtension(saveDestinationPath)) {
      view.displayFileError();
      return;
    }

    try {
      saveByExtension(image, saveDestinationPath, editor);
    } catch (IOException e) {
      view.displayFileError();
    }
  }


  @Override
  public void redComponent() {
    if (checkIsImageLoaded()) {
      return;
    }
    image = editor.redComponent(image);
    displayAndCommitImage();
  }

  @Override
  public void greenComponent() {
    if (checkIsImageLoaded()) {
      return;
    }
    image = editor.greenComponent(image);
    displayAndCommitImage();
  }

  @Override
  public void blueComponent() {
    if (checkIsImageLoaded()) {
      return;
    }
    image = editor.blueComponent(image);
    displayAndCommitImage();
  }

  @Override
  public void lumaComponent(boolean doCommit) {
    if (checkIsImageLoaded()) {
      return;
    }
    if (!doCommit) {
      previewImage = editor.lumaComponent(image);
      return;
    }
    image = editor.lumaComponent(image);
    displayAndCommitImage();
  }

  @Override
  public void horizontalFlip() {
    if (checkIsImageLoaded()) {
      return;
    }
    image = editor.horizontalFlip(image);
    displayAndCommitImage();
  }

  @Override
  public void verticalFlip() {
    if (checkIsImageLoaded()) {
      return;
    }
    image = editor.verticalFlip(image);
    displayAndCommitImage();
  }

  @Override
  public void blurImage(boolean doCommit) {
    if (checkIsImageLoaded()) {
      return;
    }
    if (!doCommit) {
      previewImage = editor.blur(image);
      return;
    }
    image = editor.blur(image);
    displayAndCommitImage();
  }

  @Override
  public void sharpenImage(boolean doCommit) {
    if (checkIsImageLoaded()) {
      return;
    }
    if (!doCommit) {
      previewImage = editor.sharpen(image);
      return;
    }
    image = editor.sharpen(image);
    displayAndCommitImage();
  }

  @Override
  public void toSepia(boolean doCommit) {
    if (checkIsImageLoaded()) {
      return;
    }

    if (!doCommit) {
      previewImage = editor.toSepia(image);
      return;
    }

    image = editor.toSepia(image);
    displayAndCommitImage();
  }

  @Override
  public void compress(String percent) {
    if (checkIsImageLoaded()) {
      return;
    }

    if (!isValidPercentage(percent)) {
      view.displayError();
      return;
    }

    image = editor.compress(image, Integer.parseInt(percent));
    displayAndCommitImage();
  }

  @Override
  public void colorCorrect(boolean doCommit) {
    if (checkIsImageLoaded()) {
      return;
    }
    if (!doCommit) {
      previewImage = editor.colorCorrect(image);
      return;
    }
    image = editor.colorCorrect(image);
    displayAndCommitImage();
  }

  @Override
  public void levelsAdjust(String b, String m, String w, boolean doCommit) {

    if (checkIsImageLoaded()) {
      return;
    }

    if (!isPixel(b) || !isPixel(m) || !isPixel(w) || isNotAscending(b, m, w)) {
      view.displayError();
      return;
    }

    if (!doCommit) {
      previewImage = editor.levelsAdjust(image, Integer.parseInt(b),
              Integer.parseInt(m), Integer.parseInt(w));
      return;
    }

    image = editor.levelsAdjust(image, Integer.parseInt(b),
            Integer.parseInt(m), Integer.parseInt(w));
    displayAndCommitImage();
  }

  @Override
  public void revertToOriginal() {
    if (checkIsImageLoaded()) {
      return;
    }

    image = editor.getCopy(original);
    refreshQueue();
    displayAndCommitImage();
  }

  @Override
  public void getSplitView(String split) {

    if (!isValidPercentage(split)) {
      view.displayError();
      return;
    }

    if (checkIsImageLoaded()) {
      return;
    }

    Image imageLast = imageDeque.peekLast();
    Image splitImage = editor.getSplitView(imageLast, previewImage,
            Integer.parseInt(split));
    view.displayImage(getBufferedImage(splitImage),
            getBufferedImage(editor.getHistogram(splitImage)));
  }

  @Override
  public void undo() {

    if (checkIsImageLoaded()) {
      return;
    }
    imageDeque.pollLast();
    image = imageDeque.peekLast();
    if (image == null) {
      image = original;
    }
    displayImage(image);
  }

  private boolean checkIsImageLoaded() {
    if (!isImageLoaded) {
      view.displayLoadImage();
      return true;
    }
    return false;
  }

  private boolean inValidFile(String saveDestinationPath) {
    File file = new File(saveDestinationPath);
    if (!file.exists()) {
      view.displayFileError();
      return true;
    }
    return false;
  }

  private BufferedImage getBufferedImage(Image image) {
    return editor.getBufferedImage(image);
  }

  private void displayImage(Image displayImage) {
    view.displayImage(getBufferedImage(displayImage),
            getBufferedImage(editor.getHistogram(displayImage)));
  }

  private void displayAndCommitImage() {
    imageDeque.addLast(image);
    displayImage(image);
  }

  private void refreshQueue() {
    imageDeque = new ArrayDeque<>();
  }

}
