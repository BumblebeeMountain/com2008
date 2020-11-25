package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import models.Constants.*;
import controllers.UserController;
import models.User;
import controllers.StudentController;
import exceptions.*;

public class AddUser extends JPanel {

    private static final long serialVersionUID = 212693124617503329L;
    private Main rootFrame;
 
    public AddUser(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToUserDashboard();
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        Title title = Title.valueOf((String)titleComboBox.getSelectedItem());
        String forename = forenameBox.getText();
        String surname = surnameBox.getText();
        String password = String.valueOf(passwordBox.getPassword());

        AccountType accountType = AccountType.valueOf((String)accountLevelComboBox.getSelectedItem());

        Boolean formValid = true;
        if (forename.equals("") || surname.equals("") || password.equals("")) {
            this.rootFrame.showError("Please fill in the form");
            formValid = false;
            clear();
        }

        try {
            if (formValid) {
                User u = UserController.createUser(title, forename, surname, password, accountType);
                this.rootFrame.showMessage("User " + u.getEmail() + " was created");
                clear();
            }
        } catch (GeneralProcessingException ex ) {
            this.rootFrame.showError("General error");
        }
    }

    private void clear() {
        this.rootFrame.moveToAddUser();
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JPanel();
        label1 = new JLabel();
        titleComboBox = new JComboBox<String>();
        label2 = new JLabel();
        forenameBox = new JTextField();
        label3 = new JLabel();
        surnameBox = new JTextField();
        label4 = new JLabel();
        passwordBox = new JPasswordField();
        label5 = new JLabel();
        accountLevelComboBox = new JComboBox<String>();
        submitButton = new JButton();

        // ======== this ========
        setLayout(new BorderLayout());

        Title[] titles = Title.class.getEnumConstants();
        AccountType[] accountTypes = AccountType.class.getEnumConstants();

        for (Title t : titles) {
            titleComboBox.addItem(t.toString());
        }

        for (AccountType a : accountTypes) {
            // Cannot create new student from this window
            if (a != AccountType.STUDENT) {
                accountLevelComboBox.addItem(a.toString());
            }
        }

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
            label1.setText("Title:");
            body.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            body.add(titleComboBox, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label2 ----
            label2.setText("Forename:");
            body.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- forenameBox ----
            forenameBox.setFont(new Font("Tahoma", Font.ITALIC, 10));
            body.add(forenameBox, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label3 ----
            label3.setText("Surname:");
            body.add(label3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- surnameBox ----
            surnameBox.setFont(new Font("Tahoma", Font.ITALIC, 10));
            body.add(surnameBox, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label4 ----
            label4.setText("Password:");
            body.add(label4, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            body.add(passwordBox, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label5 ----
            label5.setText("Account Level:");
            body.add(label5, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            body.add(accountLevelComboBox, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- submitButton ----
            submitButton.setText("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));
            body.add(submitButton, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        }
        add(body, BorderLayout.CENTER);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JPanel body;
    private JLabel label1;
    private JComboBox<String> titleComboBox;
    private JLabel label2;
    private JTextField forenameBox;
    private JLabel label3;
    private JTextField surnameBox;
    private JLabel label4;
    private JPasswordField passwordBox;
    private JLabel label5;
    private JComboBox<String> accountLevelComboBox;
    private JButton submitButton;
}
