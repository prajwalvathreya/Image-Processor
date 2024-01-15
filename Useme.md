# Documentation for using the CLI

## Running the project

Run the project using jar. First change directory to res/

```shell
cd res
```

#### Syntax to run commands through CLI using script

This can be used to run commands through a file that contains all the commands. 

```
java -jar <jar-file-name>.jar -file <path-to-file-name>.<extension>
```

#### Usage to run commands through CLI using script

```shell
java -jar PDP_Assignment.jar -file script-husky.txt
```

#### Syntax to run commands through CLI using interactive commands

This can be used to run the commands through interaction with command line interface.

```
java -jar <jar-file-name>.jar -text
```

#### Usage to run commands through CLI using interactive commands

```shell
java -jar PDP_Assignment.jar -text
```


#### Syntax to run operations through graphical user interface

```
java -jar <jar-file-name>.jar
```

#### Usage to run operations through graphical user interface

This can be used to perform operations and view results through graphical user interface.

```shell
java -jar PDP_Assignment.jar
```

## Using Command Line Interface in Interactive mode

### Load Image

Load an image from the specified path and refer it to henceforth in the program by the given image
name.

#### Syntax

```
load <image-path> <image-name>
```

#### Usage

```shell
load husky.png husky
```

### Save Image

Save the image with the given name to the specified path which should include the name of the file.

#### Syntax

```
save <dest-image-path> <image-name>
```

#### Usage

```shell
save husky-ppm.ppm husky
```

### Get the Red Component an Image

Create an image with the red-component of the image with the given name, and refer to it henceforth
in the program by the given destination name.

#### Syntax

```
red-component <image-name> <dest-image-name>
```

#### Usage

```shell
red-component husky husky-red
```

### Get the Green Component an Image

Create an image with the green-component of the image with the given name, and refer to it
henceforth in the program by the given destination name.

#### Syntax

```
green-component <image-name> <dest-image-name>
```

#### Usage

```shell
green-component husky husky-green
```

### Get the Blue Component an Image

Create an image with the blue-component of the image with the given name, and refer to it
henceforth in the program by the given destination name.

#### Syntax

```
blue-component <image-name> <dest-image-name>
```

#### Usage

```shell
blue-component husky husky-blue
```

### Get the Intensity Component an Image

Create an image with the intensity-component of the image with the given name, and refer to it
henceforth in the program by the given destination name.

#### Syntax

```
intensity-component <image-name> <dest-image-name>
```

#### Usage

```shell
intensity-component husky husky-intensity
```

### Get the Luma Component an Image

Create an image with the luma-component of the image with the given name, and refer to it
henceforth in the program by the given destination name.

#### Syntax

```
luma-component <image-name> <dest-image-name>
```

#### Usage

```shell
luma-component husky husky-luma
```

### Get the Value Component an Image

Create an image with the value-component of the image with the given name, and refer to it
henceforth in the program by the given destination name.

#### Syntax

```
value-component <image-name> <dest-image-name>
```

#### Usage

```shell
value-component husky husky-value
```

### Sharpen Image

Sharpen the given image and store the result in another image with the given name.

#### Syntax

```
sharpen <image-name> <dest-image-name>
```

#### Usage

```shell
sharpen husky husky-sharpen
```

### Blur Image

Blur the given image and store the result in another image with the given name.

#### Syntax

```
blur <image-name> <dest-image-name>
```

#### Usage

```shell
blur husky husky-blur
```

### Brighten Image

Brighten the image by the given increment to create a new image, referred to henceforth by the
given destination name.

#### Syntax

```
brighten <value> <image-name> <dest-image-name>
```

#### Usage

```shell
brighten 10 husky husky-brighten
```

### Darken image

Darken the image by the given increment to create a new image, referred to henceforth by the given
destination name.

#### Syntax

```
brighten <negative-value> <image-name> <dest-image-name>
```

#### Usage

```shell
brighten -10 husky husky-darken
```

### Create Sepia image

Produce a sepia-toned version of the given image and store the result in another image with the
given name.

#### Syntax

```
sepia <image-name> <dest-image-name>
```

#### Usage

```shell
sepia husky husky-sepia
```

### Flip Image Horizontally

Flip an image horizontally to create a new image, referred to henceforth by the given destination
name.

#### Syntax

```
horizontal-flip <image-name> <dest-image-name>
```

#### Usage

```shell
horizontal-flip husky husky-horizontal-flip
```

### Flip Image Vertically

Flip an image vertically to create a new image, referred to henceforth by the given destination
name.

#### Syntax

```
vertical-flip <image-name> <dest-image-name>
```

#### Usage

```shell
vertical-flip husky husky-vertical-flip
```

### RGB Combine

Combine the three greyscale images into a single image that gets its red, green and blue components
from the three images respectively.

#### Syntax

```
rgb-combine <dest-image-name> <image-name-red> <image-name-green> <image-name-blue>
```

#### Usage

```shell
rgb-combine husky-combine husky-red husky-green husky-blue
```

### RGB Split

Split the given image into three images containing its red, green and blue components
respectively. These would be the same images that would be individually produced with the
red-component, green-component and blue-component commands.

#### Syntax

```
rgb-split <src-image-name> <image-name-red> <image-name-green> <image-name-blue>
```

#### Usage

```shell
rgb-split husky husky-red-split husky-green-split husky-blue-split
```

### Compress

Support the ability to create a compression version of an image.

#### Syntax

```
compress <percentage> <image-name> <dest-image-name>
```

#### Usage

```shell
compress 90 husky husky-compress-90
```

### Color Correct

Support the ability to color-correct an image by aligning the meaningful peaks of its histogram.

