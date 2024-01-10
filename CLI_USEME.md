# Commands supported by the application.

## Loading an Image.

To load an image, use the `load` command followed by the path to the image and the image alias name.

For example, `load flower.png flower-test` is a valid command.

Prerequisite: The image path should exist and the alias name should be single word with no space.

## `load` command is the prerequisite for all the commands listed below.

## Saving an Image. Use this after any command below to have the image saved to your file system

To save an image, use the `save` command followed by the desired path to the image and the image 
alias name.

For example, `save path/flower.png flower-test` is a valid command.

Prerequisite: The image path should be valid and the alias name should exist (by loading or any
other operation which generates an image).

## Retrieving the red component for an Image.

To generate the red component of an image, use the `red-component` command followed by the source
image alias name and the target image alias name.

For example, `red-component flower flower-red` is a valid command. Here, `flower` is the source
image name and `flower-red` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Retrieving the green component for an Image.

To generate the green component of an image, use the `green-component` command followed by
the source image alias name and the target image alias name.

For example, `green-component flower flower-green` is a valid command. Here, `flower` is the source
image name and `flower-green` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Retrieving the blue component for an Image.

To generate the blue component of an image, use the `blue-component` command followed by the source
image alias name and the target image alias name.

For example, `blue-component flower flower-blue` is a valid command. Here, `flower` is the source
image name and `flower-blue` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Retrieving the value component for an Image.

To generate the value component of an image, use the `value-component` command followed by the
source image alias name and the target image alias name.

For example, `value-component flower flower-value` is a valid command. Here, `flower` is the source
image name and `flower-value` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Retrieving the intensity component for an Image.

To generate the intensity component of an image, use the `intensity-component` command followed by
the source image alias name and the target image alias name.

For example, `intensity-component flower flower-intensity` is a valid command. Here, `flower` is
the source image name and `flower-intensity` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Retrieving the luma component or greyscale for an Image.

To generate the luma component of an image, use the `luma-component` command followed by the source
image alias name and the target image alias name.

For example, `luma-component flower flower-luma` is a valid command. Here, `flower` is the source
image name and `flower-luma` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Greyscale Split.

To generate the luma component of an image but to have some part of the image as the same
(to refer, to juxtapose, etc.), use the `luma-component` command followed by the source
image alias name and the target image alias name and `split` keyword followed by the percent of the 
image which should be transformed.

For example, `luma-component flower flower-luma split 60` is a valid command. Here, `flower` is the
source image name and `flower-luma` is the target image alias name. Regarding the split, the first 
sixty percent of the image will be transformed.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The argument 
following the `split` keyword should be a valid integer between 0 and 100 included.

## Brightening an Image.

To generate the brightened version of an image, use the `brighten` command followed by the source
image alias name, the value to brightened (should be positive) by and the target image alias name.

For example, `brighten 50 flower flower-brighter` is a valid command. Here, `flower` is the
source image name and `flower-brighter` is the target image alias name. 50 is the amount by which 
each pixel will be brightened by.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The value 
by which the pixel should be a valid positive Integer.

## Darkening an Image.

To generate the darkened version of an image, use the `brighten` command followed by the source
image alias name, the value to darkened (should be negative) by and the target image alias name.

For example, `brighten -50 flower flower-darken` is a valid command. Here, `flower` is the
source image name and `flower-darken` is the target image alias name. 50 is the amount by which
each pixel will be darkened by.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The value
by which the image should be darkened should be a valid negative Integer.

## Flipping an Image vertically.

To generate the vertically flipped of an image, use the `vertical-flip` command followed by the
source image alias name and the target image alias name.

For example, `vertical-flip flower flower-v` is a valid command. Here, `flower` is the source
image name and `flower-v` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Flipping an Image horizontally.

To generate the horizontally flipped of an image, use the `horizontal-flip` command followed by the
source image alias name and the target image alias name.

For example, `horizontal-flip flower flower-h` is a valid command. Here, `flower` is the source
image name and `flower-h` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Generating the sepia tone of an Image.

To generate the sepia tone of an image, use the `sepia` command followed by the source
image alias name and the target image alias name.

For example, `sepia flower flower-sepia` is a valid command. Here, `flower` is the
source image name and `flower-sepia` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Sepia Split.

To generate the sepia tone of an image but to have some part of the image as the same
(to refer, to juxtapose, etc.), use the `sepia` command followed by the source
image alias name and the target image alias name and `split` keyword followed by the percent of the
image which should be transformed.

For example, `sepia flower flower-sepia split 60` is a valid command. Here, `flower` is the
source image name and `flower-sepia` is the target image alias name. Regarding the split, the first
sixty percent of the image will be transformed.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The argument
following the `split` keyword should be a valid integer between 0 and 100 included.

## Sharpening an Image.

To generate the sharpened version of an image, use the `sharpen` command followed by the source
image alias name and the target image alias name.

For example, `sharpen flower flower-sharpen` is a valid command. Here, `flower` is the
source image name and `flower-sharpen` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Sharpen Split.

To generate the sharpened version of an image but to have some part of the image as the same
(to refer, to juxtapose, etc.), use the `sharpen` command followed by the source
image alias name and the target image alias name and `split` keyword followed by the percent of the
image which should be transformed.

