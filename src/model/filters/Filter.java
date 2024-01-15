package model.filters;

import model.data.Image;

/**
 * Interface represents filters that can applied to the image.
 */
public interface Filter {
  /**
   * Returns the kernel of the filter to be applied.
   *
   * @return kernel to be used
   */
  double[][] getKernel();

  /**
   * Returns grey scale image.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @return grey scale image
   */
  Image apply(Image preFilteredImage, int percentage);
}
