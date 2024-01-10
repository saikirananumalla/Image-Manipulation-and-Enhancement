package controller.guiactions;

import javax.swing.JOptionPane;

import controller.Features;

/**
 * The action for level adjusting an image. This action is used by the GUI view. This applies
 * the level adjustment effect on the image in focus.
 */
public class LevelsInputAction implements GUIAction {

  private final boolean splitEnabled;

  /**
   * The flag to set the levels adjust operation as a split view or not.
   *
   * @param splitEnabled The toggle flag for the split view.
   */
  public LevelsInputAction(boolean splitEnabled) {
    this.splitEnabled = splitEnabled;
  }

  @Override
  public void apply(Features features) {

    String b = JOptionPane.showInputDialog("Enter base value between 0-255");
    String m = JOptionPane.showInputDialog("Enter mid value between 0-255");
    String w = JOptionPane.showInputDialog("Enter high value between 0-255");
    if (splitEnabled) {
      String split = JOptionPane.showInputDialog("Enter split view percentage");
      features.levelsAdjust(b, m, w, false);
      features.getSplitView(split);
    } else {
      features.levelsAdjust(b, m, w, true);
    }
  }
}
