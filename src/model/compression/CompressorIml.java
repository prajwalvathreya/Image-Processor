package model.compression;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.data.Image;
import model.data.ImageIml;

/**
 * This represents a class to compress the image.
 */
public class CompressorIml implements Compressor {

  /**
   * This function compresses the image.
   *
   * @param preFilteredImage image which needs to be compresses
   * @param compression      the percentage of compression required
   * @return returns the image after compression
   */
  public Image apply(Image preFilteredImage, int compression) {
    int h = preFilteredImage.getHeight();
    int w = preFilteredImage.getWidth();
    int s = nextPowerOf2(Math.max(h, w));

    Image compressedImage = new ImageIml(preFilteredImage.getHeight(),
            preFilteredImage.getWidth());
    double[][] redChannel = new double[s][s];
    double[][] greenChannel = new double[s][s];
    double[][] blueChannel = new double[s][s];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        redChannel[i][j] = preFilteredImage.getPixel(i, j).red();
        greenChannel[i][j] = preFilteredImage.getPixel(i, j).green();
        blueChannel[i][j] = preFilteredImage.getPixel(i, j).blue();
      }
    }
    compressChannels(List.of(redChannel, greenChannel, blueChannel), compression);
    for (int i = 0; i < preFilteredImage.getHeight(); i++) {
      for (int j = 0; j < preFilteredImage.getWidth(); j++) {
        compressedImage.getPixel(i, j).setRed(Math.min(Math.max((int) redChannel[i][j], 0), 255));
        compressedImage.getPixel(i, j).setGreen(Math.min(Math.max((int) greenChannel[i][j], 0),
                255));
        compressedImage.getPixel(i, j).setBlue(Math.min(Math.max((int) blueChannel[i][j], 0), 255));
      }
    }
    return compressedImage;
  }


  private double[] transform(double[] s) {
    if (s.length % 2 != 0) {
      throw new IllegalArgumentException("The size of the input array must be a multiple of 2");
    }
    int len = s.length / 2;
    double[] result = new double[2 * len];
    for (int i = 0; i < len; i++) {
      double a = s[2 * i];
      double b = s[2 * i + 1];
      result[i] = (a + b) / Math.sqrt(2);
      result[i + len] = (a - b) / Math.sqrt(2);
    }
    return result;
  }

  private double[] inverseTransform(double[] s) {
    if (s.length % 2 != 0) {
      throw new IllegalArgumentException("The size of the input array must be a multiple of 2");
    }
    int len = s.length / 2;
    double[] result = new double[2 * len];
    for (int i = 0, j = len; i < len; i++, j++) {
      double a = s[i];
      double b = s[j];
      result[2 * i] = (a + b) / Math.sqrt(2);
      result[2 * i + 1] = (a - b) / Math.sqrt(2);
    }
    return result;
  }

  private void haar(double[][] singleChannel) {
    if (singleChannel == null || singleChannel.length == 0) {
      return;
    }
    int c = singleChannel.length;
    while (c > 1) {
      transformSubArray(singleChannel, c, true, true);
      transformSubArray(singleChannel, c, true, false);
      c = c / 2;
    }
  }

  private void invhaar(double[][] singleChannel) {
    if (singleChannel == null || singleChannel.length == 0) {
      return;
    }
    int h = singleChannel.length;
    int w = singleChannel[0].length;
    if (h != w || !isPowerOfTwo(h)) {
      throw new IllegalArgumentException("The input array must be a square array");
    }
    int c = 2;
    while (c <= singleChannel.length) {
      transformSubArray(singleChannel, c, false, false);
      transformSubArray(singleChannel, c, false, true);
      c = c * 2;
    }
  }

  private void compressBelowThreshold(List<double[][]> channels, int compression) {
    Set<Double> uniqueElements = new HashSet<>();
    for (double[][] channel : channels) {
      for (double[] rows : channel) {
        for (double element : rows) {
          if (Math.abs(element) > 0.0) {
            uniqueElements.add(Math.abs(element));
          }
        }
      }
    }
    List<Double> sortedUniqueElements =
            uniqueElements.stream().sorted().collect(Collectors.toList());
    double threshold =
            sortedUniqueElements.get(((compression * sortedUniqueElements.size()) / 100) - 1);
    for (double[][] channel : channels) {
      for (double[] rows : channel) {
        for (int j = 0; j < rows.length; j++) {
          if (Math.abs(rows[j]) <= threshold) {
            rows[j] = 0;
          }
        }
      }
    }
  }

  private void compressChannels(List<double[][]> channels, int compression) {
    channels.forEach(this::haar);
    compressBelowThreshold(channels, compression);
    channels.forEach(this::invhaar);
  }

  private boolean isPowerOfTwo(int num) {
    return num != 0 && ((num & (num - 1)) == 0);
  }

  private void transformSubArray(double[][] singleChannel, int c, boolean transform, boolean row) {
    for (int i = 0; i < c; i++) {
      double[] transformedSubArray = new double[c];
      for (int k = 0; k < c; k++) {
        if (row) {
          transformedSubArray[k] = singleChannel[i][k];
        } else {
          transformedSubArray[k] = singleChannel[k][i];
        }
      }
      if (transform) {
        transformedSubArray = transform(transformedSubArray);
      } else {
        transformedSubArray = inverseTransform(transformedSubArray);
      }
      for (int k = 0; k < c; k++) {
        if (row) {
          singleChannel[i][k] = transformedSubArray[k];
        } else {
          singleChannel[k][i] = transformedSubArray[k];
        }
      }
    }
  }

  private int nextPowerOf2(int n) {
    if (n <= 0) {
      return 1;
    }
    int power = 1;
    while (power < n) {
      power *= 2;
    }
    return power;
  }

}
