package model.histogram;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

import model.data.Image;
import model.data.ImageIml;
import model.data.Pixel;

/**
 * This class represents a histogram saver.
 */
public class HistogramCreateAndSaver extends AbstractHistogramIml {

  private final int WIDTH = 256;
  private final int HEIGHT = 256;

  /**
   * This method creates and saves a histogram of the input image.
   *
   * @param preFilteredImage the image whose histogram will be generated
   * @param percentage       of the image to which the operation must be applied
   */
  @Override
  public Image apply(Image preFilteredImage, int percentage) {

    Image histogramImage = new ImageIml(256, 256);

    generateHistogramMaps(preFilteredImage);

    return saveHistogram(histogramImage);
  }

  /**
   * Function to implement perform operation.
   *
   * @param black            black value for level adjustment
   * @param white            white value for level adjustment
   * @param mid              mid value for level adjustment
   * @param preFilteredImage image on which the operation must be applied
   * @param percentage       of the image to which the operation must be applied
   * @return image after applying the operation
   */
  @Override
  public Image apply(Image preFilteredImage, int black, int white, int mid, int percentage) {
    return apply(preFilteredImage, percentage);
  }

  private Image saveHistogram(Image histogramImage) {

    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    // Graphics object to draw on the image
    Graphics graphics = image.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, WIDTH, HEIGHT);

    graphics.setColor(new Color(233, 243, 243));

    // Draw vertical grid lines
    for (int i = 0; i < WIDTH; i += 10) {
      graphics.drawLine(i, 0, i, HEIGHT);
    }

    // Draw horizontal grid lines
    for (int i = 0; i < HEIGHT; i += 10) {
      graphics.drawLine(0, i, WIDTH, i);
    }

    drawHistogram(graphics, redHistogram, Color.red, 0.8);
    drawHistogram(graphics, greenHistogram, Color.green, 1);
    drawHistogram(graphics, blueHistogram, Color.blue, 0.6);

    // Dispose the graphics object
    graphics.dispose();

    histogramImage.setPixels(new Pixel[HEIGHT][WIDTH]);

    // Get the pixel values
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        int rgb = image.getRGB(j, i);
        Color color = new Color(rgb);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        histogramImage.getPixels()[i][j] = new Pixel(r, g, b);
      }
    }

    return histogramImage;
  }

  private void drawHistogram(Graphics graphics, Map<Integer, Integer> histogram, Color color,
                             double heightFactor) {
    int previousX = 0;
    int previousY = HEIGHT;
    int maxFrequency = histogram.values().stream().max(Integer::compare).orElse(1);
    for (int key = 0; key < 256; key++) {
      int frequency = histogram.getOrDefault(key, 0);
      int y = HEIGHT - (int) ((double) frequency / maxFrequency * HEIGHT * heightFactor);
      graphics.setColor(color);
      graphics.drawLine(previousX, previousY, key, y);
      previousX = key;
      previousY = y;
    }
  }
}
