package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TeacherDashboard extends JPanel {

    private static final long serialVersionUID = 5756724153822064563L;
    private Main rootFrame;

    public TeacherDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void accessStudentRecordButtonActionPerformed(ActionEvent e) {
		this.rootFrame.logout();
	}

	private void updateGradesButtonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

    private void initComponents() {
        label1 = new JLabel();
        registrationNumberBox = new JTextField();
        panel1 = new JPanel();
        accessStudentRecordButton = new JButton();
        updateGradesButton = new JButton();

        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[] { 0, 252, 0 };

        // ---- label1 ----
        label1.setText("Please enter the student's registration number:");
        add(label1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        add(registrationNumberBox, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //======== panel1 ========
		{
			panel1.setLayout(new GridLayout(0, 2));

			//---- accessStudentRecordButton ----
			accessStudentRecordButton.setText("Access Student Record");
			accessStudentRecordButton.addActionListener(e -> accessStudentRecordButtonActionPerformed(e));
			panel1.add(accessStudentRecordButton);

			//---- updateGradesButton ----
			updateGradesButton.setText("Update Grades");
			updateGradesButton.addActionListener(e -> updateGradesButtonActionPerformed(e));
			panel1.add(updateGradesButton);
		}
        add(panel1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization //GEN-END:initComponents
    }

    private JLabel label1;
    private JTextField registrationNumberBox;
    private JPanel panel1;
    private JButton accessStudentRecordButton;
    private JButton updateGradesButton;
}
