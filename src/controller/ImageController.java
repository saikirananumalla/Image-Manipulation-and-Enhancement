package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Image;
import model.ImageEditor;
import view.TextView;

import static util.Constants.BLUE_COMPONENT;
import static util.Constants.BLUR;
import static util.Constants.BRIGHTEN;
import static util.Constants.COLOR_CORRECT;
import static util.Constants.COMPRESS;
import static util.Constants.GREEN_COMPONENT;
import static util.Constants.HISTOGRAM;
import static util.Constants.HORIZONTAL_FLIP;
import static util.Constants.INTENSITY_COMPONENT;
import static util.Constants.LEVELS_ADJUST;
import static util.Constants.LOAD;
import static util.Constants.LUMA_COMPONENT;
import static util.Constants.RED_COMPONENT;
import static util.Constants.RGB_COMBINE;
import static util.Constants.RGB_SET;
import static util.Constants.RGB_SPLIT;
import static util.Constants.RUN;
import static util.Constants.SAVE;
import static util.Constants.SEPIA;
import static util.Constants.SHARPEN;
import static util.Constants.SPLIT;
import static util.Constants.SPLIT_SET;
import static util.Constants.SRC_DEST_SET;
import static util.Constants.VALUE_COMPONENT;
import static util.Constants.VERTICAL_FLIP;

/**
 * Controller class for handling the image operations. Takes in several inputs of various
 * number of arguments and calls the image methods appropriately. Has methods to load, save,
 * extract several components of a specific image, flipping, brightening, splitting and combining
 * into different channels, blurring and sharpening of images. Also has methods which call the
 * appropriate method in the model to generate a sepia toned image from a given image.
 */
public class ImageController extends AbstractController {

  private final Map<String, Image> aliasImageMap;

  private boolean isListening;

  private final TextView textView;

  private final Readable in;

  private final ImageEditor editor;

  /**
   * Constructor for the Image controller class. Initialises a map which maintains the state of the
   * application with the alias names the image objects.
   */
  public ImageController(TextView textView, Readable in, ImageEditor editor) {

    if (textView == null || in == null || editor == null) {
      throw new IllegalArgumentException("null not allowed");
    }

    this.editor = editor;
    this.isListening = true;
    this.aliasImageMap = new HashMap<>();
    this.textView = textView;
    this.in = in;
  }

  /**
   * This method listens to the user inputs. Starts a scanner and starts listening to the
   * corresponding user input and calls process on input.
   */
  public void start() throws IOException {
    Scanner scanner = new Scanner(this.in);

    while (isListening) {
      String userInput = scanner.nextLine();
      processUserInput(userInput.strip());
    }
  }


  /**
   * Process the user input string by first validating command based on its first index, split it
   * based on white spaces in between and map to the corresponding method, ignore commands starting
   * with # and stop listening to user input if we receive quit command.
   *
   * @param s input string.
   */
  private void processUserInput(String s) throws IOException {
    if (s.isEmpty() || s.startsWith("#")) {
      return;
    } else if (s.equals("quit")) {
      isListening = false;
      textView.display("Exiting the application");
      return;
    }

    String[] command = s.split("\\s+");

    if (!validCommand(command[0], command)) {
      textView.display("Invalid command");
      return;
    }

    try {
      mapCommandToFunction(command[0], command);
    } catch (Exception e) {
      textView.display("Error : " + e.getMessage());
    }
  }


  /**
   * This helper method runs a script file provided by the user. The script is then scanned and
   * the commands are run line by line. Any line which starts with a # is considered a comment and
   * will be ignored.
   *
   * @param command The information about the script field, namely, the command and the script file
   *                location. So, a valid command might be "run /script/script.txt".
   */
  private void runScriptFile(String[] command) throws IOException {
    // take file input and process line by line.
    Scanner sc;
    String filename = command[1];

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      textView.display("File " + filename + " not found!");
      return;
    }

