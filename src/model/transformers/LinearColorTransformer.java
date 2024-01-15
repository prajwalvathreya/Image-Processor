package model.transformers;

import model.data.Image;

/**
 * This class represents transformers that can be applied on an image.
 */
public interface LinearColorTransformer {

  /**
   * Gets the filter to be applied.
   *
   * @return filter to be used
   */
  double[][] getFilter();

  /**
   * This method allows us to convert image to grey or sepia using filters.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param percentage       is the percentage to split preview
   * @return colour converted image
   */

  Image apply(Image preFilteredImage, int percentage);
}
