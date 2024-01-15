package controller;

import model.data.Image;
import view.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This controller connects the view with the model.
 */
public class GUIController extends AbstractIController implements ActionListener {

  private final IView view;
  private final List<String> guiSupportedCommands;
  private final List<String> splitModeSupportedCommands;

  /**
   * Constructs controller to connect view and model.
   * @param view view tht generates the GUI
   */
  public GUIController(IView view) {
    super();
    this.view = view;
    // TODO Check supported operations
    this.guiSupportedCommands = List.of("load", "save", "red-component",
            "green-component", "blue-component", "vertical-flip", "horizontal-flip", "blur",
            "sharpen", "luma-component", "sepia", "compress", "color-correct", "level-adjust");
    this.splitModeSupportedCommands = List.of("blur", "sharpen", "luma-component", "sepia",
            "color-correct", "level-adjust");
  }

  /**
   * This function connects view, model and controller.
   */
  @Override
  public void execute() {
    this.view.setCommandsAndParameters(
            this.guiSupportedCommands, commandSyntax, this.splitModeSupportedCommands);
    this.view.setCommandButtonListener(this);
    this.view.makeVisible();
  }

  /**
   * When execute event is called this functions processes that and calls the function to perform
   * the operation on the image.
   *
   * @param event the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    List<String> command = view.getCommand();
    int imageNameIndex = command.contains("split") ? command.size() - 3 : command.size() - 1;
    String imageName = command.get(imageNameIndex);
    String histogramImageName = "histogram-" + imageName;
    try {
      if (!this.guiSupportedCommands.contains(command.get(0))) {
        throw new IllegalArgumentException("Unknown command: " + command);
      }
      Image img = this.runOperation(command);
      Image histogramImg = imageProcessingController.generateHistogram(
              List.of(imageName, histogramImageName));
      view.setImageInPanel(imageName, img);
      view.setHistogramInPanel(imageName, histogramImg);
    } catch (Exception ex) {
      view.showErrorMessage(ex.getMessage());
    }
  }
}
