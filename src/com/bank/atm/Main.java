package com.bank.atm;

import com.bank.atm.backend.authentication.DefaultAdmin;
import com.bank.atm.backend.currency.InitExchangeRates;
import com.bank.atm.gui.login.Login;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        DefaultAdmin.run();
        try {
            InitExchangeRates.run();
        } catch (IOException e) {
            // Shouldn't happen
            e.printStackTrace();
            System.out.println("Failed to read in exchange rates! Perhaps missing the data/exchangeRates.txt file?");
        }
        JFrame frame = new Login("Login");
        frame.setVisible(true);
    }
}
