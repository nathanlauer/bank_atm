package com.bank.atm.gui.loans;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;
import com.bank.atm.backend.accounts.loan_accounts.LoanState;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.gui.user.AccountDetails;
import com.bank.atm.gui.util_gui.AccountListRenderer;
import com.bank.atm.util.ID;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ViewLoansUI extends JFrame {

    private final int frameWidth = 400;
    private final int frameHeight = 800;
    private final int ROWS_PER_LIST_ITEM = 2, COLS_PER_LIST_ITEM=15;
    
    
    private JPanel viewLoansPanel;
    private JList currentLoansList;
    private JList pendingLoansList;
    private JList rejectedLoansList;

    public ViewLoansUI(ID userID) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(viewLoansPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));//set width and height of our frame
        this.pack(); //packs frame to preferred size

        initCurrentLoansList(userID);
        initPendingLoansList(userID);
        initRejectedLoansList(userID);
    }

    private void createUIComponents() {
        currentLoansList = new JList<LoanAccount>();
        pendingLoansList = new JList<LoanAccount>();
        rejectedLoansList = new JList<LoanAccount>();
    }
    private void initCurrentLoansList(ID userID) {
        List<Account> loans = AccountsCollectionManager.getInstance().userLoansInState(userID, LoanState.APPROVED);
        currentLoansList.setListData(loans.toArray());
        currentLoansList.setCellRenderer(new AccountListRenderer(ROWS_PER_LIST_ITEM, COLS_PER_LIST_ITEM));
        currentLoansList.setBorder(new EmptyBorder(10, 10, 10, 10));
        currentLoansList.addListSelectionListener(new ListSelectionListener() {
            int i = 0;
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (i % 2 == 0) {//every click of list selection listener results in valueChanged being called twice. We use counter%2 to make sure the event is triggered only once
                    LoanAccount account = (LoanAccount) (currentLoansList.getSelectedValue());
                    LoanDetails accountDetails = new LoanDetails(account);
                    accountDetails.setVisible(true);
                }
                i++;
            }
        });
    }
    private void initPendingLoansList(ID userID) {
        List<Account> loans = AccountsCollectionManager.getInstance().userLoansInState(userID, LoanState.REQUESTED);
        pendingLoansList.setListData(loans.toArray());
        pendingLoansList.setCellRenderer(new AccountListRenderer(ROWS_PER_LIST_ITEM, COLS_PER_LIST_ITEM));
        pendingLoansList.setBorder(new EmptyBorder(10, 10, 10, 10));
        pendingLoansList.addListSelectionListener(new ListSelectionListener() {
            int i = 0;
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (i % 2 == 0) {//every click of list selection listener results in valueChanged being called twice. We use counter%2 to make sure the event is triggered only once
                    LoanAccount account = (LoanAccount) (pendingLoansList.getSelectedValue());
                    LoanDetails accountDetails = new LoanDetails(account);
                    accountDetails.setVisible(true);
                }
                i++;
            }
        });
    }
    private void initRejectedLoansList(ID userID) {
        List<Account> loans = AccountsCollectionManager.getInstance().userLoansInState(userID, LoanState.REJECTED);
        rejectedLoansList.setListData(loans.toArray());
        rejectedLoansList.setCellRenderer(new AccountListRenderer(ROWS_PER_LIST_ITEM, COLS_PER_LIST_ITEM));
        rejectedLoansList.setBorder(new EmptyBorder(10, 10, 10, 10));
        rejectedLoansList.addListSelectionListener(new ListSelectionListener() {
            int i = 0;
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (i % 2 == 0) {//every click of list selection listener results in valueChanged being called twice. We use counter%2 to make sure the event is triggered only once
                    LoanAccount account = (LoanAccount) (rejectedLoansList.getSelectedValue());
                    LoanDetails accountDetails = new LoanDetails(account);
                    accountDetails.setVisible(true);
                }
                i++;
            }
        });
    }
    
}
