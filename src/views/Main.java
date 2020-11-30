package views;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

    private static final long serialVersionUID = -8667920279388305018L;
    private Container contentPane;

    /**
     * Main constructor for the main frame
     */
    public Main() {

        // Standard info about the system
        setTitle("University Management System");
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        setSize(1200, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        add(new Login(this)); // Initial screen

        setVisible(true); // Show

    }

    // Error and info boxes ===================================================

    /**
     * Popup a box with an error message
     * 
     * @param message
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Popup a box with a message
     * 
     * @param message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Move to functions (Screen transitions) =================================

    public void logout() {
        contentPane.removeAll();
        contentPane.add(new Login(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void movetoLogin() {
        logout();
    }

    public void moveToStudentRecord(Integer registrationNumber) {
        contentPane.removeAll();
        contentPane.add(new StudentRecord(this, registrationNumber));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToRegistrationDetails(Integer registrationNumber, Character period) {
        contentPane.removeAll();
        contentPane.add(new RegistrationDetails(this, registrationNumber, period));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToTeacherDashboard() {
        contentPane.removeAll();
        contentPane.add(new TeacherDashboard(this));
        contentPane.revalidate();
        contentPane.repaint();

    }

    public void moveToModuleGradingScreen(Integer registrationNumber) {
        contentPane.removeAll();
        contentPane.add(new ModuleGrades(this, registrationNumber));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToRegistrarDashboard() {
        contentPane.removeAll();
        contentPane.add(new RegistrarDashboard(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToModuleAddDrop(Integer registrationNumber) {
        contentPane.removeAll();
        contentPane.add(new ModuleAddDrop(this, registrationNumber));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToStudentSignUp() {
        contentPane.removeAll();
        contentPane.add(new StudentSignUp(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToAdminDashboard() {
        contentPane.removeAll();
        contentPane.add(new AdminDashboard(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToUserDashboard() {
        contentPane.removeAll();
        contentPane.add(new UserDashboard(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToAddUser() {
        contentPane.removeAll();
        contentPane.add(new AddUser(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToDepartmentDashboard() {
        contentPane.removeAll();
        contentPane.add(new DepartmentDashboard(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToAddDepartment() {
        contentPane.removeAll();
        contentPane.add(new AddDepartment(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToModuleDashboard() {
        contentPane.removeAll();
        contentPane.add(new ModuleDashboard(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToAddModule() {
        contentPane.removeAll();
        contentPane.add(new AddModule(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToDegreeDashboard() {
        contentPane.removeAll();
        contentPane.add(new DegreeDashboard(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToAddDegree() {
        contentPane.removeAll();
        contentPane.add(new AddDegree(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToModuleOfferDashboard() {
        contentPane.removeAll();
        contentPane.add(new ModuleOfferDashboard(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void moveToAddModuleOffer() {
        contentPane.removeAll();
        contentPane.add(new AddModuleOffer(this));
        contentPane.revalidate();
        contentPane.repaint();
    }

}
