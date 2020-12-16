package com.bank.atm.gui.banker;

import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ViewUser extends JFrame {
    private JPanel viewUserPanel;
    private JScrollPane viewUserScrollPane;
    private JTable viewUserTable;
    private JButton backButton;
    private JPanel backPanel;
    private JPanel searchUserPanel;
    private JTextField searchUserTextField;
    private JButton searchUserButton;
    private List<User> clientList;

    public ViewUser(String title,List<User> searchedUsers) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(viewUserPanel);
        this.setPreferredSize(new Dimension(800, 500));//set width and height of our frame
        this.pack();
        this.setLocationRelativeTo(null);
        this.createViewUserTable(searchedUsers);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new BankerMenu("Banker Menu");
                frame.setVisible(true);
            }
        });
        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchString = searchUserTextField.getText().trim();
                UsersCollectionManager usersCollectionManager = UsersCollectionManager.getInstance();
                List<User> searchedUsersList = new ArrayList<User>();
                searchedUsersList.addAll(usersCollectionManager.findByFirstName(searchString));
                searchedUsersList.addAll(usersCollectionManager.findByLastName(searchString));
                createViewUserTable(searchedUsersList);
            }
        });
    }

    private void createViewUserTable(List<User> searchedUsers) {
        String[] columnNames = {"User ID","First Name",
                "Last Name"};

        this.clientList = new ArrayList<User>();
        try{
            if(searchedUsers == null || searchedUsers.isEmpty()){
                this.clientList.addAll(UsersCollectionManager.getInstance().allClients());
            } else{
                this.clientList.addAll(searchedUsers);
            }
        } catch (Exception e) {

        }

        ViewUserCustomTableModel viewUserCustomTableModel = new ViewUserCustomTableModel(this.clientList, columnNames);
        viewUserTable.setModel(viewUserCustomTableModel);
        viewUserTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && viewUserTable.getSelectedRow() != -1) {
                    ID selectedUserID = viewUserCustomTableModel.getIdAt(viewUserTable.getSelectedRow());
                    dispose();
                    JFrame frame = new ViewSingleUserDetails("User Details",selectedUserID);
                    frame.setVisible(true);
                }
            }
        });
        viewUserScrollPane.getViewport().add(viewUserTable);
        viewUserTable.setFillsViewportHeight(true);
        viewUserScrollPane.setVisible(true);
        viewUserTable.setVisible(true);
        viewUserTable.setRowHeight(30);
    }
}
