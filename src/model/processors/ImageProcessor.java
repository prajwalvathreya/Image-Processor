package model.processors;

import model.data.Image;

/**
 * This class represents processors that can be applied on an image.
 */
public interface ImageProcessor {

  /**
   * This method allows us to create images by applying various processing techniques.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param percentage       of the image to which the operation must be applied
   * @return colour converted image
   */
  Image apply(Image preFilteredImage, int percentage);

  /**
   * This method allows us to create images by applying various processing techniques.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param value            to be used to brighten or darken by
   * @param percentage       of the image to which the operation must be applied
   * @return colour converted image
   */
  Image apply(Image preFilteredImage, int value, int percentage);

}
