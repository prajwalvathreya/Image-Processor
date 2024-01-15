package controller;

import model.compression.Compressor;
import model.compression.CompressorIml;
import model.data.Image;
import model.data.ImageIml;
import model.filters.BlurFilter;
import model.filters.Filter;
import model.filters.SharpenFilter;
import model.histogram.Histogram;
import model.histogram.HistogramCreateAndColourCorrector;
import model.histogram.HistogramCreateAndSaver;
import model.histogram.HistogramLevelAdjuster;
import model.processors.ImageProcessor;
import model.processors.BrightenAndDarkenImageProcessor;
import model.processors.BlueImageProcessor;
import model.processors.RedImageProcessor;
import model.processors.GreenImageProcessor;
import model.processors.IntensityProcessor;
import model.processors.LumaProcessor;
import model.processors.FlipImageHorizontallyProcessor;
import model.processors.FlipImageVerticallyProcessor;
import model.processors.ValueProcessor;
import model.transformers.GreyScaleColorTransformer;
import model.transformers.LinearColorTransformer;
import model.transformers.SepiaColorTransformer;
import utilities.ConventionalImageUtilities;
import utilities.ImageUtilities;
import utilities.PPMImageUtilties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Controller class to implement various image processing techniques.
 */
public class ImageProcessingController {

  private static final Map<String, Image> nameToImageMap = new HashMap<>();
  private final ImageProcessor blueImageProcessor = new BlueImageProcessor();
  private final ImageProcessor greenImageProcessor = new GreenImageProcessor();
  private final ImageProcessor redImageProcessor = new RedImageProcessor();
  private final ImageProcessor flipImageHorizontally =
          new FlipImageHorizontallyProcessor();
  private final ImageProcessor flipImageVertically =
          new FlipImageVerticallyProcessor();
  private final ImageProcessor brightenAndDarkenImageProcessor =
          new BrightenAndDarkenImageProcessor();
  private final Filter sharpenFilter = new SharpenFilter();
  private final Filter blurFilter = new BlurFilter();
  private final LinearColorTransformer sepiaColorTransformer = new SepiaColorTransformer();
  private final LinearColorTransformer greyScaleColorTransformer =
          new GreyScaleColorTransformer();
  private final ImageProcessor lumaProcessor = new LumaProcessor();
  private final ImageProcessor intensityProcessor = new IntensityProcessor();
  private final ImageProcessor valueProcessor = new ValueProcessor();
  private final Compressor compressor = new CompressorIml();
  private final Histogram histogramGenerator = new HistogramCreateAndSaver();
  private final Histogram colorCorrector = new HistogramCreateAndColourCorrector();
  private final Histogram levelAdjuster = new HistogramLevelAdjuster();

  private final ImageUtilities ppmImageUtilities = new PPMImageUtilties();

  private final ImageUtilities conventionalImageUtilties = new ConventionalImageUtilities();


  protected boolean operationComplete = false;

  protected Map<String, Image> getNameToImageMap() {
    return nameToImageMap;
  }

  /**
   * Creates ImageProcessingController object.
   */
  public ImageProcessingController() {
    //Default Constructer
  }

  /**
   * This method is used to load an image from PPM and store it into a map.
   *
   * @param inputArray string array
   */
  public Image loadImage(List<String> inputArray)
          throws IllegalArgumentException {

    Image img;
    String rootPath = inputArray.get(0);
    String imageName = inputArray.get(1);

    checkLoadValidity(rootPath, imageName);

    // Create a color image
    if (ImageUtilities.getImageFileExtension(rootPath).equals("ppm")) {
      img = ppmImageUtilities.loadImage(rootPath);
    } else {
      img = conventionalImageUtilties.loadImage(rootPath);
    }
    // Store in map
    if (img.getHeight() != 0 && img.getWidth() != 0) {
      nameToImageMap.put(imageName, img);
      operationComplete = true;
    }
    //Store the rootPath to use for future saving function
    // object returned
    return img;
  }


