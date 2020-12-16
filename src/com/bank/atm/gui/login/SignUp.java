package com.bank.atm.gui.login;

import com.bank.atm.backend.authentication.Register;
import com.bank.atm.backend.users.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
/**
 * @author Navoneel Ghosh
 * SignUp GUI. Only Client users can sign up from the GUI.
 * Admin(Banker) can not sign up using GUI. The admin credentials are set from the backend.
 */
public class SignUp extends JFrame {

    private JPanel signUpPanel;
    private JTextField firstNameTextField;
    private JTextField userNameTextField;
    private JLabel firstNameLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordPasswordField;
    private JButton signUpButton;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JPasswordField confirmPasswordField;
    private JLabel confirmPasswordLabel;
    private JButton backButton;

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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new Login("Login");
                frame.setVisible(true);
            }
        });
    }
}
