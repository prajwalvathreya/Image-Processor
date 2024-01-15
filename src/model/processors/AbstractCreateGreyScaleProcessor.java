package model.processors;

import model.data.Image;
import model.data.ImageIml;

/**
 * This class represents a processor that performs operations to convert color image to grey
 * scale image.
 */
public abstract class AbstractCreateGreyScaleProcessor implements ImageProcessor {

  protected abstract void setPostFilteredImage(Image preFiltererdImage,
                                               Image postFilteredImage, int width);

  /**
   * This method allows us to create various grey scale images.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param percentage       of the image to which the operation must be applied
   * @return colour converted image
   */
  @Override
  public Image apply(Image preFilteredImage, int percentage) {
    Image postFilteredImage = new ImageIml(preFilteredImage);

    int width = preFilteredImage.getWidth() * percentage / 100;

    setPostFilteredImage(preFilteredImage, postFilteredImage, width);
    return postFilteredImage;

  }

  /**
   * This method allows us to create various grey scale images.
   *
   * @param preFilteredImage reference image on which the filter processor should be applied
   * @param value            to be used to brighten or darken by
   * @param percentage       of the image to which the operation must be applied
   * @return colour converted image
   */
  @Override
  public Image apply(Image preFilteredImage, int value, int percentage) {
    return apply(preFilteredImage, percentage);
  }
}
