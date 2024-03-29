package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.RegistrationController;
import models.Registration;
import models.SelectedModule;

public class RegistrationDetails extends JPanel {

    private static final long serialVersionUID = 2001232095172440708L;
    private Main rootFrame;
    private Integer registrationNumber;
    private Character registrationPeriod;

    public RegistrationDetails(Main rootFrame, Integer registrationNumber, Character registrationPeriod) {
        this.rootFrame = rootFrame;
        this.registrationNumber = registrationNumber;
        this.registrationPeriod = registrationPeriod;
        initComponents();
    
        // Fill in the labels
        try {
            Registration reg = RegistrationController.getStudentRegistration(registrationNumber, registrationPeriod);
            year.setText(reg.getStartYear().toString());
            level.setText(reg.getLevel().toString());
            period.setText(reg.getPeriod().toString());
        } catch (Exception e) {
            this.rootFrame.moveToStudentRecord(this.registrationNumber); // Errored
        }

        moduleTable.getTableHeader().setReorderingAllowed(false);

    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToStudentRecord(this.registrationNumber);
    }

    private void initComponents() {

        panel1 = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        scrollPane1 = new JScrollPane();
        moduleTable = new JTable();
        panel2 = new JPanel();
        label1 = new JLabel();
        year = new JLabel();
        label3 = new JLabel();
        level = new JLabel();
        label5 = new JLabel();
        period = new JLabel();

        // ======== this ========
        setLayout(new BorderLayout());

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- logoutButton ----
            logoutButton.setText("Logout");
            logoutButton.addActionListener(e -> logoutButtonActionPerformed(e));
            panel1.add(logoutButton);

            // ---- goBackButton ----
            goBackButton.setText("Go Back");
            goBackButton.addActionListener(e -> goBackButtonActionPerformed(e));
            panel1.add(goBackButton);
        }
        add(panel1, BorderLayout.NORTH);

        // ======== scrollPane1 ========
        {
            Object[] columnNames = {"Module Code", "Module Name", "Credits", "Grade (%)", "Resit Grade (%)"};
            try {
                SelectedModule[] modules = RegistrationController.getStudentRegistration(this.registrationNumber, this.registrationPeriod).getSelectedModules();
                Object[][] rowData = new Object[modules.length][columnNames.length];
                for (int i = 0; i < modules.length; i++) {
                    SelectedModule m = modules[i];
                    rowData[i][0] = m.getCode();
                    rowData[i][1] = m.getName();
                    rowData[i][2] = m.getCredits();
                    rowData[i][3] = m.getFirstAttemptResult();
                    rowData[i][4] = m.getSecondAttemptResult();
                }
                moduleTable = new JTable(rowData, columnNames);
            } catch (Exception e) {
                this.rootFrame.moveToStudentRecord(this.registrationNumber); return;
            }
            
            scrollPane1.setViewportView(moduleTable);
        }
        add(scrollPane1, BorderLayout.CENTER);

        // ======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
            ((GridBagLayout) panel2.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

            // ---- label1 ----
            label1.setText("Start Year:   ");
            label1.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- year ----
            year.setText("year");
            year.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel2.add(year, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label3 ----
            label3.setText("Level:");
            label3.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- level ----
            level.setText("level");
            level.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel2.add(level, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label5 ----
            label5.setText("Period:");
            label5.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- period ----
            period.setText("period");
            period.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel2.add(period, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel2, BorderLayout.EAST);
    }

    private JPanel panel1;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane scrollPane1;
    private JTable moduleTable;
    private JPanel panel2;
    private JLabel label1;
    private JLabel year;
    private JLabel label3;
    private JLabel level;
    private JLabel label5;
    private JLabel period;
}
