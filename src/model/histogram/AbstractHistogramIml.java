package model.histogram;

import java.util.HashMap;
import java.util.Map;

import model.data.Image;
import model.data.Pixel;

/**
 * This class is used to perform operations related to histogram
 * It implements the common function to generate histogram required for all the operations.
 */
public abstract class AbstractHistogramIml implements Histogram {

  protected Map<Integer, Integer> redHistogram = new HashMap<>();
  protected Map<Integer, Integer> greenHistogram = new HashMap<>();
  protected Map<Integer, Integer> blueHistogram = new HashMap<>();
  protected Map<Integer, Integer> intensityHistogram = new HashMap<>();

  /**
   * Abstract function to implement perform operation.
   *
   * @param preFilteredImage image on which the operation must be applied
   * @param percentage       of the image to which the operation must be applied
   * @return image after applying the operation
   */
  public abstract Image apply(Image preFilteredImage, int percentage);

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
  public abstract Image apply(Image preFilteredImage, int black, int white, int mid,
                                 int percentage);

  protected void generateHistogramMaps(Image preFilteredImage) {
    redHistogram.clear();
    greenHistogram.clear();
    blueHistogram.clear();
    intensityHistogram.clear();

    Pixel[][] currentImage = preFilteredImage.getPixels();

    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < preFilteredImage.getWidth(); j++) {

        int red = currentImage[i][j].red();
        int green = currentImage[i][j].green();
        int blue = currentImage[i][j].blue();
        int avg = (red + green + blue) / 3;

        incrementPixelCountInHistogram(redHistogram, red);
        incrementPixelCountInHistogram(greenHistogram, green);
        incrementPixelCountInHistogram(blueHistogram, blue);
        incrementPixelCountInHistogram(intensityHistogram, avg);
      }
    }
  }

  private void incrementPixelCountInHistogram(Map<Integer, Integer> histogram, int key) {
    histogram.compute(key, (k, v) -> v == null ? 1 : v + 1);
  }

}
