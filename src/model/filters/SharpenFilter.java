package model.filters;

/**
 * Class represents sharpen filter that can applied to the image.
 */
public class SharpenFilter extends FilterIml {

  private final double[][] sharpenKernel = {{-0.125, -0.125, -0.125}, {-0.125, 2.0, -0.125},
      {-0.125, -0.125, -0.125}};

  /**
   * Returns the kernel of sharpen filter to be applied.
   *
   * @return sharpen kernel to be used
   */
  @Override
  public double[][] getKernel() {
    return sharpenKernel;
  }
}
