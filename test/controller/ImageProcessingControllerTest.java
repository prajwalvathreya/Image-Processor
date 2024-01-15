package controller;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import model.data.Pixel;
import model.data.Image;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for ImageProcessingController class.
 */
public class ImageProcessingControllerTest {

  private ImageProcessingController controller;

  @Before
  public void setup() {
    controller = new ImageProcessingController();
  }


  @Test
  public void loadConventionalImageAndSaveConventionalImage() {
    Image loadedImage = controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    controller.saveImage(List.of(new String[]{"res/test/testJPG.jpg", "test"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testJPG.jpg",
        "testJPG"}));
    assertEquals(image1.getHeight(), loadedImage.getHeight());
    assertEquals(image1.getWidth(), loadedImage.getWidth());
  }

  @Test
  public void loadPPMImageAndSavePPMImage() {
    Image image = controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    controller.saveImage(List.of(new String[]{"res/test/testPPM.ppm", "test"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testPPM.ppm",
        "testPPM"}));
    assertEquals(image1.getHeight(), image.getHeight());
    assertEquals(image1.getWidth(), image.getWidth());
    assertArrayEquals(image1.getPixels(), image1.getPixels());
  }

  @Test
  public void loadPPMImageAndSaveConventionalImage() {
    Image loadedImage = controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    controller.saveImage(List.of(new String[]{"res/test/testPNG.png", "test"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testPNG.png",
        "testPNG"}));
    assertEquals(image1.getHeight(), loadedImage.getHeight());
    assertEquals(image1.getWidth(), loadedImage.getWidth());
  }

  @Test
  public void loadConventionalImageAndSavePPMImage() {
    Image loadedImage = controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    controller.saveImage(List.of(new String[]{"res/test/testPPM2.ppm", "test"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testPPM2.ppm",
        "testPPM2"}));
    assertEquals(image1.getHeight(), loadedImage.getHeight());
    assertEquals(image1.getWidth(), loadedImage.getWidth());
  }

