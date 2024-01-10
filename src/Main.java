import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import controller.GUIController;
import controller.ImageController;
import model.GUIImageEditor;
import model.GUIImageEditorImpl;
import model.ImageEditor;
import model.ImageEditorImpl;
import view.JFrameView;
import view.TextView;

/**
 * Public main class to run the image processing application. Initialises the controller
 * and the view classes needed for the execution.
 */
public class Main {

  /**
   * Main class to start the application. This class initialises the model, the controller and the
   * view. Supports a file argument to execute a script file.
   *
   * @param args The args passed to the main function for start up.
   */
  public static void main(String... args) {

    Readable r = null;


    if (args.length == 0) {
      GUIImageEditor editor = new GUIImageEditorImpl();
      JFrameView jFrameView = new JFrameView();
      GUIController controller = new GUIController(editor, jFrameView);
      return;
    }
    else if (args.length == 2 && args[0].equals("-file")) {
      r = new StringReader(String.format("run %s \nquit", args[1]));
    } else if (args[0].equals("-text")) {
      r = new InputStreamReader(System.in);
    }
    else {
      System.out.println("Invalid Arguments");
      return;
    }

    TextView view = new TextView(System.out);

    ImageEditor editor = new ImageEditorImpl();
    ImageController imageController = new ImageController(view, r, editor);
    try {
      imageController.start();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
