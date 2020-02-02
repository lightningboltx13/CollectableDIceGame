import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class Login_Prompt extends JFrame implements ActionListener
{

	//To-Do List
	/*
		>Create User File
		>Create User Collection File
		>Create New User
		>Succeed in Logging In
	*/
	
	
	//Files needed: List of Users, User's Collection.
	
	
	JPanel Main_Pane = new JPanel();
	
	JLabel Instructor = new JLabel("Enter Username and Password to login");
	
	JLabel User_Lbl = new JLabel("Username");
	JLabel Password_Lbl = new JLabel("Password");
	
	JTextField User_Entry = new JTextField();
	JTextField Password_Entry = new JTextField();
	
	
	
	JButton New_User = new JButton("New Player");
	JButton Login = new JButton("Login");
	
	public Login_Prompt() 
	{
		setUndecorated(true);
		setVisible(true);
	    setSize(200, 150);
	    setLocationRelativeTo(null);
	    
	    
	    addWindowListener(new WindowAdapter()
	    {
	      public void windowClosing(WindowEvent e)
	      {
	        System.exit(0);
	      }
	    });
	    
		add(Main_Pane);
			Main_Pane.setBorder(BorderFactory.createLineBorder(Color.black));
			Main_Pane.setBackground(new Color(100,100,150));
			Main_Pane.setLayout(new BoxLayout(Main_Pane, BoxLayout.PAGE_AXIS));
		
		Main_Pane.add(Instructor);

		Main_Pane.add(User_Lbl);
		Main_Pane.add(User_Entry);
		User_Entry.setEditable(true);
		User_Entry.setEnabled(true);
		User_Entry.setFocusable(true);

		Main_Pane.add(Password_Lbl);
		Main_Pane.add(Password_Entry);

		Main_Pane.add(New_User);
			New_User.addActionListener(this);
		Main_Pane.add(Login);
			Login.addActionListener(this);
			
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == Login)
		{
			if(Login.getText() == "Login")
			{
				//check userfile for username and then check to see if password is a match.
				
				this.hide();
				Starter.Current_Player = new User();
			}
			if(Login.getText() == "Cancle")
			{
				Login.setText("Login");
				New_User.setText("New Player");
				Instructor.setText("Enter Username and Password to login");
			}
					}
		if(e.getSource() == New_User)
		{
			if(New_User.getText() == "New Player")
			{
				Login.setText("Cancle");
				New_User.setText("Create");
				Instructor.setText("Enter new Username and Password");
			}
			if(New_User.getText() == "Create")
			{
				//create a new user entry in the userfile and then create a usercollection file.
			}
		}
	}

}
