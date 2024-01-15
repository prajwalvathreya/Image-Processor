package model.processors;

import model.data.Image;
import model.data.ImageIml;
import model.data.Pixel;

/**
 * This class represents a processor that brightens an image.
 */
public class BrightenAndDarkenImageProcessor implements ImageProcessor {

  /**
   * This method brightens a given image.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @return brightened image
   */
  @Override
  public Image apply(Image preFilteredImage, int percentage) {
    return apply(preFilteredImage, 1, percentage);
  }

  /**
   * This method brightens a given image.
   *
   * @param value            ny which the image should be brightened
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @return brightened image
   */
  @Override
  public Image apply(Image preFilteredImage, int value, int percentage) {
    Pixel[][] pixels = preFilteredImage.getPixels();
    // Creating a new matrix to store the vertically flipped image
    Image brightenedImage = new ImageIml(preFilteredImage.getHeight(),
            preFilteredImage.getWidth());


    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < preFilteredImage.getWidth(); j++) {
        brightenedImage.getPixel(i, j).setRed(Math.min(Math.max(pixels[i][j].red() + value, 0),
                255));
        brightenedImage.getPixel(i, j).setGreen(Math.min(Math.max(pixels[i][j].green() + value, 0),
                255));
        brightenedImage.getPixel(i, j).setBlue(Math.min(Math.max(pixels[i][j].blue() + value, 0),
                255));
      }
    }
    return brightenedImage;
  }

}
