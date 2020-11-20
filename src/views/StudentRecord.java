package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import controllers.DegreeController;
import controllers.RegistrationController;
import controllers.StudentController;
import models.Degree;
import models.Registration;
import models.Student;

public class StudentRecord extends JPanel {
    
    // Instance variables
    private static final long serialVersionUID = -1855081097969207983L;
    private Main rootFrame;
    private Integer registrationNumber;
    private Student student;
    private Registration firstRegistration;
    private Degree degree;

    public StudentRecord(Main rootFrame, Integer registrationNumber) {

        // Basic setup
        this.rootFrame = rootFrame;
        this.registrationNumber = registrationNumber;
        initComponents();

        try {

            // Database calls
            this.student = StudentController.getStudent(this.registrationNumber);
            this.firstRegistration = RegistrationController.getStudentRegistration(this.registrationNumber, 'A');
            this.degree = DegreeController.getDegree(this.firstRegistration.getDegreeCode(), true);

            // Set the right labels
            this.name.setText(this.student.getTitle() + " " + this.student.getForename() + " " + this.student.getSurname());
            this.regNumber.setText(this.student.getRegistrationNumber().toString());
            this.degreeName.setText(this.firstRegistration.getDegreeCode() + " - " + this.degree.getName());
            this.startYear.setText(this.firstRegistration.getStartYear().toString());
            this.personalTutor.setText(this.student.getPersonalTutor());
            if (this.student.getHasGraduated()) {
                this.degreeClassification.setText("Finish calculateDegreeClassification()");
            } else {
                this.degreeClassification.setText("Not yet graduated.");
            }

            // Table gets set in the initComponents section - go look there!

        } catch (Exception e) {
            this.rootFrame.logout(); // Error
        }
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Dominic Barter
        panel1 = new JPanel();
        logoutButton = new JButton();
        scrollPane1 = new JScrollPane();
        panel3 = new JPanel();
        label1 = new JLabel();
        name = new JLabel();
        label3 = new JLabel();
        regNumber = new JLabel();
        label5 = new JLabel();
        degreeName = new JLabel();
        label7 = new JLabel();
        startYear = new JLabel();
        label9 = new JLabel();
        personalTutor = new JLabel();
        label11 = new JLabel();
        degreeClassification = new JLabel();

        // ======== this ========
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

        // ======== scrollPane1 ========
        {
            // I've added the following 5 lines :)
            TableCellRenderer tableRenderer;
            registrationTable = new JTable(new JTableButtonModel(this.rootFrame, this.registrationNumber));
            tableRenderer = registrationTable.getDefaultRenderer(JButton.class);
            registrationTable.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
            registrationTable.addMouseListener(new JTableButtonMouseListener(registrationTable));

            scrollPane1.setViewportView(registrationTable);
        }
        add(scrollPane1, BorderLayout.CENTER);

        // ======== panel3 ========
        {
            panel3.setLayout(new GridBagLayout());
            ((GridBagLayout) panel3.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            ((GridBagLayout) panel3.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                    0.0, 0.0, 0.0, 0.0, 1.0E-4 };

            // ---- label1 ----
            label1.setText("Full Name:");
            label1.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- name ----
            name.setText("name");
            name.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel3.add(name, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label3 ----
            label3.setText("Registration Number:");
            label3.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- regNumber ----
            regNumber.setText("regNumber");
            regNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel3.add(regNumber, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label5 ----
            label5.setText("Degree Name:");
            label5.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- degreeName ----
            degreeName.setText("degreeName");
            degreeName.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel3.add(degreeName, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label7 ----
            label7.setText("Start Year:");
            label7.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label7, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- startYear ----
            startYear.setText("startYear");
            startYear.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel3.add(startYear, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label9 ----
            label9.setText("Personal Tutor:");
            label9.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label9, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- personalTutor ----
            personalTutor.setText("personalTutor");
            personalTutor.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel3.add(personalTutor, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label11 ----
            label11.setText("Degree Classification:");
            label11.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel3.add(label11, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- degreeClassification ----
            degreeClassification.setText("degreeClassification");
            degreeClassification.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel3.add(degreeClassification, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel3, BorderLayout.EAST);
        // JFormDesigner - End of component initialization //GEN-END:initComponents
    }

    private JPanel panel1;
    private JButton logoutButton;
    private JScrollPane scrollPane1;
    private JTable registrationTable;
    private JPanel panel3;
    private JLabel label1;
    private JLabel name;
    private JLabel label3;
    private JLabel regNumber;
    private JLabel label5;
    private JLabel degreeName;
    private JLabel label7;
    private JLabel startYear;
    private JLabel label9;
    private JLabel personalTutor;
    private JLabel label11;
    private JLabel degreeClassification;
}

/**
 * Class used for putting buttons in a table
 */
class JTableButtonModel extends AbstractTableModel {

    private static final long serialVersionUID = 301047396186264466L;

    private Main rootFrame;
    private Integer registrationNumber;
    private Registration[] registrations;

    public JTableButtonModel (Main rootFrame, Integer registrationNumber) {

        this.rootFrame = rootFrame;
        this.registrationNumber = registrationNumber;

        try {   

            // Get the registrations
            this.registrations = RegistrationController.getStudentRegistrations(this.registrationNumber);

            // Set the table
            String[] columnNames = {"Start Year", "Period", "Level", "View"};
            Object[][] tableData = new Object[registrations.length][columnNames.length];
            for (int i = 0; i < tableData.length; i++) {
                Registration r = this.registrations[i];
                tableData[i][0] = r.getStartYear().toString();
                tableData[i][1] = r.getPeriod().toString();
                tableData[i][2] = r.getLevel().toString();
                
                JButton viewButton = new JButton("View");
                viewButton.addActionListener(e -> {
                    this.rootFrame.showMessage("You are trying to view registration: " + r.getPeriod().toString());
                });
                tableData[i][3] = viewButton;
            }

            // Set into the abstract model
            this.rows = tableData;
            this.columns = columnNames;

        } catch (Exception e) {
            this.rootFrame.logout(); // Error
        }
        
    }

    private Object[][] rows; 
    private String[] columns; 

    public String getColumnName(int column) {
       return columns[column];
    }
    public int getRowCount() {
       return rows.length;
    }
    public int getColumnCount() {
       return columns.length;
    }
    public Object getValueAt(int row, int column) {
       return rows[row][column];
    }
    public boolean isCellEditable(int row, int column) {
       return false;
    }
    public Class<?> getColumnClass(int column) {
       return getValueAt(0, column).getClass();
    }

 }
