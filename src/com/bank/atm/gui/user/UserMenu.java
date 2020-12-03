package com.bank.atm.gui.user;

/**
 * @author Sandra Zhen
 * Class represents user menu interface when user first logs in
 */
import javax.swing.*;

public class UserMenu extends JFrame {

    private JPanel menuPanel;
    private JLabel usernameLabel;
    private JButton viewAccountsButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferMoneyButton;
    private JButton loansButton;

    public UserMenu(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menuPanel);//sets content to our menu panel
        this.pack();
        usernameLabel.setText(getUserName());
    }

    private String getUserName(){
        //TODO retrieve and return username from oauth
        return "Welcome "+"Name"+"!";
    }
    //TODO delete later. this for testing purposes
    public static void main(String [] args){
        JFrame frame = new UserMenu("User Menu");
        frame.setVisible(true);
    }

}
