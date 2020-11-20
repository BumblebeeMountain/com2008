package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.StudentController;
import models.Student;

public class TeacherDashboard extends JPanel {

    private static final long serialVersionUID = -4029694441237975254L;
    private Main rootFrame;

    public TeacherDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void studentRecordButtonActionPerformed(ActionEvent e) {
        
        String registrationNumber = registrationNumberBox.getText();
        if (registrationNumber.equals("")) {
            rootFrame.showError("Please complete the form.");
        } else {
            try {
                Student student = StudentController.getStudent(Integer.valueOf(registrationNumber));
                this.rootFrame.moveToStudentRecord(student.getRegistrationNumber());
            } catch (Exception err) {
                rootFrame.showError("Invalid registration number. Please try again.");
            }
        }

    }

    private void updateGradesButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        panel4 = new JPanel();
        panel3 = new JPanel();
        logoutButton = new JButton();
        panel5 = new JPanel();
        label2 = new JLabel();
        registrationNumberBox = new JTextField();
        panel6 = new JPanel();
        studentRecordButton = new JButton();
        updateGradesButton = new JButton();

        // ======== this ========
        setLayout(new BorderLayout());

        // ======== panel4 ========
        {
            panel4.setLayout(new BorderLayout());

            // ======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

                // ---- logoutButton ----
                logoutButton.setText("Logout");
                logoutButton.addActionListener(e -> logoutButtonActionPerformed(e));
                panel3.add(logoutButton);
            }
            panel4.add(panel3, BorderLayout.NORTH);

            // ======== panel5 ========
            {
                panel5.setLayout(new GridBagLayout());
                ((GridBagLayout) panel5.getLayout()).columnWidths = new int[] { 0, 283, 0 };

                // ---- label2 ----
                label2.setText("Please enter the student's registration number:");
                panel5.add(label2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
                panel5.add(registrationNumberBox, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

                // ======== panel6 ========
                {
                    panel6.setLayout(new GridLayout(1, 2));

                    // ---- studentRecordButton ----
                    studentRecordButton.setText("Access Student Record");
                    studentRecordButton.addActionListener(e -> studentRecordButtonActionPerformed(e));
                    panel6.add(studentRecordButton);

                    // ---- updateGradesButton ----
                    updateGradesButton.setText("Update Grades");
                    updateGradesButton.addActionListener(e -> updateGradesButtonActionPerformed(e));
                    panel6.add(updateGradesButton);
                }
                panel5.add(panel6, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
            }
            panel4.add(panel5, BorderLayout.CENTER);
        }
        add(panel4, BorderLayout.CENTER);
    }

    private JPanel panel4;
    private JPanel panel3;
    private JButton logoutButton;
    private JPanel panel5;
    private JLabel label2;
    private JTextField registrationNumberBox;
    private JPanel panel6;
    private JButton studentRecordButton;
    private JButton updateGradesButton;
}
