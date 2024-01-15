package controller;

import model.data.Image;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * This class provide common functionality required for both GUI and CLI controllers.
 */
public abstract class AbstractIController implements IController {

  protected final Map<String, Integer> commandParameters = new HashMap<>();
  protected final Map<String, Function<List<String>, Image>> supportedCommands = new HashMap<>();
  protected final Map<String, String> commandSyntax = new LinkedHashMap<>();
  protected final ImageProcessingController imageProcessingController;

  /**
   * This function processes the input commands from users in CLI and connects view, model and
   * controller in GUI.
   */
  @Override
  public abstract void execute();

  /**
   * Constructs the controller that connects model and view
   * It assigns value that is supported by the program.
   */
  public AbstractIController() {
    imageProcessingController = new ImageProcessingController();

    commandParameters.put("load", 2);
    commandParameters.put("save", 2);
    commandParameters.put("vertical-flip", 2);
    commandParameters.put("horizontal-flip", 2);
    commandParameters.put("value-component", 2);
    commandParameters.put("luma-component", 2);
    commandParameters.put("intensity-component", 2);
    commandParameters.put("red-component", 2);
    commandParameters.put("green-component", 2);
    commandParameters.put("blue-component", 2);
    commandParameters.put("blur", 2);
    commandParameters.put("sharpen", 2);
    commandParameters.put("sepia", 2);
    commandParameters.put("greyscale", 2);
    commandParameters.put("histogram", 2);
    commandParameters.put("brighten", 3);
    commandParameters.put("compress", 3);
    commandParameters.put("rgb-split", 4);
    commandParameters.put("rgb-combine", 4);
    commandParameters.put("level-adjust", 5);
    commandParameters.put("color-correct", 2);
    commandParameters.put("run", 1);

    supportedCommands.put("load", imageProcessingController::loadImage);
    supportedCommands.put("save", imageProcessingController::saveImage);
    supportedCommands.put("red-component", imageProcessingController::createRedImage);
    supportedCommands.put("green-component", imageProcessingController::createGreenImage);
    supportedCommands.put("blue-component", imageProcessingController::createBlueImage);
    supportedCommands.put("value-component", imageProcessingController::getValueImage);
    supportedCommands.put("luma-component", imageProcessingController::getLumaImage);
    supportedCommands.put("intensity-component", imageProcessingController::getIntensityImage);
    supportedCommands.put("horizontal-flip", imageProcessingController::flipImageHorizontal);
    supportedCommands.put("vertical-flip", imageProcessingController::flipImageVertical);
    supportedCommands.put("blur", imageProcessingController::blurImage);
    supportedCommands.put("sharpen", imageProcessingController::sharpenImage);
    supportedCommands.put("sepia", imageProcessingController::toSepiaImage);
    supportedCommands.put("greyscale", imageProcessingController::toGreyScale);
    supportedCommands.put("brighten", imageProcessingController::brightenImage);
    supportedCommands.put("rgb-split", imageProcessingController::splitIntoRGBImage);
    supportedCommands.put("rgb-combine", imageProcessingController::combineRGBImageIntoSingle);
    supportedCommands.put("compress", imageProcessingController::compressImage);
    supportedCommands.put("histogram", imageProcessingController::generateHistogram);
    supportedCommands.put("level-adjust", imageProcessingController::leveAdjuster);
    supportedCommands.put("color-correct", imageProcessingController::colourCorrection);

    commandSyntax.put("load", "srcImageName.format destinationImageName");
    commandSyntax.put("save", "destinationImageName.format srcImageName");
    commandSyntax.put("vertical-flip", "srcImageName destinationImageName");
    commandSyntax.put("horizontal-flip", "srcImageName destinationImageName");
    commandSyntax.put("value-component", "srcImageName destinationImageName");
    commandSyntax.put("luma-component", "srcImageName destinationImageName");
    commandSyntax.put("intensity-component", "srcImageName destinationImageName");
    commandSyntax.put("red-component", "srcImageName destinationImageName");
    commandSyntax.put("green-component", "srcImageName destinationImageName");
    commandSyntax.put("blue-component", "srcImageName destinationImageName");
    commandSyntax.put("blur", "srcImageName destinationImageName");
    commandSyntax.put("sharpen", "srcImageName destinationImageName");
    commandSyntax.put("sepia", "srcImageName destinationImageName");
    commandSyntax.put("greyscale", "srcImageName destinationImageName");
    commandSyntax.put("histogram", "srcImageName destinationImageName");
    commandSyntax.put("brighten", "value srcImageName destinationImageName");
    commandSyntax.put("compress", "compression-value(0-100) srcImageName " +
            "destinationImageName");
    commandSyntax.put("rgb-split", "srcImageName destinationRedImageName "
            + "destinationGreenImageName destinationBlueImageName");
    commandSyntax.put("rgb-combine", "destinationImageName srcRedImageName"
            + " srcGreenImageName srcBlueImageName");
    commandSyntax.put("level-adjust", "black-value mid-value white-value"
            + " srcImageName destinationImageName");
    commandSyntax.put("color-correct", "srcImageName destinationImageName");
    commandSyntax.put("run", "script-path.txt");
    commandSyntax.put("exit", "");
  }

  /**
   * Calls the functions in image processing controller that perform operation on the image.
   *
   * @param inputCommand an array of command and parameters required by it
   * @return the operated image
   */
  @Override
  public Image runOperation(List<String> inputCommand) {

    String command = inputCommand.get(0).toLowerCase();
    List<String> parameters = inputCommand.subList(1, inputCommand.size());
    return supportedCommands.get(command).apply(parameters);
  }

}
