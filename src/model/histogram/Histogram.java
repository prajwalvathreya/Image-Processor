package model.histogram;

import model.data.Image;

/**
 * This interface handles operations related to histogram.
 */
public interface Histogram {

  /**
   * Function to implement perform operation.
   *
   * @param preFilteredImage image on which the operation must be applied
   * @param percentage       of the image to which the operation must be applied
   * @return image after applying the operation
   */
  Image apply(Image preFilteredImage, int percentage);

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
  Image apply(Image preFilteredImage, int black, int white, int mid, int percentage);

}
