# Image Processing Application

This project follows MVC design. The images are loaded and saved in the `res` folder. It can
load and save images in any of the following formats `.png`, `.jpg`, `.jpeg`, `.ppm` .

## High Level Design

The `src` folder of the project has the following classes:

### Controllers

1. `IController`: This interface provides functions that supports to connect view with 
   model.
1. `AbstractIController`: Abstract controller provides common functions required by both the 
   command line interface controller and graphical user interface controller.
1. `GUIController`: This controller connects the graphical user interface view to the model. The 
   user can choose the commands to execute and enter the parameter through the view. This is 
   passed to the controller which then performs the functions and returns the final model to the 
   view. The view then displays on it on the screen.
1. `CommandLineInputsController`: This controller handles taking commands to execute from the
   users. Commands will be given through command line and can be run individually or as a script.
   This controller also checks the syntax of the commands.
1. `ImageProcessingController`: This controller handles all the image processing operations
   available. It has functions to load, save, blur, sharpen, darken, brighten, flip horizontally,
   flip vertically, rgb split, rgb combine, create red, green, blue and sepia images and create
   luma, intensity, value images, compression, preview, histogram generation, color correction,
   level adjustment.



### Models

#### Data

1. `Image`: This interface represents an image and image associated functions.
1. `ImageIml`: Represents an image. It has height, width and pixel properties. It can load an image
   as a 1D matrix of pixels and can save a 1D array of pixels as an image. It supports two types
   of images PPM and Conventional images.
1. `Pixel`: This represents a pixel in the `Image` class. It has red, green and blue properties.

#### Filters

1. `Filter`: This interface is used support various filter based operations.
1. `FilterIml`: This class is used to apply various kernels to the images based on the filter. It
   also supports preview where the operation will be applied only to some part of the image
   specified by the user.
1. `BlurFilter`: This class extends `Filter` class. It is uses Blur kernel to apply filter.
1. `SharpenFilter`: This class extends `Filter` class. It is uses Sharpen kernel to apply filter.

#### Linear Color Transformers

1. `LinearColorTransformer`: This interface provides functions to transform the color of the image.
1. `LinearColorTransformerIml`: This interface provides functions to transform the color of the
   image.
1. `GreyScaleColorTransformer`: This implements the `LinearColorTransformer`. It is used to
   convert given image to grey scale image.
1. `SepiaColorTransformer`: This implements the `LinearColorTransformer`. It is used to
   convert given image to sepia image. It also supports preview where the operation will be
   applied only to some part of the image specified by the user.

#### Processors

1. `ImageProcessor`: This interface provides functions to apply various image processing
   techniques. It also supports preview where the operation will be applied only to some part of
   the image specified by the user.
1. `RedImageProcessor`: This class implements the `ImageProcesor` interface. This class creates
   a new grey scale image using red pixels from the given image.
1. `GreenImageProcessor`: This class implements the `ImageProcesor` interface. This class creates
   a new grey scale image using green pixels from the given image.
1. `BlueImageProcessor`: This class implements the `ImageProcesor` interface. This class creates
   a new grey scale image using blue pixels from the given image.
1. `BrightenImageProcessor`: This class implements the `ImageProcesor` interface. This class creates
   a new image using brightened pixels from the given image.
1. `DarkenImageProcessor`: This class implements the `ImageProcesor` interface. This class creates
   a new image using darkened pixels from the given image.
1. `FlipImageHorizontallyProcessor`: This class implements the `ImageProcesor` interface. This
   class creates a new image by flipping the pixels of the given image horizontally.
1. `FlipImageVerticallyProcessor`: This class implements the `ImageProcesor` interface. This class
   creates a new image by flipping the pixels of the given image vertically.
1. `IntensityProcessor`: This class implements the `ImageProcesor` interface. This class creates
   a new image from the intensity of the pixels of the given image.
1. `LumaProcessor`: This class implements the `ImageProcesor` interface. This class creates
   a new image from the luma of the pixels of the given image.
1. `ValueProcessor`: This class implements the `ImageProcesor` interface. This class creates
   a new image from the value of the pixels of the given image.

#### Histogram

1. `Histogram`: This interface supports functions required to generate histogram and perform
   operations based on this histogram generated.
1. `AbstractHistogramIml`: This class implements common functions required by all the histogram
   based functions like generating frequency of each color in the image.
1. `HistogramCreateAndColorCorrector`: This class creates histogram for given image and corrects
   the colors by aligning the peaks in the histogram.
1. `HistogramCreateAndSave`: This class is used to generate histogram graph for an image.
1. `HistogramLevelAdjuster`: This class adjusts level of an image.

#### Compression

1. `CompressorIml`: This interface is used to compresses the given image by the compression ratio
   specified by the user.
1. `Compressor`: This class compresses the given image by the compression ratio specified by the
   user.

### Utilities

#### ImageUtilities

Provides various functions required for common image processing such as getting image file
extension, getting image object based on the base image type and getting absolute path of the
image file.

1. `ImageUtilities`: This interface is used to support functions related to reading and writing
   image files.
1. `AbstractImageUtilities`: This class provides function to help read and write image utilities
   such as getting file extension abd absolute path.
1. `PPMImageUtilities`: This class provides function to read and write PPM image files.
1. `ConventionalImageUtilities`: This class provides function to read and write conventional image
   files.

## Design changes

### Assignment 5

1. As per inputs from Assignment 4 evaluation we included interface for all models. We also moved 
   the 
   support to load and save images to controller from model.
1. As we moved the load and save to controller we created only one image data model instead of 
   having two different models one for ppm images and one for conventional images.
1. Apart from that we also added new model classes to support histogram, level adjustment and 
   color correction.
1. We also included functionalities for previewing blur, sepia, sharpen, grey scale, color 
   correction, levels adjustment operations.

### Assignment 6

1. As per the suggestion from assignment 5 feedback we changed the logic used by threshold 
   operation in compression
1. We included new classes in view which generates the graphical user interface. The graphical 
   user interface has two panels one for showing the image for current operation and its 
   corresponding histogram. Apart from that it has a drop down for choosing operation. It also 
   has parameter panels which will be populated with text boxes required for the operation 
   selected in the drop down. It has view  mode panel which can be used to either view the final 
   image after operation or see a preview of the operation on the image.
1. We added new controller called GUIController to connect the view to the model. 
1. Since GUIController and CommandLineController had few common functionalities we created a new 
   class called AbstractIController to implement common functionalities.
1. We also created a interface that the AbstractIController implements.


#### Refer to the [Useme](Useme.md) for comprehensive instructions on using the command-line interface (CLI) and graphical user interface(GUI).

## Citation

Image: Siberian Husky Doing a Wink Expression

Photo By: Julissa Helmuth

URL: [Image Link](https://www.pexels.com/photo/siberian-husky-doing-a-wink-expression-3196887/)

Published: Sep 25, 2019

Licence: [Free to use](https://www.pexels.com/license/)