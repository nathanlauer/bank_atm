package com.bank.atm.gui.loans;

import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;

import javax.swing.*;
import java.awt.*;

public class LoanDetails extends JFrame {
    private JPanel loanDetailsPanel;

    public LoanDetails(LoanAccount loanAccount) {

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        loanDetailsPanel = new JPanel();
        loanDetailsPanel.setLayout(new GridBagLayout());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return loanDetailsPanel;
    }
}
