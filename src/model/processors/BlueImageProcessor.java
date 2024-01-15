package model.processors;

import model.data.Image;
import model.data.Pixel;

/**
 * This class represents a processor that converts a color image to blue image.
 */
public class BlueImageProcessor extends AbstractCreateGreyScaleProcessor {

  protected void setPostFilteredImage(Image preFilteredImage, Image postFilteredImage,
                                      int width) {

    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < width; j++) {
        Pixel current = preFilteredImage.getPixel(i, j);
        postFilteredImage.getPixel(i, j).setBlue(current.blue());
        postFilteredImage.getPixel(i, j).setRed(0);
        postFilteredImage.getPixel(i, j).setGreen(0);
      }
    }
  }
}
