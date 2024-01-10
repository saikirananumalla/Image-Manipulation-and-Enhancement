package controller.guiactions;

import javax.swing.JOptionPane;

import controller.Features;

/**
 * The action for blurring an image. This action is used by the GUI view. This applies the blur
 * effect on the image in focus.
 */
public class BlurAction implements GUIAction {

  private final boolean splitEnabled;

  /**
   * The flag to set the blur as a split view or not.
   *
   * @param splitEnabled The toggle flag for the split view.
   */
  public BlurAction(boolean splitEnabled) {
    this.splitEnabled = splitEnabled;
  }

  @Override
  public void apply(Features features) {

    if (splitEnabled) {
      String split = JOptionPane.showInputDialog("Enter split view percentage");
      features.blurImage(false);
      features.getSplitView(split);
    } else {
      features.blurImage(true);
    }

  }
}
