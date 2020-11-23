package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserDashboard extends JPanel {

    private static final long serialVersionUID = -536642684504914299L;
    private Main rootFrame;

    public UserDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void addUserButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        userTable = new JTable();
        panel1 = new JPanel();
        addUserButton = new JButton();

        // ======== this ========
        setLayout(new BorderLayout());

        // ======== header ========
        {
            header.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- logoutButton ----
            logoutButton.setText("Logout");
            logoutButton.addActionListener(e -> logoutButtonActionPerformed(e));
            header.add(logoutButton);

            // ---- goBackButton ----
            goBackButton.setText("Go Back");
            goBackButton.addActionListener(e -> goBackButtonActionPerformed(e));
            header.add(goBackButton);
        }
        add(header, BorderLayout.NORTH);

        // ======== body ========
        {
            body.setViewportView(userTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addUserButton ----
            addUserButton.setText("Add User");
            addUserButton.addActionListener(e -> addUserButtonActionPerformed(e));
            panel1.add(addUserButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable userTable;
    private JPanel panel1;
    private JButton addUserButton;
}
