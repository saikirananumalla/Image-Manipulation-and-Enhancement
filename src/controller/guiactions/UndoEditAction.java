package controller.guiactions;

import controller.Features;

/**
 * Action class to undo last edit done on an image.
 */
public class UndoEditAction implements GUIAction {
  @Override
  public void apply(Features features) {
    features.undo();
  }
}
