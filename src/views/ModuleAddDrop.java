package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModuleAddDrop extends JPanel {

    private static final long serialVersionUID = -3970499508593858878L;
    private Main rootFrame;
    private Integer studentRegistrationNumber;

    public ModuleAddDrop(Main rootFrame, Integer studentRegistrationNumber) {
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

    private void addButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void submitButtonActionPerformed(ActionEvent e) {
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
        scrollPane2 = new JScrollPane();
        optionalModuleList = new JComboBox<>();
        addButton = new JButton();
        label2 = new JLabel();
        numberOfCredits = new JLabel();
        submitButton = new JButton();

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
            ((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            ((GridBagLayout) panel2.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                    1.0E-4 };

            // ---- label1 ----
            label1.setText("Select Optional Module:");
            label1.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ======== scrollPane2 ========
            {
                scrollPane2.setViewportView(optionalModuleList);
            }
            panel2.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- addButton ----
            addButton.setText("Add");
            addButton.addActionListener(e -> addButtonActionPerformed(e));
            panel2.add(addButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label2 ----
            label2.setText("Total credits:");
            label2.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label2, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- numberOfCredits ----
            numberOfCredits.setText("numberOfCredits");
            numberOfCredits.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel2.add(numberOfCredits, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- submitButton ----
            submitButton.setText("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));
            panel2.add(submitButton, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
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
    private JScrollPane scrollPane2;
    private JComboBox<String> optionalModuleList;
    private JButton addButton;
    private JLabel label2;
    private JLabel numberOfCredits;
    private JButton submitButton;
}
