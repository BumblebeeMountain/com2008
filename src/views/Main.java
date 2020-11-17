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

		setTitle("University Management System");
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1,1));
        setSize(800, 500);
        add(new Login(this)); // Initial screen
        setVisible(true); // Show
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }

    /**
     * Move to the teacher dashboard
     * @param email
     */
    public void moveToTeacherDashboard () {
        contentPane.invalidate();
        contentPane.removeAll();
        contentPane.add(new TeacherDashboard(this));
        contentPane.validate();
    }
    
    /**
     * Return the current screen to the login screen
     */
    public void logout () {
        contentPane.invalidate();
        contentPane.removeAll();
        contentPane.add(new Login(this));
        contentPane.validate();
    }

}
