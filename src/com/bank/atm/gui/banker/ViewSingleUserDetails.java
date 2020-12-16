package com.bank.atm.gui.banker;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.collections.TransactionsCollectionManager;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.transactions.Transaction;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Navoneel Ghosh
 *GUI for banker. Enables banker to see details of the selected client.
 */
public class ViewSingleUserDetails extends JFrame {
    private JPanel viewSingleUserDetailsPanel;
    private JTable singleUserTable;
    private JLabel clientNameLabel;
    private JScrollPane singleUserScrollPane;
    private JButton backButton;
    private JPanel backPanel;

    public ViewSingleUserDetails(String title, ID selectedUserID) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(viewSingleUserDetailsPanel);
        this.setPreferredSize(new Dimension(800, 500));//set width and height of our frame
        this.pack();
        this.setLocationRelativeTo(null);
        createViewUserTable(selectedUserID);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new ViewUser("View Users",new ArrayList<>());
                frame.setVisible(true);
            }
        });
    }

    private void createViewUserTable(ID selectedUserID) {
        User searchedUser = UsersCollectionManager.getInstance().findByOwnerID(selectedUserID).get(0);
        this.clientNameLabel.setText(searchedUser.getFirstName()+" "+searchedUser.getLastName());
        String[] columnNames = {"Account ID","Amount","Account Type"};
        List<Account> userAccounts = new ArrayList<Account>();
        userAccounts.addAll(AccountsCollectionManager.getInstance().findByOwnerID(selectedUserID));

        SingleUserCustomTableModel singleUserCustomTableModel = new SingleUserCustomTableModel(userAccounts, columnNames);
        singleUserTable.setModel(singleUserCustomTableModel);
        singleUserScrollPane.getViewport().add(singleUserTable);
        singleUserTable.setFillsViewportHeight(true);
        singleUserScrollPane.setVisible(true);
        singleUserTable.setVisible(true);
        singleUserTable.setRowHeight(30);
    }
}
