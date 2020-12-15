package com.bank.atm.gui.util_gui;
/**
 * @author Sandra Zhen
 * This class renders the Transaction combo box (the select-Transaction drop-down menu)
 */

import com.bank.atm.backend.transactions.Transaction;
import com.bank.atm.util.Formatter;

import javax.swing.*;
import java.awt.*;

public class TransactionListRenderer extends JTextArea implements ListCellRenderer<Transaction> {
    private static final int DEFAULT_ROWS = 5;
    private static final int DEFAULT_COLS = 15;
    public TransactionListRenderer(){
        this(DEFAULT_ROWS,DEFAULT_COLS);
    }

    /**
     *
     * @param rows - number of rows this component spans
     * @param cols - number of columns this component spans
     */
    public TransactionListRenderer(int rows, int cols){
        super(rows,cols);
        setOpaque(true);
        setLineWrap(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Transaction> list, Transaction transaction, int index, boolean isSelected, boolean cellHasFocus) {
        String transactionStr = Formatter.splitCamelCase(transaction.getClass().getSimpleName())+" "+transaction.getAmount();
        if(transaction.getFromAccountId()!=null){
            transactionStr+=" from "+transaction.getFromAccountId();
        }
        if(transaction.getToAccountId()!=null){
            transactionStr+=" to "+transaction.getToAccountId();
        }
        setText(transactionStr);
        return this;
    }
}
