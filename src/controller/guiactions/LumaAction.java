package controller.guiactions;

import javax.swing.JOptionPane;

import controller.Features;

/**
 * The action for applying greyscale to an image. This action is used by the GUI view.
 * This applies the greyscale effect on the image in focus.
 */
public class LumaAction implements GUIAction {

  private final boolean splitEnabled;

  /**
   * The flag to set the luma component as a split view or not.
   *
   * @param splitEnabled The toggle flag for the split view.
   */
  public LumaAction(boolean splitEnabled) {
    this.splitEnabled = splitEnabled;
  }

  @Override
  public void apply(Features features) {

    if (splitEnabled) {
      String split = JOptionPane.showInputDialog("Enter split view percentage");
      features.lumaComponent(false);
      features.getSplitView(split);
    } else {
      features.lumaComponent(true);
    }
  }
}
