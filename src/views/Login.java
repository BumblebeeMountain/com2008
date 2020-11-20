package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.StudentController;
import controllers.UserController;
import exceptions.IncorrectLoginCredentialsException;
import models.Student;
import models.User;

public class Login extends JPanel {

    private static final long serialVersionUID = -7805314236285617248L;
    private Main rootFrame;

    public Login(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void loginButtonActionPerformed(ActionEvent e) {

        String email = emailTextBox.getText().trim().toLowerCase();
        System.out.println(email);
        String password = new String(passwordBox.getPassword());
        System.out.println(password);

        if (email.equals("") || password.equals("")) {
            rootFrame.showError("Please complete the form.");
        } else {
            User user = null;
            try {
                user = UserController.login(email, password);
                switch (user.getAccountType()) {
                    case ADMINISTRATOR:
                        rootFrame.showMessage("The admin dashboard is still in development.");
                        break;
                    case REGISTRAR:
                        this.rootFrame.moveToRegistrarDashboard();
                        break;
                    case TEACHER:
                        rootFrame.showMessage("The teacher dashboard is still in development.");
                        break;
                    case STUDENT:
                        Student student = StudentController.getStudent(email);
                        this.rootFrame.moveToStudentRecord(student.getRegistrationNumber());
                        break;
                    default:
                        throw new Exception();
                }
            } catch (IncorrectLoginCredentialsException err) {
                rootFrame.showError("Incorrect login credentials. Please try again.");
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
        label1 = new JLabel();
        emailTextBox = new JTextField();
        label2 = new JLabel();
        passwordBox = new JPasswordField();
        loginButton = new JButton();

        // ======== this ========
        setLayout(new BorderLayout());

        // ======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 249 };

            // ---- label1 ----
            label1.setText("Email:");
            panel1.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            panel1.add(emailTextBox, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label2 ----
            label2.setText("Password:");
            panel1.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            panel1.add(passwordBox, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- loginButton ----
            loginButton.setText("Login");
            loginButton.addActionListener(e -> loginButtonActionPerformed(e));
            panel1.add(loginButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        }
        add(panel1, BorderLayout.CENTER);
    }

    private JPanel panel1;
    private JLabel label1;
    private JTextField emailTextBox;
    private JLabel label2;
    private JPasswordField passwordBox;
    private JButton loginButton;
}
