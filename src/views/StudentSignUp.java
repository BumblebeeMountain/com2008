package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.DegreeController;
import controllers.RegistrationController;
import controllers.StudentController;
import models.Constants;
import models.Degree;
import models.Student;

public class StudentSignUp extends JPanel {

    private static final long serialVersionUID = 6755749167224810905L;
    private Main rootFrame;

    public StudentSignUp(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();

        // Set the JComboBoxes
        Constants.Title[] titles = Constants.Title.values();
        for (Constants.Title t : titles) titleComboBox.addItem(t.toString());
        try {
            Degree[] degrees = DegreeController.getAllDegrees(true);
            for (Degree d : degrees) degreeComboBox.addItem(d.getCode());
        } catch (Exception e) {
            this.rootFrame.moveToRegistrarDashboard(); // Error - go back
        }
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToRegistrarDashboard();
    }

    private void createStudentButtonActionPerformed(ActionEvent e) {
        String title = (String)titleComboBox.getSelectedItem();
        String forename = forenameBox.getText().trim();
        String surname = surnameBox.getText().trim();
        String password = new String(passwordBox.getPassword());
        String personalTutor = personalTutorBox.getText().trim();
        String degreeCode = (String)degreeComboBox.getSelectedItem();

        if (title.equals("") || forename.equals("") || surname.equals("") || password.equals("") || personalTutor.equals("") || degreeCode.equals("")) {
            rootFrame.showError("Please complete the form.");
        } else {
            try {
                Student student = StudentController.createStudent(Constants.Title.valueOf(title), forename, surname, password, Constants.AccountType.STUDENT, personalTutor);
                RegistrationController.createInitialRegistration(student.getRegistrationNumber(), degreeCode);
                rootFrame.showMessage("Student has been registered:" + System.lineSeparator() + "Email: " + student.getEmail() + System.lineSeparator() + "Registration Number: " + student.getRegistrationNumber());
                rootFrame.moveToRegistrarDashboard();
            } catch (Exception err) {
                rootFrame.showError("There was an error. Please try again.");
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Dominic Barter
        panel1 = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        panel3 = new JPanel();
        label1 = new JLabel();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        titleComboBox = new JComboBox<>();
        passwordBox = new JPasswordField();
        label2 = new JLabel();
        label5 = new JLabel();
        forenameBox = new JTextField();
        personalTutorBox = new JTextField();
        label3 = new JLabel();
        label6 = new JLabel();
        surnameBox = new JTextField();
        scrollPane2 = new JScrollPane();
        degreeComboBox = new JComboBox<>();
        createStudentButton = new JButton();

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

        // ======== panel3 ========
        {
            panel3.setLayout(new GridBagLayout());
            ((GridBagLayout) panel3.getLayout()).columnWidths = new int[] { 105, 100 };

            // ---- label1 ----
            label1.setText("Title:");
            label1.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ---- label4 ----
            label4.setText("Password:");
            label4.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label4, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ======== scrollPane1 ========
            {
                scrollPane1.setViewportView(titleComboBox);
            }
            panel3.add(scrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
            panel3.add(passwordBox, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label2 ----
            label2.setText("Forename:");
            label2.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ---- label5 ----
            label5.setText("Personal Tutor:");
            label5.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label5, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            panel3.add(forenameBox, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
            panel3.add(personalTutorBox, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label3 ----
            label3.setText("Surname:");
            label3.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ---- label6 ----
            label6.setText("Degree:");
            label6.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label6, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            panel3.add(surnameBox, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

            // ======== scrollPane2 ========
            {
                scrollPane2.setViewportView(degreeComboBox);
            }
            panel3.add(scrollPane2, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- createStudentButton ----
            createStudentButton.setText("Create Student");
            createStudentButton.addActionListener(e -> createStudentButtonActionPerformed(e));
            panel3.add(createStudentButton, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        }
        add(panel3, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization //GEN-END:initComponents
    }

    private JPanel panel1;
    private JButton logoutButton;
    private JButton goBackButton;
    private JPanel panel3;
    private JLabel label1;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JComboBox<String> titleComboBox;
    private JPasswordField passwordBox;
    private JLabel label2;
    private JLabel label5;
    private JTextField forenameBox;
    private JTextField personalTutorBox;
    private JLabel label3;
    private JLabel label6;
    private JTextField surnameBox;
    private JScrollPane scrollPane2;
    private JComboBox<String> degreeComboBox;
    private JButton createStudentButton;
}
