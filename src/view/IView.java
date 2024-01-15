package view;

import model.data.Image;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * An interface that would contain methods for the view.
 */
public interface IView {

  /**
   * Sets commands and parameters.
   * @param guiSupportedCommands supported commands by gui
   * @param commandSyntax command syntax
   * @param splitModeSupportedCommands operations supported in split
   */
  void setCommandsAndParameters(List<String> guiSupportedCommands,
                                Map<String, String> commandSyntax,
                                List<String> splitModeSupportedCommands);

  /**
   * Make the view visible, This is usually called after the view is constructed.
   */
  void makeVisible();


  /**
   * Get the command typed by the user.
   *
   * @return List of command strings with parameters
   */
  List<String> getCommand();

  /**
   * Sets even listener for execute button on which the controller performs the operation.
   * @param actionEvent controller class that handles the event
   */
  void setCommandButtonListener(ActionListener actionEvent);

  /**
   * Sets image in the panel.
   *
   * @param imageName     to be set to the panel.
   * @param toBeInsertedImage of images as input.
   */
  void setImageInPanel(String imageName, Image toBeInsertedImage);

  /**
   * Sets histogram in the panel.
   *
   * @param imageName     to be set to the panel.
   * @param toBeInsertedImage of images as input.
   */
  void setHistogramInPanel(String imageName, Image toBeInsertedImage);

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param message file extension passed by user.
   */
  void showErrorMessage(String message);

  /**
   * Signal the view to draw itself.
   */
  void refresh();
}