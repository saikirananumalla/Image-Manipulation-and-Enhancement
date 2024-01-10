package controller.guiactions;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;

/**
 * The action for loading an image. This action is used by the GUI view. This loads
 * the image in focus.
 */
public class FileOpenAction implements GUIAction {
  @Override
  public void apply(Features features) {
    final JFileChooser fChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG, PPM Images", "jpg", "png", "ppm");

    fChooser.setAcceptAllFileFilterUsed(false);
    fChooser.setFileFilter(filter);

    int retValue = fChooser.showOpenDialog(null);
    if (retValue == JFileChooser.APPROVE_OPTION) {

      File f = fChooser.getSelectedFile();

      features.load(f.getAbsolutePath());
    }
  }
}
