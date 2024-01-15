package model.transformers;

/**
 * This class represents the transformer that converts a color image to grey scale image.
 */
public class GreyScaleColorTransformer extends AbstractLinearTransformer {

  private static final double[][] greyFilter
          = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}};

  /**
   * Gets the filter to be applied.
   *
   * @return filter to be used
   */
  @Override
  public double[][] getFilter() {
    return greyFilter;
  }

}
