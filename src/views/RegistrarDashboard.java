package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.StudentController;
import models.Student;

public class RegistrarDashboard extends JPanel {

    private static final long serialVersionUID = 2713093958128383127L;
    private Main rootFrame;

    public RegistrarDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void studentSignUpButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToStudentSignUp();
    }

    private void moduleButtonActionPerformed(ActionEvent e) {
        
        String registrationNumber = registrationNumberBox.getText();
        if (registrationNumber.equals("")) {
            rootFrame.showError("Please complete the form.");
        } else {
            try {
                Student student = StudentController.getStudent(Integer.valueOf(registrationNumber));
                this.rootFrame.moveToModuleAddDrop(student.getRegistrationNumber());
            } catch (Exception err) {
                rootFrame.showError("Invalid registration number. Please try again.");
            }
        }

    }

    private void initComponents() {
        panel1 = new JPanel();
        logoutButton = new JButton();
        panel2 = new JPanel();
        panel4 = new JPanel();
        studentSignUpButton = new JButton();
        label1 = new JLabel();
        registrationNumberBox = new JTextField();
        moduleButton = new JButton();

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
            ((GridBagLayout) panel2.getLayout()).columnWidths = new int[] { 304 };

            // ======== panel4 ========
            {
                panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

                // ---- studentSignUpButton ----
                studentSignUpButton.setText("Initial Student Sign Up");
                studentSignUpButton.addActionListener(e -> studentSignUpButtonActionPerformed(e));
                panel4.add(studentSignUpButton);
            }
            panel2.add(panel4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label1 ----
            label1.setText("Please enter student's registration number:");
            panel2.add(label1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            panel2.add(registrationNumberBox, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- moduleButton ----
            moduleButton.setText("Add / Drop Modules");
            moduleButton.addActionListener(e -> moduleButtonActionPerformed(e));
            panel2.add(moduleButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel2, BorderLayout.CENTER);
    }

    private JPanel panel1;
    private JButton logoutButton;
    private JPanel panel2;
    private JPanel panel4;
    private JButton studentSignUpButton;
    private JLabel label1;
    private JTextField registrationNumberBox;
    private JButton moduleButton;
}
