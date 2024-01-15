package model.processors;

import model.data.Image;
import model.data.Pixel;

/**
 * This class represents a processor that creates a grey scale image with value of the
 * image.
 */
public class ValueProcessor extends AbstractCreateGreyScaleProcessor {

  protected void setPostFilteredImage(Image preFilteredImage, Image postFilteredImage,
                                      int width) {

    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < width; j++) {

        Pixel current = preFilteredImage.getPixel(i, j);
        postFilteredImage.getPixel(i, j)
                .setRed(Math.max(Math.max(current.red(), current.green()), current.blue()));
        postFilteredImage.getPixel(i, j)
                .setGreen(Math.max(Math.max(current.red(), current.green()), current.blue()));
        postFilteredImage.getPixel(i, j)
                .setBlue(Math.max(Math.max(current.red(), current.green()), current.blue()));
      }
    }
  }
}
