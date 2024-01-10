package view;

import java.io.IOException;

/**
 * A view for text based input, it takes a string input and writes it to the appendable object
 * provided to us.
 */
public class TextView {

  private final Appendable out;

  public void display(String s) throws IOException {
    this.out.append(s).append(System.getProperty("line.separator"));
  }

  /**
   * Create a new text view instance with the given appendable object. This lets the program
   * start writing to the output.
   *
   * @param out Appendable
   */
  public TextView(Appendable out) {
    this.out = out;
  }

}
