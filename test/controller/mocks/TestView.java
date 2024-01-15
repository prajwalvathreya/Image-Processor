package controller.mocks;

import java.util.List;

import model.data.Image;
import view.IView;

/**
 * An interface to test view.
 */
public interface TestView extends IView {


  /**
   * Get the command typed by the user.
   *
   * @return List of command strings with parameters
   */
  List<String> getCommand();

  /**
   * Sets image in the panel.
   *
   * @param imageName         to be set to the panel.
   * @param toBeInsertedImage of images as input.
   */
  void setImageInPanel(String imageName, Image toBeInsertedImage);

  /**
   * Sets histogram in the panel.
   *
   * @param imageName         to be set to the panel.
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
   * Function to provide error message.
   *
   * @return error message
   */
  public String getError();


}
