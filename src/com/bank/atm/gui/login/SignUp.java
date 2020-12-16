package com.bank.atm.gui.login;

import com.bank.atm.backend.authentication.Register;
import com.bank.atm.backend.users.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SignUp extends JFrame {

    private JPanel signUpPanel;
    private JTextField firstNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField userNameTextField;
    private JLabel firstNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordPasswordField;
    private JButton signUpButton;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JPasswordField confirmPasswordField;
    private JLabel confirmPasswordLabel;

    public SignUp(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(signUpPanel);
        this.setPreferredSize(new Dimension(500, 500));//set width and height of our frame
        this.pack();
        this.setLocationRelativeTo(null);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName, lastName, username, password, confirmPassword;
                    firstName = firstNameTextField.getText().trim();
                    lastName = lastNameTextField.getText().trim();
                    username = userNameTextField.getText().trim();
                    password = Arrays.toString(passwordPasswordField.getPassword()).trim();
                    confirmPassword = Arrays.toString(confirmPasswordField.getPassword()).trim();
                    Register reg = new Register(firstName, lastName, username, password, confirmPassword, UserType.CLIENT);
                    reg.run();
                    dispose();
                    JFrame frame = new Login("Login");
                    frame.setVisible(true);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(signUpPanel, exception.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridBagLayout());
        signUpPanel.setName("Sign Up");
        firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        signUpPanel.add(firstNameLabel, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        signUpPanel.add(spacer2, gbc);
        firstNameTextField = new JTextField();
        firstNameTextField.setMinimumSize(new Dimension(100, 30));
        firstNameTextField.setPreferredSize(new Dimension(100, 30));
        firstNameTextField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(firstNameTextField, gbc);
        phoneNumberLabel = new JLabel();
        phoneNumberLabel.setText("Phone Number");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        signUpPanel.add(phoneNumberLabel, gbc);
        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setMinimumSize(new Dimension(100, 30));
        phoneNumberTextField.setPreferredSize(new Dimension(100, 30));
        phoneNumberTextField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(phoneNumberTextField, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(spacer3, gbc);
        userNameLabel = new JLabel();
        userNameLabel.setText("User Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        signUpPanel.add(userNameLabel, gbc);
        userNameTextField = new JTextField();
        userNameTextField.setMinimumSize(new Dimension(100, 30));
        userNameTextField.setPreferredSize(new Dimension(100, 30));
        userNameTextField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(userNameTextField, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(spacer4, gbc);
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        signUpPanel.add(passwordLabel, gbc);
        passwordPasswordField = new JPasswordField();
        passwordPasswordField.setMinimumSize(new Dimension(100, 30));
        passwordPasswordField.setPreferredSize(new Dimension(100, 30));
        passwordPasswordField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(passwordPasswordField, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.VERTICAL;
        signUpPanel.add(spacer5, gbc);
        signUpButton = new JButton();
        signUpButton.setText("Sign Up");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(signUpButton, gbc);
        lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        signUpPanel.add(lastNameLabel, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        signUpPanel.add(spacer6, gbc);
        lastNameTextField = new JTextField();
        lastNameTextField.setMinimumSize(new Dimension(100, 30));
        lastNameTextField.setOpaque(true);
        lastNameTextField.setPreferredSize(new Dimension(100, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(lastNameTextField, gbc);
        confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setText("Confirm Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        signUpPanel.add(confirmPasswordLabel, gbc);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setMinimumSize(new Dimension(100, 30));
        confirmPasswordField.setPreferredSize(new Dimension(100, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        signUpPanel.add(confirmPasswordField, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        signUpPanel.add(spacer7, gbc);
        firstNameLabel.setLabelFor(firstNameTextField);
        phoneNumberLabel.setLabelFor(phoneNumberTextField);
        userNameLabel.setLabelFor(userNameTextField);
        passwordLabel.setLabelFor(passwordPasswordField);
        lastNameLabel.setLabelFor(lastNameTextField);
        confirmPasswordLabel.setLabelFor(confirmPasswordField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return signUpPanel;
    }

}
