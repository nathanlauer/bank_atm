package com.bank.atm.gui.banker;

import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.transactions.Transaction;
import com.bank.atm.backend.users.Client;
import com.bank.atm.backend.users.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TxnCustomTableModel extends AbstractTableModel {

    private List<Transaction> data;
    private String[] columnNames;

    public TxnCustomTableModel(List<Transaction> data, String[] columnNames){
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
        Transaction transaction = data.get(rowIndex);
        User user = UsersCollectionManager.getInstance().findByOwnerID(transaction.getUserId()).get(0);
        String emptyString = "";
        switch (columnIndex) {
            case 0:
                if(transaction.getID()==null){
                    return emptyString;
                }
                return transaction.getID();
            case 1:
                if(user.getFirstName()==null){
                    return emptyString;
                }
                return user.getFirstName();
            case 2:
                if(user.getLastName()==null){
                    return emptyString;
                }
                return user.getLastName();
            case 3:
                if(transaction.getFromAccountId()==null){
                    return emptyString;
                }
                return transaction.getFromAccountId();
            case 4:
                if(transaction.getToAccountId()==null){
                    return emptyString;
                }
                return transaction.getToAccountId();
            case 5:
                if((Double)(transaction.getAmount())==null){
                    return emptyString;
                }
                return transaction.getAmount();
            case 6:
                if(transaction.getTransactionType()==null){
                    return emptyString;
                }
                String txnType = transaction.getTransactionType().toString();
                return txnType;
            case 7:
                return transaction.getDate();
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

}

