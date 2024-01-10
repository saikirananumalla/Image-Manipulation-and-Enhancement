package controller;

import java.io.IOException;

import model.Image;
import model.ImageEditor;

import static util.Constants.JPG;
import static util.Constants.PNG;
import static util.Constants.PPM;

/**
 * Abstract controller class for handling the image operations. This specific abstract class has
 * all the common helper methods like save, valid file, validate strings, etc. that are required for
 * all the controller classes that extend this.
 */
public abstract class AbstractController {

  /**
   * The helper method to save an Image object to file and at the path given by the user.
   *
   * @param savedImage          The image which should be saved in file.
   * @param saveDestinationPath The path where the file must be saved.
   */
  protected void saveByExtension(Image savedImage, String saveDestinationPath, ImageEditor editor)
          throws IOException {
    int lastDot = saveDestinationPath.lastIndexOf('.');

    String fileExtension = saveDestinationPath.substring(lastDot + 1);

    switch (fileExtension) {
      case PPM:
        editor.saveAsPPM(savedImage, saveDestinationPath);
        break;
      case JPG:
        editor.saveAsJPG(savedImage, saveDestinationPath);
        break;
      default:
        editor.saveAsPNG(savedImage, saveDestinationPath);
    }
  }

  /**
   * Helper method to check whether a filepath has a valid extension related to images.
   *
   * @param filePath The file path given by the view.
   * @return The boolean result determining the validity of the image file. Returns false if the
   *         image is valid, true if the image name is invalid.
   */
  protected boolean inValidExtension(String filePath) {
    int lastDot = filePath.lastIndexOf('.');

    if (lastDot > 0 && lastDot < filePath.length() - 1) {
      // Extract the file extension
      String ext = filePath.substring(lastDot + 1);

      return !ext.equals(PPM) && !ext.equals(PNG) && !ext.equals(JPG);

    }
    return true;
  }

  /**
   * Check if a, b, c values are ascending in order.
   *
   * @param a The first value.
   * @param b The second value.
   * @param c The third value.
   * @return The boolean result. True if ascending. False if not.
   */
  protected boolean isNotAscending(String a, String b, String c) {
    if (!isInteger(a) || !isInteger(b) || !isInteger(c)) {
      return true;
    }

    return !(Integer.parseInt(a) < Integer.parseInt(b) &&
            Integer.parseInt(b) < Integer.parseInt(c));
  }

  /**
   * Helper method to check whether a string is a valid integer.
   *
   * @param str The input string.
   * @return The boolean result. True is yes, else false.
   */
  protected boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Check whether the string is a pixel or no. Valid value between 0 and 255.
   *
   * @param str The string input.
   * @return The boolean result. True if the pixel is valid. False if not.
   */
  protected boolean isPixel(String str) {
    if (inValidString(str)) {

      return false;
    }
    return isInteger(str) && Integer.parseInt(str) >= 0 && Integer.parseInt(str) <= 255;
  }

  /**
   * Check if the percentage string is valid i.e. between 0 and 100.
   *
   * @param str The percentage string.
   * @return The boolean result. True if the percentage string is valid. False if not.
   */
  protected boolean isValidPercentage(String str) {
    if (inValidString(str)) {

      return false;
    }
    return isInteger(str) && Integer.parseInt(str) >= 0 && Integer.parseInt(str) <= 100;
  }


  private boolean inValidString(String str) {
    return str == null || str.isEmpty();
  }

}
