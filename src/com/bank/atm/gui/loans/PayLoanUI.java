package com.bank.atm.gui.loans;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;
import com.bank.atm.backend.accounts.loan_accounts.LoanState;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.collections.TransactionsCollectionManager;
import com.bank.atm.backend.transactions.LoanPayment;
import com.bank.atm.util.Formatter;
import com.bank.atm.util.ID;
import com.bank.atm.util.IllegalTransactionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;

public class PayLoanUI extends JFrame {
    private final int frameWidth = 500;
    private final int frameHeight = 500;
    
    private ID userID;
    private JFrame viewLoanFrame;
    
    private JComboBox loansComboBox;
    private JFormattedTextField amountToPayTextField;
    private JFormattedTextField loanAmountLeft;
    private JButton viewLoanButton;
    private JPanel payLoanPanel;
    private JButton payLoanButton;

    public PayLoanUI(ID userID,LoanAccount loanAccount){
        this(userID);
        loansComboBox.setSelectedItem(loanAccount);
        updateLoanAmountLeft(loanAccount);
    }
    public PayLoanUI(ID userID) {
        this.userID=userID;
        $$$setupUI$$$();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(payLoanPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));//set width and height of our frame
        this.pack(); //packs frame to preferred size
        viewLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoanAccount loanAccount=getSelectedLoanAccount();
                if(loanAccount!=null) {
                    viewLoanFrame = new LoanDetails(userID, loanAccount);
                    viewLoanFrame.setVisible(true);
                }
            }
        });
        payLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payLoan(userID,getSelectedLoanAccount());
                if(viewLoanFrame!=null)
                    viewLoanFrame.dispose();
                updateLoanAmountLeft(getSelectedLoanAccount());
            }
        });
        updateLoanAmountLeft(getSelectedLoanAccount());
    }
    
    private void updateLoanAmountLeft(LoanAccount loanAccount){
        if(loanAccount!=null)
            loanAmountLeft.setText(loanAccount.getMoneyOwed()+"");
    }
    private void payLoan(ID userID, LoanAccount loanAccount){
        if(loanAccount==null)
            return;
        try {
            TransactionsCollectionManager.getInstance().executeTransaction(new LoanPayment(userID,loanAccount.getID(),getPayAmt()));

            JOptionPane.showMessageDialog(PayLoanUI.this,"Paid "+getPayAmt()+"to Loan "+loanAccount.getID());
        } catch (IllegalTransactionException e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    
    private LoanAccount getSelectedLoanAccount(){
        return (LoanAccount) loansComboBox.getSelectedItem();
    }
    /**
     * returns payment amt entered by user
     * @return
     */
    private double getPayAmt(){
        double amt = 0;
        try {
            amt = ((Number) amountToPayTextField.getValue()).doubleValue();
        } catch (NullPointerException e) {
            amt=0;
        }
        return amt;
    }
    private LoanAccount[] getLoanAccounts(ID userID){
        List<Account> loanActList = AccountsCollectionManager.getInstance().userLoansInState(userID, LoanState.APPROVED);
        LoanAccount[] loanAccounts=new LoanAccount[loanActList.size()];
        for (int i = 0; i<loanAccounts.length;i++){
            loanAccounts[i]=(LoanAccount) loanActList.get(i);
        }
        return loanAccounts;
        
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        payLoanPanel = new JPanel();
        payLoanPanel.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setText("Make Payment to Loan");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        payLoanPanel.add(label1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        payLoanPanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        payLoanPanel.add(spacer2, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Loan");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        payLoanPanel.add(label2, gbc);
        loansComboBox = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        payLoanPanel.add(loansComboBox, gbc);
        amountToPayTextField = new JFormattedTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        payLoanPanel.add(amountToPayTextField, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Payment Amount:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        payLoanPanel.add(label3, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        payLoanPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        payLoanPanel.add(spacer4, gbc);
        loanAmountLeft = new JFormattedTextField();
        loanAmountLeft.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        payLoanPanel.add(loanAmountLeft, gbc);
        viewLoanButton = new JButton();
        viewLoanButton.setText("View Loan");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        payLoanPanel.add(viewLoanButton, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        payLoanPanel.add(spacer5, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Loan Amount Left:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        payLoanPanel.add(label4, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return payLoanPanel;
    }

    private void createUIComponents() {
        amountToPayTextField=new JFormattedTextField(NumberFormat.getInstance());
        amountToPayTextField.setText("0");
        loansComboBox=new JComboBox<LoanAccount>(getLoanAccounts(userID));
    }
    
}
