package controller.guiactions;

import java.io.File;

import javax.swing.JFileChooser;

import controller.Features;

/**
 * The action for saving an image. This action is used by the GUI view. This saves
 * the image in focus.
 */
public class FileSaveAction implements GUIAction {
  @Override
  public void apply(Features features) {
    final JFileChooser fChooser = new JFileChooser(".");
    int retValue = fChooser.showSaveDialog(null);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      features.save(f.getAbsolutePath());
    }
  }
}
