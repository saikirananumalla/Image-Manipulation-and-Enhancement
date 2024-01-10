package controller.guiactions;

import controller.Features;

/**
 * The action interface for the GUI view. This interface can be implemented by each "operation" and
 * the logic for that operation will be present in the apply method of the action. And the
 * implemented class should be present in the action listener method in the view to take effect.
 */
public interface GUIAction {

  /**
   * The apply method. This contains the logic to implement the specific operations such as sepia,
   * red component, etc.
   *
   * @param features The features object. This will be controller for the UI.
   */
  void apply(Features features);
}
