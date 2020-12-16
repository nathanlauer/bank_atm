package com.bank.atm.gui.loans;
/**
 * @author Sandra Zhen
 * UI for viewing details on loan
 */

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;
import com.bank.atm.backend.currency.CurrencyType;
import com.bank.atm.backend.currency.JPY;
import com.bank.atm.backend.interest.InterestEarnable;
import com.bank.atm.backend.interest.InterestEarningExecutor;
import com.bank.atm.util.Formatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;

public class LoanDetails extends JFrame {
    private final int frameWidth = 500;
    private final int frameHeight = 500;
    private JTextArea collateralValueTextArea;
    private JTextArea interestTextArea;
    private JTextArea collateralTextArea;
    private JTextArea currencyTypeTextArea;
    private JTextArea balanceOwedTextArea;
    private JTextArea balancePaidTextArea;
    private JPanel loanDetailsPanel;
    private JPanel viewLoanPanel;
    private JTextArea dateOpenedTextArea;
    private JButton payLoanButton;


    public LoanDetails(LoanAccount loanAccount) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(loanDetailsPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));//set width and height of our frame
        this.pack();

        setLabels(loanAccount);
        payLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PayLoanUI payLoanUI = new PayLoanUI(loanAccount);
                payLoanUI.setVisible(true);
                setLabels(loanAccount);
            }
        });

    }

    /**
     * Sets labels of each gui component according to loan account values
     * @param loanAccount
     */
    private void setLabels(LoanAccount loanAccount){
        collateralValueTextArea.setText(loanAccount.getCollateralValue()+"");
        collateralTextArea.setText(loanAccount.getCollateral());
        currencyTypeTextArea.setText(loanAccount.getCurrency()+"");
        balanceOwedTextArea.setText(loanAccount.getMoneyOwed()+"");
        balancePaidTextArea.setText(loanAccount.getMoneyPaid()+"");
        dateOpenedTextArea.setText(loanAccount.getOpened()+"");

        if(loanAccount instanceof InterestEarnable) {
            InterestEarnable earnable = (InterestEarnable) loanAccount;
            InterestEarningExecutor executor = earnable.getInterestEarningExecutor();
            interestTextArea.setText(executor.getApy()+"");
        }
    }
}
