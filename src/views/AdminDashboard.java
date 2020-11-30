package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminDashboard extends JPanel {

    private static final long serialVersionUID = 4627354792820397735L;
    private Main rootFrame;

    public AdminDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void userDashButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToUserDashboard();
    }

    private void moduleDashButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToModuleDashboard();
    }

    private void degreeDashButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToDegreeDashboard();
    }

    private void departmentDashButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToDepartmentDashboard();
    }

    private void moduleOfferDashButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToModuleOfferDashboard();
    }

    private void initComponents() {
        panel1 = new JPanel();
        logoutButton = new JButton();
        panel2 = new JPanel();
        userDashButton = new JButton();
        moduleDashButton = new JButton();
        degreeDashButton = new JButton();
        departmentDashButton = new JButton();
        moduleOfferDashButton = new JButton();

        // ======== this ========
        setLayout(new BorderLayout());

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- logoutButton ----
            logoutButton.setText("Logout");
            logoutButton.addActionListener(e -> logoutButtonActionPerformed(e));
            panel1.add(logoutButton);
        }
        add(panel1, BorderLayout.NORTH);

        // ======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout) panel2.getLayout()).columnWidths = new int[] { 135 };

            // ---- userDashButton ----
            userDashButton.setText("User Dashboard");
            userDashButton.addActionListener(e -> userDashButtonActionPerformed(e));
            panel2.add(userDashButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- moduleDashButton ----
            moduleDashButton.setText("Module Dashboard");
            moduleDashButton.addActionListener(e -> moduleDashButtonActionPerformed(e));
            panel2.add(moduleDashButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- degreeDashButton ----
            degreeDashButton.setText("Degree Dashboard");
            degreeDashButton.addActionListener(e -> degreeDashButtonActionPerformed(e));
            panel2.add(degreeDashButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- departmentDashButton ----
            departmentDashButton.setText("Department Dashboard");
            departmentDashButton.addActionListener(e -> departmentDashButtonActionPerformed(e));
            panel2.add(departmentDashButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- moduleOfferDashButton ----
            moduleOfferDashButton.setText("Module Offer Dashboard");
            moduleOfferDashButton.addActionListener(e -> moduleOfferDashButtonActionPerformed(e));
            panel2.add(moduleOfferDashButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel2, BorderLayout.CENTER);
    }

    private JPanel panel1;
    private JButton logoutButton;
    private JPanel panel2;
    private JButton userDashButton;
    private JButton moduleDashButton;
    private JButton degreeDashButton;
    private JButton departmentDashButton;
    private JButton moduleOfferDashButton;
}
