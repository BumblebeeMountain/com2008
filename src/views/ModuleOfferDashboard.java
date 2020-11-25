package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModuleOfferDashboard extends JPanel {

    private static final long serialVersionUID = -8156607164022916548L;
    private Main rootFrame;

    public ModuleOfferDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void addModuleOfferButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAdminDashboard();
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        moduleOfferTable = new JTable();
        panel1 = new JPanel();
        addModuleOfferButton = new JButton();

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
            body.setViewportView(moduleOfferTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addModuleOfferButton ----
            addModuleOfferButton.setText("Add Module Offer");
            addModuleOfferButton.addActionListener(e -> addModuleOfferButtonActionPerformed(e));
            panel1.add(addModuleOfferButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable moduleOfferTable;
    private JPanel panel1;
    private JButton addModuleOfferButton;
}
