package controller.guiactions;

import controller.Features;

/**
 * Action class to get the red component of an image.
 */
public class RedComponentAction implements GUIAction {
  @Override
  public void apply(Features features) {
    features.redComponent();
  }
}
