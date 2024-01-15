package model.transformers;

import model.data.Image;
import model.data.ImageIml;
import model.data.Pixel;

/**
 * This class represents transformers that can be applied on an image.
 */
public abstract class AbstractLinearTransformer implements LinearColorTransformer {

  /**
   * Gets the filter to be applied.
   *
   * @return filter to be used
   */
  public abstract double[][] getFilter();

  /**
   * This method allows us to convert image to grey or sepia using filters.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param percentage       is the percentage to split preview
   * @return colour converted image
   */
  @Override
  public Image apply(Image preFilteredImage, int percentage) {

    Image postFilteredImage = new ImageIml(preFilteredImage);

    int width = preFilteredImage.getWidth() * percentage / 100;


    double[][] filter = getFilter();

    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < width; j++) {

        Pixel current = preFilteredImage.getPixel(i, j);
        int[] sum = {0, 0, 0};

        for (int k = 0; k <= 2; k++) {
          sum[k] += (int) (current.red() * filter[k][0]);
          sum[k] += (int) (current.green() * filter[k][1]);
          sum[k] += (int) (current.blue() * filter[k][2]);
        }

        postFilteredImage.getPixel(i, j).setRed(Math.min(Math.max(sum[0], 0), 255));
        postFilteredImage.getPixel(i, j).setGreen(Math.min(Math.max(sum[1], 0), 255));
        postFilteredImage.getPixel(i, j).setBlue(Math.min(Math.max(sum[2], 0), 255));
      }
    }

    return postFilteredImage;
  }
}
