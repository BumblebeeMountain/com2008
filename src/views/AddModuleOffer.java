package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddModuleOffer extends JPanel {

    private static final long serialVersionUID = 4558153664567909648L;
    private Main rootFrame;

    public AddModuleOffer(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JPanel();
        label1 = new JLabel();
        moduleCode = new JComboBox<>();
        label2 = new JLabel();
        degreeCode = new JComboBox<>();
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

            // ---- isCoreModule ----
            isCoreModule.setText("Core Module");
            isCoreModule.setSelected(true);
            body.add(isCoreModule, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- submitButton ----
            submitButton.setText("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));
            body.add(submitButton, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        }
        add(body, BorderLayout.CENTER);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JPanel body;
    private JLabel label1;
    private JComboBox<String> moduleCode;
    private JLabel label2;
    private JComboBox<String> degreeCode;
    private JCheckBox isCoreModule;
    private JButton submitButton;
}