    while (sc.hasNextLine()) {
      processUserInput(sc.nextLine());
    }
  }


  /**
   * Method to load image data from a specified file location. Location should include the image
   * name as well. This also sets the image object with an alias name.
   *
   * @param srcPath   The destination path of the image from the root.
   * @param aliasName The alias name of the image given by the user. This is a string.
   */
  private void load(String srcPath, String aliasName) throws IllegalArgumentException, IOException {
    validateFile(srcPath);
    Image newImage = editor.load(srcPath);
    this.addToMap(aliasName, newImage);
  }

  /**
   * Method to save an image to a specified path. the path should include the format of the file
   * as well. This also sets the image object with an alias name.
   *
   * @param saveDestinationPath The path where the image is to be saved.
   * @param aliasName           The alias name of the image given by the user. This is a string.
   */
  private void save(String saveDestinationPath, String aliasName)
          throws IllegalArgumentException, IOException {
    validateFileExtension(saveDestinationPath);
    checkAliasNameExists(aliasName);
    saveByExtension(aliasImageMap.get(aliasName), saveDestinationPath, editor);
  }


  /**
   * Extract the red component of the image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to extract the
   *                        specified component of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void redComponent(String sourceAliasName, String targetAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image redComponentImage = editor.redComponent(aliasImageMap.get(sourceAliasName));
    addToMap(targetAliasName, redComponentImage);
  }

  /**
   * Extract the green component of the image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to extract the
   *                        specified component of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void greenComponent(String sourceAliasName, String targetAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image greenComponentImage = editor.greenComponent(aliasImageMap.get(sourceAliasName));
    addToMap(targetAliasName, greenComponentImage);
  }

  /**
   * Extract the blue component of the image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to extract the
   *                        specified component of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void blueComponent(String sourceAliasName, String targetAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image blueComponentImage = editor.blueComponent(aliasImageMap.get(sourceAliasName));
    addToMap(targetAliasName, blueComponentImage);
  }

  /**
   * Extract the value component of the image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to extract the
   *                        specified component of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void valueComponent(String sourceAliasName, String targetAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image valueComponentImage = editor.valueComponent(aliasImageMap.get(sourceAliasName));
    addToMap(targetAliasName, valueComponentImage);
  }

  /**
   * Extract the intensity component of the image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to extract the
   *                        specified component of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void intensityComponent(String sourceAliasName, String targetAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image intensityComponentImage = editor.intensityComponent(aliasImageMap.get(sourceAliasName));
    addToMap(targetAliasName, intensityComponentImage);
  }

  /**
   * Extract the luma component of the image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to extract the
   *                        specified component of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void lumaComponent(String sourceAliasName, String targetAliasName, int split) {
    checkAliasNameExists(sourceAliasName);
    Image original = aliasImageMap.get(sourceAliasName);
    Image lumaComponentImage = editor.lumaComponent(original);
    addToMap(targetAliasName, editor.getSplitView(original, lumaComponentImage, split));
  }

  /**
   * Flip the image horizontally. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void horizontalFlip(String sourceAliasName, String targetAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image horizontalFlipImage = editor.horizontalFlip(aliasImageMap.get(sourceAliasName));
    addToMap(targetAliasName, horizontalFlipImage);
  }

  /**
   * Flip the image vertically. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void verticalFlip(String sourceAliasName, String targetAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image verticalFlipImage = editor.verticalFlip(aliasImageMap.get(sourceAliasName));
    addToMap(targetAliasName, verticalFlipImage);
  }

  /**
   * Blur the image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void blurImage(String sourceAliasName, String targetAliasName, int split) {
    checkAliasNameExists(sourceAliasName);
    Image original = aliasImageMap.get(sourceAliasName);
    Image blurImage = editor.blur(original);
    addToMap(targetAliasName, editor.getSplitView(original, blurImage, split));
  }

  /**
   * Sharpen the image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void sharpenImage(String sourceAliasName, String targetAliasName, int split) {
    checkAliasNameExists(sourceAliasName);
    Image original = aliasImageMap.get(sourceAliasName);
    Image sharpenImage = editor.sharpen(original);
    addToMap(targetAliasName, editor.getSplitView(original, sharpenImage, split));
  }

  /**
   * Convert the image to a sepia tone. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void toSepia(String sourceAliasName, String targetAliasName, int split) {
    checkAliasNameExists(sourceAliasName);
    Image original = aliasImageMap.get(sourceAliasName);
    Image sepiaImage = editor.toSepia(original);
    this.addToMap(targetAliasName, editor.getSplitView(original, sepiaImage, split));
  }

  /**
   * Increase or decrease the brightness of an image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   * @param value           The value by the image should be brightened or darkened.
   */
  private void brighten(String sourceAliasName, String targetAliasName, int value) {
    checkAliasNameExists(sourceAliasName);
    Image brightenedImage = editor.brighten(aliasImageMap.get(sourceAliasName), value);
    this.addToMap(targetAliasName, brightenedImage);
  }

  /**
   * Split the image to three components for RGB. Will result in three image objects.
   * This also sets the newly created images object with other alias names provided.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param redAliasName    The target name of the result. This will be where the newly created
   *                        red component image will be pointed to.
   * @param greenAliasName  The target name of the result. This will be where the newly created
   *                        *                  green component image will be pointed to.
   * @param blueAliasName   The target name of the result. This will be where the newly created
   *                        *                 blue component image will be pointed to.
   */
  private void rgbSplit(String sourceAliasName, String redAliasName, String greenAliasName,
                        String blueAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image image = aliasImageMap.get(sourceAliasName);
    addToMap(redAliasName, editor.redComponent(image));
    addToMap(greenAliasName, editor.greenComponent(image));
    addToMap(blueAliasName, editor.blueComponent(image));
  }


  /**
   * Combine three components for RGB into a single image. Will result in a new image.
   * This also sets the newly created image object with an alias name.
   *
   * @param targetAliasName The target image. This alias will be used to store the image.
   * @param redAliasName    The red component of the input.
   * @param greenAliasName  The green component of the input.
   * @param blueAliasName   The blue component of the input.
   */
  private void rgbCombine(String targetAliasName, String redAliasName, String greenAliasName,
                          String blueAliasName) {

    checkAliasNameExists(redAliasName);
    checkAliasNameExists(greenAliasName);
    checkAliasNameExists(blueAliasName);


    addToMap(targetAliasName, editor.rgbCombine(aliasImageMap.get(redAliasName),
            aliasImageMap.get(greenAliasName),
            aliasImageMap.get(blueAliasName))
    );
  }

  /**
   * Compress the image by a given percentage. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   * @param percent         The percentage of compression
   */
  private void compress(String sourceAliasName, String targetAliasName, int percent) {
    checkAliasNameExists(sourceAliasName);
    Image compressedImage = editor.compress(aliasImageMap.get(sourceAliasName), percent);
    this.addToMap(targetAliasName, compressedImage);
  }

  /**
   * Compress the image by a given percentage. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void colorCorrect(String sourceAliasName, String targetAliasName, int split) {
    checkAliasNameExists(sourceAliasName);
    Image original = aliasImageMap.get(sourceAliasName);
    Image colorCorrectedImage = editor.colorCorrect(original);
    this.addToMap(targetAliasName, editor.getSplitView(original, colorCorrectedImage, split));
  }

  /**
   * Adjusts the level of the image by a given parameters of b, m and w. Will result in a
   * new image object. This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   * @param b               The shadow value.
   * @param m               The mid value.
   * @param w               The high value.
   */
  private void levelsAdjust(String sourceAliasName, String targetAliasName,
                            int b, int m, int w, int split) {
    checkAliasNameExists(sourceAliasName);
    Image original = aliasImageMap.get(sourceAliasName);
    Image levelAdjustedImage;
    levelAdjustedImage = editor.levelsAdjust(original, b, m, w);
    this.addToMap(targetAliasName, editor.getSplitView(original, levelAdjustedImage, split));
  }

  /**
   * Get the rgb values histogram as an image for given image. Will result in a new image object.
   * This also sets the newly created image object with an alias name.
   *
   * @param sourceAliasName The source image. This image will be used as a base to run the
   *                        processing of the image.
   * @param targetAliasName The target name of the result. This will be where the newly created
   *                        object will be pointed to.
   */
  private void getHistogram(String sourceAliasName, String targetAliasName) {
    checkAliasNameExists(sourceAliasName);
    Image histogram = editor.getHistogram(aliasImageMap.get(sourceAliasName));
    this.addToMap(targetAliasName, histogram);
  }

  /**
   * Check if a valid image exists at a particular location.
   *
   * @param srcPath The path to be checked if a valid image is located there.
   */
  private void validateFile(String srcPath) {
    validateFileExtension(srcPath);
    checkFileExists(srcPath);
  }

  /**
   * Helper method to check whether a file exists at the given path.
   *
   * @param srcPath The path given by the view.
   */
  private static void checkFileExists(String srcPath) {
    File file = new File(srcPath);

    if (!file.exists()) {
      throw new IllegalArgumentException("file does not exist at : " + srcPath);
    }
  }

  /**
   * Checks the existence of an alias name.
   *
   * @param aliasName The alias name to be checked.
   */
  private void checkAliasNameExists(String aliasName) {

    if (!aliasImageMap.containsKey(aliasName)) {

      throw new IllegalArgumentException("The source image name does not exist");
    }
  }

  /**
   * Add a key value pair of string and image to the map.
   *
   * @param aliasName The key alias name.
   * @param image     The image object.
   */
  private void addToMap(String aliasName, Image image) {
    aliasImageMap.put(aliasName, image);
  }

  /**
   * Helper method to validate the commands passed by the user. This command is checked against a
   * collection of enums to check the validity.
   *
   * @param command The command with the keyword and command arguments.
   * @param s       command of the string the first value.
   */
  private boolean validCommand(String s, String[] command) {
    // check the command belongs to which category of map and validate its corresponding input size.
    if (SRC_DEST_SET.contains(s)) {
      return command.length == 3;
    } else if (RGB_SET.contains(s)) {
      return command.length == 5;
    } else if (BRIGHTEN.equals(s)) {
      return command.length == 4 && isInteger(command[1]);
    } else if (COMPRESS.equals(s)) {
      return command.length == 4 && isValidPercentage(command[1]);
    } else if (s.equals(RUN)) {
      return command.length == 2;
    } else if (SPLIT_SET.contains(s)) {
      if (command.length == 5) {
        return SPLIT.equals(command[3]) && isValidPercentage(command[4]);
      }
      return command.length == 3;
    } else if (LEVELS_ADJUST.equals(s)) {
      if (command.length >= 4 && isNotAscending(command[1], command[2], command[3])) {
        return false;
      }
      if (command.length == 8) {
        return SPLIT.equals(command[6]) && isValidPercentage(command[7]);
      }
      return command.length == 6 && isPixel(command[1]) &&
              isPixel(command[2]) && isPixel(command[3]);
    }
    return false;
  }

  /**
   * Helper method to validate the file extension is valid at the particular position.
   *
   * @param srcPath The path given by the view.
   */
  private void validateFileExtension(String srcPath) {
    if (inValidExtension(srcPath)) {
      throw new IllegalArgumentException("Invalid file extension");
    }
  }



  private void mapCommandToFunction(String s, String[] command) throws IOException {
    switch (s) {
      case LOAD:
        load(command[1], command[2]);
        break;
      case SAVE:
        save(command[1], command[2]);
        break;
      case RED_COMPONENT:
        redComponent(command[1], command[2]);
        break;
      case GREEN_COMPONENT:
        greenComponent(command[1], command[2]);
        break;
      case BLUE_COMPONENT:
        blueComponent(command[1], command[2]);
        break;
      case VALUE_COMPONENT:
        valueComponent(command[1], command[2]);
        break;
      case INTENSITY_COMPONENT:
        intensityComponent(command[1], command[2]);
        break;
      case LUMA_COMPONENT:
        if (command.length == 3) {
          lumaComponent(command[1], command[2], 100);
        } else {
          lumaComponent(command[1], command[2], Integer.parseInt(command[4]));
        }
        break;
      case HORIZONTAL_FLIP:
        horizontalFlip(command[1], command[2]);
        break;
      case VERTICAL_FLIP:
        verticalFlip(command[1], command[2]);
        break;
      case BLUR:
        if (command.length == 3) {
          blurImage(command[1], command[2], 100);
        } else {
          blurImage(command[1], command[2], Integer.parseInt(command[4]));
        }
        break;
      case SHARPEN:
        if (command.length == 3) {
          sharpenImage(command[1], command[2], 100);
        } else {
          sharpenImage(command[1], command[2], Integer.parseInt(command[4]));
        }
        break;
      case SEPIA:
        if (command.length == 3) {
          toSepia(command[1], command[2], 100);
        } else {
          toSepia(command[1], command[2], Integer.parseInt(command[4]));
        }
        break;
      case BRIGHTEN:
        brighten(command[2], command[3], Integer.parseInt(command[1]));
        break;
      case RGB_SPLIT:
        rgbSplit(command[1], command[2], command[3], command[4]);
        break;
      case RGB_COMBINE:
        rgbCombine(command[1], command[2], command[3], command[4]);
        break;
      case HISTOGRAM:
        getHistogram(command[1], command[2]);
        break;
      case RUN:
        runScriptFile(command);
        break;
      case LEVELS_ADJUST:
        if (command.length == 6) {
          levelsAdjust(command[4], command[5], Integer.parseInt(command[1]),
                  Integer.parseInt(command[2]),
                  Integer.parseInt(command[3]), 100);
        } else {
          levelsAdjust(command[4], command[5], Integer.parseInt(command[1]),
                  Integer.parseInt(command[2]),
                  Integer.parseInt(command[3]), Integer.parseInt(command[7]));
        }
        break;
      case COLOR_CORRECT:
        if (command.length == 3) {
          colorCorrect(command[1], command[2], 100);
        } else {
          colorCorrect(command[1], command[2], Integer.parseInt(command[4]));
        }
        break;
      case COMPRESS:
        compress(command[2], command[3], Integer.parseInt(command[1]));
        break;
      default:
        textView.display("Invalid Command - Not supported");
        break;
    }
  }
}