  @Test
  public void createRedImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.createRedImage(List.of(new String[]{"test", "testRed"}));
    controller.saveImage(List.of(new String[]{"res/test/testRed.ppm", "testRed"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testRed.ppm",
        "testRed"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());

  }

  @Test
  public void createBlueImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.createBlueImage(List.of(new String[]{"test", "testBlue"}));
    controller.saveImage(List.of(new String[]{"res/test/testBlue.ppm", "testBlue"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testBlue.ppm",
        "testBlue"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void createGreenImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.createGreenImage(List.of(new String[]{"test", "testGreen"}));
    controller.saveImage(List.of(new String[]{"res/test/testGreen.ppm", "testGreen"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testGreen.ppm",
        "testGreen"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void flipImageHorizontal() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.flipImageHorizontal(List.of(new String[]{"test",
        "testFlipHorizontal"}));
    controller.saveImage(List.of(new String[]{"res/test/testHorizontal.ppm",
        "testFlipHorizontal"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testHorizontal.ppm",
        "testFlipHorizontal"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void flipImageVertical() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.flipImageVertical(List.of(new String[]{"test",
        "testFlipVertical"}));
    controller.saveImage(List.of(new String[]{"res/test/testVertical.ppm", "testFlipVertical"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testVertical.ppm",
        "testFlipVertical"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void darkenImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.brightenImage(List.of(new String[]{"-50", "test", "testDarken"}));
    controller.saveImage(List.of(new String[]{"res/test/testDarkened.ppm", "testDarken"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testDarkened.ppm",
        "testDarken"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());

  }

  @Test
  public void brightenImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.brightenImage(List.of(new String[]{"50", "test", "testBrighten"}));
    controller.saveImage(List.of(new String[]{"res/test/testBrighten.ppm", "testBrighten"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testBrighten.ppm",
        "testBrighten"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void getIntensityImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.getIntensityImage(List.of(new String[]{"test", "testIntensity"}));
    controller.saveImage(List.of(new String[]{"res/test/testIntensity.ppm", "testIntensity"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testIntensity.ppm",
        "testIntensity"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void getValueImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.getValueImage(List.of(new String[]{"test", "testValue"}));
    controller.saveImage(List.of(new String[]{"res/test/testValue.ppm", "testValue"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testValue.ppm",
        "testValue"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void getLumaImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.getLumaImage(List.of(new String[]{"test", "testLuma"}));
    controller.saveImage(List.of(new String[]{"res/test/testLuma.ppm", "testLuma"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testLuma.ppm",
        "testLuma"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void blurImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    controller.blurImage(List.of(new String[]{"test", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    Image image = controller.blurImage(List.of(new String[]{"testBlur", "testBlur"}));
    controller.saveImage(List.of(new String[]{"res/test/testBlur.ppm", "testBlur"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testBlur.ppm",
        "testBlur"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void sharpenImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    controller.sharpenImage(List.of(new String[]{"test", "testSharpen"}));
    controller.sharpenImage(List.of(new String[]{"testSharpen", "testSharpen"}));
    Image image = controller.sharpenImage(List.of(new String[]{"testSharpen", "testSharpen"}));
    controller.saveImage(List.of(new String[]{"res/test/testSharpen.ppm", "testSharpen"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testSharpen.ppm",
        "testSharpen"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void toSepiaImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    Image image = controller.toSepiaImage(List.of(new String[]{"test", "testSepia"}));
    controller.saveImage(List.of(new String[]{"res/test/testSepia.ppm", "testSepia"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testSepia.ppm",
        "testSepia"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void splitIntoRGBImage() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    controller.splitIntoRGBImage(List.of(new String[]{"test", "testRedSplit", "testGreenSplit",
        "testBlueSplit"}));
    Image imageRed = controller.getNameToImageMap().get("testRedSplit");
    Image imageGreen = controller.getNameToImageMap().get("testGreenSplit");
    Image imageBlue = controller.getNameToImageMap().get("testBlueSplit");
    controller.saveImage(List.of(new String[]{"res/test/testRedSplit.ppm", "testRedSplit"}));
    controller.saveImage(List.of(new String[]{"res/test/testGreenSplit.ppm", "testGreenSplit"}));
    controller.saveImage(List.of(new String[]{"res/test/testBlueSplit.ppm", "testBlueSplit"}));
    Image imageRed1 = controller.loadImage(List.of(new String[]{"res/test/testRedSplit.ppm",
        "testRedSplit"}));
    Image imageGreen1 = controller.loadImage(List.of(new String[]{"res/test/testGreenSplit" +
        ".ppm", "testGreenSplit"}));
    Image imageBlue1 = controller.loadImage(List.of(new String[]{"res/test/testBlueSplit.ppm",
        "testBlueSplit"}));
    assertEquals(imageRed.getHeight(), imageRed1.getHeight());
    assertEquals(imageRed.getWidth(), imageRed1.getWidth());
    assertArrayEquals(imageRed.getPixels(), imageRed1.getPixels());
    assertEquals(imageGreen.getHeight(), imageGreen1.getHeight());
    assertEquals(imageGreen.getWidth(), imageGreen1.getWidth());
    assertArrayEquals(imageGreen.getPixels(), imageGreen1.getPixels());
    assertEquals(imageBlue.getHeight(), imageBlue1.getHeight());
    assertEquals(imageBlue.getWidth(), imageBlue1.getWidth());
    assertArrayEquals(imageBlue.getPixels(), imageBlue1.getPixels());
  }

  @Test
  public void combineRGBImageIntoSingle() {
    controller.loadImage(List.of(new String[]{"res/test/test.ppm", "test"}));
    controller.splitIntoRGBImage(List.of(new String[]{"test", "testRedSplit",
        "testGreenSplit", "testBlueSplit"}));
    Image image = controller.combineRGBImageIntoSingle(List.of(new String[]{"testCombine",
        "testRedSplit", "testGreenSplit", "testBlueSplit"}));
    controller.saveImage(List.of(new String[]{"res/test/testCombine.ppm", "testCombine"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testCombine.ppm",
        "testCombine"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());

  }

  @Test
  public void testHistogramCreation() {
    controller.loadImage(List.of(new String[]{"res/test/test.png", "histogram"}));
    Image image = controller.generateHistogram(List.of(new String[]{"histogram", "hist"}));
    controller.saveImage(List.of(new String[]{"res/test/histogram.png", "hist"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/histogram.png",
        "histogram"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());

  }

  @Test
  public void testCompress() {

    Image load = controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    Image imageCompress1 = controller.compressImage(List.of(new String[]{"20", "test",
        "test-compress-20"}));

    boolean artifactOne = testArtifcats(load, imageCompress1);

    controller.saveImage(List.of(new String[]{"res/test/testCompress20.png", "test-compress-20"}));

    Image imageCompress2 = controller.compressImage(List.of(new String[]{"99", "test", "test" +
        "-compress-99"}));

    boolean artifact2 = testArtifcats(load, imageCompress2);

    controller.saveImage(List.of(new String[]{"res/test/testCompress99.png", "test-compress-99"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testCompress20.png",
        "test"}));

    Image image2 = controller.loadImage(List.of(new String[]{"res/test/testCompress99.png",
        "test"}));

    assertTrue(artifactOne);
    assertTrue(artifact2);
    assertEquals(imageCompress1.getHeight(), image1.getHeight());
    assertEquals(imageCompress1.getWidth(), image1.getWidth());
    assertArrayEquals(imageCompress1.getPixels(), image1.getPixels());
    assertEquals(imageCompress2.getHeight(), image2.getHeight());
    assertEquals(imageCompress2.getWidth(), image2.getWidth());
    assertArrayEquals(imageCompress2.getPixels(), image2.getPixels());
  }

  private boolean testArtifcats(Image original, Image compressed) {

    Pixel[][] originalPixels = original.getPixels();
    Pixel[][] compressedPixels = compressed.getPixels();

    boolean isCompressed = false;

    if (original.getHeight() == compressed.getHeight()
            && original.getWidth() == compressed.getWidth()) {
      for (int i = 0; i < original.getHeight(); i++) {
        for (int j = 0; j < original.getWidth(); j++) {
          Pixel originalPixel = originalPixels[i][j];
          Pixel compressedPixel = compressedPixels[i][j];

          if ((originalPixel.red() > compressedPixel.red())
                  && (originalPixel.green() > compressedPixel.green())
                  && (originalPixel.blue() > compressedPixel.blue())) {
            isCompressed = true;
          }

        }
      }
    }

    return isCompressed;
  }

  //
  @Test
  public void testColourCorrection() {
    controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    controller.generateHistogram(List.of(new String[]{"test", "test-histogram"}));
    controller.saveImage(List.of(new String[]{"res/test/testColorCorrectHistogram.png",
        "test-histogram"}));
    Image imageColorCorrect = controller.colourCorrection(List.of(new String[]{"test",
        "test-color-correct"}));
    controller.saveImage(List.of(new String[]{"res/test/testColorCorrect.png",
        "test-color-correct"}));
    Image imageColorCorrectHistogram = controller.generateHistogram(List.of(new String[]{"test" +
        "-color-correct", "test-color-correct-histogram"}));
    controller.saveImage(List.of(new String[]{"res/test/testAfterColorCorrectHistogram.png",
        "test-color-correct-histogram"}));
    Image imageColorCorrect1 = controller.loadImage(
        List.of(new String[]{"res/test/testColorCorrect.png", "test-histogram"}));
    Image imageColorCorrectHistogram1 = controller.loadImage(
        List.of(new String[]{"res/test/testAfterColorCorrectHistogram.png",
          "test-histogram-color-correct"}));

    assertEquals(imageColorCorrect.getHeight(), imageColorCorrect1.getHeight());
    assertEquals(imageColorCorrect.getWidth(), imageColorCorrect1.getWidth());
    assertArrayEquals(imageColorCorrect.getPixels(), imageColorCorrect1.getPixels());
    assertEquals(imageColorCorrectHistogram.getHeight(), imageColorCorrectHistogram1.getHeight());
    assertEquals(imageColorCorrectHistogram.getWidth(), imageColorCorrectHistogram1.getWidth());
    assertArrayEquals(imageColorCorrectHistogram.getPixels(),
        imageColorCorrectHistogram1.getPixels());
  }

  @Test
  public void testLevelAdjust() {
    controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    Image image = controller.leveAdjuster(List.of(new String[]{"10", "150", "255", "test",
        "test-level-adjust"}));
    controller.saveImage(List.of(new String[]{"res/test/testLevelAdjust.png",
        "test-level-adjust"}));
    Image image1 = controller.loadImage(List.of(new String[]{"res/test/testLevelAdjust.png",
        "test"}));
    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void testPreviewColorCorrect() {
    controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    Image image = controller.colourCorrection(List.of(new String[]{"test",
        "test-color-correct-split", "split", "50"}));
    controller.saveImage(List.of(new String[]{"res/test/testColorCorrectSplit.png",
        "test-color-correct-split"}));
    Image image1 = controller.loadImage(
        List.of(new String[]{"res/test/testColorCorrectSplit.png", "test-split"}));

    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void testPreviewLevelAdjust() {
    controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    Image image = controller.leveAdjuster(List.of(new String[]{"10", "150", "255", "test",
        "test-after", "split", "50"}));
    controller.saveImage(List.of(new String[]{"res/test/testLevelAdjustSplit.png",
        "test-after"}));
    Image image1 = controller.loadImage(
        List.of(new String[]{"res/test/testLevelAdjustSplit.png", "test-after"}));

    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void testPreviewBlur() {
    controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    Image image = controller.blurImage(List.of(new String[]{"test",
        "test-after", "split", "50"}));
    controller.saveImage(List.of(new String[]{"res/test/testBlurSplit.png",
        "test-after"}));
    Image image1 = controller.loadImage(
        List.of(new String[]{"res/test/testBlurSplit.png", "test-after"}));

    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void testPreviewSharpen() {
    controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    Image image = controller.sharpenImage(List.of(new String[]{"test",
        "test-after", "split", "50"}));
    controller.saveImage(List.of(new String[]{"res/test/testSharpenSplit.png",
        "test-after"}));
    Image image1 = controller.loadImage(
        List.of(new String[]{"res/test/testSharpenSplit.png", "test-after"}));

    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

  @Test
  public void testPreviewGreyScale() {
    controller.loadImage(List.of(new String[]{"res/test/test.png", "test"}));
    Image image = controller.toGreyScale(List.of(new String[]{"test",
        "test-after", "split", "50"}));
    controller.saveImage(List.of(new String[]{"res/test/testGreyScaleSplit.png",
        "test-after"}));
    Image image1 = controller.loadImage(
        List.of(new String[]{"res/test/testGreyScaleSplit.png", "test-after"}));

    assertEquals(image.getHeight(), image1.getHeight());
    assertEquals(image.getWidth(), image1.getWidth());
    assertArrayEquals(image.getPixels(), image1.getPixels());
  }

}