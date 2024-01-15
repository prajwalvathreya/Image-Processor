package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Controller class to process and handle user input commands.
 */
public class CommandLineInputsController extends AbstractIController {

  private final PrintStream output;

  /**
   * The CommandLineInputsController constructor initializes the output streams.
   *
   * @param output the output stream object
   */
  public CommandLineInputsController(PrintStream output) {
    super();
    this.output = output;
  }

  /**
   * This function processes the input commands from users.
   */
  @Override
  public void execute() {
    printCommands();
    PrintStream outputStream = new PrintStream(out);
    Scanner sc = new Scanner(in);

    while (true) {
      String command = sc.nextLine().strip();
      String[] commandInput = command.split(" ");

      List<String> inputCommands = new ArrayList<>(Arrays.asList(commandInput));

      if (commandInput[0].equalsIgnoreCase("exit")) {
        // Break the loop when 'exit' command or empty input is given
        outputStream.println("Exiting image processing application.");
        break;
      } else if (command.isEmpty() || command.charAt(0) == '#') {
        outputStream.println("Enter command: ");
      } else {
        if (commandParameters.containsKey(commandInput[0])) {
          if ((commandInput.length - 1) >= commandParameters.get(commandInput[0])) {
            this.processCommand(inputCommands);
            outputStream.println();
            outputStream.println("Enter command: ");
          } else {
            outputStream.println();
            outputStream.println("The command " + commandInput[0] + " requires "
                    + commandParameters.get(commandInput[0]) + " parameters, but you have input "
                    + (commandInput.length - 1));

            outputStream.println();
            outputStream.println("Enter valid command");
          }
        } else {
          output.println("Command keyword is incorrect, please check your spelling.");
          outputStream.println("Enter valid command");
        }
      }
    }
  }

  /**
   * This is a function to run a script.
   *
   * @param scriptFilePath path to the script
   */
  public void runScript(String scriptFilePath) {

    StringBuilder commands = fetchAndPrepareScript(scriptFilePath, output);
    Scanner scriptScanner = new Scanner(commands.toString());

    while (scriptScanner.hasNextLine()) {
      String currentLine = scriptScanner.nextLine();
      if (!currentLine.isEmpty()) {

        String[] commandInput = currentLine.split(" ");

        List<String> inputCommands = new ArrayList<>(Arrays.asList(commandInput));

        if (commandParameters.containsKey(commandInput[0])) {
          if ((commandInput.length - 1) >= commandParameters.get(commandInput[0])) {
            this.processCommand(inputCommands);
          } else {
            output.println("The command " + commandInput[0] + " requires "
                    + commandParameters.get(commandInput[0]) + " parameters, but you have input "
                    + (commandInput.length - 1));
          }
        } else {
          output.println("Command keyword is incorrect, please check your spelling.");
        }
      }
    }
  }

  /**
   * This function provides the list of commands supported and prints it for viewing.
   */

  private void printCommands() {

    output.println(System.lineSeparator() + "Image processing application loaded");
    output.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    output.println("Here are the list the supported commands");
    output.println("Follow the format given below to execute commands");
    output.println("Keyword <space> parameters");
    output.println();
    for (Map.Entry<String, String> entry : commandSyntax.entrySet()) {

      String command = entry.getKey();
      String syntax = entry.getValue();
      output.println(command + " " + syntax);
    }
    output.println(System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    output.println(System.lineSeparator() + "Enter command: ");

  }

  private void processCommand(List<String> inputCommand) {

    String command = inputCommand.get(0);
    try {
      if (command.equals("run")) {
        String convertedStringCommand = String.join(" ", inputCommand);
        runScript(convertedStringCommand);
      } else {
        this.runOperation(inputCommand);
      }
    } catch (Exception e) {
      output.println(e.getMessage());
    }

    if (imageProcessingController.getOperationComplete()) {
      output.println("Completed operation " + command);
      imageProcessingController.resetOperationComplete();
    }

  }

  private StringBuilder fetchAndPrepareScript(String scriptFilePath, PrintStream output) {
    StringBuilder scriptContent = new StringBuilder();

    try {
      File scriptFile = new File(scriptFilePath);
      Scanner fileScanner = new Scanner(scriptFile);

      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();

        // Skipping empty lines
        if (line.isBlank()) {
          continue;
        }

        // Skipping comment lines
        if (!line.startsWith("#")) {
          scriptContent.append(line).append(System.lineSeparator());
        }

      }
      fileScanner.close();
    } catch (FileNotFoundException exception) {
      output.print("File not found. Please check your path.");
    }

    return scriptContent;
  }

}
