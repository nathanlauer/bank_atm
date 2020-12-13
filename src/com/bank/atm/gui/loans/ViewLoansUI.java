package com.bank.atm.gui.loans;

import javax.swing.*;
import java.awt.*;

public class ViewLoansUI extends JFrame {

    private final int frameWidth = 500;
    private final int frameHeight = 500;
    private JScrollPane currentLoansScrollPane;
    private JScrollPane pendingLoansScrollPane;
    private JPanel currentLoansPanel;
    private JPanel pendingLoansPanel;
    private JPanel viewLoansPanel;

    public ViewLoansUI() {

        $$$setupUI$$$();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(viewLoansPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));//set width and height of our frame
        this.pack(); //packs frame to preferred size
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setText("Loans");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer2, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Current Loans");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer3, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Pending Loans");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label3, gbc);
        currentLoansScrollPane = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(currentLoansScrollPane, gbc);
        currentLoansPanel = new JPanel();
        currentLoansPanel.setLayout(new GridBagLayout());
        currentLoansScrollPane.setViewportView(currentLoansPanel);
        pendingLoansScrollPane = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(pendingLoansScrollPane, gbc);
        pendingLoansPanel = new JPanel();
        pendingLoansPanel.setLayout(new GridBagLayout());
        pendingLoansScrollPane.setViewportView(pendingLoansPanel);
    }
}
