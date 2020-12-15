package com.bank.atm.gui.transactions;
/**
 * UI for transferring money
 * @author Sandra Zhen
 */

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.collections.TransactionsCollectionManager;
import com.bank.atm.backend.currency.ExchangeRateTable;
import com.bank.atm.backend.currency.UnknownExchangeRateException;
import com.bank.atm.backend.transactions.Transfer;
import com.bank.atm.gui.util_gui.AccountListRenderer;
import com.bank.atm.util.ID;
import com.bank.atm.util.IllegalTransactionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

public class TransferMoneyUI extends JFrame {

    private final int frameWidth = 500;
    private final int frameHeight = 500;
    private ID userID;
    private JPanel transferMoneyPanel;
    private JComboBox fromAccountComboBox;
    private JComboBox toAccountComboBox;
    private JLabel fromAccountCurrencyType;
    private JLabel toAccountCurrencyType;
    private JFormattedTextField transactionFeeTextField;
    private JFormattedTextField transferAmount;
    private JFormattedTextField convertedTransferAmount;
    private JFormattedTextField fromAccountBalanceTextField;
    private JFormattedTextField toAccountBalanceTextField;
    private JButton transferButton;
    private JLabel transactionFeeCurrencyLabel;


    public TransferMoneyUI(ID userID) {
        this.userID = userID;
        $$$setupUI$$$();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(transferMoneyPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));//set width and height of our frame
        this.pack(); //packs frame to preferred size
        transferAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                //do not allow user to enter any characters besides digits and backspace/delete
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.')) {
                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        //when transfer button is pressed, money is transfered
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account fromAccount = (Account) fromAccountComboBox.getSelectedItem();
                Account toAccount = (Account) toAccountComboBox.getSelectedItem();
                double amt = 0;
                try {
                    amt = ((Number) transferAmount.getValue()).doubleValue();
                } catch (NullPointerException exception) {
                    exception.printStackTrace();
                }
                double fee = getTransferFee(fromAccount, toAccount);

                if (transferMoney(fromAccount, toAccount, amt, fee)) {
                    JOptionPane.showMessageDialog(TransferMoneyUI.this, "Transferred " + amt + "\nfrom Account " + fromAccount.getID() + "\nto Account " + toAccount.getID());
                    updateLabels();
                }
            }
        });
        fromAccountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updateLabels();
            }
        });
        toAccountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLabels();
            }
        });
        updateLabels();
    }

    /**
     * Updates labels of each component with associated values
     * based on the two choices users selects for fromAccount and toAccount
     */
    private void updateLabels() {
        Account fromAccount = (Account) fromAccountComboBox.getSelectedItem();
        Account toAccount = (Account) toAccountComboBox.getSelectedItem();

        //update currency labels
        toAccountCurrencyType.setText(toAccount.getCurrency().toString());
        fromAccountCurrencyType.setText(fromAccount.getCurrency().toString());
        //update balances
        toAccountBalanceTextField.setText(toAccount.displayAccountValue());
        fromAccountBalanceTextField.setText(fromAccount.displayAccountValue());

        //update transaction fee
        double fee = getTransferFee(fromAccount, toAccount);
        transactionFeeTextField.setText("" + fee);
        transactionFeeCurrencyLabel.setText(fromAccount.getCurrency().toString());
    }

    /**
     * Returns the fee necessary when transferring between the two accounts
     *
     * @param fromAccount
     * @param toAccount
     * @return
     */
    private double getTransferFee(Account fromAccount, Account toAccount) {
        double fee = 0;
        if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            //if there's a different currency type between accounts, fee is the exchange rate
            try {
                fee = ExchangeRateTable.getInstance().getExchangeRate(fromAccount.getCurrency(), toAccount.getCurrency()).getRate();
            } catch (UnknownExchangeRateException e) {

            }
        }
        return fee;
    }

    /**
     * transfers money from one account to another
     *
     * @param fromAccount - account from which to transfer money
     * @param toAccount   - account to which money is transferred
     * @param amount      - amount to transfer
     *                    return whether or not transaction was successful
     */
    private boolean transferMoney(Account fromAccount, Account toAccount, double amount, double fee) {

        try {
            TransactionsCollectionManager.getInstance().executeTransaction(new Transfer(userID,fromAccount.getID(),toAccount.getID(),amount));
        } catch (IllegalTransactionException e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
            return false;
        }
        return true;
    }

    private void createUIComponents() {
        fromAccountComboBox = new JComboBox<Account>(getUserAccounts());
        toAccountComboBox = new JComboBox<Account>(getUserAccounts());
        fromAccountComboBox.setRenderer(new AccountListRenderer());
        toAccountComboBox.setRenderer(new AccountListRenderer());
        transferAmount = new JFormattedTextField(NumberFormat.getInstance());
        transferAmount.setText("0");
        fromAccountBalanceTextField = new JFormattedTextField(NumberFormat.getInstance());
        transactionFeeTextField = new JFormattedTextField(NumberFormat.getInstance());//this should match the from account currency type
        convertedTransferAmount = new JFormattedTextField(NumberFormat.getInstance());
        toAccountBalanceTextField = new JFormattedTextField(NumberFormat.getInstance());
    }

    private Account[] getUserAccounts() {
        List<Account> accountList = AccountsCollectionManager.getInstance().findByOwnerID(userID);
        Account[] accounts = new Account[accountList.size()];
        for (int i = 0; i < accountList.size(); i++) {
            accounts[i] = accountList.get(i);
        }
        return accounts;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        transferMoneyPanel = new JPanel();
        transferMoneyPanel.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setText("Transfer Money");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(label1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        transferMoneyPanel.add(spacer2, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("From:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(label2, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(fromAccountComboBox, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        transferMoneyPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        transferMoneyPanel.add(spacer4, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("To:");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(label3, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(toAccountComboBox, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(transferAmount, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Amount:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(label4, gbc);
        fromAccountCurrencyType = new JLabel();
        fromAccountCurrencyType.setText("USD");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(fromAccountCurrencyType, gbc);
        toAccountCurrencyType = new JLabel();
        toAccountCurrencyType.setText("JPY");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(toAccountCurrencyType, gbc);
        transactionFeeTextField.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(transactionFeeTextField, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("transaction fee:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(label5, gbc);
        transactionFeeCurrencyLabel = new JLabel();
        transactionFeeCurrencyLabel.setText("USD");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(transactionFeeCurrencyLabel, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Balance:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        transferMoneyPanel.add(label6, gbc);
        convertedTransferAmount.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(convertedTransferAmount, gbc);
        fromAccountBalanceTextField.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(fromAccountBalanceTextField, gbc);
        toAccountBalanceTextField.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(toAccountBalanceTextField, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        transferMoneyPanel.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(spacer7, gbc);
        transferButton = new JButton();
        transferButton.setText("Transfer");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferMoneyPanel.add(transferButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return transferMoneyPanel;
    }

}
