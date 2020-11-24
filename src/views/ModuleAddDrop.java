package views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import controllers.DegreeController;
import controllers.ModuleController;
import controllers.RegistrationController;
import models.Module;
import models.Registration;
import models.SelectedModule;

public class ModuleAddDrop extends JPanel {

    private static final long serialVersionUID = -3970499508593858878L;
    private Main rootFrame;
    private Integer studentRegistrationNumber;
    private ModuleAddDropTable mainTable;
    private SelectedModule[] initialSelectedModules;

    public ModuleAddDrop(Main rootFrame, Integer studentRegistrationNumber) {

        this.rootFrame = rootFrame;
        this.studentRegistrationNumber = studentRegistrationNumber;

        initComponents();

        try {

            // Get the most recent registration
            Registration r = RegistrationController.getMostRecentRegistration(this.studentRegistrationNumber);
            initialSelectedModules = r.getSelectedModules(); // For use later on!

            // Get the optional modules for the current level and set in combo box
            Module[] optionalModules = DegreeController.getOptionalModules(r.getDegreeCode(), r.getLevel());
            for (Module m : optionalModules)
                optionalModuleList.addItem(m.getCode());

            // Table gets set in the initComponents and class section

            // Set the number of credits
            this.numberOfCredits.setText(calculateCredits(this.mainTable).toString());

        } catch (Exception e) {
            this.rootFrame.moveToRegistrarDashboard(); // Errored
        }
    }

    /**
     * Calculate the number of credits from a table model
     */
    protected Integer calculateCredits(ModuleAddDropTable t) {
        Integer credits = 0;
        for (Object[] row : t.rows) {
            credits += Integer.valueOf((Integer) row[2]);
        }
        return credits;
    }

