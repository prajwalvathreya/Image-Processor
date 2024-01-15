package model.data;

/**
 * This class represents a unit pixel.
 */

public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * This constructor creates an object with R, G, B values.
   *
   * @param red   pixel
   * @param green pixel
   * @param blue  pixel
   */

  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  /**
   * This is a getter for red colour.
   *
   * @return red colour
   */

  public int red() {
    return this.red;
  }

  /**
   * This is a getter for green colour.
   *
   * @return red colour
   */

  public int green() {
    return this.green;
  }

  /**
   * This is a getter for blue colour.
   *
   * @return red colour
   */

  public int blue() {
    return this.blue;
  }

  /**
   * This is a setter for red colour.
   *
   * @param red new value of red
   */
  public void setRed(int red) {
    this.red = red;
  }

  /**
   * This is a setter for green colour.
   *
   * @param green new value of green
   */
  public void setGreen(int green) {
    this.green = green;
  }

  /**
   * This is a setter for blue colour.
   *
   * @param blue new value of blue
   */
  public void setBlue(int blue) {
    this.blue = blue;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj.getClass() != this.getClass()) {
      return false;
    }

    final Pixel other = (Pixel) obj;

    return this.red == other.red && this.green == other.green && this.blue == other.blue;
  }

  /**
   * Overrides Hashcode.
   *
   * @return hashcode for pixel equals
   */
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + this.red;
    result = 31 * result + this.green;
    result = 31 * result + this.blue;
    return result;
  }


}