For example, `sharpen flower flower-sharpen split 60` is a valid command. Here, `flower` is the
source image name and `flower-sharpen` is the target image alias name. Regarding the split, the first
sixty percent of the image will be transformed.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The argument
following the `split` keyword should be a valid integer between 0 and 100 included.

## Blurring an Image.

To generate the blurred version of an image, use the `blur` command followed by the source
image alias name and the target image alias name.

For example, `blur flower flower-blur` is a valid command. Here, `flower` is the
source image name and `flower-blur` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Blur Split.

To generate a blurred version of an image but to have some part of the image as the same
(to refer, to juxtapose, etc.), use the `blur` command followed by the source
image alias name and the target image alias name and `split` keyword followed by the percent of the
image which should be transformed.

For example, `blur flower flower-blur split 60` is a valid command. Here, `flower` is the
source image name and `flower-blur` is the target image alias name. Regarding the split, the first
sixty percent of the image will be transformed.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The argument
following the `split` keyword should be a valid integer between 0 and 100 included.

## Splitting an Image into its R, G, B components.

To generate the images which are the red, green, blue components of an image, use the `rgb-split`
command followed by the source image alias name and the target image alias names for the r, g, b
 components respectively.

For example, `rgb-split flower flower-red flower-green flower-blue` is a valid command.
Here, `flower` is the source image name and `flower-red`, `flower-green`, `flower-blue` are the
target image alias names for the red, green and the blue components of the source image.

Prerequisite: The source image alias name should exist beforehand. If the target image alias names
coincides with an existing image alias name, the existing image will get overwritten.

## Combining three images as R, G, B components of one Image.

To generate an image given its red, green, blue components, use the `rgb-combine`
command followed by the target image alias name and the source image alias names for the r, g, b
components respectively.

For example, `rgb-combine flower flower-red flower-green flower-blue` is a valid command.
Here, `flower` is the target image name and `flower-red`, `flower-green`, `flower-blue` are the
source image alias names for the red, green and the blue components of the source image.

Prerequisite: The source red, green, blue image alias names should exist beforehand. If the target
image alias names coincides with an existing image alias name, the existing image will get
overwritten.

## Run a script file from the command line.

To run a script file containing any of the commands provided, use the `run` command followed by the 
path to the script file.

For example, `run path/script.txt` is a valid command. Here `path/script.txt` is the file path to 
script file.

Prerequisite: the script file should exist in the specified path.

## Compressing Images.

To generate the compressed version of an image, use the `compress` command followed by the source
image alias name, the percent by the image should be compressed by and the target image alias name.

For example, `compress 20 flower flower-compress` is a valid command. Here, `flower` is the
source image name and `flower-compress` is the target image alias name. 20 is the percent by which
the source image is to be compressed by.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The 
compression percentage must be between 0 and 100.

## Get Histogram for an Image.

To generate the histogram of an image, use the `histogram` command followed by the source
image alias name and the target image alias name.

For example, `histogram flower flower-histogram` is a valid command. Here, `flower` is the
source image name and `flower-histogram` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Color correction for an Image.

To generate the color corrected version of an image, use the `color-correct` command followed by the
source image alias name and the target image alias name.

For example, `color-correct flower flower-color-correct` is a valid command. Here, `flower` is the
source image name and `flower-color-correct` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten.

## Color correction split.

To generate the color corrected version of an image but to have some part of the image as the same
(to refer, to juxtapose, etc.), use the `color-correct` command followed by the source
image alias name and the target image alias name and `split` keyword followed by the percent of the
image which should be transformed.

For example, `color-correct flower flower-color-correct split 60` is a valid command. Here,
`flower` is the source image name and `flower-color-correct` is the target image alias name.
Regarding the split, the first sixty percent of the image will be transformed.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The argument
following the `split` keyword should be a valid integer between 0 and 100 included.

## Levels Adjustment for an Image.

To generate the levels-adjusted version of an image, use the `levels-adjust` command followed by
the source image alias name and the target image alias name.

For example, `levels-adjust flower flower-levels-adjust` is a valid command. Here, `flower` is the
source image name and `flower-levels-adjust` is the target image alias name.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The b, m, w
values (the 2nd, 3rd, 4th arguments) should be in strict ascending order and in the
range of 0 to255.

## Levels adjust split.

To generate the level adjusted version of an image but to have some part of the image as the same
(to refer, to juxtapose, etc.), use the `levels-adjust` command followed by the source
image alias name and the target image alias name and `split` keyword followed by the percent of the
image which should be transformed.

For example, `levels-adjust 10 100 200 flower flower-level-adjust split 60` is a valid command. 
Here, `flower` is the source image name and `flower-levels-adjust` is the target image alias name.
Regarding the split, the first sixty percent of the image will be transformed.

Prerequisite: The source image alias name should exist beforehand. If the target image alias name
coincides with an existing image alias name, the existing image will get overwritten. The argument
following the `split` keyword should be a valid integer between 0 and 100 included. The b, m, w 
values (the 2nd, 3rd, 4th arguments) should be in ascending order and in the range of 0 to 255.

## Accept script file through main arguments.

run through jar -> java -jar mime.jar -file script.txt

To run a script file while launching the program, use the `-file path/to/file/script.txt` when
launching the application.

For example, `java Main -file path/to/file/script.txt` is a valid command.

Prerequisite: The path to the script file should be valid and should exist.