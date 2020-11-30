package views;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.DegreeController;
import controllers.ModuleController;
import exceptions.ExistingRecordException;
import exceptions.GeneralProcessingException;
import models.Degree;
import models.Module;


public class AddModuleOffer extends JPanel {

    private static final long serialVersionUID = 4558153664567909648L;
    private Main rootFrame;

    public AddModuleOffer(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToModuleOfferDashboard();
    }

    private void submitButtonActionPerformed(ActionEvent e) {

        String modCode = String.valueOf(moduleCode.getSelectedItem());
        String degCode = String.valueOf(degreeCode.getSelectedItem());
        String modLevel = String.valueOf(levelComboBox.getSelectedItem());
        Boolean isCore = isCoreModule.isSelected();

        System.out.println("Trying to add: " + modCode + " " + degCode + " " + modLevel);

        try {
            DegreeController.createDegreeModule(degCode, modCode, isCore, modLevel);
            this.rootFrame.showMessage("Module offer created.");
            this.rootFrame.moveToAddModuleOffer();
        } catch (ExistingRecordException err) {
            rootFrame.showError("This module offer already exists.");
        } catch (Exception err) {
            rootFrame.showError("An error occurred.");
        }
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JPanel();
        label1 = new JLabel();

        Module[] offeredModules = new Module[1];

        try {
            // Gets all offered modules
            offeredModules = ModuleController.getAllModules(true);

        } catch (GeneralProcessingException err) {
            rootFrame.showError("An error occurred.");
            this.rootFrame.moveToModuleOfferDashboard();
            return;
        }

        String[] offModuleCodes = new String[offeredModules.length];

        for (int i = 0; i < offModuleCodes.length; i++) {
            // Gets the name of all of these modules
            offModuleCodes[i] = offeredModules[i].getCode();
        }

        moduleCode = new JComboBox<String>(offModuleCodes);
        label2 = new JLabel();

        Degree[] offeredDegrees = new Degree[1];

        try {
            // Gets all offered degrees
            offeredDegrees = DegreeController.getAllDegrees(true);

        } catch (GeneralProcessingException err) {
            rootFrame.showError("An error occurred.");
            this.rootFrame.moveToModuleOfferDashboard();
            return;
        }

        String[] offDegreeCodes = new String[offeredDegrees.length];

        for (int i = 0; i < offDegreeCodes.length; i++) {
            // Gets the name of all of these modules
            offDegreeCodes[i] = offeredDegrees[i].getCode();
        }

        degreeCode = new JComboBox<String>(offDegreeCodes);
        label3 = new JLabel();
        String[] levels = {"1", "2", "3", "4", "P"};
        levelComboBox = new JComboBox<String>(levels);
        isCoreModule = new JCheckBox();
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
            ((GridBagLayout) body.getLayout()).columnWidths = new int[] { 140 };

            // ---- label1 ----
            label1.setText("Module Code:");
            body.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            body.add(moduleCode, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label2 ----
            label2.setText("Degree Code:");
            body.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            body.add(degreeCode, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label3 ----
            label3.setText("Level:");
            body.add(label3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            body.add(levelComboBox, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- isCoreModule ----
            isCoreModule.setText("Core Module");
            isCoreModule.setSelected(true);
            body.add(isCoreModule, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- submitButton ----
            submitButton.setText("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));
            body.add(submitButton, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        }
        add(body, BorderLayout.CENTER);

        // private String[] getAllModuleCode() {
        //     // Gets all offered modules
        //     Module[] offeredModules = ModuleController.getAllModules(true);

        //     // Gets the name of all of these modules
        //     String[] offModuleNames = new String[offeredModules.length];

        //     for (int i = 0; i < offModuleNames.length; i++) {
        //         offModuleNames[i] = offeredModules[i].getName();
        //     }
        // }
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JPanel body;
    private JLabel label1;
    private JComboBox<String> moduleCode;
    private JLabel label2;
    private JComboBox<String> degreeCode;
    private JLabel label3;
    private JComboBox<String> levelComboBox;
    private JCheckBox isCoreModule;
    private JButton submitButton;
}
