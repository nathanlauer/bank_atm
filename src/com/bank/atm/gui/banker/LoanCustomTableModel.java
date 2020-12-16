package com.bank.atm.gui.banker;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.users.Client;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author Navoneel Ghosh
 * Custom table model for showing the Loans in the UI
 */
public class LoanCustomTableModel extends AbstractTableModel {

    private List<Account> data;
    private String[] columnNames;

    public LoanCustomTableModel(List<Account> data, String[] columnNames){
        super();
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        if(data == null || data.isEmpty()){
            return 0;
        }
        else{
            return data.size();
        }
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LoanAccount loanAccount = (LoanAccount)data.get(rowIndex);
        User user = UsersCollectionManager.getInstance().findByOwnerID(loanAccount.getManagers().get(0)).get(0);
        switch (columnIndex) {
            case 0:
                return user.getID().toString().substring(0,5)+"...";
            case 1:
                return user.getFirstName();
            case 2:
                return user.getLastName();
            case 3:
                return loanAccount.getID();
            case 4:
                return loanAccount.getCurrency().displayMoney(loanAccount.getMoneyOwed());
            case 5:
                return loanAccount.getLoanState().toString();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public Account getAccountAt(int rowIndex){
        return data.get(rowIndex);
    }
}
