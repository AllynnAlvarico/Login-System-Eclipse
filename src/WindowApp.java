import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class WindowApp extends JFrame implements ActionListener, ItemListener, PropertyChangeListener{

	@Override
	public void itemStateChanged(ItemEvent i) {
		// TODO Auto-generated method stub
		if (i.getSource() == cmbUserType && i.getStateChange() == ItemEvent.SELECTED) {
	          Object item = i.getItem();
	          System.out.println("Hello Bitch");
	          System.out.println("Selected: " + item.toString());
	       }
		else if (i.getSource() == cmbLoginWindow && i.getStateChange() == ItemEvent.SELECTED) {
	           Object item = i.getItem();
	           System.out.println("Login Window selection: " + item.toString());
	       }
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if(a.getSource() == quit) System.exit(0);
		else if(a.getActionCommand().equalsIgnoreCase("Log out")) logout();
		else if(a.getActionCommand().equalsIgnoreCase("Log in")) login();
		else if(a.getActionCommand().equalsIgnoreCase("Sign up")) registration();
		//prediction Implementation
//		else if (a.getActionCommand().equalsIgnoreCase("Reset Password")) resetPassword();
	}

	@Override
	public void propertyChange(PropertyChangeEvent p) {
		// TODO Auto-generated method stub
		String propertyName = p.getPropertyName();
        if ("Individual".equals(propertyName)) System.out.println("Hello My friend");
	}
	
	private AdminSystem admin;
	private boolean isValid;
	private boolean isExists;
	
	String strUser;
    String strPassword;
	
    private JMenuBar menu;
	
	private JMenu user;
	private JMenu view;
	
	private JMenuItem signUp;
	private JMenuItem login;
	private JMenuItem logout;
	
	private JPanel panel;
	private JTabbedPane tabbedPane;
	private JButton showTabsButton;
	
	String message;
	String[] userType = {"Individual", "Corporate", "Nonprofit Organisation"};
	int result;
	
	JTextField userField;
	JPasswordField passField;
	JComboBox cmbUserType;
	JComboBox cmbLoginWindow;
	
	JMenuItem quit;
	
	public WindowApp(){
		
		admin = new AdminSystem();
		
		menubar();
		
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void menubar(){
		menu = new JMenuBar();
		
		user = new JMenu("User");
		view = new JMenu("View");
		
		view.setEnabled(false);
		
		signUp = new JMenuItem("Sign Up");
		signUp.setMnemonic(KeyEvent.VK_U);
		
		login = new JMenuItem("Log In");
		login.setMnemonic(KeyEvent.VK_L);
		
		logout = new JMenuItem("Log Out");
		logout.setMnemonic(KeyEvent.VK_O);
		
		quit = new JMenuItem("Quit");
		quit.setMnemonic(KeyEvent.VK_Q);
		
		user.add(signUp);
		user.add(login);
		user.add(logout);
		
		user.addSeparator();
		
		user.add(quit);
		
		menu.add(user);
		menu.add(view);
		
		this.setJMenuBar(menu);
		
		signUp.addActionListener(this);
		login.addActionListener(this);
		logout.addActionListener(this);
		
		quit.addActionListener(this);
		
		this.setLocation(450, 350);
		this.setPreferredSize(new Dimension(300, 300));
		
		
		
		//This is the Prediction 
//		JMenuItem resetPassword = new JMenuItem("Reset Password");
//		resetPassword.addActionListener(this);
//		user.add(resetPassword);

		
	}
	
	public void login(){
		message = "Please enter your user name and password.";

	    userField = new JTextField();
	    passField = new JPasswordField();
	    
	    result = JOptionPane.showOptionDialog(this, new Object[] {message, userField, passField}, 
	    "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    
	    strUser = userField.getText();
	    strPassword = new String(passField.getPassword());
	    
	    User temp = null;
	    
	    isValid = (admin.validateUser(strUser, strPassword)) ? true : false;
	    
	    if(isValid) {
	    	temp = (User)admin.getUser(strUser, strPassword);
	    	System.out.println("User is Validated");
	    	System.out.println("User: " + temp.getUserName() + "Password: " + temp.getUserPassword());
	    	view.setEnabled(true);
	    	panel(temp.getUserName());
	    }else{
	    	JOptionPane.showMessageDialog(this, "Invalid user name or password !");
	    }
	}
	
	public void registration(){
		
		message = "User registration:";
		
		userField = new JTextField();
	    passField = new JPasswordField();
	    
	    cmbUserType = new JComboBox<String>(userType);
	    cmbUserType.addItemListener(this);
	    
	    result = JOptionPane.showOptionDialog(this, new Object[] {message, userField, passField, cmbUserType},
	    "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

	    if (result == JOptionPane.OK_OPTION) {
	    	//Sequence of taking the data Strings were in the wrong sequence.
	        strUser = userField.getText();
	        strPassword = new String(passField.getPassword());
	        if (admin.isUsernameTaken(strUser)) {
	            JOptionPane.showMessageDialog(this, "User Exists!");
	        } else {
	            admin.addUser(strUser, strPassword, (String) cmbUserType.getSelectedItem());
	            System.out.println(strUser + " " + strPassword + " " + cmbUserType.getSelectedItem());
	            JOptionPane.showMessageDialog(this, "User is Stored!");
	        }
	    }
	}
	 public void panel(String userName){
	        panel = new JPanel();
	        panel.setLayout(new BorderLayout());
	        
	        
	        JLabel lblWelcome = new JLabel("Welcome " + userName, JLabel.CENTER);
	        
//	        String strWelcome = "Welcome " + userName;
	        
//	        lblWelcome.setText(strWelcome);
	        
//	        showTabsButton = new JButton("Show Tabs");
//	        showTabsButton.addActionListener(e -> showTabs());
	        
	        cmbLoginWindow = new JComboBox<String>(userType);
			 
	        panel.add(cmbLoginWindow);
	        cmbLoginWindow.addItemListener(this);
	        
	        

	        panel.add(lblWelcome);

	        this.add(panel);
	        this.pack();
	    }
	 public void logout(){
		 
		    strUser = null;
		    strPassword = null;
		    
		    view.setEnabled(false);

		    if (panel != null) {
		        this.remove(panel);
		        panel = null;
		    }

		    JOptionPane.showMessageDialog(this, "You have been logged out successfully!");

		    this.revalidate();
		    this.repaint();
	 }
	 
//	 private void showTabs() {
//	        if (tabbedPane == null) {
//	            tabbedPane = new JTabbedPane();
//
//	            tabbedPane.addTab("Tab 1", new JLabel("Content of Tab 1"));
//	            tabbedPane.addTab("Tab 2", new JLabel("Content of Tab 2"));
//	            tabbedPane.addTab("Tab 3", new JLabel("Content of Tab 3"));
//
//	            panel.add(tabbedPane, BorderLayout.CENTER);
//
//	            panel.revalidate();
//	            panel.repaint();
//	        }
//	    }
	 
	 
}
