package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DegreeDashboard extends JPanel {

    private static final long serialVersionUID = 4177033550031280596L;
    private Main rootFrame;

    public DegreeDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void addDegreeButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        degreeTable = new JTable();
        panel1 = new JPanel();
        addDegreeButton = new JButton();

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
            body.setViewportView(degreeTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addDegreeButton ----
            addDegreeButton.setText("Add Degree");
            addDegreeButton.addActionListener(e -> addDegreeButtonActionPerformed(e));
            panel1.add(addDegreeButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable degreeTable;
    private JPanel panel1;
    private JButton addDegreeButton;
}
