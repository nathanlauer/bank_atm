package com.bank.atm.gui.banker;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.util.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static com.bank.atm.backend.accounts.loan_accounts.LoanState.REQUESTED;

public class ViewLoans extends JFrame{
    private JTable viewLoansTable;
    private JPanel viewLoansPanel;
    private JScrollPane viewLoansScrollPane;
    private JButton backButton;
    private JPanel backPanel;
    private JButton rejectLoanButton;
    private JButton approveLoanButton;
    private LoanCustomTableModel loanCustomTableModel;

    public ViewLoans(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(viewLoansPanel);
        this.setPreferredSize(new Dimension(800, 500));//set width and height of our frame
        this.pack();
        this.setLocationRelativeTo(null);
        this.createLoansTable();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new BankerMenu("Banker Menu");
                frame.setVisible(true);
            }
        });
        approveLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows[] = viewLoansTable.getSelectedRows();
                for(int i = 0; i<rows.length;i++){
                    LoanAccount loanAccount = (LoanAccount) (loanCustomTableModel.getAccountAt(i));
                    if(loanAccount.getLoanState().equals(REQUESTED)){
                        AccountsCollectionManager.getInstance().approveLoan(loanAccount);
                    }
                }
                createLoansTable();
            }
        });
        rejectLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows[] = viewLoansTable.getSelectedRows();
                for(int i = 0; i<rows.length;i++){
                    LoanAccount loanAccount = (LoanAccount) (loanCustomTableModel.getAccountAt(i));
                    if(loanAccount.getLoanState().equals(REQUESTED)){
                        AccountsCollectionManager.getInstance().rejectLoan(loanAccount);
                    }
                }
                createLoansTable();
            }
        });
    }

    private void createLoansTable(){
        String[] columnNames = {"User ID","First Name",
                "Last Name", "Loan Account ID", "Loan Amount", "Loan Status"};
        List<Account> accountList = AccountsCollectionManager.getInstance().allLoans();
        this.loanCustomTableModel = new LoanCustomTableModel(accountList, columnNames);
        viewLoansTable.setModel(this.loanCustomTableModel);
        viewLoansTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        viewLoansScrollPane.getViewport().add(viewLoansTable);
        viewLoansTable.setFillsViewportHeight(true);
        viewLoansScrollPane.setVisible(true);
        viewLoansTable.setVisible(true);
        viewLoansTable.setRowHeight(30);
    }
}
