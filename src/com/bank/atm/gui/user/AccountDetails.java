package com.bank.atm.gui.user;

import javax.swing.*;
import java.awt.*;

/**
 * The UI for showing the full account details
 * @author Sandra Zhen
 */
public class AccountDetails extends JFrame {
    private final int frameWidth =500;
    private final int frameHeight=500;

    private JButton depositButton;
    private JButton withdrawButton;
    private JPanel transactionPanel;
    private JLabel balanceValueLabel;
    private JComboBox currencyTypeComboBox;
    private JPanel accountDetailsPanel;
    private JLabel accountTypeValueLabel;
    private JPanel accountDetailsMainPanel;

    public AccountDetails(/*Account account*/){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(accountDetailsMainPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth,frameHeight));//set width and height of our frame
        this.pack();

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here. dynamically generate components based on what account is created in the accountDetails panel
        currencyTypeComboBox = new JComboBox();
    }
}
