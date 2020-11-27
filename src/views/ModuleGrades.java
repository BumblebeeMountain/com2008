package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import controllers.ModuleController;
import controllers.RegistrationController;
import controllers.StudentController;
import models.Registration;
import models.SelectedModule;
import models.Constants.PassLevel;

public class ModuleGrades extends JPanel {

    private static final long serialVersionUID = -2358750203321858050L;
    private Main rootFrame;
    private Integer studentRegistrationNumber;
    private ModuleGradesTable moduleGradesTable;
    public Character currentPeriod;
    public SelectedModule[] initialSelectedModules;

    public ModuleGrades(Main rootFrame, Integer studentRegistrationNumber) {
        this.rootFrame = rootFrame;
        this.studentRegistrationNumber = studentRegistrationNumber;
        initComponents();
        moduleTable.getTableHeader().setReorderingAllowed(false);
        this.registrationNumber.setText(studentRegistrationNumber.toString());
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToTeacherDashboard();
    }

    private void saveButtonActionPerformed(ActionEvent e) {
        
        if (this.saveGrades()) {
            this.rootFrame.showMessage("Grades saved.");
        }
        
    }

    private Boolean saveGrades () {

        // Validate the data in the rows
        for (Object[] row: this.moduleGradesTable.rows) {
            Float grade = (Float)row[3];
            Float resitGrade = (Float)row[4];
            if (grade < 0 || grade >= 100 || resitGrade < 0 || resitGrade > 100) {
                this.rootFrame.showError("You have entered invalid grades, please try again.");
                return false;
            }
        }

        // Now cross compare and only update those who have changed
        try {
            for (int i = 0; i < this.initialSelectedModules.length; i++) {
                SelectedModule m = this.initialSelectedModules[i];
                Object[] row = this.moduleGradesTable.rows[i];

                if (m.getFirstAttemptResult() != (Float)row[3]) {
                    RegistrationController.updateFirstGrade(this.studentRegistrationNumber, this.currentPeriod, m.getCode(), (Float)row[3]);
                }
                if (m.getSecondAttemptResult() != (Float)row[4]) {
                    RegistrationController.updateResitGrade(this.studentRegistrationNumber, this.currentPeriod, m.getCode(), (Float)row[4]);
                }
            }

            return true;

        } catch (Exception err) {
            this.rootFrame.showError("There was an error, please try again. Please make sure that the student has enrolled on all relevant modules.");
        }

        return false;

    }

    private void registerNextButtonActionPerformed(ActionEvent e) {
        
        if (this.saveGrades()) { // If saving grades was successful
            
            try {

                // Get full year grade
                Float grade = RegistrationController.calculateOverallGrade(this.studentRegistrationNumber, this.currentPeriod);

                // Get pass level
                PassLevel pl = RegistrationController.calculatePassLevel(this.studentRegistrationNumber, this.currentPeriod);

                // Get theoretical next level
                Character nextLevel = RegistrationController.getNextProgressingLevel(this.studentRegistrationNumber);

                // Is this currently their resit year
                Boolean currentlyOnResit = false;
                Registration currentReg = RegistrationController.getStudentRegistration(this.studentRegistrationNumber, this.currentPeriod);
                if ((int)this.currentPeriod > (int)'A') {
                    Registration prevReg = RegistrationController.getStudentRegistration(this.studentRegistrationNumber, (char)(this.currentPeriod - 1));
                    if (currentReg.getLevel().equals(prevReg.getLevel())) {
                        currentlyOnResit = true;
                    }
                }

                // If on fourth year we need to not allow a resit
                if (currentReg.getLevel().equals('4')) {
                    currentlyOnResit = true; // If pass - fine, if not they will graduate - let other function calculate classification
                }

                // If they passed move them onto next year / graduate
                if (pl == PassLevel.PASS || pl == PassLevel.CONCEDED_PASS) {

                    // if next level = G then we need to graduate them
                    if (nextLevel.equals('G')) {

                        StudentController.graduateStudent(this.studentRegistrationNumber); // Graduate the student
                        this.rootFrame.showMessage(
                            "Student achieved: " + pl + " at " + grade + System.lineSeparator() +
                            "Progressing to: Graduation" 
                        );
                        this.rootFrame.moveToTeacherDashboard();
                        return;

                    } else {

                        RegistrationController.generateNextRegistration(this.studentRegistrationNumber, nextLevel); // Gen next registration
                        this.rootFrame.showMessage(
                            "Student achieved: " + pl + " at " + grade + System.lineSeparator() +
                            "Progressing to level: " + nextLevel
                        );
                        this.rootFrame.moveToTeacherDashboard();
                        return;

                    }
                    
                }

                // If they failed but they haven't resit this year yet
                if (pl == PassLevel.FAIL && !currentlyOnResit) {

                    RegistrationController.generateNextRegistration(this.studentRegistrationNumber, currentReg.getLevel()); // Gen same level
                    
                    // Copy all modules over
                    for (SelectedModule m: RegistrationController.getStudentRegistration(this.studentRegistrationNumber, this.currentPeriod).getSelectedModules()) {
                        RegistrationController.createSelectedModule(this.studentRegistrationNumber, (char)(this.currentPeriod + 1), m.getCode());
                        
                        Float firstGrade = m.getFirstAttemptResult();
                        Float secondGrade = m.getSecondAttemptResult();
                        if (secondGrade > 40) secondGrade = 40f; // flattening
                        
                        Float maxGrade = Math.max(firstGrade, secondGrade); // Get max grade
                        if (maxGrade >= (0.9 * 40)) { // If they passed this module then copy grade over
                            RegistrationController.updateFirstGrade(this.studentRegistrationNumber, (char)(this.currentPeriod + 1), m.getCode(), maxGrade);
                        }

                    }

                    this.rootFrame.showMessage(
                        "Student achieved: " + pl + " at " + grade + System.lineSeparator() +
                        "Resitting level: " + currentReg.getLevel()
                    );
                    this.rootFrame.moveToTeacherDashboard();
                    return;

                }

                // Fail - on resit - graduate with fail
                StudentController.graduateStudent(this.studentRegistrationNumber); // Graduate with fail
                this.rootFrame.showMessage(
                    "Student achieved: " + pl + " at " + grade + System.lineSeparator() +
                    "Progressing to: Graduation" 
                );
                this.rootFrame.moveToTeacherDashboard();


            } catch (Exception err) {
                this.rootFrame.showError("There was an error, please try again. Please make sure that the student has enrolled on all relevant modules. ");
            }

        }

    }

