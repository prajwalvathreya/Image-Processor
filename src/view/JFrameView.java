package view;

import model.data.Pixel;
import model.data.Image;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main view class used to implement the view functionalities.
 */
public class JFrameView extends JFrame implements IView {

  private final JButton commandButton;

  private final JComboBox<String> commandsBox;

  private final JPanel parametersPanel;

  private List<JTextField> parameters;

  private final JPanel viewModePanel;

  private final JPanel togglePanel;

  private final JTextField splitPercentage;

  private final JPanel imagePanel;

  private final JPanel histogramPanel;

  /**
   * Constructor.
   */
  public JFrameView() {
    super();
    this.setTitle("Image Manipulation Application");
    this.setSize(400, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //for elements to be arranged vertically within this panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);

    //operations panel
    JPanel operationsPanel = new JPanel();
    operationsPanel.setLayout(new FlowLayout());

    JPanel commandPanel = new JPanel();
    commandPanel.setBorder(BorderFactory.createTitledBorder("Command"));
    commandsBox = new JComboBox<>();
    commandPanel.add(commandsBox);
    operationsPanel.add(commandPanel);

    parametersPanel = new JPanel();
    parametersPanel.setLayout(new GridLayout(0, 4, 2, 2));
    parametersPanel.setBorder(BorderFactory.createTitledBorder("Parameters"));
    operationsPanel.add(parametersPanel);

    viewModePanel = new JPanel();
    viewModePanel.setBorder(BorderFactory.createTitledBorder("View mode"));
    viewModePanel.setLayout(new BoxLayout(viewModePanel, BoxLayout.PAGE_AXIS));
    JLabel description = new JLabel("Toggle between Full image mode and Preview Image mode");
    description.setAlignmentX(CENTER_ALIGNMENT);
    viewModePanel.add(description);
    splitPercentage = new JTextField("50", 5);
    togglePanel = new JPanel();
    addSplitImageToggle();
    operationsPanel.add(viewModePanel);
    //button
    commandButton = new JButton("Execute");
    operationsPanel.add(commandButton);
    mainPanel.add(operationsPanel);

    JPanel resultPanel = new JPanel();
    resultPanel.setLayout(new GridLayout(1, 2));

    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    resultPanel.add(imagePanel);

    //show an image with a scrollbar
    histogramPanel = new JPanel();
    //a border around the panel with a caption
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Showing histogram"));
    histogramPanel.setLayout(new GridLayout(1, 0, 10, 10));
    resultPanel.add(histogramPanel);

    mainPanel.add(resultPanel);

    parameters = List.of();

    // Create an initial blank panel
    addInitialBlankImagePanel(imagePanel);
    addInitialBlankImagePanel(histogramPanel);
    makeVisible();
    this.pack();
  }

  /**
   * Refreshes the image panel in the GUI.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Displays the error message in case the user inputs wrong values or if there is some problem
   * in the execution.
   * @param message error message to be displayed.
   */
  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Message",
            JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Makes the GUI visible.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Gets the command and parameters entered by the user.
   * @return a list of string values that includes the commands and the parameters
   */
  @Override
  public List<String> getCommand() {
    List<String> command =
            parameters.stream().map(JTextComponent::getText).collect(Collectors.toList());
    command.add(0, (String)commandsBox.getSelectedItem());
    if (this.togglePanel.isVisible()) {
      command.add("split");
      command.add(splitPercentage.getText());
    }
    return command;
  }

  /**
   * Sets even listener for execute button on which the controller performs the operation.
   * @param actionEvent controller class that handles the event
   */
  @Override
  public void setCommandButtonListener(ActionListener actionEvent) {
    commandButton.addActionListener(actionEvent);
  }

  /**
   * Sets image in the panel.
   *
   * @param imageName     to be set to the panel.
   * @param toBeInsertedImage of images as input.
   */
  @Override
  public void setImageInPanel(String imageName, Image toBeInsertedImage) {
    setInPanel(imagePanel, toBeInsertedImage);
  }

  /**
   * Sets histogram in the panel.
   *
   * @param imageName     to be set to the panel.
   * @param toBeInsertedImage of images as input.
   */
  @Override
  public void setHistogramInPanel(String imageName, Image toBeInsertedImage) {
    setInPanel(histogramPanel, toBeInsertedImage);
  }

  /**
   * Sets commands and parameters.
   * @param guiSupportedCommands supported commands by gui
   * @param commandSyntax command syntax
   * @param splitModeSupportedCommands operations supported in split
   */
  @Override
  public void setCommandsAndParameters(List<String> guiSupportedCommands,
                                       Map<String, String> commandSyntax,
                                       List<String> splitModeSupportedCommands) {
    guiSupportedCommands.forEach(commandsBox::addItem);
    commandsBox.addActionListener(event -> {
      parametersPanel.removeAll();
      String command = (String) commandsBox.getSelectedItem();
      String[] parameterLabels = commandSyntax.get(command).split(" ");
      parameters = Arrays.stream(parameterLabels).map(parameter -> new JTextField(10))
                      .collect(Collectors.toList());
      for (int i = 0; i < parameterLabels.length; i++) {
        parametersPanel.add(new JLabel(parameterLabels[i]));
        parametersPanel.add(parameters.get(i));
      }
      parametersPanel.revalidate();
      parametersPanel.repaint();
      this.viewModePanel.setVisible(splitModeSupportedCommands.contains(command));
      this.pack();
    });
    commandsBox.setSelectedIndex(0);
  }

  private void addInitialBlankImagePanel(JPanel panel) {
    JLabel[] imageLabel = new JLabel[1];
    JScrollPane[] imageScrollPane = new JScrollPane[1];

    imageLabel[0] = new JLabel();
    imageScrollPane[0] = new JScrollPane(imageLabel[0]);
    imageLabel[0].setText("Please load an image!");
    imageLabel[0].setHorizontalAlignment(SwingConstants.CENTER); // Center the text
    imageLabel[0].setFont(new Font("Arial", Font.PLAIN, 24)); // Increase the font size
    imageScrollPane[0].setPreferredSize(new Dimension(100, 450));
    panel.add(imageScrollPane[0]);
  }

  private void setInPanel(JPanel panel, Image toBeInsertedImage) {

    panel.removeAll();
    int width = toBeInsertedImage.getWidth();
    int height = toBeInsertedImage.getHeight();

    // Create a new label to hold the image
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < toBeInsertedImage.getHeight(); y++) {
      for (int x = 0; x < toBeInsertedImage.getWidth(); x++) {
        Pixel pixel = toBeInsertedImage.getPixel(y, x);
        Color temp = new Color(pixel.red(), pixel.green(), pixel.blue());
        newImage.setRGB(x, y, temp.getRGB());
      }
    }

    JLabel imageLabel = new JLabel(new ImageIcon(newImage));
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    panel.add(imageScrollPane);

    // Tell the panel to redraw itself
    panel.revalidate();
    panel.repaint();

  }

  private void addSplitImageToggle() {
    JPanel viewModeInputPanel = new JPanel();
    viewModeInputPanel.setLayout(new BoxLayout(viewModeInputPanel, BoxLayout.LINE_AXIS));
    viewModeInputPanel.setAlignmentX(CENTER_ALIGNMENT);
    JToggleButton toggleButton = new JToggleButton("Full image mode");
    splitPercentage.setText("50");
    togglePanel.add(new JLabel("Split Percentage"));
    togglePanel.add(splitPercentage);
    togglePanel.setVisible(false);

    toggleButton.addItemListener(itemEvent -> {
      int state = itemEvent.getStateChange();
      if (state == ItemEvent.SELECTED) {
        toggleButton.setText("Preview mode");
        togglePanel.setVisible(true);
      }
      else {
        toggleButton.setText("Full image mode");
        togglePanel.setVisible(false);
      }
      togglePanel.revalidate();
      togglePanel.repaint();
      this.pack();
    });
    viewModeInputPanel.add(toggleButton);
    viewModeInputPanel.add(togglePanel);
    viewModePanel.add(viewModeInputPanel);
  }
}