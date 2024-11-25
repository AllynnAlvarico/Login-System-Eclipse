import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
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
		else if(a.getSource() == addRev)review();
		else if(a.getSource() == btnSubmit)getData();
		else if(a.getSource() == viewRevMenu)viewFrame();
		else if(a.getSource() == placeOrder)oderItems();
		
		//prediction Implementation
//		else if (a.getActionCommand().equalsIgnoreCase("Reset Password")) resetPassword();
	}

	@Override
	public void propertyChange(PropertyChangeEvent p) {
		// TODO Auto-generated method stub
		String propertyName = p.getPropertyName();
        if ("Individual".equals(propertyName)) System.out.println("Hello My friend");
	}
	
	private ArrayList<Review> reviewList = new ArrayList();
	Review reviewData;
	
	JFrame reviewFrame;
	JFrame viewFrame;
	JFrame orderFrame;
	
	JTextArea txtAreaView;
	
	JFormattedTextField dob;
	JTextField txtComment;
	JSlider sldRating;
	
	
	private AdminSystem admin;
	private boolean isValid;
	private boolean isExists;
	
	String strUser;
    String strPassword;
	
    private JMenuBar menu;
	
	private JMenu user;
	private JMenu review;
	private JMenu order;
	
	private JMenuItem signUp;
	private JMenuItem login;
	private JMenuItem logout;
	
	private JMenuItem addRev;
	private JMenuItem viewRevMenu;
	
	private JMenuItem placeOrder;
	
	private JButton btnSubmit;
	
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
	
	
	
	
	JComboBox cmbProducts;
	JTextField txtQuantity;
	JButton btnAdd;
	JTextArea orderList;
	
	
	
	
	
	
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
		review = new JMenu("Review");
		order = new JMenu("Order");
		
		review.setEnabled(true);
		order.setEnabled(true);
		
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
		
		
		addRev = new JMenuItem("Add Review");
		viewRevMenu = new JMenuItem("View Review");
		
		review.add(addRev);
		review.add(viewRevMenu);
		
		placeOrder = new JMenuItem("Place Order");
		
		order.add(placeOrder);
		
		
		menu.add(user);
		menu.add(review);
		menu.add(order);
		
		this.setJMenuBar(menu);
		
		signUp.addActionListener(this);
		login.addActionListener(this);
		logout.addActionListener(this);
		
		addRev.addActionListener(this);
		
		viewRevMenu.addActionListener(this);
		placeOrder.addActionListener(this);
		
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
	    	review.setEnabled(true);
	    	order.setEnabled(true);
	    	panelWelcome(temp.getUserName());
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
	 public void panelWelcome(String userName){
	        panel = new JPanel();
	        JLabel lblWelcome = new JLabel();
	        
//	        JLabel lblWelcome = new JLabel("Welcome " + userName, JLabel.CENTER);
//	        
	        String strWelcome = "Welcome " + userName;
	        
	        lblWelcome.setText(strWelcome);
	        
//	        cmbLoginWindow = new JComboBox<String>(userType);
			 
//	        panel.add(cmbLoginWindow);
	        cmbLoginWindow.addItemListener(this);
	        
	        

	        panel.add(lblWelcome);

	        this.add(panel);
	        this.pack();
	    }
	 public void logout(){
		 
		    strUser = null;
		    strPassword = null;
		    
		    review.setEnabled(false);
		    order.setEnabled(false);

		    if (panel != null) {
		        this.remove(panel);
		        panel = null;
		    }

		    JOptionPane.showMessageDialog(this, "You have been logged out successfully!");

		    this.revalidate();
		    this.repaint();
	 }
	 private void review(){
		 panel = new JPanel();
		 reviewFrame = new JFrame("Review");
		 
		 
		 
		 JLabel review = new JLabel("Review Date:");
		 JLabel comment = new JLabel("Comment:");
		 JLabel rating = new JLabel("Rating:");
		 
		 dob = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		 
		 txtComment = new JTextField();
		 btnSubmit = new JButton("Submit");
		 
		 txtComment.setColumns(10);
		 dob.setColumns(10);
		 
		 sldRating = new JSlider(JSlider.HORIZONTAL, 0, 4, 3);
		 
		 Hashtable<Integer, JComponent> labels =
				 sldRating.createStandardLabels(1);
		 sldRating.setLabelTable(labels);
		 sldRating.setPaintLabels(true);
		 
		 
		 reviewFrame.setPreferredSize(new Dimension (300, 300));
		 reviewFrame.setLocation(450, 350);
		 
		 panel.add(review);
		 panel.add(dob);
		 
		 panel.add(comment);
		 panel.add(txtComment);
		 
		 panel.add(rating);
		 panel.add(sldRating);
		 
		 panel.add(btnSubmit);
		 
		 btnSubmit.addActionListener(this);
		 
		 
		 panel.setLayout(new FlowLayout());
//		 reviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 
		 reviewFrame.getContentPane().add(panel);
		 
		 reviewFrame.pack();
		 reviewFrame.setVisible(true);
		 
	 }
	 public void viewFrame(){
		 panel = new JPanel();
		 viewFrame = new JFrame("View Review");
		 
		 txtAreaView = new JTextArea();
		 txtAreaView.setPreferredSize(new Dimension(500, 200));
		 
		 panel.add(txtAreaView);
		 
		 panel.setLayout(new FlowLayout());
		 
		 txtAreaView.setText(reviewData.toString());
		 
		 viewFrame.add(panel);
		 
		 viewFrame.setPreferredSize(new Dimension (300, 300));
		 viewFrame.setLocation(450, 350);
		 
		 viewFrame.getContentPane().add(panel);
		 viewFrame.pack();
		 viewFrame.setVisible(true);
	 }
	 public void oderItems(){
		 panel = new JPanel();
		 orderFrame = new JFrame("Place Order");
		 
		 panel.setLayout(new FlowLayout());
		 
		 cmbProducts = new JComboBox();
		 txtQuantity = new JTextField();
		 btnAdd = new JButton("Add");
		 orderList = new JTextArea();
		 
		 panel.add(cmbProducts);
		 panel.add(txtQuantity);
		 panel.add(btnAdd);
		 panel.add(orderList);
		 
		 orderFrame.add(panel);
		 
		 orderFrame.setPreferredSize(new Dimension (300, 300));
		 orderFrame.setLocation(450, 350);
//		 orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 orderFrame.getContentPane().add(panel);
		 orderFrame.pack();
		 orderFrame.setVisible(true);
		 
	 }
	 public void getData(){
		 
		 reviewData = new Review(dob.getText(), txtComment.getText(), sldRating.getValue());
		 reviewList.add(reviewData);
		 panel = null;
		 reviewFrame.dispose();
		 
		 
		 System.out.print(reviewData.toString());
	 }
	 
	 
}
