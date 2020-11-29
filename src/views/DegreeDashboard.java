package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import models.Degree;
import controllers.DegreeController;
import exceptions.*;

public class DegreeDashboard extends JPanel {

    private static final long serialVersionUID = 4177033550031280596L;
    private Main rootFrame;

    public DegreeDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void addDegreeButtonActionPerformed(ActionEvent e) {
        System.out.println("Add degree button clicked");
        this.rootFrame.moveToAddDegree();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAdminDashboard();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void deleteDegreeButtonActionPerformed(ActionEvent e) {
        System.out.println("Delete degree button clicked");
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        // degreeTable = new JTable();
        panel1 = new JPanel();
        addDegreeButton = new JButton();

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
            degreeTable = new JTable(new JTableButtonModelDegree(this.rootFrame));
            tableRenderer = degreeTable.getDefaultRenderer(JButton.class);
            degreeTable.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
            degreeTable.addMouseListener(new JTableButtonMouseListener(degreeTable)); // <--- You were missing this line
            body.setViewportView(degreeTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addDegreeButton ----
            addDegreeButton.setText("Add Degree");
            addDegreeButton.addActionListener(e -> addDegreeButtonActionPerformed(e));
            panel1.add(addDegreeButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable degreeTable;
    private JPanel panel1;
    private JButton addDegreeButton;
}

/**
 * Class used for putting buttons in a table
 */
class JTableButtonModelDegree extends AbstractTableModel {

    private static final long serialVersionUID = 301047398326264466L;

    private Main rootFrame;
    // private Integer registrationNumber;
    private Degree[] degrees;

    public JTableButtonModelDegree (Main rootFrame) {

        this.rootFrame = rootFrame;

        try {   

            // Get the degrees
            this.degrees = DegreeController.getAllDegrees(true);

            // Set the table
            String[] columnNames = {"Degree Name", "Degree Code", "Delete"};
            Object[][] tableData = new Object[degrees.length][columnNames.length];
            for (int i = 0; i < tableData.length; i++) {
                Degree d = this.degrees[i];
                tableData[i][0] = d.getName().toString();
                tableData[i][1] = d.getCode().toString();
                
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e -> {
                    
                    System.out.println("Trying to delete: " + d.getCode());

                    this.deleteButtonClicked(d);
                    this.rootFrame.moveToDegreeDashboard();
                });
                tableData[i][2] = deleteButton;
            }

            // Set into the abstract model
            this.rows = tableData;
            this.columns = columnNames;

        } catch (Exception e) {
            e.printStackTrace();
            this.rootFrame.logout(); // Error
        }
        
    }

    private void deleteButtonClicked(Degree d) {
        try {
            DegreeController.removeDegree(d.getCode());
        } catch (GeneralProcessingException ex ) {
            ex.printStackTrace();
            this.rootFrame.showError("Error deleting that degree");
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
