package utilities;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.data.Image;
import model.data.ImageIml;
import model.data.Pixel;

/**
 * A class created to handle common functions related to reading and writing image for conventional
 * images.
 */
public class ConventionalImageUtilities extends AbstractImageUtilities {

  /**
   * Load the conventional image at given location.
   *
   * @param srcPath path where the image file is present
   */
  @Override
  public Image loadImage(String srcPath) {

    BufferedImage image;

    try {
      String path = getAbsolutePath(srcPath);
      File file = new File(path);
      image = ImageIO.read(file);

      int width = image.getWidth();
      int height = image.getHeight();
      Image img = new ImageIml(height, width);
      img.setPixels(new Pixel[height][width]);
      img.setHeight(height);
      img.setWidth(width);
      // Get the pixel values
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int rgb = image.getRGB(j, i);
          Color color = new Color(rgb);
          int r = color.getRed();
          int g = color.getGreen();
          int b = color.getBlue();
          img.setPixel(i, j, new Pixel(r, g, b));
        }
      }
      return img;
    } catch (IOException e) {
      throw new IllegalArgumentException("File does not exist: " + srcPath);
    }
  }

  /**
   * Saves the conventional image at the given location.
   *
   * @param destinationPath the image is saved here
   */
  @Override
  public void saveImage(String destinationPath, Image img) {
    String path = getAbsolutePath(destinationPath);
    BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        Pixel pixel = img.getPixel(y, x);
        Color temp = new Color(pixel.red(),
                pixel.green(),
                pixel.blue());
        image.setRGB(x, y, temp.getRGB());
      }
    }

    File output = new File(path);
    try {
      ImageIO.write(image, ImageUtilities.getImageFileExtension(destinationPath), output);
    } catch (Exception e) {
      System.out.print("File " + path + " not found!");
    }
  }
}
