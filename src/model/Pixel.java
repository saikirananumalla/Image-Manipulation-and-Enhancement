package model;

import java.util.Objects;

import static util.Constants.MAX_PIXEL_VALUE;
import static util.Constants.MIN_PIXEL_VALUE;

/**
 * The class for the pixel. Contains the methods to retrieve the multiple components of a pixel,
 * to retrieve the different statistics of a pixel, to brighten or darken a pixel, to convert to
 * greyscale and sepia tones.
 */
public class Pixel {
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor method for the pixel class. Checks if the incoming values are under a specific
   * range of 0-255.
   *
   * @param red   The red value of the pixel.
   * @param green The green value of the pixel.
   * @param blue  The blue value of the pixel.
   */
  public Pixel(int red, int green, int blue) {
    this.red = clampPixelValue(red);
    this.green = clampPixelValue(green);
    this.blue = clampPixelValue(blue);
  }

  /**
   * Return the red value of the pixel.
   *
   * @return The red component of the pixel.
   */
  public int getRed() {
    return red;
  }

  /**
   * Return the green value of the pixel.
   *
   * @return The green component of the pixel.
   */
  public int getGreen() {
    return green;
  }

  /**
   * Return the blue value of the pixel.
   *
   * @return The blue component of the pixel.
   */
  public int getBlue() {
    return blue;
  }

  /**
   * Return a new pixel which only represents the red component of the pixel.
   *
   * @return The new red pixel of the original pixel.
   */
  public Pixel getPixelWithRed() {
    return new Pixel(red, 0, 0);
  }

  /**
   * Return a new pixel which only represents the green component of the pixel.
   *
   * @return The new green pixel of the original pixel.
   */
  public Pixel getPixelWithGreen() {
    return new Pixel(0, green, 0);
  }

  /**
   * Return a new pixel which only represents the blue component of the pixel.
   *
   * @return The new blue pixel of the original pixel.
   */
  public Pixel getPixelWithBlue() {
    return new Pixel(0, 0, blue);
  }

  /**
   * Return a new pixel which only represents the value component of the pixel.
   *
   * @return The new value pixel of the original pixel.
   */
  public Pixel getPixelWithValueComp() {
    int valuePixel = Math.max(red, Math.max(green, blue));
    return getNewPixel(valuePixel);
  }

  /**
   * Return a new pixel which only represents the intensity component of the pixel.
   *
   * @return The new intensity pixel of the original pixel.
   */
  public Pixel getPixelWithIntensityComp() {
    return getNewPixel((red + green + blue) / 3);
  }

  /**
   * Return a new pixel which only represents the luma component of the pixel. This also works as
   * a greyscale converter.
   *
   * @return The new luma pixel of the original pixel.
   */
  public Pixel getPixelWithLumaComp() {
    int lumaValue = ((int) (
            0.2126 * red + 0.7152 * green + 0.0722 * blue));
    return getNewPixel(lumaValue);
  }

  /**
   * Return a new pixel which only represents the brightened version of the pixel.
   *
   * @param brightnessValue The value by the pixel should be brightened.
   * @return The new brightened pixel of the original pixel.
   */
  public Pixel brighten(int brightnessValue) {
    return new Pixel(clampPixelValue(red + brightnessValue),
            clampPixelValue(green + brightnessValue),
            clampPixelValue(blue + brightnessValue));
  }

  /**
   * Return a new pixel which only represents the sepia version of the pixel.
   *
   * @return The new sepia version pixel of the original pixel.
   */
  public Pixel toSepia() {
    int newRedValue = ((int) (
            0.393 * red + 0.769 * green + 0.189 * blue));
    int newGreenValue = ((int) (
            0.349 * red + 0.686 * green + 0.168 * blue));
    int newBlueValue = ((int) (
            0.272 * red + 0.534 * green + 0.131 * blue));
    return new Pixel(clampPixelValue(newRedValue),
            clampPixelValue(newGreenValue),
            clampPixelValue(newBlueValue));
  }

  /**
   * Add the given rgb values to the red,green,blue values of this pixel and clamp as necessary.
   *
   * @param r red value to be added
   * @param g green value to be added
   * @param b blue value to be added
   * @return new Pixel with the resulting channel values
   */
  public Pixel offset(int r, int g, int b) {
    return new Pixel(clampPixelValue(red + r), clampPixelValue(green + g),
            clampPixelValue(blue + b));
  }

  /**
   * Level adjust the pixel and generate a new pixel.
   *
   * @param p The x square coefficient in the transformation.
   * @param q The x coefficient in the transformation.
   * @param r The constant in the transformation.
   * @return The new transformed pixel.
   */
  public Pixel levelsAdjust(double p, double q, double r) {

    int br = this.getRed();
    int bg = this.getGreen();
    int bb = this.getBlue();
    return new Pixel(getLevelAdjustedValue(p, q, r, br),
            getLevelAdjustedValue(p, q, r, bg),
            getLevelAdjustedValue(p, q, r, bb));
  }

  /**
   * The equals method which is overridden accordingly. We consider a pixel is equal when all the
   * channel values are equal to each other.
   *
   * @param o The other pixel object.
   * @return The boolean representing the equality. True if yes and false if no.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    return red == pixel.red && green == pixel.green && blue == pixel.blue;
  }

  /**
   * The hashcode implementation of the pixel class.
   *
   * @return The hashed value of the pixel channel values.
   */
  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }

  /**
   * The helper method which clamps a pixel value in the limit of 0 to 255. If the value is greater
   * than 255, it will be clamped to the max value which is 255. If the pixel is less than 0,
   * it will be clamped to 0 as it is the minimum value.
   *
   * @param pixelValue The pixel value which should be clamped accordingly.
   * @return The clamped pixel value.
   */
  private int clampPixelValue(int pixelValue) {

    if (pixelValue < MIN_PIXEL_VALUE) {

      return MIN_PIXEL_VALUE;
    } else if (pixelValue > MAX_PIXEL_VALUE) {

      return MAX_PIXEL_VALUE;
    }
    return pixelValue;
  }

  /**
   * This helper method returns a new pixel, which has all the channel values equal which is the
   * user input.
   *
   * @param value The user input. This value will be put in all channels of the pixel. This will
   *              result in a greyscale image.
   * @return The new pixel which has all the three channel values as the input provided.
   */
  private Pixel getNewPixel(int value) {

    return new Pixel(value, value, value);
  }

  /**
   * Calculates the appropriate value given the transformation function.
   *
   * @param p     The x square coefficient in the transformation.
   * @param q     The x coefficient in the transformation.
   * @param r     The constant in the transformation.
   * @param value The value of the pixel in the specified channel.
   * @return The new value which should be the output after applying the transformation function..
   */
  private int getLevelAdjustedValue(double p, double q, double r, int value) {

    double y = (p * value * value) + (q * value) + (r);
    return clampPixelValue((int) Math.round(y));
  }
}
