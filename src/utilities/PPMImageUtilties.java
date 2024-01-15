package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.data.Image;
import model.data.ImageIml;
import model.data.Pixel;

/**
 * A class created to handle common functions related to reading and writing image for PPM images.
 */
public class PPMImageUtilties extends AbstractImageUtilities {

  /**
   * Load the ppm image at given location.
   *
   * @param srcPath path where the image file is present
   */
  @Override
  public Image loadImage(String srcPath) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(srcPath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File does not exist: " + srcPath);
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment line
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.isEmpty() && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    Image img = new ImageIml(height, width);
    System.out.println("Width of image: " + img.getWidth());
    System.out.println("Height of image: " + img.getHeight());
    img.setPixels(new Pixel[img.getHeight()][img.getWidth()]);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        img.setPixel(i, j, new Pixel(r, g, b));
      }
    }
    return img;
  }

  /**
   * Saves the ppm at the given location.
   *
   * @param destinationPath the image is saved here
   */
  @Override
  public void saveImage(String destinationPath, Image img) {
    String path = getAbsolutePath(destinationPath);
    File file = new File(path);
    FileWriter writer;

    StringBuilder builder = new StringBuilder();
    builder.append("P3\n");
    builder.append(img.getWidth()).append(" ").append(img.getHeight()).append("\n");
    builder.append(255 + "\n");

    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        Pixel pixel = img.getPixel(y, x);
        builder.append(pixel.red()).append("\n");
        builder.append(pixel.green()).append("\n");
        builder.append(pixel.blue()).append("\n");
      }
      builder.append("\n");
    }

    try {
      writer = new FileWriter(file);
      writer.write(builder.toString());
      writer.close();
    } catch (IOException e) {
      System.out.print("File " + path + " not found!");
    }
  }
}
