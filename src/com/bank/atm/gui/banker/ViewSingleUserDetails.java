package com.bank.atm.gui.banker;

import javax.swing.*;
import java.awt.*;

public class ViewSingleUserDetails extends JFrame{
    private JPanel viewSingleUserDetailsPanel;
    private JTable table1;

    public ViewSingleUserDetails(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(viewSingleUserDetailsPanel);
        this.setPreferredSize(new Dimension(800, 500));//set width and height of our frame
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
