package com.bank.atm.gui.banker;

import com.bank.atm.backend.users.Client;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import javax.swing.table.AbstractTableModel;
import java.util.List;
/**
 * @author Navoneel Ghosh
 *Custom table model to show all users
 */
public class ViewUserCustomTableModel extends AbstractTableModel {

    private List<User> data;
    private String[] columnNames;

    public ViewUserCustomTableModel(List<User> data, String[] columnNames){
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
        Client client = (Client)data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return client.getID().toString().substring(0,5)+"...";
            case 1:
                return client.getFirstName();
            case 2:
                return client.getLastName();
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
        return ((Client)data.get(rowIndex)).getID();
    }
}
