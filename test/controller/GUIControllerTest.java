package controller;

import org.junit.Test;

import java.util.List;

import controller.mocks.InvalidCompressRatioAbove100;
import controller.mocks.InvalidCompressRatioBelow0;
import controller.mocks.InvalidCompressRatioLetter;
import controller.mocks.InvalidLevelAdjust;
import controller.mocks.InvalidLevelAdjustOutOfRange;
import controller.mocks.InvalidLoadFileDoesNotExist;
import controller.mocks.InvalidLoadWrongExtension;
import controller.mocks.InvalidSourceNameParameter;
import controller.mocks.InvalidSaveWrongExtension;
import controller.mocks.InvalidSplitPercentageAbove100;
import controller.mocks.InvalidSplitPercentageBelow0;
import controller.mocks.InvalidSplitPercentageLetter;
import controller.mocks.TestView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test for GUIController class.
 */
public class GUIControllerTest {

  ImageProcessingController imageProcessingController = new ImageProcessingController();

  @Test
  public void testInvalidLoadWrongExtension() {
    TestView iView = new InvalidLoadWrongExtension();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("The image can only be loaded as .png, .ppm, .jpg or .jpeg formats",
            iView.getError());
  }

  @Test
  public void testInvalidLoadFileNotPresent() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidLoadFileDoesNotExist();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("File does not exist: res/test/test-does-not-exist.png",
            iView.getError());
  }

  @Test
  public void testInvalidSave() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidSaveWrongExtension();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("The image can only be loaded as .png, .ppm, .jpg or .jpeg formats",
            iView.getError());
  }

  @Test
  public void testInvalidParameterImageNotLoaded() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidSourceNameParameter();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("The source image name is incorrect or has not been loaded", iView.getError());
  }

  @Test
  public void testInvalidPreviewAbove100() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidSplitPercentageAbove100();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("Split percentage must lie between 0 and 100", iView.getError());
  }

  @Test
  public void testInvalidPreviewBelow0() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidSplitPercentageBelow0();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("Split percentage must lie between 0 and 100", iView.getError());
  }

  @Test
  public void testInvalidPreviewLetter() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidSplitPercentageLetter();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("Split percentage must lie between 0 and 100", iView.getError());
  }

  @Test
  public void testInvalidCompressionRatioAbove100() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidCompressRatioAbove100();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("Compression value must be between 0 and 100", iView.getError());
  }

  @Test
  public void testInvalidCompressionRatioBelow0() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidCompressRatioBelow0();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("Compression value must be between 0 and 100", iView.getError());
  }

  @Test
  public void testInvalidCompressionRatioLetter() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidCompressRatioLetter();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("Compression value must be between 0 and 100", iView.getError());
  }

  @Test
  public void testInvalidLevelAdjust() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidLevelAdjust();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("Ensure that the values black < mid < white", iView.getError());
  }

  @Test
  public void testInvalidLevelAdjustOutOfRange() {
    imageProcessingController.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    TestView iView = new InvalidLevelAdjustOutOfRange();
    GUIController guiController = new GUIController(iView);
    guiController.actionPerformed(null);
    assertFalse(iView.getError().isEmpty());
    assertEquals("Ensure that all the values lie within the range of 0-255", iView.getError());
  }

}
