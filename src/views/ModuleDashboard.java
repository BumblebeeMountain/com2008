package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModuleDashboard extends JPanel {

    private static final long serialVersionUID = -4113817667508088358L;
    private Main rootFrame;

    public ModuleDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void addModuleButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        moduleTable = new JTable();
        panel1 = new JPanel();
        addModuleButton = new JButton();

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
            body.setViewportView(moduleTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addModuleButton ----
            addModuleButton.setText("Add Module");
            addModuleButton.addActionListener(e -> addModuleButtonActionPerformed(e));
            panel1.add(addModuleButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable moduleTable;
    private JPanel panel1;
    private JButton addModuleButton;
}
