package com.bank.atm.gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame{
    private JPanel loginPanel;
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel forgotPasswordLabel;
    private JButton loginButton;
    private JButton signUpButton;

    public Login(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.setPreferredSize(new Dimension(500,500));//set width and height of our frame
        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //To-Do : Add authorisation and open "UserMenu"

                System.out.println("enter the valid username and password");
                JOptionPane.showMessageDialog(loginPanel,"Incorrect login or password",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dispose();
                JFrame frame = new SignUp("Sign Up");
                frame.setVisible(true);
            }
        });
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
                try {
                    dispose();
                    JFrame frame = new ForgotPassword("Forgot Password");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent event) {
                forgotPasswordLabel.setText("<html><a href=''>Forgot Password?</a></html>");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                forgotPasswordLabel.setText("Forgot Password?");
            }
        });
    }

    public static void main(String [] args){
        JFrame frame = new Login("Login");
        frame.setVisible(true);
    }
}
