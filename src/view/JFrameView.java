package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import controller.GUIActionListener;
import util.ButtonConfiguration;

/**
 * The view class for the Image Editor. Has all the panels necessary for the buttons for the
 * operations, the image area and the histograms for the images.
 */
public class JFrameView extends JFrame {
  private final JPanel mainPanel;
  private final JLabel imageLabel;
  private final JLabel histogramLabel;
  private final JPanel editorPanel;
  private final List<JButton> buttons;
  private final List<JRadioButton> radioButtons;

  /**
   * The constructor for the view class. This class initialises the required panels for the buttons
   * image panel and the histogram panel. Also, setups the functionality for the prompting the user
   * to save the image before exiting.
   */
  public JFrameView() {

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    setVisible(true);
    buttons = new ArrayList<>();
    radioButtons = new ArrayList<>();

    histogramLabel = new JLabel();
    editorPanel = new JPanel();
    imageLabel = new JLabel();

    JPanel imagePanel = getPanels();

    setTitle("Image Editor");
    setSize(1075, 575);

    // Initializing main panel.
    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new GridLayout(1, 0, 10, 10));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // For the image and the editor panels.
    mainPanel.add(imagePanel);
    editorPanel.setBorder(BorderFactory.createTitledBorder("Image Edit options"));
    editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.Y_AXIS));
  }

  /**
   * Method to display a specific image and its corresponding histogram. This will insert the
   * respective images in the image panels.
   * @param image The image to be shown by the view.
   * @param histogram The histogram for the corresponding image.
   */
  public void displayImage(BufferedImage image, BufferedImage histogram) {
    imageLabel.setIcon(new ImageIcon(image));
    histogramLabel.setIcon(new ImageIcon(histogram));
  }

  /**
   * The method to add features to the view which include the buttons and their action listeners.
   * This allows the view to interact with the controller and let the action listeners to redirect
   * to the controller which implements these features as in callback methods.
   * @param actionListener The actionListener object. Will be the provided by the controller.
   */
  public void setUpButtonsAndListener(GUIActionListener actionListener) {

    List<ButtonConfiguration> buttonConfigurations = actionListener.getButtons();

    for (ButtonConfiguration config : buttonConfigurations) {
      createButton(config.getButtonName(), config.getButtonCommand(), config.getSplitPreview());
    }

    for (JButton button : buttons) {
      button.addActionListener(actionListener);
    }

    for (JRadioButton radioButton :radioButtons) {
      radioButton.addActionListener(actionListener);
    }

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent windowEvent) {
        actionListener.promptBeforeExit();
      }
    });
  }

  /**
   * This method is used to let the user know that an error has occurred and user has entered
   * an invalid value. A new pop-up window will let the user know.
   */
  public void displayError() {
    JOptionPane.showMessageDialog(mainPanel, "Invalid value(s), please retry");
  }

  /**
   * This method is used to let the user know that the user did not load an image prior to
   * operating on it. A new pop-up window will let the user know.
   */
  public void displayLoadImage() {
    JOptionPane.showMessageDialog(mainPanel, "Please load the image");
  }

  /**
   * This method is used to let the user know that the user did not load a valid image path.
   * A new pop-up window will let the user know this information.
   */
  public void displayFileError() {
    JOptionPane.showMessageDialog(mainPanel, "Invalid file location or extension");
  }

  private void createButton(String buttonName, String actionCommand, boolean splitEnable) {
    JPanel jPanel = new JPanel(new GridLayout(1, 0));
    editorPanel.add(jPanel);
    JButton button = new JButton(buttonName);
    buttons.add(button);
    button.setActionCommand(actionCommand);
    jPanel.add(button);

    if (splitEnable) {
      JRadioButton yesRadioButton = new JRadioButton("Split Preview");

      radioButtons.add(yesRadioButton);
      yesRadioButton.setActionCommand("Split " + buttonName);

      // Create a radioPanel to hold the radio buttons
      JPanel radioPanel = new JPanel();
      radioPanel.setLayout(new GridLayout(1, 0));
      radioPanel.add(yesRadioButton);
      jPanel.add(radioPanel);
    }

  }

  private JPanel getPanels() {
    JPanel imagePanel = new JPanel();

    imagePanel.add(editorPanel);
    imagePanel.setLayout(new FlowLayout());

    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    imagePanel.add(imageScrollPane);

    JScrollPane histogramScrollPane = new JScrollPane(histogramLabel);
    histogramScrollPane.setPreferredSize(new Dimension(260, 260));
    imagePanel.add(histogramScrollPane);
    return imagePanel;
  }
}
