package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import models.Department;
import controllers.DepartmentController;

public class DepartmentDashboard extends JPanel {

    private static final long serialVersionUID = 310582786290004699L;
    private Main rootFrame;

    public DepartmentDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
        this.deptTable.getTableHeader().setReorderingAllowed(false);
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAdminDashboard();
    }

    private void addDeptButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAddDepartment();
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        deptTable = new JTable();
        panel1 = new JPanel();
        addDeptButton = new JButton();

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
            
            TableCellRenderer tableRenderer;
            deptTable = new JTable(new JTableButtonModelDepartment(this.rootFrame));
            tableRenderer = deptTable.getDefaultRenderer(JButton.class);
            deptTable.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
            deptTable.addMouseListener(new JTableButtonMouseListener(deptTable));

            body.setViewportView(deptTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addDeptButton ----
            addDeptButton.setText("Add Department");
            addDeptButton.addActionListener(e -> addDeptButtonActionPerformed(e));
            panel1.add(addDeptButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable deptTable;
    private JPanel panel1;
    private JButton addDeptButton;
}
/**
 * Class used for putting buttons in a table
 */
class JTableButtonModelDepartment extends AbstractTableModel {

    private static final long serialVersionUID = 301047396186264466L;

    private Main rootFrame;
    private Department[] departments;

    public JTableButtonModelDepartment (Main rootFrame) {

        this.rootFrame = rootFrame;

        try {   

            // Get the departments
            this.departments = DepartmentController.getAllDepartments();

            // Set the table
            String[] columnNames = {"Department Name", "Department Code", "Delete"};
            Object[][] tableData = new Object[departments.length][columnNames.length];
            for (int i = 0; i < tableData.length; i++) {
                Department d = this.departments[i];
                tableData[i][0] = d.getName().toString();
                tableData[i][1] = d.getCode().toString();
                
                JButton viewButton = new JButton("Delete");
                viewButton.addActionListener(e -> {
                    try {
                        DepartmentController.removeDepartment(d.getCode());
                        this.rootFrame.showMessage(d.getCode() + " deleted.");
                        this.rootFrame.moveToDepartmentDashboard();
                    } catch (Exception ex) {
                        this.rootFrame.showError("There was an error, please try again.");
                    } 
                });
                tableData[i][2] = viewButton;
            }

            // Set into the abstract model
            this.rows = tableData;
            this.columns = columnNames;

        } catch (Exception e) {
            e.printStackTrace();
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