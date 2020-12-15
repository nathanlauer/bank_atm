package com.bank.atm;

import com.bank.atm.backend.authentication.DefaultAdmin;
import com.bank.atm.gui.login.Login;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        DefaultAdmin.run();
        JFrame frame = new Login("Login");
        frame.setVisible(true);
    }
}
