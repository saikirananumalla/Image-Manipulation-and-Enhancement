package controller.guiactions;

import javax.swing.JOptionPane;

import controller.Features;

/**
 * The action for color correcting an image. This action is used by the GUI view. This applies the
 * color correct effect on the image in focus.
 */
public class ColorCorrectAction implements GUIAction {

  private final boolean splitEnabled;

  /**
   * The flag to set the color correct as a split view or not.
   *
   * @param splitEnabled The toggle flag for the split view.
   */
  public ColorCorrectAction(boolean splitEnabled) {
    this.splitEnabled = splitEnabled;
  }

  @Override
  public void apply(Features features) {

    if (splitEnabled) {
      String split = JOptionPane.showInputDialog("Enter split view percentage");
      features.colorCorrect(false);
      features.getSplitView(split);
    } else {
      features.colorCorrect(true);
    }
  }
}
