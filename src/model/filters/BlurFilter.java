package model.filters;

/**
 * Class represents blur filter that can applied to the image.
 */
public class BlurFilter extends FilterIml {

  private static final double[][] blurKernel = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
      {0.0625, 0.125, 0.0625}};

  /**
   * Returns the kernel of blur filter to be applied.
   *
   * @return blur kernel to be used
   */
  @Override
  public double[][] getKernel() {
    return blurKernel;
  }
}
