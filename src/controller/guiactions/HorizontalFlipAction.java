package controller.guiactions;

import controller.Features;

/**
 * Action class to flip an image horizontally.
 */
public class HorizontalFlipAction implements GUIAction {
  @Override
  public void apply(Features features) {
    features.horizontalFlip();
  }
}
