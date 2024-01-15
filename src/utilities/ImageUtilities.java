package utilities;

import model.data.Image;

/**
 * A class created to handle common functions related to reading and writing image.
 */
public interface ImageUtilities {

  /**
   * Load the image at given location.
   *
   * @param srcPath path where the image file is present
   */
  // TODO test for nonexistent file
  public Image loadImage(String srcPath);

  /**
   * Save the image at given location.
   *
   * @param destinationPath path where the image file is saved
   */
  public void saveImage(String destinationPath, Image img);


  /**
   * Function to get the image type.
   *
   * @param fileName image file whose type has to be determined
   * @return the type of the image
   */
  public static String getImageFileExtension(String fileName) {
    String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
    if (tokens.length == 2) {
      return tokens[1];
    }
    return "";
  }


  /**
   * Returns the absolute path.
   *
   * @param path The source path for which absolute path must be returned
   * @return absolute path
   */
  public String getAbsolutePath(String path);
}
