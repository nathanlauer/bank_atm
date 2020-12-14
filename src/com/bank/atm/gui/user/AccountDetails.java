package com.bank.atm.gui.user;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.gui.transactions.DepositUI;
import com.bank.atm.gui.transactions.WithdrawUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UI for showing the full account details
 *
 * @author Sandra Zhen
 */
public class AccountDetails extends JFrame {
    private final int frameWidth = 500;
    private final int frameHeight = 500;

    private JButton depositButton;
    private JButton withdrawButton;
    private JPanel transactionPanel;
    private JTextField balanceValueLabel;
    private JComboBox currencyTypeComboBox;
    private JPanel accountDetailsPanel;
    private JTextField accountTypeValueLabel;
    private JPanel accountDetailsMainPanel;
    private JFormattedTextField currencyTypeLabel;
    private JTextField dateOpenedTextField;
    private JLabel accountNameLabel;

    public AccountDetails(Account account) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(accountDetailsMainPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));//set width and height of our frame
        this.pack();
        accountNameLabel.setText("Account "+account.getID()+" Details");
        accountTypeValueLabel.setText(account.getClass().toString());
        balanceValueLabel.setText(""+account.getMoney());
        dateOpenedTextField.setText(""+account.getOpened());


        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositUI depositUI = new DepositUI();
                depositUI.setVisible(true);
            }
        });
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WithdrawUI withdrawUI = new WithdrawUI();
                withdrawUI.setVisible(true);
            }
        });
    }


}