  /**
   * Creates a red image.
   *
   * @param inputArray input string with contains parameter
   * @return new red image
   */
  public Image createRedImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image redImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        redImage = redImageProcessor.apply(preFilteredImage, percentage);
      } else {
        redImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      redImage = redImageProcessor.apply(preFilteredImage, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, redImage);
    operationComplete = true;
    //Call save function with the destination path
    return redImage;
  }

  /**
   * Creates a green image.
   *
   * @param inputArray input string with contains parameter
   * @return new green image
   */
  public Image createGreenImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image greenImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        greenImage = greenImageProcessor.apply(preFilteredImage, percentage);
      } else {
        greenImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      greenImage = greenImageProcessor.apply(preFilteredImage, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, greenImage);
    operationComplete = true;
    //Call save function with the destination path
    return greenImage;
  }

  /**
   * Creates a blue image.
   *
   * @param inputArray input string with contains parameter
   * @return new blue image
   */
  public Image createBlueImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image blueImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        blueImage = blueImageProcessor.apply(preFilteredImage, percentage);
      } else {
        blueImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      blueImage = blueImageProcessor.apply(preFilteredImage, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, blueImage);
    operationComplete = true;
    //Call save function with the destination path
    return blueImage;
  }

  /**
   * Flips an image horizontally.
   *
   * @param inputArray input string with contains parameter
   * @return new flipped image
   */
  public Image flipImageHorizontal(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image horizontalImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        throw new IllegalArgumentException("Preview is not supported for this operation");
      }
      horizontalImage = null;
    } catch (IndexOutOfBoundsException e) {
      horizontalImage = flipImageHorizontally.apply(preFilteredImage, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, horizontalImage);
    operationComplete = true;
    //Call save function with the destination path
    return horizontalImage;
  }

  /**
   * Flips an image vertically.
   *
   * @param inputArray input string with contains parameter
   * @return new flipped image
   */
  public Image flipImageVertical(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image verticalImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        throw new IllegalArgumentException("Preview is not supported for this operation");
      }
      verticalImage = null;
    } catch (IndexOutOfBoundsException e) {
      verticalImage = flipImageVertically.apply(preFilteredImage, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, verticalImage);
    operationComplete = true;
    //Call save function with the destination path
    return verticalImage;
  }

  /**
   * Brightens a image.
   *
   * @param inputArray input string with contains parameter
   * @return new brightened image
   */
  public Image brightenImage(List<String> inputArray)
          throws IllegalArgumentException {

    int value = Integer.parseInt(inputArray.get(0));
    String srcImageName = inputArray.get(1);
    String destImageName = inputArray.get(2);
    Image brightImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(3);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(4));
        int percentage = Integer.parseInt(inputArray.get(4));
        brightImage = brightenAndDarkenImageProcessor.apply(preFilteredImage, value, percentage);
      } else {
        brightImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      brightImage = brightenAndDarkenImageProcessor.apply(preFilteredImage, value, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, brightImage);
    operationComplete = true;
    //Call save function with the destination path
    return brightImage;
  }

  /**
   * Converts a image to intensity image.
   *
   * @param inputArray input string with contains parameter
   * @return new intensity image
   */
  public Image getIntensityImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image intensityImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        intensityImage = intensityProcessor.apply(preFilteredImage, percentage);
      } else {
        intensityImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      intensityImage = intensityProcessor.apply(preFilteredImage, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, intensityImage);
    operationComplete = true;
    //Call save function with the destination path
    return intensityImage;

  }

  /**
   * Converts a image to value image.
   *
   * @param inputArray input string with contains parameter
   * @return new value image
   */
  public Image getValueImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image valueImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        valueImage = valueProcessor.apply(preFilteredImage, percentage);
      } else {
        valueImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      valueImage = valueProcessor.apply(preFilteredImage, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, valueImage);
    operationComplete = true;
    //Call save function with the destination path
    return valueImage;

  }

  /**
   * Converts a image to luma image.
   *
   * @param inputArray input string with contains parameter
   * @return new luma image
   */
  public Image getLumaImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image lumaImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //create red image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        lumaImage = lumaProcessor.apply(preFilteredImage, percentage);
      } else {
        lumaImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      lumaImage = lumaProcessor.apply(preFilteredImage, 100);
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, lumaImage);
    operationComplete = true;
    //Call save function with the destination path
    return lumaImage;

  }

  /**
   * Blurs a image.
   *
   * @param inputArray input string with contains parameter
   * @return new blurred image
   */
  public Image blurImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image blurredImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //Call blur image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        blurredImage = blurFilter.apply(preFilteredImage, percentage);
      } else {
        blurredImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      blurredImage = blurFilter.apply(preFilteredImage, 100);
    }

    //Get the abstract image object
    //Add to map with the givenName
    nameToImageMap.put(destImageName, blurredImage);
    operationComplete = true;
    //Call save function with the destination path
    return blurredImage;

  }

  /**
   * Sharpens a image.
   *
   * @param inputArray input string with contains parameter
   * @return new sharpened image
   */
  public Image sharpenImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image sharpenedImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //Call blur image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        sharpenedImage = sharpenFilter.apply(preFilteredImage, percentage);
      } else {
        sharpenedImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      sharpenedImage = sharpenFilter.apply(preFilteredImage, 100);
    }

    //Get the abstract image object
    //Add to map with the givenName
    nameToImageMap.put(destImageName, sharpenedImage);
    operationComplete = true;
    //Call save function with the destination path
    return sharpenedImage;
  }

  /**
   * Converts an image to sepia image.
   *
   * @param inputArray input string with contains parameter
   * @return new sepia image
   */
  public Image toSepiaImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image sepiaImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //Call blur image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        sepiaImage = sepiaColorTransformer.apply(preFilteredImage, percentage);
      } else {
        sepiaImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      sepiaImage = sepiaColorTransformer.apply(preFilteredImage, 100);
    }

    //Get the abstract image object
    //Add to map with the givenName
    nameToImageMap.put(destImageName, sepiaImage);
    operationComplete = true;
    //Call save function with the destination path
    return sepiaImage;
  }

  /**
   * Converts an image to greyscale image.
   *
   * @param inputArray input string with contains parameter
   * @return new sepia image
   */
  public Image toGreyScale(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image greyScaleImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //Call blur image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        greyScaleImage = greyScaleColorTransformer.apply(preFilteredImage, percentage);
      } else {
        greyScaleImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      greyScaleImage = greyScaleColorTransformer.apply(preFilteredImage, 100);
    }

    //Get the abstract image object
    //Add to map with the givenName
    nameToImageMap.put(destImageName, greyScaleImage);
    operationComplete = true;
    //Call save function with the destination path
    return greyScaleImage;
  }

  /**
   * Splits the image into red image, green image and blue image.
   *
   * @param inputArray input string with contains parameter
   */
  public Image splitIntoRGBImage(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destRedImageName = inputArray.get(1);
    String destGreenImageName = inputArray.get(2);
    String destBlueImageName = inputArray.get(3);

    checkSplitValidity(srcImageName, destRedImageName, destGreenImageName, destBlueImageName);

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //Call convert image to red
    Image redImage = redImageProcessor.apply(preFilteredImage, 100);
    //Add to map with the givenName
    nameToImageMap.put(destRedImageName, redImage);
    //Call save function with the destination path
    //Call convert image to red
    Image greenImage = greenImageProcessor.apply(preFilteredImage, 100);
    //Add to map with the givenName
    nameToImageMap.put(destGreenImageName, greenImage);
    //Call save function with the destination path
    //Call convert image to red
    Image blueImage = blueImageProcessor.apply(preFilteredImage, 100);
    //Add to map with the givenName
    nameToImageMap.put(destBlueImageName, blueImage);
    operationComplete = true;
    //Call save function with the destination path
    return preFilteredImage;
  }

  /**
   * Combine red, green and blue image into a color image.
   *
   * @param inputArray input string with contains parameter
   * @return the new image formed from combined images
   */
  public Image combineRGBImageIntoSingle(List<String> inputArray)
          throws IllegalArgumentException {

    String newImageName = inputArray.get(0);
    String srcRedImageName = inputArray.get(1);
    String srcGreenImageName = inputArray.get(2);
    String srcBlueImageName = inputArray.get(3);

    checkCombineValidity(newImageName, srcRedImageName, srcGreenImageName, srcGreenImageName);

    //Get red color image from the map or from path if not present in the map
    Image imageRed = nameToImageMap.get(srcRedImageName);
    //Get green color image from the map or from path if not present in the map
    Image imageGreen = nameToImageMap.get(srcGreenImageName);
    //Get blue color image from the map or from path if not present in the map
    Image imageBlue = nameToImageMap.get(srcBlueImageName);

    //Create new color object
    Image combinedImage = new ImageIml(imageRed.getHeight(), imageRed.getWidth());
    // Iterate through their pixel array and combine the values to create new pixel array
    for (int i = 0; i < imageRed.getHeight(); i++) {
      for (int j = 0; j < imageRed.getWidth(); j++) {
        combinedImage.getPixel(i, j).setRed(imageRed.getPixel(i, j).red());
        combinedImage.getPixel(i, j).setBlue(imageBlue.getPixel(i, j).blue());
        combinedImage.getPixel(i, j).setGreen(imageGreen.getPixel(i, j).green());
      }
    }
    //Add to map with the givenName
    nameToImageMap.put(newImageName, combinedImage);
    operationComplete = true;
    //Call save image
    return combinedImage;
  }

  /**
   * Save the src image.
   *
   * @param inputArray input string with contains parameter
   * @return the saved image
   */
  public Image saveImage(List<String> inputArray)
          throws IllegalArgumentException {

    String finalImagePath = inputArray.get(0);
    String srcImageName = inputArray.get(1);

    checkSaveValidity(finalImagePath, srcImageName);

    Image imageToBeSaved = nameToImageMap.get(srcImageName);
    if (ImageUtilities.getImageFileExtension(finalImagePath).equals("ppm")) {
      ppmImageUtilities.saveImage(finalImagePath, imageToBeSaved);
    } else {
      conventionalImageUtilties.saveImage(finalImagePath, imageToBeSaved);
    }
    operationComplete = true;
    return imageToBeSaved;

  }

  /**
   * This method compresses a given image.
   *
   * @param inputArray input string with contains parameter
   * @return the compressed image
   */
  public Image compressImage(List<String> inputArray)
          throws IllegalArgumentException {


    String srcImageName = inputArray.get(1);
    String destImageName = inputArray.get(2);
    Image compressedImage;
    checkTwoParamValidity(srcImageName, destImageName);
    checkCompressionPercentage(inputArray.get(0));
    int compression = Integer.parseInt(inputArray.get(0));

    //Get color image of the given name from map or from path if not present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);

    //Call flip horizontal function
    compressedImage = compressor.apply(preFilteredImage, compression);

    //Add to map with the givenName
    nameToImageMap.put(destImageName, compressedImage);
    operationComplete = true;
    //Call save function with the destination path
    return compressedImage;
  }

  /**
   * This method generates a histogram for a given image.
   *
   * @param inputArray input string with contains parameter
   * @return the histogram image
   */
  public Image generateHistogram(List<String> inputArray)
          throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image histogramImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get image present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //Generate the histogram image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        histogramImage = histogramGenerator.apply(preFilteredImage, percentage);
        operationComplete = true;
      } else {
        histogramImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      histogramImage = histogramGenerator.apply(preFilteredImage, 100);
      operationComplete = true;
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, histogramImage);

    return histogramImage;
  }

  /**
   * This method color corrects an image.
   *
   * @param inputArray input string with contains parameter
   * @return the color corrected image
   */
  public Image colourCorrection(List<String> inputArray) throws IllegalArgumentException {

    String srcImageName = inputArray.get(0);
    String destImageName = inputArray.get(1);
    Image colourCorrectImage;
    checkTwoParamValidity(srcImageName, destImageName);

    //Get image present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);
    //Generate the color corrected image
    try {
      String split = inputArray.get(2);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(3));
        int percentage = Integer.parseInt(inputArray.get(3));
        colourCorrectImage = colorCorrector.apply(preFilteredImage, percentage);
        operationComplete = true;
      } else {
        colourCorrectImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      colourCorrectImage = colorCorrector.apply(preFilteredImage, 100);
      operationComplete = true;
    }

    //Add to map with the givenName
    nameToImageMap.put(destImageName, colourCorrectImage);

    return colourCorrectImage;
  }

  /**
   * This method adjusts the level of color of an image.
   *
   * @param inputArray input string with contains parameter
   * @return the leve adjusted image
   */
  public Image leveAdjuster(List<String> inputArray) throws IllegalArgumentException {

    int black = Integer.parseInt(inputArray.get(0));
    int mid = Integer.parseInt(inputArray.get(1));
    int white = Integer.parseInt(inputArray.get(2));
    String srcImageName = inputArray.get(3);
    String destImageName = inputArray.get(4);
    Image levelAdjustImage;

    checkLevelAdjustValidity(black, mid, white, srcImageName, destImageName);
    //Get image present in the map
    Image preFilteredImage = nameToImageMap.get(srcImageName);

    //Generate the histogram image
    try {
      String split = inputArray.get(5);
      if (split.equals("split")) {
        checkSplitPercentage(inputArray.get(6));
        int percentage = Integer.parseInt(inputArray.get(6));
        levelAdjustImage = levelAdjuster.apply(preFilteredImage, black, mid,
                white, percentage);
        operationComplete = true;
      } else {
        levelAdjustImage = null;
      }
    } catch (IndexOutOfBoundsException e) {
      levelAdjustImage = levelAdjuster.apply(preFilteredImage, black, mid, white, 100);
      operationComplete = true;
    }
    //Add to map with the givenName
    nameToImageMap.put(destImageName, levelAdjustImage);
    return levelAdjustImage;
  }

  // VALIDITY CHECKING HELPER FUNCTIONS

  private void checkLoadValidity(String paraOne, String paraTwo) throws IllegalArgumentException {
    checkImageExtension(paraOne);
    checkEmpty(paraTwo);

    if (paraTwo.indexOf('.') != -1) {
      throw new IllegalArgumentException("The image name should not have a period");
    }
  }

  private void checkSaveValidity(String paraOne, String paraTwo) throws IllegalArgumentException {
    checkImageExtension(paraOne);
    checkSourceImageValidity(paraTwo);
  }

  private void checkTwoParamValidity(String paraOne, String paraTwo)
          throws IllegalArgumentException {
    checkSourceImageValidity(paraOne);
    checkDestinationImageValidity(paraTwo);
  }

  private void checkSplitValidity(String paraOne, String paraTwo, String paraThree, String paraFour)
          throws IllegalArgumentException {

    checkSourceImageValidity(paraOne);
    checkDestinationImageValidity(paraTwo);
    checkDestinationImageValidity(paraThree);
    checkDestinationImageValidity(paraFour);
  }

  private void checkCombineValidity(String paraOne, String paraTwo, String paraThree,
                                    String paraFour) throws IllegalArgumentException {

    checkDestinationImageValidity(paraOne);
    checkSourceImageValidity(paraTwo);
    checkSourceImageValidity(paraThree);
    checkSourceImageValidity(paraFour);
  }

  private void checkLevelAdjustValidity(int black, int mid, int white, String paraOne,
                                        String paraTwo)
          throws IllegalArgumentException {

    checkTwoParamValidity(paraOne, paraTwo);

    if (!(black < mid && mid < white)) {
      throw new IllegalArgumentException("Ensure that the values black < mid < white");
    }

    if (!(0 <= black && black <= 255 && mid <= 255 && white <= 255)) {
      throw new IllegalArgumentException("Ensure that all the values lie within the range of " +
              "0-255");
    }

  }

  private void checkEmpty(String param) {
    if (param == null || param.isEmpty()) {
      throw new IllegalArgumentException("Parameters cannot be null or empty");
    }
  }

  private void checkImageExtension(String param) {
    checkEmpty(param);
    String end;

    try {
      end = param.split("\\.")[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("First parameter must be in the form of imageName" +
              ".extension");
    }

    if (!(end.equals("ppm") || end.equals("jpg") || end.equals("png") || end.equals("jpeg"))) {
      throw new IllegalArgumentException("The image can only be loaded as .png, .ppm, .jpg or " +
              ".jpeg formats");
    }
  }

  private void checkSourceImageValidity(String param) {
    checkEmpty(param);
    if (param.indexOf('.') == -1) {
      if (checkIfMatrixAbsentInMap(param)) {
        throw new IllegalArgumentException("The source image name is incorrect or has not been " +
                "loaded");
      }
    } else {
      throw new IllegalArgumentException("The source image name should not have a period");
    }
  }

  private void checkDestinationImageValidity(String param) {
    checkEmpty(param);
    if (param.indexOf('.') != -1) {
      throw new IllegalArgumentException("The destination image name should not have a period");
    }
  }

  private void checkSplitPercentage(String split) {
    try {
      int splitPercentage = Integer.parseInt(split);
      if (!(0 <= splitPercentage && splitPercentage <= 100)) {
        throw new IllegalArgumentException("Split percentage must lie between 0 and 100");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Split percentage must lie between 0 and 100");
    }
  }

  private void checkCompressionPercentage(String compression) {
    try {
      int compressionPercentage = Integer.parseInt(compression);
      if (!(0 < compressionPercentage && compressionPercentage < 100)) {
        throw new IllegalArgumentException("Compression value must be between 0 and 100");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Compression value must be between 0 and 100");
    }
  }

  private boolean checkIfMatrixAbsentInMap(String srcImageName) {
    return nameToImageMap.get(srcImageName) == null;
  }

  protected boolean getOperationComplete() {
    return operationComplete;
  }

  protected void resetOperationComplete() {
    this.operationComplete = false;
  }


}
