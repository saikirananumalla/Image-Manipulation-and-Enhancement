package controller.guiactions;

import javax.swing.JOptionPane;

import controller.Features;

/**
 * The action for sharpening an image. This action is used by the GUI view. This applies the sharpen
 * effect on the image in focus.
 */
public class SharpenAction implements GUIAction {

  private final boolean splitEnabled;

  /**
   * The flag to set the sharpen action as a split view or not.
   *
   * @param splitEnabled The toggle flag for the split view.
   */
  public SharpenAction(boolean splitEnabled) {
    this.splitEnabled = splitEnabled;
  }

  @Override
  public void apply(Features features) {

    if (splitEnabled) {
      String split = JOptionPane.showInputDialog("Enter split view percentage");
      features.sharpenImage(false);
      features.getSplitView(split);
    } else {
      features.sharpenImage(true);
    }
  }
}
