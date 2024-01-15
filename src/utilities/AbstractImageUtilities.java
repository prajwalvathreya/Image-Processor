package utilities;

import java.nio.file.Path;
import java.nio.file.Paths;

import model.data.Image;

/**
 * A class created to handle common functions related to image.
 */

public abstract class AbstractImageUtilities implements ImageUtilities {


  /**
   * Load the image at given location.
   *
   * @param srcPath path where the image file is present
   */
  public abstract Image loadImage(String srcPath);

  /**
   * Save the image at given location.
   *
   * @param destinationPath path where the image file is saved
   */
  public abstract void saveImage(String destinationPath, Image iml);


  /**
   * Returns the absolute path.
   *
   * @param path The source path for which absolute path must be returned
   * @return absolute path
   */
  public String getAbsolutePath(String path) {
    Path currentPath = Paths.get(path);
    if (!currentPath.isAbsolute()) {
      return currentPath.toAbsolutePath().toString();
    }
    return path;
  }
}
