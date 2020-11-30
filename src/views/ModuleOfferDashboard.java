package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import controllers.DegreeController;
import models.DegreeModule;

public class ModuleOfferDashboard extends JPanel {

    private static final long serialVersionUID = -8156607164022916548L;
    private Main rootFrame;

    public ModuleOfferDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
        this.moduleOfferTable.getTableHeader().setReorderingAllowed(false);
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void addModuleOfferButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAddModuleOffer();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAdminDashboard();
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        moduleOfferTable = new JTable();
        panel1 = new JPanel();
        addModuleOfferButton = new JButton();

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
            moduleOfferTable = new JTable(new JTableButtonModeDegreeModule(this.rootFrame));
            tableRenderer = moduleOfferTable.getDefaultRenderer(JButton.class);
            moduleOfferTable.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
            moduleOfferTable.addMouseListener(new JTableButtonMouseListener(moduleOfferTable));

            body.setViewportView(moduleOfferTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addModuleOfferButton ----
            addModuleOfferButton.setText("Add Module Offer");
            addModuleOfferButton.addActionListener(e -> addModuleOfferButtonActionPerformed(e));
            panel1.add(addModuleOfferButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable moduleOfferTable;
    private JPanel panel1;
    private JButton addModuleOfferButton;
}

/**
 * Class used for putting buttons in a table
 */
class JTableButtonModeDegreeModule extends AbstractTableModel {

    private static final long serialVersionUID = 301047396186264466L;

    private Main rootFrame;
    private DegreeModule[] degreeModules;

    public JTableButtonModeDegreeModule (Main rootFrame) {
        
        this.rootFrame = rootFrame;

        try {   

            // Get offered modules 
            this.degreeModules = DegreeController.getAllDegreeModules();

            // Set the table
            String[] columnNames = {"Module Code" ,"Name", "Degree Offered On", "Core", "Level", "Delete"};
            Object[][] tableData = new Object[degreeModules.length][columnNames.length];

            for (int i = 0; i < tableData.length; i++) {
                DegreeModule m = this.degreeModules[i];
                tableData[i][0] = m.getCode().toString();
                tableData[i][1] = m.getName().toString();
                tableData[i][2] = m.getDegreeCode().toString();
                tableData[i][3] = m.getIsCore();
                tableData[i][4] = m.getLevel();

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e -> {
                    try {
                        DegreeController.removeDegreeModule(m.getDegreeCode(), m.getCode());
                        this.rootFrame.showMessage("Module offer removed.");
                        this.rootFrame.moveToModuleOfferDashboard();
                    } catch (Exception err) {
                        err.printStackTrace();
                        rootFrame.showError("There was an error, please try again.");
                    }
                });

                tableData[i][5] = deleteButton;

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
