package model.compression;

import model.data.Image;

/**
 * This represents a class to compress the image.
 */
public interface Compressor {


  /**
   * This function compresses the image.
   *
   * @param preFilteredImage image which needs to be compresses
   * @param compression      the percentage of compression required
   * @return returns the image after compression
   */
  Image apply(Image preFilteredImage, int compression);


}

