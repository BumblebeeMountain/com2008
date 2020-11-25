package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import models.User;
import models.Student;
import controllers.UserController;
import controllers.StudentController;
import exceptions.*;

public class UserDashboard extends JPanel {

    private static final long serialVersionUID = -536642684504914299L;
    private Main rootFrame;

    public UserDashboard(Main rootFrame) {
        this.rootFrame = rootFrame;
        initComponents();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        this.rootFrame.logout();
    }

    private void goBackButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAdminDashboard();
    }

    private void addUserButtonActionPerformed(ActionEvent e) {
        this.rootFrame.moveToAddUser();
    }

    private void initComponents() {
        header = new JPanel();
        logoutButton = new JButton();
        goBackButton = new JButton();
        body = new JScrollPane();
        // userTable = new JTable();
        panel1 = new JPanel();
        addUserButton = new JButton();

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
            userTable = new JTable(new JTableButtonModelUser(this.rootFrame));
            tableRenderer = userTable.getDefaultRenderer(JButton.class);
            userTable.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
            userTable.addMouseListener(new JTableButtonMouseListener(userTable));

            body.setViewportView(userTable);
        }
        add(body, BorderLayout.CENTER);

        // ======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            // ---- addUserButton ----
            addUserButton.setText("Add User");
            addUserButton.addActionListener(e -> addUserButtonActionPerformed(e));
            panel1.add(addUserButton);
        }
        add(panel1, BorderLayout.EAST);
    }

    private JPanel header;
    private JButton logoutButton;
    private JButton goBackButton;
    private JScrollPane body;
    private JTable userTable;
    private JPanel panel1;
    private JButton addUserButton;

}
/**
 * Class used for putting buttons in a table
 */
class JTableButtonModelUser extends AbstractTableModel {

    private static final long serialVersionUID = 301047396186264466L;

    private Main rootFrame;
    // private Integer registrationNumber;
    private User[] users;

    public JTableButtonModelUser (Main rootFrame) {

        this.rootFrame = rootFrame;
        // this.registrationNumber = registrationNumber;

        try {   

            // Get the registrations
            // this.registrations = RegistrationController.getStudentRegistrations(this.registrationNumber);
            this.users = UserController.getAllUsers();

            // Set the table
            String[] columnNames = {"Name", "Email", "Account Type", "Delete"};
            Object[][] tableData = new Object[users.length][columnNames.length];
            for (int i = 0; i < tableData.length; i++) {
                User u = this.users[i];
                tableData[i][0] = u.getForename().toString() + " " + u.getSurname().toString();
                tableData[i][1] = u.getEmail().toString();
                tableData[i][2] = u.getAccountType().toString();
                
                JButton viewButton = new JButton("Delete");
                viewButton.addActionListener(e -> {
                    // this.rootFrame.showMessage("You are trying to delete user: " + u.getEmail().toString());
                    if (StudentController.isStudent(u)) {
                        try {
                            Student s = StudentController.getStudent(u.getEmail());
                            System.out.println("That user is a student");
                            System.out.println("Deleting student: " + s.getEmail());
                            StudentController.removeStudent(s.getRegistrationNumber());
                        } catch (GeneralProcessingException ex) {
                            ex.printStackTrace();
                        } catch (NoRecordException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            System.out.println("User is not student");
                            System.out.println("Deleting user: " + u.getEmail());
                            UserController.removeUser(u.getEmail());
                        } catch (GeneralProcessingException ex) {
                            ex.printStackTrace();
                        } catch (NoRecordException ex) {
                            ex.printStackTrace();
                        }
                    }
                    this.rootFrame.repaint();
                });
                tableData[i][3] = viewButton;
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
