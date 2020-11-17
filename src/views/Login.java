package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JPanel {

    private static final long serialVersionUID = 8069814527553036323L;
    private Main rootFrame;

    public Login(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        System.out.println("Hello!");
        rootFrame.moveToTeacherDashboard();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Dominic Barter
        label6 = new JLabel();
        panel1 = new JPanel();
        label4 = new JLabel();
        emailBox = new JTextField();
        label5 = new JLabel();
        passwordBox = new JPasswordField();
        loginButton = new JButton();

        // ======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[] { 0, 218, 0 };
        ((GridBagLayout) getLayout()).rowHeights = new int[] { 45, 155, 40 };

        // ---- label6 ----
        label6.setText("Login using your university credentials");
        label6.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(label6, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        // ======== panel1 ========
        {
            panel1.setLayout(new GridLayout(4, 0));

            // ---- label4 ----
            label4.setText("Email:");
            panel1.add(label4);
            panel1.add(emailBox);

            // ---- label5 ----
            label5.setText("Password:");
            panel1.add(label5);
            panel1.add(passwordBox);
        }
        add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        // ---- loginButton ----
        loginButton.setText("Login");
        loginButton.addActionListener(e -> loginButtonActionPerformed(e));
        add(loginButton, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization //GEN-END:initComponents
    }

    private JLabel label6;
    private JPanel panel1;
    private JLabel label4;
    private JTextField emailBox;
    private JLabel label5;
    private JPasswordField passwordBox;
    private JButton loginButton;

}
