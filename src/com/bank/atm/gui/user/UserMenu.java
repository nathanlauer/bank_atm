package com.bank.atm.gui.user;

/**
 * @author Sandra Zhen
 * Class represents user menu interface when user first logs in
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenu extends JFrame {
    private final int frameWidth=500;
    private final int frameHeight=500;

    private JPanel userMenuPanel;
    private JLabel usernameLabel;
    private JButton viewAccountsButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferMoneyButton;
    private JButton loansButton;
    private JButton addNewAccountButton;

    public UserMenu(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(userMenuPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth,frameHeight));//set width and height of our frame
        this.pack(); //packs frame to preferred size
        usernameLabel.setText("Welcome "+getUserName()+"!");

        viewAccountsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                UserViewAccounts userViewAccounts = new UserViewAccounts(getUserID());
                userViewAccounts.setVisible(true);
            }
        });
    }

    /**
     * Returns name of the user
     * @return
     */
    private String getUserName(){
        //TODO retrieve and return username from oauth
        return "Name";
    }
    private String getUserID(){
        //TODO retrieve and return userID from oauth
        return "98454598";
    }

    //TODO delete later. this for testing purposes
    public static void main(String [] args){
        JFrame frame = new UserMenu("User Menu");
        frame.setVisible(true);
    }

}
