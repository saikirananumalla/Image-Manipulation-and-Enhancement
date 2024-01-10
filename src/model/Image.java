package model;

/**
 * Interface for the Image object type. Contains methods to do multiple types of transformations
 * resulting in new images. Methods include loading, saving, splitting into multiple components,
 * generating values such as brightness for the image, flipping, brightening and operations such
 * as converting to sepia.
 */
public interface Image {
  /**
   * Gets the height of the image. This is the number of pixels row-wise.
   *
   * @return height as int
   */
  int getHeight();

  /**
   * Gets the width of the image. This is the number of pixels column-wise.
   *
   * @return width as int
   */
  int getWidth();

  /**
   * Get pixel at the given grid location in the image.
   *
   * @param i row number
   * @param j column number
   * @return pixel
   */
  Pixel getPixel(int i, int j);

  /**
   * Extract the red component of the image. Will result in a new image object.
   *
   * @return The new image with the red component data.
   */
  Image redComponent();

  /**
   * Extract the green component of the image. Will result in a new image object.
   *
   * @return The new image with the green component data.
   */
  Image greenComponent();

  /**
   * Extract the blue component of the image. Will result in a new image object.
   *
   * @return The new image with the blue component data.
   */
  Image blueComponent();

  /**
   * Calculate the value component of the image. Will result in a new image object where every
   * pixel is the value result of that respective pixel in the original image.
   *
   * @return The new image with the value component data.
   */
  Image valueComponent();

  /**
   * Calculate the intensity component of the image. Will result in a new image object where every
   * pixel is the intensity result of that respective pixel in the original image.
   *
   * @return The new image with the intensity component data.
   */
  Image intensityComponent();

  /**
   * Calculate the luma component of the image. Will result in a new image object where every
   * pixel is the luma result of that respective pixel in the original image.
   *
   * @return The new image with the luma component data.
   */
  Image lumaComponent();

  /**
   * Flips the image horizontally. The resulting image has all the pixels which are to left, to
   * the right end. This will result in the horizontal flip. This method will give a new image as a
   * result.
   *
   * @return The new image which horizontally flipped with respect to the original image.
   */
  Image horizontalFlip();

  /**
   * Flips the image vertically. The resulting image has all the pixels which are to bottom, to the
   * top. This will result in the vertical flip. This method will give a new image as a
   * result.
   *
   * @return The new image which vertically flipped with respect to the original image.
   */
  Image verticalFlip();

  /**
   * Increase or decrease the brightness of an image. If the value provided is positive, this will
   * result in a brighter image. If the value provided is negative, this will result in a darkened
   * image.
   *
   * @param brightnessValue The value by which an image is to be brightened or darkened. Depends
   *                        on the sign of the value.
   * @return The new image which brightened or darkened appropriately.
   */
  Image brighten(int brightnessValue);

  /**
   * Method which blurs the image. This results in a new image which is blurred with respect to
   * the original image.
   *
   * @return The new image which would be blurred.
   */
  Image blur();

  /**
   * Method which sharpens the image. This results in a new image which is sharper with respect to
   * the original image.
   *
   * @return The new image which would be sharper.
   */
  Image sharpen();

  /**
   * Method which changes the tone of the image to sepia. Results in a new image which is the
   * sepia toned image with respect to the original image.
   *
   * @return The sepia toned image with respect to the first image.
   */
  Image toSepia();

  /**
   * Combine the three greyscale images into a single image that gets its red, green and blue
   * components from the three images respectively.
   *
   * @param redImage   greyscale image for red values
   * @param greenImage greyscale image for green values
   * @param blueImage  greyscale image for blue values
   * @return new image with combined values
   */
  Image rgbCombine(Image redImage, Image greenImage, Image blueImage);

  /**
   * Method to generate a new image which is the level adjusted image of the original image. Takes
   * in the values for the parameters b, m, w representing the black, mid and the white values.
   *
   * @param b The black value.
   * @param m The mid value.
   * @param w The white value.
   * @return The new image which is the level adjusted version of the base image.
   */
  Image levelsAdjust(int b, int m, int w);

  /**
   * Adjusts the peaks of r g b values in the histogram to coincide at same x-axis value by offset
   * -ting the pixel values of image based on the average calculated.
   *
   * @return The new compressed image.
   */
  Image colorCorrect();

  /**
   * Provides a vertical spliterator type functionality, the int split is a value between 0 and 100
   * where 0 means image argument passed, or 100 means this image. We can adjust that value to get
   * a split view of this image and the image passed as argument.
   *
   * @param image image to be added to the right using split percentage
   * @param split percentage of split space to be provided
   * @return new Image with split of two images
   */
  Image getSplitView(Image image, int split);
}
