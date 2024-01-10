package model;

import java.io.IOException;

/**
 * An interface to perform operations on the Image like loading, saving, compressing and editing,
 * this is the front face of our model to the controller. The controller is passed an object of the
 * editor which is responsible for all operations to be done on the image model.
 */
public interface ImageEditor {
  /**
   * Method to load image data from a specified file location.
   *
   * @param srcPath source path of the image
   * @return Image model
   */
  Image load(String srcPath) throws IOException;

  /**
   * Saves provided image as a png file.
   *
   * @param image           image to be saved
   * @param destinationPath destination location path
   */
  void saveAsPNG(Image image, String destinationPath) throws IOException;

  /**
   * Saves provided image as a jpg file.
   *
   * @param image           image to be saved
   * @param destinationPath destination location path
   */
  void saveAsJPG(Image image, String destinationPath) throws IOException;

  /**
   * Saves provided image as a ppm file.
   *
   * @param image           image to be saved
   * @param destinationPath destination location path
   */
  void saveAsPPM(Image image, String destinationPath) throws IOException;

  /**
   * Gets red component of given image.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image redComponent(Image image);

  /**
   * Gets green component of given image.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image greenComponent(Image image);

  /**
   * Gets blue component of given image.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image blueComponent(Image image);

  /**
   * Gets value component of given image.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image valueComponent(Image image);

  /**
   * Gets intensity component of given image.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image intensityComponent(Image image);

  /**
   * Gets luma component of given image.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image lumaComponent(Image image);

  /**
   * Flip the given image horizontally.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image horizontalFlip(Image image);


  /**
   * Flip the given image vertically.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image verticalFlip(Image image);

  /**
   * Brighten or darken the given image by provided int value.
   *
   * @param image           Image to process
   * @param brightnessValue value to be added to component values of a pixel
   * @return processed Image result
   */
  Image brighten(Image image, int brightnessValue);

  /**
   * Blur the image.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image blur(Image image);

  /**
   * Sharpen the image.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image sharpen(Image image);

  /**
   * Convert the image to sepia tone.
   *
   * @param image Image to process
   * @return processed Image result
   */
  Image toSepia(Image image);

  /**
   * Combine the given three images, extract r g b values and add it to the result image.
   *
   * @param redImage   Image to get red component from
   * @param greenImage Image to get green component from
   * @param blueImage  Image to get blue component from
   * @return processed Image result
   */
  Image rgbCombine(Image redImage, Image greenImage, Image blueImage);

  /**
   * Gets the rgb value histogram for a given image.
   *
   * @param image Image to process
   * @return histogram as an image
   */
  Image getHistogram(Image image);

  /**
   * Method to generate a new image which is the level adjusted image of the original image. Takes
   * in the values for the parameters b, m, w representing the black, mid and the white values.
   *
   * @param image The base image which should be colour corrected.
   * @param b     The black value.
   * @param m     The mid value.
   * @param w     The white value.
   * @return The new image which is the level adjusted version of the base image.
   */
  Image levelsAdjust(Image image, int b, int m, int w);

  /**
   * Compresses the image and generates a new image. Takes in the image object and the compression
   * ratio.
   *
   * @param image   The base image.
   * @param percent The percentage by which the image should be compressed by.
   * @return The new compressed image.
   */
  Image compress(Image image, int percent);


  /**
   * Adjusts the peaks of r g b values in the histogram to coincide at same x-axis value by offset
   * -ting the pixel values of image based on the average calculated.
   *
   * @param image The base image.
   * @return The new compressed image.
   */
  Image colorCorrect(Image image);

  Image getSplitView(Image image1, Image image2, int percent);

}
