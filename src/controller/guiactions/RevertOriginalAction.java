package controller.guiactions;

import controller.Features;

/**
 * Action class to revert all the edits done an image.
 */
public class RevertOriginalAction implements GUIAction {
  @Override
  public void apply(Features features) {
    features.revertToOriginal();
  }
}
