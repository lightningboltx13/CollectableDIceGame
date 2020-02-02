import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Starter extends JFrame implements ActionListener
{
	
	//Eventually will need to add the login portion
	static User Current_Player;
	
	
	//Main Menu
	JPanel Btn_Pane = new JPanel();
	
	JButton Play_Btn = new JButton("PLAY");
	JButton Collection_Btn = new JButton("COLLECTION");
	
	
	
	

	public Starter() 
	{
		setTitle("Collectable Dice Game");
		setVisible(true);
		setResizable(false);
	    setSize(700, 600);
	    setLocationRelativeTo(null);
	    
	    
	   // while(Current_Player == null)
	    	//this.
	    
	    addWindowListener(new WindowAdapter()
	    {
	      public void windowClosing(WindowEvent e)
	      {
	        System.exit(0);
	      }
	    });
		    
		    
		    
		this.add(Btn_Pane);
		Btn_Pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		Btn_Pane.add(Play_Btn, c);
		c.gridy = 1;
		Btn_Pane.add(Collection_Btn, c);
		Play_Btn.addActionListener(this);
		Collection_Btn.addActionListener(this);
		
		Login_Prompt UserLogin_Window = new Login_Prompt();
		
		
	}

	public static void main(String[] args) 
	{
		new Starter();

		
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == Play_Btn)
		{
			
		}
		if(e.getSource() == Collection_Btn)
		{
			this.hide();
			new Collection_Menu(getX(), getY());
			
		}
		
	}
	
	static void Login_Function()
	{
		
	}

}
