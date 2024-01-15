package model.processors;

import model.data.Image;
import model.data.Pixel;

/**
 * This class represents a processor that creates a grey scale image with luma value of the
 * image.
 */
public class LumaProcessor extends AbstractCreateGreyScaleProcessor {

  protected void setPostFilteredImage(Image preFilteredImage, Image postFilteredImage,
                                      int width) {

    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < width; j++) {

        Pixel current = preFilteredImage.getPixel(i, j);
        int luma =
                (int) (0.2126 * current.red() + 0.7152 * current.green() + 0.0722 * current.blue());
        postFilteredImage.getPixel(i, j).setRed(luma);
        postFilteredImage.getPixel(i, j).setGreen(luma);
        postFilteredImage.getPixel(i, j).setBlue(luma);
      }
    }
  }
}
