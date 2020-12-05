package com.bank.atm.gui.user;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class UserDeposit extends JFrame {

    private final int frameWidth=500;
    private final int frameHeight=500;


    private JPanel depositPanel;
    private JLabel amountLabel;
    private JFormattedTextField depositAmountField;

    /**
     * TODO add field for choosing account to make deposit and adjust currency format according to locale of that account
     */
    public UserDeposit(){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(depositPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth,frameHeight));//set width and height of our frame
        this.pack(); //packs frame to preferred size
    }

    private void createUIComponents() {
        depositAmountField = new JFormattedTextField(NumberFormat.getCurrencyInstance(Locale.US));
        amountLabel.setLabelFor(depositAmountField);
    }
}
