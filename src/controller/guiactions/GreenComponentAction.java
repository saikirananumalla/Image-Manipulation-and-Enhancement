package controller.guiactions;

import controller.Features;

/**
 * Action class to get the green component of an image.
 */
public class GreenComponentAction implements GUIAction {
  @Override
  public void apply(Features features) {
    features.greenComponent();
  }
}
