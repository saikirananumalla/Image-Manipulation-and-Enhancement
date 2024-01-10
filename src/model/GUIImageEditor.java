package model;

import java.awt.image.BufferedImage;

/**
 * This is the interface which will be connected to the GUI controller class. As this interface is
 * extending the image editor interface, it has the existing methods for the image operations
 * which needs to be supported by the UI. This class also has additional functionality
 * getBufferedImage, copy an image which are necessary for the GUI controller.
 */
public interface GUIImageEditor extends ImageEditor {

  /**
   * Given an Image object, get the corresponding BufferedImage of the input.
   *
   * @param image The input image.
   * @return The corresponding Buffered Image.
   */
  BufferedImage getBufferedImage(Image image);

  /**
   * Copies the contents of the input image and generates a new image.
   *
   * @param image The input Image.
   * @return The copied image.
   */
  Image getCopy(Image image);
}
