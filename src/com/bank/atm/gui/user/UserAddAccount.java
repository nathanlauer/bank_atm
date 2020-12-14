package com.bank.atm.gui.user;
/**
 * Interface for users to add new account
 *
 * @author Sandra Zhen
 */
import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactory;
import com.bank.atm.backend.accounts.AccountType;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Euro;
import com.bank.atm.backend.currency.JPY;
import com.bank.atm.backend.currency.USD;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;


public class UserAddAccount extends JFrame {
    private final int frameWidth = 500;
    private final int frameHeight = 500;

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        userAddAccountPanel = new JPanel();
        userAddAccountPanel.setLayout(new GridBagLayout());
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userAddAccountPanel.add(spacer1, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Type of Account:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        userAddAccountPanel.add(label1, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userAddAccountPanel.add(accountTypeComboBox, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Currency Type:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        userAddAccountPanel.add(label2, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userAddAccountPanel.add(currencyTypeComboBox, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        userAddAccountPanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        userAddAccountPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        userAddAccountPanel.add(spacer4, gbc);
        createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userAddAccountPanel.add(createAccountButton, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Create New Account");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        userAddAccountPanel.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Initial Balance:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        userAddAccountPanel.add(label4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        userAddAccountPanel.add(spacer5, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userAddAccountPanel.add(initialBalanceTextField, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return userAddAccountPanel;
    }

    private enum CurrencyType {
        USD, JPY, EURO
    }

    private JPanel userAddAccountPanel;
    private JComboBox<AccountType> accountTypeComboBox;
    private JComboBox<CurrencyType> currencyTypeComboBox;
    private JButton createAccountButton;
    private JFormattedTextField initialBalanceTextField;

    /**
     * @param userID - ID of user to add account for
     */
    public UserAddAccount(ID userID) {
        $$$setupUI$$$();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(userAddAccountPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));//set width and height of our frame
        this.pack();

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account createdAccount = createAccount(userID);
                if (createdAccount != null) {//if successfully created account, save it
                    try {
                        AccountsCollectionManager.getInstance().save(createdAccount);
                        JOptionPane.showMessageDialog(UserAddAccount.this,
                                "Account has been created with ID " + createdAccount.getID() + "");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    System.out.println("Account has been created.");
                }
            }
        });

        initialBalanceTextField.addKeyListener(new KeyAdapter() {
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
    }


    private void createUIComponents() {
        accountTypeComboBox = new JComboBox<AccountType>(AccountType.values());

        currencyTypeComboBox = new JComboBox<>(CurrencyType.values());
        //todo change locale to the currency type of account
        initialBalanceTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        initialBalanceTextField.setText("0");//default starting balance to 0


    }

    /**
     * Calls the account factory to create the appropriate account
     *
     * @param userID - user to create account for
     * @return created account
     */
    private Account createAccount(ID userID) {
        double initBalance = 0;
        try {
            initBalance = ((Number) initialBalanceTextField.getValue()).doubleValue();
        } catch (NullPointerException e) {
        }
        Currency currency = null;
        switch ((CurrencyType) currencyTypeComboBox.getSelectedItem()) {
            case USD:
                currency = USD.getInstance();
                break;
            case JPY:
                currency = JPY.getInstance();
                break;
            case EURO:
                currency = Euro.getInstance();
                break;
        }
        return AccountFactory.createAccount((AccountType) accountTypeComboBox.getSelectedItem(), currency, initBalance, userID);
    }

}
