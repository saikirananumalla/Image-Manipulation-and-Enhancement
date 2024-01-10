package controller.guiactions;

import controller.Features;

/**
 * Action class to get the blue component of an image.
 */
public class BlueComponentAction implements GUIAction {
  @Override
  public void apply(Features features) {
    features.blueComponent();
  }
}
