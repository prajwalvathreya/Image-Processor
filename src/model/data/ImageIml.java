package model.data;


/**
 * This class represents a single image of height and width containing pixels.
 */

public class ImageIml implements Image {

  private Pixel[][] pixels;
  private int height;
  private int width;


  /**
   * This constructor creates a blank image with all pixels set to white.
   *
   * @param height of the image we want to create
   * @param width  of the image we want to create
   */
  public ImageIml(int height, int width) {

    pixels = new Pixel[height][width];
    this.height = height;
    this.width = width;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = 0;
        int g = 0;
        int b = 0;
        pixels[i][j] = new Pixel(r, g, b);
      }
    }
  }


  /**
   * This constructor creates an image with all pixels set to pixel of the reference image.
   *
   * @param img reference image
   */
  public ImageIml(Image img) {

    this.height = img.getHeight();
    this.width = img.getWidth();
    pixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = img.getPixel(i, j).red();
        int g = img.getPixel(i, j).green();
        int b = img.getPixel(i, j).blue();
        pixels[i][j] = new Pixel(r, g, b);
      }
    }

  }

  /**
   * This constructor loads a pre-existing image matrix object.
   *
   * @param pixels array of pixels
   */
  public void setPixels(Pixel[][] pixels) {
    this.pixels = pixels;
  }

  /**
   * This method gets the image matrix which consists of 2D array of pixels.
   *
   * @return 2D array of pixels
   */
  public Pixel[][] getPixels() {
    return pixels;
  }

  /**
   * This method gets the pixel present at a given index.
   *
   * @param row row of the pixel
   * @param col column of the pixel
   * @return the pixel present at that index
   */
  public Pixel getPixel(int row, int col) {
    return pixels[row][col];
  }

  /**
   * This method sets the pixel present at a given index.
   *
   * @param row row of the pixel
   * @param col column of the pixel
   * @param pixel pixel present at that index
   */
  public void setPixel(int row, int col, Pixel pixel) {
    this.pixels[row][col] = pixel;
  }

  /**
   * This method gets the height of the image.
   *
   * @return height of the image
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * This method gets the width of the image.
   *
   * @return width of the image
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * This method sets the height of the image.
   *
   * @param height height of the image
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * This method sets the width of the image.
   *
   * @param width width of the image
   */
  public void setWidth(int width) {
    this.width = width;
  }


}
