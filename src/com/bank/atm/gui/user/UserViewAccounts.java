package com.bank.atm.gui.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserViewAccounts extends JFrame {

    private final int frameWidth=500;
    private final int frameHeight=500;

    private JPanel userViewAccountsPanel;
    private JButton addNewAcountButton;
    private JPanel accountsPanel;

    public UserViewAccounts(String userID){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(userViewAccountsPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth,frameHeight));//set width and height of our frame
        this.pack();
        addNewAcountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAddAccount userAddAccount = new UserAddAccount(userID);
                userAddAccount.setVisible(true);
            }
        });
        initAccountsPanel(userID);
    }

    private void initAccountsPanel(String userID){
        BoxLayout boxLayout = new BoxLayout(accountsPanel,BoxLayout.Y_AXIS);
        accountsPanel.setLayout(boxLayout);
        List<JButton> acctBtns = createAccountBtns(userID);
        for(JButton acctBtn:acctBtns) {

//            acctBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            accountsPanel.add(acctBtn);
            accountsPanel.add(Box.createRigidArea(new Dimension(10, 10)));//add space padding between each btn

        }

    }
    /**
     * populates the gui view with user account btns
     * @param userID
     * @return
     */
    private List<JButton> createAccountBtns(String userID){
        List<JButton> acctBtns = new ArrayList<>();
        for(String account:getAccounts(userID)){//todo replace string with account obj
            JButton acctBtn = new JButton(account);
            acctBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Replace this with go to account details: "+account);
                }
            });
            acctBtns.add(acctBtn);
        }

        return acctBtns;
    }
    /**
     * Retrieves all accounts that the current user has
     * @param userID - id of user to get accounts for
     * @return list of accounts of the user
     */
    //TODO replace String with Account model if one is created later
    private List<String> getAccounts(String userID){
        List<String> accList = new ArrayList<>();
        for(int i = 0; i<10; i++){//todo replace this with user's actual accounts
            accList.add("SampleAcc"+i);

        }
        return accList;
    }

}
