package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Test for CommandLineInputController class.
 */
public class CommandLineInputsControllerTest {

  private CommandLineInputsController cmd;
  private ByteArrayOutputStream byteArrayOutputStream;

  private final String testingScriptFolder = "test/resources/test";

  @Before
  public void setup() {

    byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteArrayOutputStream);
    cmd = new CommandLineInputsController(out);

  }

  @Test
  public void testFileNotPresent() {

    String filePath = testingScriptFolder + "randomScript.txt"; // this script doesn't exist

    cmd.runScript(filePath);

    String expected = "File not found. Please check your path.";

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void testScript() {

    String filePath = testingScriptFolder + "Script.txt";

    String expected = "Completed operation load\n" +
            "Completed operation brighten\n" +
            "Completed operation vertical-flip\n" +
            "Completed operation horizontal-flip\n" +
            "Completed operation value-component\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation load\n" +
            "Completed operation rgb-split\n" +
            "Completed operation brighten\n" +
            "Completed operation rgb-combine\n" +
            "Completed operation save\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testDefaultScript() {

    String filePath = "all-commands.txt";
    cmd.runScript(filePath);

    String expected = "Completed operation load\n" +
            "Completed operation red-component\n" +
            "Completed operation green-component\n" +
            "Completed operation blue-component\n" +
            "Completed operation intensity-component\n" +
            "Completed operation luma-component\n" +
            "Completed operation value-component\n" +
            "Completed operation sharpen\n" +
            "Completed operation blur\n" +
            "Completed operation brighten\n" +
            "Completed operation brighten\n" +
            "Completed operation sepia\n" +
            "Completed operation horizontal-flip\n" +
            "Completed operation vertical-flip\n" +
            "Completed operation rgb-combine\n" +
            "Completed operation rgb-split\n" +
            "Completed operation compress\n" +
            "Completed operation compress\n" +
            "Completed operation histogram\n" +
            "Completed operation color-correct\n" +
            "Completed operation histogram\n" +
            "Completed operation level-adjust\n" +
            "Completed operation level-adjust\n" +
            "Completed operation histogram\n" +
            "Completed operation histogram\n" +
            "Completed operation blur\n" +
            "Completed operation sharpen\n" +
            "Completed operation sepia\n" +
            "Completed operation color-correct\n" +
            "Completed operation level-adjust\n" +
            "Completed operation red-component\n" +
            "Completed operation blue-component\n" +
            "Completed operation green-component\n" +
            "Completed operation luma-component\n" +
            "Completed operation intensity-component\n" +
            "Completed operation value-component\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n" +
            "Completed operation save\n";

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testLoadCommand() {

    String filePath = testingScriptFolder + "LoadScript.txt";

    String expected = "Completed operation load\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testIncorrectLoadCommand() {

    String filePath = testingScriptFolder + "IncorrectLoad.txt";

    String expected = "First parameter must be in the form of imageName.extension\n" +
            "The image name should not have a period\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testSaveCommand() {

    String filePath = testingScriptFolder + "SaveScript.txt";

    String expected = "Completed operation load\n" +
            "Completed operation save\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testIncorrectSave() {

    String filePath = testingScriptFolder + "IncorrectSave.txt";

    String expected = "Completed operation load\n" +
            "First parameter must be in the form of imageName.extension\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testIncorrectSourceImage() {

    String filePath = testingScriptFolder + "IncorrectSourceImage.txt";

    String expected = "Completed operation load\n" +
            "The source image name is incorrect or has not been loaded\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  // Test commands individually
  @Test
  public void testBrighten() {

    String filePath = testingScriptFolder + "Brighten.txt";

    String expected = "Completed operation load\n" +
            "Completed operation brighten\n" +
            "Completed operation brighten\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The command brighten requires 3 parameters, but you have input 2\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testRedComponent() {

    String filePath = testingScriptFolder + "RedComponent.txt";

    String expected = "Completed operation load\n" +
            "Completed operation red-component\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The source image name is incorrect or has not been loaded\n" +
            "The command red-component requires 2 parameters, but you have input 1\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testGreenComponent() {

    String filePath = testingScriptFolder + "GreenComponent.txt";

    String expected = "Completed operation load\n" +
            "Completed operation green-component\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The source image name is incorrect or has not been loaded\n" +
            "The command green-component requires 2 parameters, but you have input 1\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testBlueComponent() {

    String filePath = testingScriptFolder + "BlueComponent.txt";

    String expected = "Completed operation load\n" +
            "Completed operation blue-component\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The source image name is incorrect or has not been loaded\n" +
            "The command blue-component requires 2 parameters, but you have input 1\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testValueComponent() {

    String filePath = testingScriptFolder + "ValueComponent.txt";

    String expected = "Completed operation load\n" +
            "Completed operation value-component\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The source image name is incorrect or has not been loaded\n" +
            "The command value-component requires 2 parameters, but you have input 1\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testIntensityComponent() {

    String filePath = testingScriptFolder + "IntensityComponent.txt";

    String expected = "Completed operation load\n" +
            "Completed operation intensity-component\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The source image name is incorrect or has not been loaded\n" +
            "The command intensity-component requires 2 parameters, but you have input 1\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testLumaComponent() {

    String filePath = testingScriptFolder + "LumaComponent.txt";

    String expected = "Completed operation load\n" +
            "Completed operation luma-component\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The source image name is incorrect or has not been loaded\n" +
            "The command luma-component requires 2 parameters, but you have input 1\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testRGBSplit() {

    String filePath = testingScriptFolder + "RGBSplit.txt";

    String expected = "Completed operation load\n" +
            "Completed operation rgb-split\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The command rgb-split requires 4 parameters, but you have input 3\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testRGBCombine() {

    String filePath = testingScriptFolder + "RGBCombine.txt";

    String expected = "Completed operation load\n" +
            "Completed operation rgb-split\n" +
            "Completed operation rgb-combine\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The command rgb-combine requires 4 parameters, but you have input 2\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testCompression() {

    String filePath = testingScriptFolder + "Compression.txt";

    String expected = "Completed operation load\n" +
            "Completed operation compress\n" +
            "Compression value must be between 0 and 100\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The command compress requires 3 parameters, but you have input 2\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testPreview() {

    String filePath = testingScriptFolder + "Preview.txt";

    String expected = "Completed operation load\n" +
            "Completed operation blur\n" +
            "Completed operation sepia\n" +
            "Completed operation sharpen\n" +
            "Completed operation greyscale\n" +
            "Completed operation color-correct\n" +
            "Completed operation level-adjust\n" +
            "Split percentage must lie between 0 and 100\n" +
            "Split percentage must lie between 0 and 100\n" +
            "Split percentage must lie between 0 and 100\n" +
            "Split percentage must lie between 0 and 100\n" +
            "Split percentage must lie between 0 and 100\n" +
            "Split percentage must lie between 0 and 100\n";


    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testHistogramCreation() {

    String filePath = testingScriptFolder + "Histogram.txt";

    String expected = "Completed operation load\n" +
            "Completed operation histogram\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The command histogram requires 2 parameters, but you have input 1\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);


  }

  @Test
  public void testColorCorrection() {

    String filePath = testingScriptFolder + "ColorCorrection.txt";

    String expected = "Completed operation load\n" +
            "Completed operation color-correct\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "The command color-correct requires 2 parameters, but you have input 1\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }

  @Test
  public void testAdjustLevel() {

    String filePath = testingScriptFolder + "LevelAdjust.txt";

    String expected = "Completed operation load\n" +
            "Completed operation level-adjust\n" +
            "Command keyword is incorrect, please check your spelling.\n" +
            "Ensure that the values black < mid < white\n" +
            "Ensure that all the values lie within the range of 0-255\n" +
            "The command level-adjust requires 5 parameters, but you have input 4\n";

    cmd.runScript(filePath);

    String actual = byteArrayOutputStream.toString();

    assertEquals(expected, actual);

  }


}
