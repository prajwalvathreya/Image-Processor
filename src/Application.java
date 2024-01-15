import controller.CommandLineInputsController;
import controller.GUIController;
import controller.IController;
import view.IView;
import view.JFrameView;

import static java.lang.System.out;

/**
 * This class represents the main file to run the application.
 */
public class Application {

  /**
   * The program starts from here.
   *
   * @param args user provided arguments
   */
  public static void main(String[] args) {
    IController controller;
    if (args.length == 0) {
      IView view = new JFrameView();
      controller = new GUIController(view);
      controller.execute();
    } else {
      switch (args[0]) {
        case "-file":
          CommandLineInputsController cmd = new CommandLineInputsController(out);
          cmd.runScript(args[1]);
          break;
        case "-text":
          controller = new CommandLineInputsController(out);
          controller.execute();
          System.out.println("Please enter your commands");
          break;
        default:
          System.out.println("Please enter a valid argument: -file or -text");
          break;
      }
    }
  }

}