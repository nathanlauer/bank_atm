package com.bank.atm.gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame {

    private JPanel signUpPanel;
    private JTextField nameTextField;
    private JTextField phoneNumberTextField;
    private JTextField userNameTextField;
    private JLabel nameLabel;
    private JLabel phoneNumberLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordPasswordField;
    private JButton signUpButton;

    public SignUp(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(signUpPanel);
        this.setPreferredSize(new Dimension(500,500));//set width and height of our frame
        this.pack();


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // To-Do : Add user to file
            }
        });
    }
}
