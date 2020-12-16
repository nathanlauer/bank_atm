package com.bank.atm.gui.banker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLoans extends JFrame{
    private JTable viewLoansTable;
    private JPanel viewLoansPanel;
    private JScrollPane viewLoansScrollPane;
    private JButton backButton;
    private JPanel backPanel;

    public ViewLoans(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(viewLoansPanel);
        this.setPreferredSize(new Dimension(800, 500));//set width and height of our frame
        this.pack();
        this.setLocationRelativeTo(null);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new BankerMenu("Banker Menu");
                frame.setVisible(true);
            }
        });
    }
}
