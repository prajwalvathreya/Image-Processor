package model.histogram;

import model.data.Image;
import model.data.ImageIml;
import model.data.Pixel;

/**
 * This class performs level adjustment operation on images.
 */
public class HistogramLevelAdjuster implements Histogram {
  /**
   * This function is used to adjust levels of an image based on given b, m, w values.
   *
   * @param preFilteredImage image on which the operation must be implemented
   * @param black            value of black given by user
   * @param mid              value of mid given by user
   * @param white            value of white given by user
   * @param percentage       of the image to which the operation must be applied
   * @return level adjusted image
   */
  public Image apply(Image preFilteredImage, int black, int mid,
                     int white, int percentage) {

    Image levelAdjustedImage = new ImageIml(preFilteredImage.getHeight(),
            preFilteredImage.getWidth());

    int width = preFilteredImage.getWidth() * percentage / 100;

    Pixel[][] oldPixels = preFilteredImage.getPixels();
    Pixel[][] newPixels = levelAdjustedImage.getPixels();

    double denominator = black * black * (mid - white) - black * (mid * mid - white * white)
            + white * mid * mid - mid * white * white;
    double aA = -black * (128 - 255) + 128 * white - 255 * mid;
    double aB = black * black * (128 - 255) + 255 * mid * mid - 128 * white * white;
    double aC = black * black * (255 * mid - 128 * white) -
            black * (255 * mid * mid - 128 * white * white);

    double a = aA / denominator;
    double b = aB / denominator;
    double c = aC / denominator;

    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < preFilteredImage.getWidth(); j++) {
        if (j < width) {
          newPixels[i][j].setRed(Math.max(0, Math.min(255, computeNewColorValue(a, b, c,
                  oldPixels[i][j].red()))));
          newPixels[i][j].setGreen(Math.max(0, Math.min(255, computeNewColorValue(a, b, c,
                  oldPixels[i][j].green()))));
          newPixels[i][j].setBlue(Math.max(0, Math.min(255, computeNewColorValue(a, b, c,
                  oldPixels[i][j].blue()))));
        } else {
          newPixels[i][j].setRed(oldPixels[i][j].red());
          newPixels[i][j].setBlue(oldPixels[i][j].blue());
          newPixels[i][j].setGreen(oldPixels[i][j].green());
        }
      }
    }
    return levelAdjustedImage;
  }

  /**
   * Function to implement perform operation.
   *
   * @param preFilteredImage image on which the operation must be applied
   * @param percentage       of the image to which the operation must be applied
   * @return image after applying the operation
   */
  @Override
  public Image apply(Image preFilteredImage, int percentage) {
    return apply(preFilteredImage, 0, 127, 255, percentage);
  }

  private int computeNewColorValue(double a, double b, double c, int pixelColorValue) {
    return (int) Math.round((a) * pixelColorValue * pixelColorValue + (b) * pixelColorValue + (c));
  }

}
