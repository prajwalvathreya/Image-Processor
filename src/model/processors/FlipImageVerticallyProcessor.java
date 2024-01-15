package model.processors;

import model.data.Image;
import model.data.ImageIml;

/**
 * This class represents a processor that flips an image vertically.
 */
public class FlipImageVerticallyProcessor implements ImageProcessor {

  /**
   * This method flips a given image vertically.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @return image flipped vertically
   */
  @Override
  public Image apply(Image preFilteredImage, int percentage) {

    Image flippedImage = new ImageIml(preFilteredImage.getHeight(), preFilteredImage.getWidth());
    // Creating a new matrix to store the vertically flipped image

    for (int i = 0; i < preFilteredImage.getWidth(); i++) {
      for (int j = 0; j < preFilteredImage.getHeight(); j++) {
        // Flip the pixels horizontally by reversing the order within each row
        int flippedRow = preFilteredImage.getHeight() - 1 - j;
        flippedImage.setPixel(flippedRow, i, preFilteredImage.getPixel(j, i));
      }
    }

    return flippedImage;
  }

  /**
   * This method flips a given image vertically.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param value            not used in flip processor
   * @return image flipped vertically
   */
  @Override
  public Image apply(Image preFilteredImage, int value, int percentage) {
    return apply(preFilteredImage, percentage);
  }

}
