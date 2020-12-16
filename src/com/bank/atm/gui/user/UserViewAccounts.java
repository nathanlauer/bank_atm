package com.bank.atm.gui.user;
/**
 * Interface for user to view all accounts they have in the bank
 * @author Sandra Zhen
 *
 */

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.gui.util_gui.AccountListRenderer;
import com.bank.atm.util.Formatter;
import com.bank.atm.util.ID;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserViewAccounts extends JFrame {

    private final int frameWidth = 500;
    private final int frameHeight = 500;

    private JPanel userViewAccountsPanel;
    private JButton addNewAcountButton;
    private JList accountsList;
    private JPanel accountsPanel;

    public UserViewAccounts(ID userID) {

        $$$setupUI$$$();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(userViewAccountsPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));//set width and height of our frame
        this.pack();
        addNewAcountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAddAccount userAddAccount = new UserAddAccount(userID);
                userAddAccount.setVisible(true);
                UserViewAccounts.this.dispose();
            }
        });
        initAccountsList(userID);
    }

    private void initAccountsList(ID userID) {
        accountsList.setListData(getAccountsData(userID).toArray());
        accountsList.setCellRenderer(new AccountListRenderer(2, 15));
        accountsList.setBorder(new EmptyBorder(10, 10, 10, 10));
        accountsList.addListSelectionListener(new ListSelectionListener() {
            int i = 0;
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (i % 2 == 0) {//every click of list selection listener results in valueChanged being called twice. We use counter%2 to make sure the event is triggered only once
                    Account account = (Account) (accountsList.getSelectedValue());
                    AccountDetails accountDetails = new AccountDetails(userID, account);
                    accountDetails.setVisible(true);
                }
                i++;
            }
        });
    }


    /**
     * Retrieves all accounts and their info that the current user has
     *
     * @param userID - id of user to get accounts for
     * @return list of accounts of the user
     */
    private List<Account> getAccountsData(ID userID) {
        return AccountsCollectionManager.getInstance().findByOwnerID(userID);
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
        userViewAccountsPanel = new JPanel();
        userViewAccountsPanel.setLayout(new GridBagLayout());
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        userViewAccountsPanel.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("View Accounts");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        userViewAccountsPanel.add(panel2, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Accounts");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label2, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 200, 0, 0);
        panel2.add(spacer1, gbc);
        addNewAcountButton = new JButton();
        addNewAcountButton.setText("Add New Acount");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(addNewAcountButton, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        userViewAccountsPanel.add(spacer2, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(300, 300));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        userViewAccountsPanel.add(scrollPane1, gbc);
        scrollPane1.setViewportView(accountsList);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return userViewAccountsPanel;
    }

    private void createUIComponents() {
        accountsList = new JList<Account>();
    }
}
