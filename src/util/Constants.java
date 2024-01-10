package util;

import java.util.Set;

/**
 * A class to store all the global constants being used in the application including commands, and
 * the set of commands with similar inputs, file extensions.
 */
public class Constants {
  public static final String LOAD = "load";
  public static final String SAVE = "save";
  public static final String RED_COMPONENT = "red-component";
  public static final String GREEN_COMPONENT = "green-component";
  public static final String BLUE_COMPONENT = "blue-component";
  public static final String VALUE_COMPONENT = "value-component";
  public static final String INTENSITY_COMPONENT = "intensity-component";
  public static final String LUMA_COMPONENT = "luma-component";
  public static final String HORIZONTAL_FLIP = "horizontal-flip";
  public static final String VERTICAL_FLIP = "vertical-flip";
  public static final String BRIGHTEN = "brighten";
  public static final String RGB_SPLIT = "rgb-split";
  public static final String RGB_COMBINE = "rgb-combine";
  public static final String BLUR = "blur";
  public static final String SHARPEN = "sharpen";
  public static final String SEPIA = "sepia";
  public static final String RUN = "run";
  public static final String HISTOGRAM = "histogram";
  public static final String COLOR_CORRECT = "color-correct";
  public static final String LEVELS_ADJUST = "levels-adjust";
  public static final String COMPRESS = "compress";
  public static final String SPLIT = "split";
  public static final String PPM = "ppm";

  public static final String PNG = "png";

  public static final String JPG = "jpg";

  public static final int MAX_PIXEL_VALUE = 255;

  public static final int MIN_PIXEL_VALUE = 0;

  public static final Set<String> SRC_DEST_SET = Set.of(LOAD, SAVE, RED_COMPONENT,
          GREEN_COMPONENT, BLUE_COMPONENT, HISTOGRAM, HORIZONTAL_FLIP, VERTICAL_FLIP);

  public static final Set<String> SPLIT_SET = Set.of(VALUE_COMPONENT, INTENSITY_COMPONENT,
          LUMA_COMPONENT, BLUR, SHARPEN, SEPIA, COLOR_CORRECT);

  public static final Set<String> RGB_SET = Set.of(RGB_COMBINE, RGB_SPLIT);

}
