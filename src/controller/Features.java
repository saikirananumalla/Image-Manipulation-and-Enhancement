package controller;

/**
 * This class is for the features to be implemented by the gui controller. This class currently has
 * features such as load, save, color components, greyscale, flipping, blurring, sharpening,
 * sepia, compress, color correction, levels adjust.
 */
public interface Features {

  /**
   * Load an image from the given source path. The source path must be valid. The source image
   * then becomes the image in focus.
   *
   * @param srcPath The source path given by the user.
   */
  void load(String srcPath);

  /**
   * Save an image to the given target path. The target path must be valid.
   *
   * @param saveDestinationPath The target path given by the user.
   */
  void save(String saveDestinationPath);

  /**
   * Generate the red component of the image present in the focus. The resultant image will
   * be the Image in focus.
   */
  void redComponent();

  /**
   * Generate the green component of the image present in the focus. The resultant image will
   * be the Image in focus.
   */
  void greenComponent();

  /**
   * Generate the blue component of the image present in the focus. The resultant image will
   * be the Image in focus.
   */
  void blueComponent();

  /**
   * Generate the luma component of the image present in the focus. This is also the greyscale
   * version of the image in focus. The resultant image will be the Image in focus.
   */
  void lumaComponent(boolean doCommit);

  /**
   * Flips the image in focus horizontally. The resultant image will be the Image in focus.
   */
  void horizontalFlip();

  /**
   * Flips the image in focus vertically. The resultant image will be the Image in focus.
   */
  void verticalFlip();

  /**
   * Blur the image in focus. The resultant image will be the Image in focus.
   *
   * @param doCommit Flag to commit the resultant image to the queue or not. This is set to toggle
   *                 split preview mode. True for yes, False for no.
   */
  void blurImage(boolean doCommit);

  /**
   * Sharpen the image in focus. The resultant image will be the Image in focus.
   *
   * @param doCommit Flag to commit the resultant image to the queue or not. This is set to toggle
   *                 split preview mode. True for yes, False for no.
   */
  void sharpenImage(boolean doCommit);

  /**
   * Generate the sepia tone of the image in focus. The resultant image will be the Image in focus.
   *
   * @param doCommit Flag to commit the resultant image to the queue or not. This is set to toggle
   *                 split preview mode. True for yes, False for no.
   */
  void toSepia(boolean doCommit);

  /**
   * Compress the image in focus. The resultant image will be the Image in focus.
   *
   * @param percent The percentage to compress the image in focus. This number should be valid and
   *                must be between 0 and 100 (inclusive).
   */
  void compress(String percent);

  /**
   * Color correct the image in focus. This is done by using the histogram of the image in focus.
   * The resultant image will be the Image in focus.
   *
   * @param doCommit Flag to commit the resultant image to the queue or not. This is set to toggle
   *                 split preview mode. True for yes, False for no.
   */
  void colorCorrect(boolean doCommit);

  /**
   * Level adjust the image in focus. The resultant image will be the Image in focus.
   *
   * @param b        The black value for level adjusting.
   * @param m        The mid value for level adjusting.
   * @param w        The white value for level adjusting.
   * @param doCommit Flag to commit the resultant image to the queue or not. This is set to toggle
   *                 split preview mode. True for yes, False for no.
   */
  void levelsAdjust(String b, String m, String w, boolean doCommit);

  /**
   * Reverts the flow back to original. After many operations, if the user wishes to start over,
   * this is the quick option to use.
   */
  void revertToOriginal();

  /**
   * The method to generate split view images. This method does not store the resultant images in
   * the queue.
   *
   * @param split The split percentage. This value must be valid and must be between 0 and 100.
   */
  void getSplitView(String split);

  /**
   * This is the method to undo a committed image. Preview mode images are not committed, hence,
   * it is not possible to undo and view previous split images.
   */
  void undo();
}
