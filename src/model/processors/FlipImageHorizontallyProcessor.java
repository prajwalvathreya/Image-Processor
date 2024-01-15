package model.processors;

import model.data.Image;
import model.data.ImageIml;

/**
 * This class represents a processor that flips an image horizontally.
 */
public class FlipImageHorizontallyProcessor implements ImageProcessor {

  /**
   * This method flips a given image horizontally.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @return image flipped horizontally
   */
  @Override
  public Image apply(Image preFilteredImage, int percentage) {

    // Creating a new matrix to store the horizontally flipped image
    Image flippedImage = new ImageIml(preFilteredImage.getHeight(), preFilteredImage.getWidth());


    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < preFilteredImage.getWidth(); j++) {
        // Flip the pixels horizontally by reversing the order within each row
        int flippedColumn = preFilteredImage.getWidth() - 1 - j;
        flippedImage.setPixel(i, flippedColumn, preFilteredImage.getPixel(i, j));
      }
    }

    return flippedImage;
  }

  /**
   * This method flips a given image horizontally.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param value            not used in flip processor
   * @return image flipped horizontally
   */
  @Override
  public Image apply(Image preFilteredImage, int value, int percentage) {
    return apply(preFilteredImage, percentage);
  }
}
