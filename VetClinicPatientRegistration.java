/*Names: Adam Sabatini
Student Number: 100945612
File Name: VetClinicPatientRegistration.java
Date: April 17,2024
Description: Create a form that will take in new patient information including patient name, owner, a valid email address, and the vet assigned to that patient.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VetClinicPatientRegistration extends JFrame {
    // text fields
    private JTextField patientNameField, ownerNameField, emailField;
    // labels
    private JLabel statusLabel;
    // radio buttons
    private JRadioButton vetAdamRadioButton, vetSabatiniRadioButton;
    // buttons
    private ButtonGroup vetGroup;

    /**
     * Creates the window for the UI and loads a custom icon as the logo
     * @author Adam Sabatini
     */
    public VetClinicPatientRegistration() {
        setTitle("New Patient Registry");
        setSize(550, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        // Load the custom icon image
        ImageIcon icon = new ImageIcon("\\Users\\adams\\OneDrive\\Desktop\\images.png");
        setIconImage(icon.getImage());
        
        addComponents();
        setVisible(true);
    }

    /**
     * creates labels, buttons, radiobuttons, text fields and tool tips
     * @author Adam Sabatini
     */
    private void addComponents() {
        // Labels and Text Fields
        add(new JLabel("Patient Name:"));
        patientNameField = new JTextField();
        add(patientNameField);

        add(new JLabel("Owner Name:"));
        ownerNameField = new JTextField();
        add(ownerNameField);

        add(new JLabel("Email Address:"));
        emailField = new JTextField();
        add(emailField);

        // Radio Buttons for Veterinarians
        vetAdamRadioButton = new JRadioButton("Dr. Adam");
        vetSabatiniRadioButton = new JRadioButton("Dr. Sabatini");
        vetAdamRadioButton.setSelected(true);

        vetGroup = new ButtonGroup();
        vetGroup.add(vetAdamRadioButton);
        vetGroup.add(vetSabatiniRadioButton);
        add(vetAdamRadioButton);
        add(vetSabatiniRadioButton);

        // Buttons
        JButton registerButton = new JButton("Register");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        registerButton.addActionListener(e -> registerPatient());
        clearButton.addActionListener(e -> clearForm());
        exitButton.addActionListener(e -> System.exit(0));

        add(registerButton);
        add(clearButton);
        add(exitButton);

        // Add tooltips to the text fields, radio buttons and buttons
        patientNameField.setToolTipText("Input your pet's name here");
        ownerNameField.setToolTipText("Input your name here");
        emailField.setToolTipText("Input your email here");
        vetAdamRadioButton.setToolTipText("Select Dr. Adam");
        vetSabatiniRadioButton.setToolTipText("Select Dr.Sabatini");
        registerButton.setToolTipText("Click this button to register");
        clearButton.setToolTipText("Click this button to clear all fields");
        exitButton.setToolTipText("Click this button to exit");


        // Status Label
        statusLabel = new JLabel(" ");
        add(statusLabel);

        shortcutKeyBindings(registerButton, clearButton, exitButton);
    }

    /**
     * creates keyboard shortcuts for all buttons within the form
     * @param registerButton
     * @param clearButton
     * @param exitButton
     * @author Adam Sabatini
     */
    private void shortcutKeyBindings(JButton registerButton, JButton clearButton, JButton exitButton) {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        // register button
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), "register");
        actionMap.put("register", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerPatient();
            }
        });

        // clear button
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK), "clear");
        actionMap.put("clear", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // exit button
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK), "exit");
        actionMap.put("exit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * get patient name, owner name and email
     * @author Adam Sabatini
     */
    private void registerPatient() {
        String patientName = patientNameField.getText();
        String ownerName = ownerNameField.getText();
        String email = emailField.getText();

        // validate inputs
        if (validateInput(patientName, ownerName, email)) {
            String vetName = vetAdamRadioButton.isSelected() ? vetAdamRadioButton.getText() : vetSabatiniRadioButton.getText();
            writeToFile(patientName, ownerName, email, vetName);
        }
    }

    /**
     * validate inputs of all text fields
     * @param patientName
     * @param ownerName
     * @param email
     * @return true (if valid)
     * @author Adam Sabatini
     */
    private boolean validateInput(String patientName, String ownerName, String email) {
        if (patientName.isEmpty() || ownerName.isEmpty() || !isValidEmail(email)) {
            statusLabel.setText("Invalid input. Please correct your entry fields.");
            return false;
        }
        return true;
    }

    /**
     * check if the email address is valid
     * @param email
     * @return
     * @author Adam Sabatini
     */
    private boolean isValidEmail(String email) {
        String regexPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
                + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * write info from form into a text document. Provide feedback to tell user if it was successful.
     * @param patientName
     * @param ownerName
     * @param email
     * @param vetName
     * @author Adam Sabatini
     */
    private void writeToFile(String patientName, String ownerName, String email, String vetName) {
        try (FileWriter writer = new FileWriter("newpatientfile.txt", true)) {
            writer.write("Patient Registration Document\n");
            writer.write("Patient Name: " + patientName + "\n");
            writer.write("Owner Name: " + ownerName + "\n");
            writer.write("Email Address: " + email + "\n");
            writer.write("Veterinarian: " + vetName + "\n");
            writer.write("Date: " + new Date() + "\n\n");
            statusLabel.setText("Registration successful!");
        } catch (IOException e) {
            statusLabel.setText("Failed to write to file.");
        }
    }

    /**
     * clear the form and set it back to default configuration
     * @author Adam Sabatini
     */
    private void clearForm() {
        patientNameField.setText("");
        ownerNameField.setText("");
        emailField.setText("");
        vetAdamRadioButton.setSelected(true);
        statusLabel.setText(" ");
        patientNameField.requestFocusInWindow();
    }

    // main method to display UI
    public static void main(String[] args) {

        new VetClinicPatientRegistration();
    }
}