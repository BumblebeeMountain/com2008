package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModuleGrades extends JPanel {

    private static final long serialVersionUID = -2358750203321858050L;
    private Main rootFrame;
    private Integer studentRegistrationNumber;

    public ModuleGrades(Main rootFrame, Integer studentRegistrationNumber) {
        this.rootFrame = rootFrame;
        this.studentRegistrationNumber = studentRegistrationNumber;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void saveButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void registerNextButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        panel1 = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        scrollPane1 = new JScrollPane();
        moduleTable = new JTable();
        panel2 = new JPanel();
        label1 = new JLabel();
        registrationNumber = new JLabel();
        saveButton = new JButton();
        registerNextButton = new JButton();

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
            scrollPane1.setViewportView(moduleTable);
        }
        add(scrollPane1, BorderLayout.CENTER);

        // ======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0 };
            ((GridBagLayout) panel2.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

            // ---- label1 ----
            label1.setText("Registration Number:");
            label1.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- registrationNumber ----
            registrationNumber.setText("reg number");
            panel2.add(registrationNumber, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- saveButton ----
            saveButton.setText("Save Changes");
            saveButton.addActionListener(e -> saveButtonActionPerformed(e));
            panel2.add(saveButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- registerNextButton ----
            registerNextButton.setText("Register For Next Year");
            registerNextButton.addActionListener(e -> registerNextButtonActionPerformed(e));
            panel2.add(registerNextButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
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
    private JLabel registrationNumber;
    private JButton saveButton;
    private JButton registerNextButton;
}
