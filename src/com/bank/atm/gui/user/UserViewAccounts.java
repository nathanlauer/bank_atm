package com.bank.atm.gui.user;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserViewAccounts extends JFrame {

    private final int frameWidth=500;
    private final int frameHeight=500;

    private JPanel userViewAccountsPanel;
    public UserViewAccounts(String userID){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(userViewAccountsPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth,frameHeight));//set width and height of our frame
        this.pack();
    }

    /**
     * Retrieves all accounts that the current user has
     * @param userID - id of user to get accounts for
     * @return list of accounts of the user
     */
    //TODO replace String with Account model if one is created later
    private List<String> getAccounts(String userID){
        List<String> accList = new ArrayList<>();
        accList.add("SampleAcc1");
        accList.add("SampleAcc2");
        return accList;
    }

}
