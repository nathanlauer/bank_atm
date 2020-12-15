package com.bank.atm.gui.banker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankerMenu extends JFrame{
    private JPanel bankerMenuPanel;
    private JButton viewUsersButton;
    private JButton viewLoansButton;
    private JButton dailyTransactionReportsButton;

    public BankerMenu(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(bankerMenuPanel);
        this.setPreferredSize(new Dimension(300,300));//set width and height of our frame
        this.pack();
        this.setLocationRelativeTo(null);

        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new ViewUser("View Users");
                frame.setVisible(true);
            }
        });

        viewLoansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        dailyTransactionReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String [] args){
        JFrame frame = new BankerMenu("Banker Menu");
        frame.setVisible(true);
    }
}
