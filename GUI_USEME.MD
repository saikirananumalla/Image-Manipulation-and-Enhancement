# Commands supported by the gui application.

## Loading an Image.

To load an image, use the `Upload Image` button which will open a file chooser and the user can 
choose the image to be worked on.

Prerequisite: The image should be valid and should be of type png, jpg or ppm.

### Note: `Upload Image` button is the prerequisite for all the buttons listed below.

## `Undo Edit` button

This button is used to undo the last operation done by the user by one step. 

## `Revert to original` button

This button is used to revert to the original image which is the latest loaded image. This image 
can be retrieved even after many operations are done.

## `Save File` button. Can be used to have the image saved to your file system. 

To save an image, use the `Save File` button which opens a file chooser and the user can save the 
resultant file (non-preview operations) to file system.

Prerequisite: An image must be loaded in and 0 or more operations must be done to be saved.

## `Compress Image` button.

To generate the compressed version of an image, use the `compress` button which opens a window to 
input the percentage by which the image has to be compressed by. Must be between 0-100 inclusive.

## `Red component` button.

To generate the red component of an image, use the `Red Component` button to generate the red 
component of the latest non-preview image.

## `Green component` button.

To generate the green component of an image, use the `Green Component` button to generate the green
component of the latest non-preview image.

## `Blue component` button.

To generate the blue component of an image, use the `Blue Component` button to generate the blue
component of the latest non-preview image.

## `Vertical Flip` button.

To vertically flip an image, use the `Vertical Flip` button to generate the vertical flip
of the latest non-preview image.

## `Horizontal Flip` button.

To horizontally flip an image, use the `Horizontal Flip` button to generate the horizontal flip
of the latest non-preview image.

## `Levels Adjust` button.

To generate the levels-adjusted version of an image, use the `Level Adjust` button which opens 
three windows to input the b, m, w values in order. These values must be ascending in order and all
three values must be between 0-255 inclusive.

## Levels adjust split preview radio button.

To generate the level adjusted version of an image but to just preview the resultant image
(to refer, to juxtapose, etc.), use the `Split Preview` radio button beside the levels adjust
button which opens four windows to input the b, m, w and the split percentage values in order. 
The b, m, w values must be ascending in order and all three values must be between 0-255 inclusive.
The split percentage must be between 0-100 inclusive. This preview image cannot be saved or can
chain other operations on it. Any operations done on previewed image will apply the operation on
the image which was in focus just before the preview operation.

## `Greyscale` button.

To generate the greyscale version of an image, use the `Greyscale` button.

## Greyscale split preview radio button.

To generate the greyscale version of an image but to just preview the resultant image
(to refer, to juxtapose, etc.), use the `Split Preview` radio button beside the greyscale
button which opens a window to input the split percentage value.
The split percentage must be between 0-100 inclusive. This preview image cannot be saved or can
chain other operations on it. Any operations done on previewed image will apply the operation on 
the image which was in focus just before the preview operation.

## `Blur Image` button.

To generate the blurred version of an image, use the `Blur Image` button.

## Blur split preview radio button.

To generate the blurred version of an image but to just preview the resultant image
(to refer, to juxtapose, etc.), use the `Split Preview` radio button beside the Blur Image
button which opens a window to input the split percentage value.
The split percentage must be between 0-100 inclusive. This preview image cannot be saved or can
chain other operations on it. Any operations done on previewed image will apply the operation on
the image which was in focus just before the preview operation.

## `Sharpen Image` button.

To generate the sharpened version of an image, use the `Sharpen Image` button.

## Sharpen split preview radio button.

To generate the sharpened version of an image but to just preview the resultant image
(to refer, to juxtapose, etc.), use the `Split Preview` radio button beside the Sharpen Image
button which opens a window to input the split percentage value.
The split percentage must be between 0-100 inclusive. This preview image cannot be saved or can
chain other operations on it. Any operations done on previewed image will apply the operation on
the image which was in focus just before the preview operation.

## `Color Correct` button.

To generate the color corrected version of an image, use the `Color Correct` button.

## Color Correct split preview radio button.

To generate the color corrected version of an image but to just preview the resultant image
(to refer, to juxtapose, etc.), use the `Split Preview` radio button beside the Color Correct
button which opens a window to input the split percentage value.
The split percentage must be between 0-100 inclusive. This preview image cannot be saved or can
chain other operations on it. Any operations done on previewed image will apply the operation on
the image which was in focus just before the preview operation.

## `Sepia` button.

To generate the sepia tone of an image, use the `sepia` button.

## Sepia split preview radio button.

To generate the sepia version of an image but to just preview the resultant image
(to refer, to juxtapose, etc.), use the `Split Preview` radio button beside the sepia
button which opens a window to input the split percentage value.
The split percentage must be between 0-100 inclusive. This preview image cannot be saved or can
chain other operations on it. Any operations done on previewed image will apply the operation on
the image which was in focus just before the preview operation.