    /**
     * Check whether the table contains a module or not
     */
    private Boolean tableContains(ModuleAddDropTable t, String code) {
        for (Object[] row : t.rows) {
            if (((String) row[0]).equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Logout button pressed
     */
    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    /**
     * Go back button pressed
     */
    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToRegistrarDashboard();
    }

    /**
     * We try to add a module to the list
     */
    private void addButtonActionPerformed(ActionEvent e) {

        String moduleCode = (String) optionalModuleList.getSelectedItem();

        if (tableContains(this.mainTable, moduleCode)) {
            this.rootFrame.showError("This module has already been selected, please try a different one.");
        } else {
            try {
                Module m = ModuleController.getModule(moduleCode, true);
                this.mainTable.insertRow(m);

                // Set the number of credits
                this.numberOfCredits.setText(calculateCredits(this.mainTable).toString());

            } catch (Exception err) {
                this.rootFrame.showError("There was an error, please try again.");
            }

        }

    }

    /**
     * Submit the table to be saved
     */
    private void submitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        panel1 = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        scrollPane1 = new JScrollPane();
        moduleTable = new JTable();
        panel2 = new JPanel();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        optionalModuleList = new JComboBox<>();
        addButton = new JButton();
        label2 = new JLabel();
        numberOfCredits = new JLabel();
        submitButton = new JButton();

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

            // I've added the following 5 lines :)
            TableCellRenderer tableRenderer;
            mainTable = new ModuleAddDropTable(this.rootFrame, this.studentRegistrationNumber, this);
            moduleTable = new JTable(mainTable);
            tableRenderer = moduleTable.getDefaultRenderer(JButton.class);
            moduleTable.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
            moduleTable.addMouseListener(new JTableButtonMouseListener(moduleTable));

            scrollPane1.setViewportView(moduleTable);

        }
        add(scrollPane1, BorderLayout.CENTER);

        // ======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            ((GridBagLayout) panel2.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                    1.0E-4 };

            // ---- label1 ----
            label1.setText("Select Optional Module:   ");
            label1.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ======== scrollPane2 ========
            {
                scrollPane2.setViewportView(optionalModuleList);
            }
            panel2.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- addButton ----
            addButton.setText("Add");
            addButton.addActionListener(e -> addButtonActionPerformed(e));
            panel2.add(addButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- label2 ----
            label2.setText("Total credits:");
            label2.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel2.add(label2, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- numberOfCredits ----
            numberOfCredits.setText("numberOfCredits");
            numberOfCredits.setFont(new Font("Tahoma", Font.PLAIN, 11));
            panel2.add(numberOfCredits, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

            // ---- submitButton ----
            submitButton.setText("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));
            panel2.add(submitButton, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
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
    private JScrollPane scrollPane2;
    private JComboBox<String> optionalModuleList;
    private JButton addButton;
    private JLabel label2;
    protected JLabel numberOfCredits;
    private JButton submitButton;
}

/**
 * Class used for putting buttons in a table
 */
class ModuleAddDropTable extends AbstractTableModel {

    private static final long serialVersionUID = 301047396186264466L;

    private Main rootFrame;
    private Integer registrationNumber;
    private Registration currentRegistration;
    private Module[] coreModules;
    private ModuleAddDrop rootPanel;

    public ModuleAddDropTable(Main rootFrame, Integer registrationNumber, ModuleAddDrop rootPanel) {

        this.rootFrame = rootFrame;
        this.registrationNumber = registrationNumber;
        this.rootPanel = rootPanel;

        try {

            // Get the most recent registration
            this.currentRegistration = RegistrationController.getMostRecentRegistration(this.registrationNumber);

            // Get all the core modules - add them
            Module[] coreModules = DegreeController.getCoreModules(this.currentRegistration.getDegreeCode(),
                    this.currentRegistration.getLevel());
            this.coreModules = coreModules;

            // Get all the current selected modules
            SelectedModule[] selectedModules = this.currentRegistration.getSelectedModules();

            // Set the table
            String[] columnNames = { "Module Code", "Module Name", "Credits", "Teaching Period", "Core", "Drop" };
            Object[][] tableData;

            if (selectedModules.length == 0) { // Nothing been set yet

                System.out.println("Setting a core only table");

                tableData = new Object[coreModules.length][columnNames.length];
                for (int i = 0; i < tableData.length; i++) {
                    Module m = coreModules[i];
                    tableData[i][0] = m.getCode();
                    tableData[i][1] = m.getName();
                    tableData[i][2] = m.getCredits();
                    tableData[i][3] = m.getTeachingPeriod();
                    tableData[i][4] = true;
                    JButton viewButton = new JButton("n/a");
                    viewButton.setEnabled(false);
                    tableData[i][5] = viewButton;
                }

            } else { // Core already been set in

                System.out.println("Setting a selected table");

                tableData = new Object[selectedModules.length][columnNames.length];
                for (int i = 0; i < tableData.length; i++) {
                    Module m = selectedModules[i];
                    tableData[i][0] = m.getCode();
                    tableData[i][1] = m.getName();
                    tableData[i][2] = m.getCredits();
                    tableData[i][3] = m.getTeachingPeriod();
                    tableData[i][4] = contains(coreModules, m.getCode());

                    if (!contains(coreModules, m.getCode())) { // If optional
                        JButton viewButton = new JButton("Drop");
                        viewButton.addActionListener(e -> {
                            this.removeRow(m.getCode());
                        });
                        tableData[i][5] = viewButton;
                    }

                }

            }

            // Set into the abstract model
            this.rows = tableData;
            this.columns = columnNames;

        } catch (Exception e) {
            this.rootFrame.logout(); // Error
        }

    }

    /**
     * Remove a row from the table
     */
    public void removeRow(String code) {

        if (rowsContain(this.rows, code)) {

            // Add a row and repaint
            Object[][] currentRows = this.rows.clone();
            Object[][] newRows = new Object[currentRows.length - 1][currentRows[0].length];

            int counter = 0;
            for (Object[] r : currentRows) {
                if (!((String) r[0]).equals(code)) {
                    newRows[counter] = r;
                    counter++;
                }
            }

            this.rows = newRows;
            this.fireTableDataChanged();
            this.rootPanel.numberOfCredits.setText(this.rootPanel.calculateCredits(this).toString());

        }

    }

    /**
     * Insert a row into the table
     */
    public void insertRow(Module m) {
        // Add a row and repaint
        Object[][] currentRows = this.rows.clone();
        Object[][] newRows = new Object[currentRows.length + 1][currentRows[0].length];
        System.arraycopy(currentRows, 0, newRows, 0, currentRows.length);
        newRows[currentRows.length][0] = m.getCode();
        newRows[currentRows.length][1] = m.getName();
        newRows[currentRows.length][2] = m.getCredits();
        newRows[currentRows.length][3] = m.getTeachingPeriod();
        newRows[currentRows.length][4] = contains(coreModules, m.getCode());
        if (!contains(coreModules, m.getCode())) { // If optional
            JButton viewButton = new JButton("Drop");
            viewButton.addActionListener(e -> {
                this.removeRow(m.getCode());
            });
            newRows[currentRows.length][5] = viewButton;
        }

        this.rows = newRows;
        this.fireTableDataChanged();
    }

    /**
     * Check if any row contains the module code
     * 
     * @param rx
     * @param code
     * @return
     */
    private Boolean rowsContain(Object[][] rx, String code) {
        for (Object[] row : rx) {
            if (((String) row[0]).equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if an array of modules has a module code in
     * 
     * @param mx
     * @param code
     * @return
     */
    private Boolean contains(Module[] mx, String code) {
        for (Module m : mx) {
            if (m.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public Object[][] rows;
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