package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddDepartment extends JPanel {

    private static final long serialVersionUID = -1381829755397917973L;
    private Main rootFrame;

    public AddDepartment(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JPanel();
        label1 = new JLabel();
        deptName = new JTextField();
        label2 = new JLabel();
        deptCode = new JTextField();
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
            ((GridBagLayout) body.getLayout()).columnWidths = new int[] { 185 };

            // ---- label1 ----
            label1.setText("Department Name:");
            body.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- deptName ----
            deptName.setFont(new Font("Tahoma", Font.ITALIC, 10));
            body.add(deptName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label2 ----
            label2.setText("Department Code:");
            body.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- deptCode ----
            deptCode.setFont(new Font("Tahoma", Font.ITALIC, 10));
            body.add(deptCode, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- submitButton ----
            submitButton.setText("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));
            body.add(submitButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        }
        add(body, BorderLayout.CENTER);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JPanel body;
    private JLabel label1;
    private JTextField deptName;
    private JLabel label2;
    private JTextField deptCode;
    private JButton submitButton;
}
