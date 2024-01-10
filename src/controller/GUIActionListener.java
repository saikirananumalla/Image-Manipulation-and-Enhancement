package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.guiactions.BlueComponentAction;
import controller.guiactions.BlurAction;
import controller.guiactions.ColorCorrectAction;
import controller.guiactions.CompressAction;
import controller.guiactions.FileOpenAction;
import controller.guiactions.FileSaveAction;
import controller.guiactions.GUIAction;
import controller.guiactions.GreenComponentAction;
import controller.guiactions.HorizontalFlipAction;
import controller.guiactions.LevelsInputAction;
import controller.guiactions.LumaAction;
import controller.guiactions.RedComponentAction;
import controller.guiactions.RevertOriginalAction;
import controller.guiactions.SepiaAction;
import controller.guiactions.SharpenAction;
import controller.guiactions.UndoEditAction;
import controller.guiactions.VerticalFlipAction;
import util.ButtonConfiguration;

/**
 * Action listener class for GUI View which handles all the actions for the buttons given to the
 * jFrame view by the controller.
 */
public class GUIActionListener implements ActionListener {

  private final Features features;
  private boolean levelAdjustSplitEnabled;
  private boolean greyScaleSplitEnabled;
  private boolean blurSplitEnabled;
  private boolean sharpenSplitEnabled;
  private boolean colorCorrectSplitEnabled;
  private boolean sepiaSplitEnabled;

  /**
   * Create a new GUIActionListener class with the features interface, passed by the controller.
   *
   * @param features Interface for all the callback methods of controller
   */
  public GUIActionListener(Features features) {
    this.features = features;
  }

  /**
   * This is the method where the buttons lead to. Each button corresponds to a switch case and the
   * corresponding operations are run.
   *
   * @param event the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent event) {

    GUIAction action = null;

    switch (event.getActionCommand()) {

      case "Open file":
        action = new FileOpenAction();
        break;
      case "Luma Component":
        action = new LumaAction(greyScaleSplitEnabled);
        break;
      case "Levels Input":
        action = new LevelsInputAction(levelAdjustSplitEnabled);
        break;
      case "Color Correct":
        action = new ColorCorrectAction(colorCorrectSplitEnabled);
        break;
      case "Sepia":
        action = new SepiaAction(sepiaSplitEnabled);
        break;
      case "Blur":
        action = new BlurAction(blurSplitEnabled);
        break;
      case "Sharpen":
        action = new SharpenAction(sharpenSplitEnabled);
        break;
      case "Red Component":
        action = new RedComponentAction();
        break;
      case "Green Component":
        action = new GreenComponentAction();
        break;
      case "Blue Component":
        action = new BlueComponentAction();
        break;
      case "Compress":
        action = new CompressAction();
        break;
      case "Vertical Flip":
        action = new VerticalFlipAction();
        break;
      case "Horizontal Flip":
        action = new HorizontalFlipAction();
        break;
      case "Revert":
        action = new RevertOriginalAction();
        break;
      case "Undo":
        action = new UndoEditAction();
        break;
      case "Save File":
        action = new FileSaveAction();
        break;
      case "Split Levels Adjust":
        levelAdjustSplitEnabled = toggleButton(levelAdjustSplitEnabled);
        break;
      case "Split Greyscale":
        greyScaleSplitEnabled = toggleButton(greyScaleSplitEnabled);
        break;
      case "Split Blur Image":
        blurSplitEnabled = toggleButton(blurSplitEnabled);
        break;
      case "Split Sharpen Image":
        sharpenSplitEnabled = toggleButton(sharpenSplitEnabled);
        break;
      case "Split Color Correct":
        colorCorrectSplitEnabled = toggleButton(colorCorrectSplitEnabled);
        break;
      case "Split Sepia":
        sepiaSplitEnabled = toggleButton(sepiaSplitEnabled);
        break;
      case "Exit":
        promptBeforeExit();
        break;
      default:
        break;
    }
    if (action != null) {
      action.apply(features);
    }
  }

  /**
   * Exit button prompt, ask the user if he wants to save the image on screen before exit.
   */
  public void promptBeforeExit() {
    int option = JOptionPane.showConfirmDialog(
            null,
            "Do you want to save the image before exiting?",
            "Exit Confirmation",
            JOptionPane.YES_NO_CANCEL_OPTION);

    if (option == JOptionPane.YES_OPTION) {
      new FileSaveAction().apply(features);
      System.exit(0);
    } else if (option == JOptionPane.NO_OPTION) {
      System.exit(0);
    }
  }

  /**
   * This method adds all the buttons necessary for the application.
   * @return The List of button configurations necessary for the application.
   */
  public List<ButtonConfiguration> getButtons() {

    List<ButtonConfiguration> buttons = new ArrayList<>();

    buttons.add(new ButtonConfiguration("Upload Image", "Open file",
            false));

    buttons.add(new ButtonConfiguration("Undo Edit", "Undo",
            false));

    buttons.add(new ButtonConfiguration("Revert to Original", "Revert",
            false));

    buttons.add(new ButtonConfiguration("Save Image", "Save File",
            false));

    buttons.add(new ButtonConfiguration("Compress Image", "Compress",
            false));

    buttons.add(new ButtonConfiguration("Red Component", "Red Component",
            false));

    buttons.add(new ButtonConfiguration("Green Component",
            "Green Component", false));

    buttons.add(new ButtonConfiguration("Blue Component",
            "Blue Component", false));

    buttons.add(new ButtonConfiguration("Vertical Flip", "Vertical Flip",
            false));

    buttons.add(new ButtonConfiguration("Horizontal Flip",
            "Horizontal Flip", false));

    buttons.add(new ButtonConfiguration("Levels Adjust", "Levels Input",
            true));

    buttons.add(new ButtonConfiguration("Greyscale", "Luma Component",
            true));

    buttons.add(new ButtonConfiguration("Blur Image", "Blur",
            true));

    buttons.add(new ButtonConfiguration("Sharpen Image", "Sharpen",
            true));

    buttons.add(new ButtonConfiguration("Color Correct", "Color Correct",
            true));

    buttons.add(new ButtonConfiguration("Sepia", "Sepia",
            true));

    return buttons;
  }

  private boolean toggleButton(boolean splitEnabled) {
    return !splitEnabled;
  }
}
