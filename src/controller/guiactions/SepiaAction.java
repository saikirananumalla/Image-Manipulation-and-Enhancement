package controller.guiactions;

import javax.swing.JOptionPane;

import controller.Features;

/**
 * The action for applying sepia tone an image. This action is used by the GUI view.
 * This applies the sepia effect on the image in focus.
 */
public class SepiaAction implements GUIAction {

  private final boolean splitEnabled;

  /**
   * The flag to set the sepia action as a split view or not.
   *
   * @param splitEnabled The toggle flag for the split view.
   */
  public SepiaAction(boolean splitEnabled) {
    this.splitEnabled = splitEnabled;
  }

  @Override
  public void apply(Features features) {
    if (splitEnabled) {
      String split = JOptionPane.showInputDialog("Enter split view percentage");
      features.toSepia(false);
      features.getSplitView(split);
    } else {
      features.toSepia(true);
    }
  }
}
