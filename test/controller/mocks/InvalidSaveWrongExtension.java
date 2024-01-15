package controller.mocks;

import java.util.ArrayList;
import java.util.List;

import model.data.Image;
import view.JFrameView;

/**
 * Class to test invalid save where format is wrong.
 */
public class InvalidSaveWrongExtension extends JFrameView implements TestView {
  private List<String> commands = new ArrayList<>();
  private String error = "";

  /**
   * Gets the command and parameters entered by the user.
   *
   * @return a list of string values that includes the commands and the parameters
   */
  @Override
  public List<String> getCommand() {
    commands.add("save");
    commands.add("testNew.ppx");
    commands.add("test");
    return commands;
  }

  /**
   * Sets histogram in the panel.
   *
   * @param imageName         to be set to the panel.
   * @param toBeInsertedImage of images as input.
   */
  @Override
  public void setHistogramInPanel(String imageName, Image toBeInsertedImage) {
    //Do Nothing
  }

  /**
   * Displays the error message in case the user inputs wrong values or if there is some problem
   * in the execution.
   *
   * @param message error message to be displayed.
   */
  @Override
  public void showErrorMessage(String message) {
    error = message;
  }

  /**
   * Function to provide error message.
   *
   * @return error message
   */
  public String getError() {
    return error;
  }
}
