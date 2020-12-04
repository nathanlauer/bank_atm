package com.bank.atm.gui.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface for users to add new account
 * @author szhen
 */
public class UserAddAccount extends JFrame {
    private final int frameWidth =500;
    private final int frameHeight=500;

    //list of available account types and their options. Add any new account types here.
    private enum AccountType{
        CHECKINGS("Standard"),
        SAVINGS("Low-End","High-End"),
        SECURITY("Standard");

        private String[] options;
        AccountType(String... options){
            this.options = options;
        }
        public String[] getOptions() {
            return options;
        }
    }

    private enum CurrencyType{
        USD,JPY,EURO
    }

    private JPanel userAddAccountPanel;
    private JComboBox<AccountType> accountTypeComboBox;
    private JComboBox<CurrencyType> currencyTypeComboBox;
    private JComboBox<String> accountOptionsComboBox;
    private JTextArea accountNameTextArea;
    private JButton createAccountButton;

    /**
     *
     * @param userID - ID of user to add account for
     */
    public UserAddAccount(String userID){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(userAddAccountPanel);//sets content to our menu panel
        this.setPreferredSize(new Dimension(frameWidth,frameHeight));//set width and height of our frame
        this.pack();

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo when createAccount button is clicked, create account.
//                createAccount();
                System.out.println("Account is being created");
            }
        });
        accountTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<AccountType> comboBox = (JComboBox<AccountType>) event.getSource();
                //change the available account options based on selected account type
                String [] accountOptions = ((AccountType)accountTypeComboBox.getSelectedItem()).getOptions();
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( accountOptions );
                accountOptionsComboBox.setModel( model );
            }
        });
    }




    private void createUIComponents() {
        accountTypeComboBox = new JComboBox<AccountType>(AccountType.values());
        String [] accountOptions = ((AccountType)accountTypeComboBox.getSelectedItem()).getOptions();
        accountOptionsComboBox = new JComboBox<>(accountOptions);

        currencyTypeComboBox = new JComboBox<>(CurrencyType.values());



    }
//    //TODO implement create account
//    private Account createAccount(){
//
//        switch (accountTypeComboBox.getPrototypeDisplayValue()){
//            case SAVINGS:
//                //iterate through selected options for savings account and construct account
//                break;
//            case SECURITY:
//                break;
//            case CHECKINGS:
//                break;
//        }
//        switch (currencyTypeComboBox.getPrototypeDisplayValue()){
//            case USD:
//                break;
//            case JPY:
//                break;
//            case EURO:
//                break;
//        }
//        return null;
//    }

}
