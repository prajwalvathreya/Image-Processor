package controller;

import model.data.Image;

import java.util.List;

/**
 * This interface provides common functionality required for both GUI and CLI controllers.
 */
public interface IController {

  /**
   * This function processes the input commands from users in CLI and connects view, model and
   * controller in GUI.
   */
  void execute();

  /**
   * Calls the functions in image processing controller that perform operation on the image.
   *
   * @param command an array of command and parameters required by it
   * @return the operated image
   */
  Image runOperation(List<String> command);

}
