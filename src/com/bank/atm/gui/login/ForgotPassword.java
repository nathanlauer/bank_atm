package com.bank.atm.gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassword extends JFrame{
    private JPanel forgotPasswordPanel;
    private JLabel recoveryPhoneNumberLabel;
    private JTextField phoneNumberTextField;
    private JButton sendOTPButton;
    private JTextField userNameTextField;
    private JLabel userNameLabel;

    public ForgotPassword(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(forgotPasswordPanel);
        this.setPreferredSize(new Dimension(500,500));//set width and height of our frame
        this.pack();
        sendOTPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // To-do : Do a reset password operation.
                dispose();
                JFrame frame = new Login("Login");
                frame.setVisible(true);
            }
        });
    }

}
