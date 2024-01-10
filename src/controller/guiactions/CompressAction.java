package controller.guiactions;

import javax.swing.JOptionPane;

import controller.Features;

/**
 * Action class to get the compressed version of an image.
 */
public class CompressAction implements GUIAction {

  @Override
  public void apply(Features features) {
    features.compress(JOptionPane.showInputDialog("Enter compression percentage"));
  }
}
