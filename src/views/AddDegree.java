package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.TreeSet;

import models.Degree;
import models.Department;
import controllers.DegreeController;
import controllers.DepartmentController;
import exceptions.*;

public class AddDegree extends JPanel {

    private static final long serialVersionUID = 7494136331052392856L;
    private Main rootFrame;

    public AddDegree(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToDegreeDashboard();
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        String degName = this.degreeName.getText();
        String degCode = this.degreeCode.getText();
        int maxLevelSelected = Integer.valueOf(String.valueOf(this.maxLevel.getSelectedItem()));
        boolean yii = String.valueOf(this.yearIndustry.getSelectedItem()).equals("YES");
        String leadDep = String.valueOf(this.leadDept.getSelectedItem());
        String[] partnerDeps = new String[this.partnerDepSet.size()];
        partnerDeps = this.partnerDepSet.toArray(partnerDeps);
        try {
            DegreeController.createDegree(degCode, degName, yii, maxLevelSelected);
            DepartmentController.createDegreeDepartment(leadDep, degCode, true);
            for (String d : partnerDeps) {
                DepartmentController.createDegreeDepartment(leadDep, d, false);
            }
            this.rootFrame.showMessage("Degree " + degName + " was created");
            this.rootFrame.moveToAddDegree();
        } catch (GeneralProcessingException ex ) {
            ex.printStackTrace();
            this.rootFrame.showError("General proceessing error");
            this.rootFrame.logout();
        } catch (ExistingRecordException ex) {
            this.rootFrame.showError("That degree already exists");
        } 
    }

    private void addPartnerButtonActionPerformed(ActionEvent e) {
        System.out.println("addPartnerButtonClicked");
        String selectedPartner = String.valueOf(partnerDept.getSelectedItem());
        String leadDep = String.valueOf(leadDept.getSelectedItem());
        System.out.println(selectedPartner);
        String newPartnerDepString = "<html>";
        if (!this.partnerDepSet.contains(leadDep) && !leadDep.equals(selectedPartner))
            this.partnerDepSet.add(selectedPartner);

        for (String s : this.partnerDepSet) {
            newPartnerDepString += s+"<br>";
        }
        newPartnerDepString += "</html>";

        this.partnerDepartments.setText(newPartnerDepString);
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JPanel();
        label1 = new JLabel();
        label5 = new JLabel();
        degreeName = new JTextField();
        leadDept = new JComboBox<>();
        degreeCode = new JTextField();
        maxLevel = new JComboBox<>();
        label2 = new JLabel();
        label6 = new JLabel();
        panel2 = new JPanel();
        partnerDept = new JComboBox<>();
        addPartnerButton = new JButton();
        label3 = new JLabel();
        panel1 = new JPanel();
        partnerDepartments = new JLabel();
        label4 = new JLabel();
        yearIndustry = new JComboBox<>();
        submitButton = new JButton();
        partnerDepSet = new TreeSet<String>();

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
        
        Department[] allDeps = new Department[0];
        try {
            allDeps = DepartmentController.getAllDepartments();
        } catch (GeneralProcessingException ex ) {
            ex.printStackTrace();
        } finally {
            for (Department d : allDeps) {
                String depCode = d.getCode();
                leadDept.addItem(depCode);
                partnerDept.addItem(depCode);
            }
        }

        yearIndustry.addItem("NO");
        yearIndustry.addItem("YES");

        for (int i=1; i<=6; i++) {
            maxLevel.addItem(String.valueOf(i));
        }

        // ======== body ========
        {
            body.setLayout(new GridBagLayout());
            ((GridBagLayout) body.getLayout()).columnWidths = new int[] { 130, 125 };

            // ---- label1 ----
            label1.setText("Degree Name:");
            body.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ---- label5 ----
            label5.setText("Lead Department:");
            body.add(label5, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- degreeName ----
            degreeName.setFont(new Font("Tahoma", Font.ITALIC, 10));
            body.add(degreeName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
            body.add(leadDept, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- degreeCode ----
            degreeCode.setFont(new Font("Tahoma", Font.ITALIC, 10));
            body.add(degreeCode, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
            body.add(maxLevel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ---- label2 ----
            label2.setText("Degree Code:");
            body.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ---- label6 ----
            label6.setText("Partner Departments:");
            body.add(label6, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ======== panel2 ========
            {
                panel2.setLayout(new GridLayout(1, 2));
                panel2.add(partnerDept);

                // ---- addPartnerButton ----
                addPartnerButton.setText("Add");
                addPartnerButton.addActionListener(e -> addPartnerButtonActionPerformed(e));
                panel2.add(addPartnerButton);
            }
            body.add(panel2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label3 ----
            label3.setText("Max Level:");
            body.add(label3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ======== panel1 ========
            {
                panel1.setLayout(new GridLayout(1, 2));

                // ---- partnerDepartments ----
                partnerDepartments.setText(partnerDepartmentsList);
                panel1.add(partnerDepartments);
            }
            body.add(panel1, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label4 ----
            label4.setText("Has Year In Industry:");
            body.add(label4, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
            body.add(yearIndustry, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ---- submitButton ----
            submitButton.setText("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));
            body.add(submitButton, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        }
        add(body, BorderLayout.CENTER);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JPanel body;
    private JLabel label1;
    private JLabel label5;
    private JTextField degreeName;
    private JComboBox<String> leadDept;
    private JTextField degreeCode;
    private JComboBox<String> maxLevel;
    private JLabel label2;
    private JLabel label6;
    private JPanel panel2;
    private JComboBox<String> partnerDept;
    private JButton addPartnerButton;
    private JLabel label3;
    private JPanel panel1;
    private JLabel partnerDepartments;
    private JLabel label4;
    private JComboBox<String> yearIndustry;
    private JButton submitButton;
    private String partnerDepartmentsList;
    private TreeSet<String> partnerDepSet;
}
