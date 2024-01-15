package model.transformers;

/**
 * This class represents the transformer that converts an image to a sepia filtered image.
 */

public class SepiaColorTransformer extends AbstractLinearTransformer {

  private static final double[][] sepiaFilter
          = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};

  /**
   * Gets the filter to be applied.
   *
   * @return filter to be used
   */
  @Override
  public double[][] getFilter() {
    return sepiaFilter;
  }

}
