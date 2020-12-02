package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.ModuleController;
import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;

public class AddModule extends JPanel {

    private static final long serialVersionUID = -917324455397999555L;
    private Main rootFrame;

    public AddModule(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToModuleDashboard();
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        String name = moduleName.getText();
        String code = moduleCode.getText().toUpperCase();
        String credits = moduleCredits.getText();
        String periodOfTeaching = String.valueOf(teachingPeriod.getSelectedItem());

        if (name.equals("") || code.equals("") || credits.equals("") || periodOfTeaching.equals("")) { // Check
            this.rootFrame.showError("Please complete the form");
            return;
        }

        Integer creditsI = null;
        try {
            creditsI = Integer.valueOf(credits);
        } catch (Exception err) {
            this.rootFrame.showMessage("The number of credits must be an integer value.");
            return;
        }
        

        if (creditsI <= 0) { // Credits guard
            this.rootFrame.showMessage("Please enter a valid number of credits. ");
            return;
        }

        if (!code.matches("[A-Z][A-Z][A-Z][0-9][0-9][0-9][0-9]")) {
            this.rootFrame.showError("Module code must be three alphabetic characters in length, followed by 4 digits.");
            return;
        }

        try {
            ModuleController.createModule(code, name, creditsI, periodOfTeaching);
            rootFrame.showMessage("Module added.");
            this.rootFrame.moveToAddModule();
        } catch (ExistingRecordException err) {
            rootFrame.showError("This module already exists / this module has previously existed and has since been archived and cannot be used.");
        } catch (GeneralProcessingException err) {
            rootFrame.showError("There was an error, please try again.");
        }

    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JPanel();
        label1 = new JLabel();
        moduleName = new JTextField();
        label2 = new JLabel();
        moduleCode = new JTextField();
        label3 = new JLabel();
        moduleCredits = new JTextField();
        label4 = new JLabel();
        String[] periods = {"AUTUMN", "SPRING", "AUTUMN~SPRING"};
        teachingPeriod = new JComboBox<String>(periods);
        submitButton = new JButton();

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
            body.setLayout(new GridBagLayout());
            ((GridBagLayout) body.getLayout()).columnWidths = new int[] { 218 };

            // ---- label1 ----
            label1.setText("Module Name:");
            body.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- moduleName ----
            moduleName.setFont(new Font("Tahoma", Font.PLAIN, 10));
            body.add(moduleName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label2 ----
            label2.setText("Module Code:");
            body.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- moduleCode ----
            moduleCode.setFont(new Font("Tahoma", Font.PLAIN, 10));
            body.add(moduleCode, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label3 ----
            label3.setText("Module Credits:");
            body.add(label3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- moduleCredits ----
            moduleCredits.setFont(new Font("Tahoma", Font.PLAIN, 10));
            body.add(moduleCredits, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label4 ----
            label4.setText("Teaching Period:");
            body.add(label4, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            body.add(teachingPeriod, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- submitButton ----
            submitButton.setText("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));
            body.add(submitButton, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        }
        add(body, BorderLayout.CENTER);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JPanel body;
    private JLabel label1;
    private JTextField moduleName;
    private JLabel label2;
    private JTextField moduleCode;
    private JLabel label3;
    private JTextField moduleCredits;
    private JLabel label4;
    private JComboBox<String> teachingPeriod;
    private JButton submitButton;
}
