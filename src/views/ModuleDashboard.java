package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import controllers.ModuleController;
import exceptions.GeneralProcessingException;
import models.Module;

public class ModuleDashboard extends JPanel {

    private static final long serialVersionUID = -4113817667508088358L;
    private Main rootFrame;

    public ModuleDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void addModuleButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAddModule();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAdminDashboard();
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        moduleTable = new JTable();
        panel1 = new JPanel();
        addModuleButton = new JButton();

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
            moduleTable = new JTable(new JTableButtonModelModule(this.rootFrame));
            tableRenderer = moduleTable.getDefaultRenderer(JButton.class);
            moduleTable.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
            moduleTable.addMouseListener(new JTableButtonMouseListener(moduleTable));

            body.setViewportView(moduleTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addModuleButton ----
            addModuleButton.setText("Add Module");
            addModuleButton.addActionListener(e -> addModuleButtonActionPerformed(e));
            panel1.add(addModuleButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable moduleTable;
    private JPanel panel1;
    private JButton addModuleButton;
}

/**
 * Class used for putting buttons in a table
 */
class JTableButtonModelModule extends AbstractTableModel {

    private static final long serialVersionUID = 301047396186264466L;

    private Main rootFrame;
    private Module[] offeredModules;

    public JTableButtonModelModule (Main rootFrame) {

        this.rootFrame = rootFrame;

        try {   

            // Get offered modules 
            this.offeredModules = ModuleController.getAllModules(true);

            // Set the table
            String[] columnNames = {"Code", "Name", "Credits", "Teaching Period", "Delete"};
            Object[][] tableData = new Object[offeredModules.length][columnNames.length];

            for (int i = 0; i < tableData.length; i++) {
                Module m = this.offeredModules[i];
                tableData[i][0] = m.getCode().toString();
                tableData[i][1] = m.getName().toString();
                tableData[i][2] = m.getCredits().toString();
                tableData[i][3] = m.getTeachingPeriod().toString();

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e -> {
                    // this.rootFrame.showMessage("You are trying to delete the module: " + m.getName().toString());
                    try {
                        ModuleController.removeModule(m.getCode().toString());
                        System.out.println("module code: " + m.getCode().toString());
                    } catch (GeneralProcessingException err) {
                        this.rootFrame.showMessage("An error occured.");
                    }
                    
                });
                tableData[i][4] = deleteButton;

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
