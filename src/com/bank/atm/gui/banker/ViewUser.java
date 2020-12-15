package com.bank.atm.gui.banker;

import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.users.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ViewUser extends JFrame{
    private JPanel viewUserPanel;
    private JScrollPane viewUserScrollPane;
    private JTable viewUserTable;
    private JButton backButton;
    private JPanel backPanel;

    public ViewUser(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(viewUserPanel);
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

    public static void main(String [] args){
        JFrame frame = new ViewUser("View Users");
        frame.setVisible(true);
    }

    private void createViewUserTable(){
        String[] columnNames = {"First Name",
                "Last Name"};

        List<User> clientList = new ArrayList<User>();
        clientList.addAll(UsersCollectionManager.getInstance().allClients());

        for(User tempClient : clientList){
            System.out.println(tempClient.getID());
        }

        ViewUserCustomTableModel viewUserCustomTableModel = new ViewUserCustomTableModel(clientList,columnNames);
        viewUserTable = new JTable(viewUserCustomTableModel);
        viewUserTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && viewUserTable.getSelectedRow() != -1){
                    System.out.println("Double Click done");
                    System.out.println(viewUserCustomTableModel.getIdAt(viewUserTable.getSelectedRow()));
                    dispose();
                    JFrame frame = new ViewSingleUserDetails("User Details");
                    frame.setVisible(true);
                }
            }
        });
        viewUserScrollPane = new JScrollPane(viewUserTable);
        viewUserTable.setFillsViewportHeight(true);
        viewUserScrollPane.setVisible(true);
        viewUserTable.setVisible(true);
    }

    private void createUIComponents() {
        this.createViewUserTable();
    }
}
