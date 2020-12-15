package com.bank.atm.gui.transactions;
/**
 * UI for viewing transactions
 * @author Sandra Zhen
 */

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.users.User;
import com.bank.atm.gui.user.AccountDetails;
import com.bank.atm.gui.util_gui.AccountListRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ViewTransactionsUI extends JFrame {
    private JList transactionsList;

    /**
     * Constructs UI to view transactions associated with account
     * @param account
     */
    public ViewTransactionsUI(Account account){

    }
    /**
     * Constructs UI to view transactions associated with user
     * @param user
     */
    public ViewTransactionsUI(User user){

    }

    private void initTransactionsList(List<Transaction> transactionList){
        transactionsList.setListData(getAccountsData(userID).toArray());
        transactionsList.setCellRenderer(new AccountListRenderer(2, 15));
        transactionsList.setBorder(new EmptyBorder(10, 10, 10, 10));
        transactionsList.addListSelectionListener(new ListSelectionListener() {
            int i = 0;

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (i % 2 == 0) {//every click of list selection listener results in valueChanged being called twice. We use counter%2 to make sure the event is triggered only once
                    //code runs when item from transactions list is clicked
                }
                i++;
            }
        });
    }

    private void createUIComponents() {

        transactionsList = new JList<Transaction>();
    }
}
