package controller.guiactions;

import controller.Features;

/**
 * Action class to flip an image vertically.
 */
public class VerticalFlipAction implements GUIAction {

  @Override
  public void apply(Features features) {
    features.verticalFlip();
  }
}
