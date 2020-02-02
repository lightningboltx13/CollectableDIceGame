import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class User extends JPanel implements ActionListener
{
	//User is the term used for a player out of battle.
	
	//Find way to represent the COLLECTION
	
	String User_Name = "Test";
	Bag[] Bags = new Bag[5];
	int Credit = 0;
	int Rank_Value = 0;
	
	JPanel User_Box = new JPanel();
	JLabel Name_Label = new JLabel();
	JLabel Credit_Label = new JLabel();
	JLabel Rank_Label = new JLabel();
	
	
	public User() 
	{
		// TODO Auto-generated constructor stub
		setVisible(true);
		add(User_Box);
		User_Box.add(Name_Label);
		User_Box.add(Credit_Label);
		User_Box.add(Rank_Label);
		
		Name_Label.setText(User_Name);
		Credit_Label.setText("Credits:" + Credit);
		Rank_Label.setText("Rank:" + Rank_Value);
		
		User_Box.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

}
