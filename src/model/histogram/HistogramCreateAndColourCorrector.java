package model.histogram;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import model.data.Image;
import model.data.ImageIml;
import model.data.Pixel;

/**
 * This class performs color correction operation on images.
 */
public class HistogramCreateAndColourCorrector extends AbstractHistogramIml {

  /**
   * This operation does the colour correction on an input image.
   *
   * @param preFilteredImage input image whose color correction has to be done
   * @param percentage       of the image to which the operation must be applied
   * @return color corrected image
   */
  @Override
  public Image apply(Image preFilteredImage, int percentage) {

    Image colourCorrectedImage = new ImageIml(preFilteredImage);

    int width = preFilteredImage.getWidth() * percentage / 100;


    generateHistogramMaps(preFilteredImage);

    int redMax = getPeak(redHistogram);
    int greenMax = getPeak(greenHistogram);
    int blueMax = getPeak(blueHistogram);

    int avg = (redMax + blueMax + greenMax) / 3;

    int redDiff = avg - redMax;
    int greenDiff = avg - greenMax;
    int blueDiff = avg - blueMax;


    colourCorrectedImage.setPixels(shiftPixelValues(colourCorrectedImage.getPixels(), redDiff,
            greenDiff, blueDiff, width));
    return colourCorrectedImage;
  }

  /**
   * Function to implement perform operation.
   *
   * @param black            black value for level adjustment
   * @param white            white value for level adjustment
   * @param mid              mid value for level adjustment
   * @param preFilteredImage image on which the operation must be applied
   * @param percentage       of the image to which the operation must be applied
   * @return image after applying the operation
   */
  @Override
  public Image apply(Image preFilteredImage, int black, int white, int mid, int percentage) {
    return apply(preFilteredImage, percentage);
  }

  private Pixel[][] shiftPixelValues(Pixel[][] currentPixels, int redDiff,
                                     int greenDiff, int blueDiff, int width) {
    int minValue = 0;
    int maxValue = 255;

    for (Pixel[] pixels : currentPixels) {
      for (int j = 0; j < width; j++) {
        pixels[j].setRed(Math.max(minValue, Math.min(maxValue, pixels[j].red() + redDiff)));
        pixels[j].setGreen(Math.max(minValue, Math.min(maxValue, pixels[j].green() + greenDiff)));
        pixels[j].setBlue(Math.max(minValue, Math.min(maxValue, pixels[j].blue() + blueDiff)));
      }
    }
    return currentPixels;
  }

  private int getPeak(Map<Integer, Integer> histogram) {
    Map<Integer, Integer> filteredHistogram = histogram.entrySet()
            .stream()
            .filter(s -> s.getKey() > 10 && s.getKey() < 245)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    return Collections.max(filteredHistogram.entrySet(), Map.Entry.comparingByValue()).getKey();
  }
}
