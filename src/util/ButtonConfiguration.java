package util;

/**
 * A button configuration util class to store the name, action command and is split preview enabled
 * for a given button to be added to view. We only provide getters because this read only for any
 * view.
 */
public class ButtonConfiguration {
  private final String ButtonName;
  private final String ButtonCommand;
  private final Boolean splitPreview;

  /**
   * Create a new button config using given name command and split preview.
   *
   * @param buttonName    name to be displayed on the button.
   * @param buttonCommand action command evoked on clicking this button.
   * @param splitPreview  is split preview mode supported.
   */
  public ButtonConfiguration(String buttonName, String buttonCommand, Boolean splitPreview) {
    ButtonName = buttonName;
    ButtonCommand = buttonCommand;
    this.splitPreview = splitPreview;
  }

  /**
   * Get the name of the button.
   *
   * @return button name.
   */
  public String getButtonName() {
    return ButtonName;
  }

  /**
   * Get the action command of a button.
   *
   * @return action command as string.
   */
  public String getButtonCommand() {
    return ButtonCommand;
  }

  /**
   * Get is preview supported boolean.
   *
   * @return true is supported and vice-versa.
   */
  public Boolean getSplitPreview() {
    return splitPreview;
  }
}
