package com.bank.atm.gui.banker;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.users.Client;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author Navoneel Ghosh
 * Custom table model to show the selected users details
 */
public class SingleUserCustomTableModel extends AbstractTableModel {

    private List<Account> data;
    private String[] columnNames;

    public SingleUserCustomTableModel(List<Account> data, String[] columnNames){
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
        Account account = data.get(rowIndex);
        String emptyString = "";
        switch (columnIndex) {
            case 0:
                return account.getID();
            case 1:
                if(account.getMoney()==null || account.getCurrency().displayMoney(account.getMoney())==null){
                    return emptyString;
                }
                return account.getCurrency().displayMoney(account.getMoney());
            case 2:
                if(account.getAccountType()==null){
                    return emptyString;
                }
                return account.getAccountType().toString();
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

    public ID getIdAt(int rowIndex){
        return (data.get(rowIndex)).getID();
    }
}
