package model.processors;

import model.data.Image;
import model.data.Pixel;

/**
 * This class represents a processor that creates a grey scale image with intensity value of the
 * image.
 */
public class IntensityProcessor extends AbstractCreateGreyScaleProcessor {

  protected void setPostFilteredImage(Image preFilteredImage, Image postFilteredImage,
                                      int width) {

    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < width; j++) {

        Pixel current = preFilteredImage.getPixel(i, j);
        postFilteredImage.getPixel(i, j)
                .setRed((current.red() + current.green() + current.blue()) / 3);
        postFilteredImage.getPixel(i, j)
                .setGreen((current.red() + current.green() + current.blue()) / 3);
        postFilteredImage.getPixel(i, j)
                .setBlue((current.red() + current.green() + current.blue()) / 3);
      }
    }
  }
}