#### Syntax

```
color-correct <image-name> <dest-image-name>
```

#### Usage

```shell
color-correct husky husky-color-correct
```

### Histogram Generate

Support the ability to produce an image that represents the histogram of a given image.
The size of this image should be 256x256. It should contain the histograms for the red, green and
blue channels as line graphs.

#### Syntax

```
histogram <image-name> <dest-image-name>
```

#### Usage

```shell
histogram husky husky-histogram
```

### Level Adjust

Support the ability to adjust levels of an image.

#### Syntax

```
level-adjust <b> <m> <w> <image-name> <dest-image-name>, where b<m<w
```

#### Usage

```shell
level-adjust 20 150 255 husky husky-level-adjust
```

### Preview

Support the ability to specify a vertical line to generate a split view of operations.
Operations supported: blur, sharpen, level-adjust, color-correct, brighten, darken, intensity-component,
luma-component, value-component, red-component, blue-component, green-component

#### Syntax

```
<operation> <operation-parameters> <image-name> <dest-image-name> split <percentage>
```

#### Usage

```shell
blur husky husky-blur split 50
```

```shell
sharpen husky husky-sharpen split 70
```

```shell
level-adjust 20 150 255 husky husky-level-adjust split 50
```

```shell
color-correct husky husky-color-correct split 50
```

```shell
brighten 10 husky husky-brighten-10 split 50
```

```shell
darken 10 husky husky-darken-10 split 50
```

```shell
intensity-component husky husky-intensity split 30
```

```shell
luma-component husky husky-luma split 60
```

```shell
value-component husky husky-value split 80
```

```shell
red-component husky husky-red split 50
```

```shell
blue-component husky husky-blue split 50
```

```shell
green-component husky husky-green split 50
```

### Run

Load and run the script commands in the specified file.

#### Syntax

```
run <path-to-file-name>.<extension>
```

#### Usage with .JAR file

```shell
run script-husky.txt
```

#### or running the application locally

```shell
run all-commands.txt
```

### Exit

Exit the image processing application.

#### Syntax

```
exit
```

#### Usage

```shell
exit
```

## Using Graphical user interface

### General Instructions

1. From the drop-down menu select the operation to be performed.
1. The operations panel will get populated with text fields corresponding to the parameters 
   required for the operation. 
1. Enter all the parameters required by the operation.
1. If the operation supports preview, The view mode panel appears and allows to view the 
   preview of the operation. The current view mode is displayed on the button. Press it to shift 
   to another view mode.
1. If the selected mode(Displayed on the button) is preview mode then enter the percentage of 
   the image on which the operation must be shown.
1. If the selected mode(Displayed on the button) is full image mode then the operation is 
   applied to the entire image.
1. Once all the details are entered press execute.
1. The image will be displayed below on the left and its corresponding histogram will be 
   displayed on the right.

### Operation wise instructions

#### Load
1. From the drop down menu select load.
1. Enter the name of the image that must be loaded with extension in srcImage.format text field. 
1. Enter image name with which it must stored in the program for future use in the 
   destinationImageName field.
1. Press execute button.

#### Save

1. From the drop down menu select save.
1. Enter the name and extension with which the image must be stored destinationImageName.format 
   text field.
1. Enter image name which has to be saved in the srcImageName field.
1. Press execute button.

#### Red Component

1. From the drop down menu select red-component.
1. Enter the name of the image on which the operation must be performed in the srcImage text 
   field.
1. Enter image name with which it has to be stored in the program for future use in the 
   destinationImageName field.
1. Press execute button.

#### Green Component

1. From the drop down menu select green-component.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. Press execute button.

#### Blue Component

1. From the drop down menu select blue-component.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. Press execute button.

#### Vertical Flip

1. From the drop down menu select vertical-flip.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. Press execute button.

#### Horizontal Flip

1. From the drop down menu select horizontal-flip.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. Press execute button.

#### Blur

1. From the drop down menu select blur.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. If you want to view the image in preview mode, press the toggle button in the view mode panel
   and enter a value between 0-100 on which the operation must be applied.
1. Press execute button.

#### Sharpen

1. From the drop down menu select sharpen.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. If you want to view the image in preview mode, press the toggle button in the view mode panel
   and enter a value between 0-100 on which the operation must be applied.
1. Press execute button.

#### Sepia

1. From the drop down menu select sepia.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. If you want to view the image in preview mode, press the toggle button in the view mode panel
   and enter a value between 0-100 on which the operation must be applied.
1. Press execute button.

#### Luma

1. From the drop down menu select luma-component.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. If you want to view the image in preview mode, press the toggle button in the view mode panel
   and enter a value between 0-100 on which the operation must be applied.
1. Press execute button.

#### Compress

1. From the drop down menu select compress.
1. Enter a value between 0-100  to indicate the percentage by which the image must be compressed.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. Press execute button.

#### Color Correct

1. From the drop down menu select color-correct.
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. If you want to view the image in preview mode, press the toggle button in the view mode panel
   and enter a value between 0-100 on which the operation must be applied.
1. Press execute button.

#### Level Adjust

1. From the drop down menu select level-adjust.
1. Enter black value in black-value text field.
1. Enter mid value in mid-value text field.
1. Enter white value in the white-value text field.
1. The above numbers should be between 0-255.
1. The values should be such that black < mid < white. 
1. Enter the name of the image on which the operation must be performed in the srcImage text
   field.
1. Enter image name with which it has to be stored in the program for future use in the
   destinationImageName field.
1. If you want to view the image in preview mode, press the toggle button in the view mode panel 
   and enter a value between 0-100 on which the operation must be applied. 
1. Press execute button.
