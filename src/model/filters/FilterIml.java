package model.filters;

import model.data.Image;
import model.data.ImageIml;
import model.data.Pixel;

/**
 * Class represents filters that can applied to the image.
 */
public abstract class FilterIml implements Filter {


  /**
   * Returns the kernel of the filter to be applied.
   *
   * @return kernel to be used
   */
  public abstract double[][] getKernel();

  /**
   * Returns grey scale image.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param percentage       of the image to which the operation must be applied
   * @return grey scale image
   */
  public Image apply(Image preFilteredImage, int percentage) {

    // example = 100 - 100* 0.3 = 70.
    int width = preFilteredImage.getWidth() * percentage / 100;

    Image postFilteredImage = new ImageIml(preFilteredImage);


    double[][] kernel = getKernel();

    // Apply the filter to each pixel of every channel
    for (int i = 1; i < preFilteredImage.getHeight() - 1; i++) {
      for (int j = 1; j < width - 1; j++) {

        double sumRed = 0.0;
        double sumGreen = 0.0;
        double sumBlue = 0.0;

        for (int u = -1; u <= 1; u++) {
          for (int v = -1; v <= 1; v++) {
            Pixel pixel = preFilteredImage.getPixel(i + u, j + v);
            sumRed += kernel[u + 1][v + 1] * pixel.red();
            sumGreen += kernel[u + 1][v + 1] * pixel.green();
            sumBlue += kernel[u + 1][v + 1] * pixel.blue();
          }
        }
        postFilteredImage.getPixel(i, j).setRed(Math.min(Math.max((int) sumRed, 0), 255));
        postFilteredImage.getPixel(i, j).setGreen(Math.min(Math.max((int) sumGreen, 0), 255));
        postFilteredImage.getPixel(i, j).setBlue(Math.min(Math.max((int) sumBlue, 0), 255));
      }
    }

    return postFilteredImage;
  }

}
