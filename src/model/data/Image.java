package model.data;

/**
 * This interface represents a single image of height and width containing pixels.
 */
public interface Image {

  /**
   * This constructor loads a pre-existing image matrix object.
   *
   * @param pixels array of pixels
   */
  public void setPixels(Pixel[][] pixels);

  /**
   * This method gets the image matrix which consists of 2D array of pixels.
   *
   * @return 2D array of pixels
   */
  public Pixel[][] getPixels();

  /**
   * This method gets the pixel present at a given index.
   *
   * @param row row of the pixel
   * @param col column of the pixel
   * @return the pixel present at that index
   */
  public Pixel getPixel(int row, int col);

  /**
   * This method sets the pixel present at a given index.
   *
   * @param row row of the pixel
   * @param col column of the pixel
   * @param pixel pixel present at that index
   */
  public void setPixel(int row, int col, Pixel pixel);

  /**
   * This method gets the height of the image.
   *
   * @return height of the image
   */
  public int getHeight();

  /**
   * This method gets the width of the image.
   *
   * @return width of the image
   */
  public int getWidth();

  /**
   * This method sets the height of the image.
   *
   * @param height height of the image
   */
  public void setHeight(int height);

  /**
   * This method sets the width of the image.
   *
   * @param width width of the image
   */
  public void setWidth(int width);


}
