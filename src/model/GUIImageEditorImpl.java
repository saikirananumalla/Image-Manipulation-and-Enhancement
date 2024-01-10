package model;

import java.awt.image.BufferedImage;

/**
 * This is the editor class which will be connected to the GUI controller class. As this class is
 * extending the image editor class, it has the existing implementations for the image operations
 * which needs to be supported by the UI. This class also has additional functionality which
 * are necessary for the GUI controller.
 */
public class GUIImageEditorImpl extends ImageEditorImpl implements GUIImageEditor {

  @Override
  public BufferedImage getBufferedImage(Image image) {
    return this.getBufferedImageUtil(image);
  }

  @Override
  public Image getCopy(Image image) {
    return RGBImage.getLoader().loadFromBufferedImage(getBufferedImageUtil(image));
  }
}