    private void initComponents() {
        panel1 = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        scrollPane1 = new JScrollPane();
        moduleTable = new JTable();
        panel2 = new JPanel();
        label1 = new JLabel();
        registrationNumber = new JLabel();
        saveButton = new JButton();
        registerNextButton = new JButton();

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

        // ======== scrollPane1 ========
        {
            this.moduleGradesTable = new ModuleGradesTable(this.rootFrame, this.studentRegistrationNumber, this);
            moduleTable = new JTable(this.moduleGradesTable);
            scrollPane1.setViewportView(moduleTable);
        }
        add(scrollPane1, BorderLayout.CENTER);

        // ======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0 };
            ((GridBagLayout) panel2.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

            // ---- label1 ----
            label1.setText("Registration Number:");
            label1.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- registrationNumber ----
            registrationNumber.setText("reg number");
            registrationNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel2.add(registrationNumber, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- saveButton ----
            saveButton.setText("Save Changes");
            saveButton.addActionListener(e -> saveButtonActionPerformed(e));
            panel2.add(saveButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- registerNextButton ----
            registerNextButton.setText("Register For Next Year");
            registerNextButton.addActionListener(e -> registerNextButtonActionPerformed(e));
            panel2.add(registerNextButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel2, BorderLayout.EAST);
    }

    private JPanel panel1;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane scrollPane1;
    private JTable moduleTable;
    private JPanel panel2;
    private JLabel label1;
    private JLabel registrationNumber;
    private JButton saveButton;
    private JButton registerNextButton;
}

/**
 * Class used for putting buttons in a table
 */
class ModuleGradesTable extends AbstractTableModel {

    private static final long serialVersionUID = 301047396186264466L;

    private Main rootFrame;
    private Integer registrationNumber;
    private ModuleGrades rootPanel;

    public ModuleGradesTable (Main rootFrame, Integer registrationNumber, ModuleGrades rootPanel) {

        this.rootFrame = rootFrame;
        this.registrationNumber = registrationNumber;
        this.rootPanel = rootPanel;

        try {   

            String[] columnNames = {"Module Code", "Module Name", "Credits", "Grade (%)", "Resit Grade (%)"};
            Registration r = RegistrationController.getMostRecentRegistration(this.registrationNumber);
            SelectedModule[] modules = r.getSelectedModules();
            this.rootPanel.initialSelectedModules = modules; // For use later on
            this.rootPanel.currentPeriod = r.getPeriod(); // For use later on
            Object[][] rowData = new Object[modules.length][columnNames.length];
            for (int i = 0; i < modules.length; i++) {
                SelectedModule m = modules[i];
                rowData[i][0] = m.getCode();
                rowData[i][1] = m.getName();
                rowData[i][2] = m.getCredits();
                rowData[i][3] = m.getFirstAttemptResult();
                rowData[i][4] = m.getSecondAttemptResult();
            }

            this.rows = rowData;
            this.columns = columnNames;

        } catch (Exception e) {
            this.rootFrame.moveToTeacherDashboard(); // Error
        }
        
    }

    public Object[][] rows; 
    private String[] columns; 

    private final Class[] columnClass = new Class[] {
        String.class, String.class, String.class, Float.class, Float.class
    };

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
    public void setValueAt(Object value, int row, int column) {
        this.rows[row][column] = value;
    }
    public boolean isCellEditable(int row, int column) {
       if (column == 3 || column == 4) {
           return true;
       } else {
           return false;
       }
    }
    public Class<?> getColumnClass(int column) {
       return getValueAt(0, column).getClass();
    }

 }
