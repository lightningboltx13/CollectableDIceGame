import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;












import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;



public class Unit extends JPanel implements ActionListener
{
	//Unit represents a Die
	
	String Name;
	char Rarity;
	String Set;
	String[] Face_Keys = new String[6];
	String Active_Face;
	
	Color L_Clr = new Color(190,190,230);
	Color R_Clr = new Color(180,160,90);
	Color U_Clr = new Color(130,130,130);
	Color C_Clr = new Color(160,130,90);
	
	//This is the Constructor for a Unit_Stat Display Box
	
	JPanel Unit_Box = new JPanel();
	
	JLabel Name_Label = new JLabel(this.Name);
	
	
	//Battle Mode
	JPanel Battle_Pane = new JPanel();
	
	//1 Image
	
	JLabel Active_Display = new JLabel();
	
	JPanel Battle_Btn_Pane = new JPanel();
	JButton Damage_Btn = new JButton("");
	
	
	
	JButton Select_Btn = new JButton("");
	JButton Information_Btn = new JButton("Info");
	
	//View Mode
	JPanel View_Pane = new JPanel();
	
	//6 Images
	JPanel Face_Pane = new JPanel();
	JLabel[] Debug_Faces = new JLabel[6];
	JLabel[] Face_Icons = new JLabel[6];
	
	JButton View_Btn = new JButton();
	
	
	//Selection states of Buttons
	LineBorder Selected = new LineBorder(Color.BLUE, 2);
	LineBorder UnSelected = new LineBorder(Color.BLACK, 2);
	
	
	
	//This is the Constructor for a Die.
	//importing a Die Constructor Hash
	//Name,Rarity,(Faces,)
	public Unit(String Constructor_Hash) 
	{

		//Unit Hash Converter
		int Start_Index = 0;
		int End_Index = Constructor_Hash.indexOf(":");
		
		
		if(Start_Index != -1)
		{
			Name = Constructor_Hash.substring(Start_Index, End_Index);
			Name_Label = new JLabel(Name);
		}
		
		Start_Index = End_Index + 1;
		End_Index = Constructor_Hash.indexOf(":" , Start_Index);
		Rarity = Constructor_Hash.charAt(Start_Index);
		Start_Index = End_Index + 1;
		
		for(int Side_Index = 0; Side_Index < 6; Side_Index++)
		{
			End_Index = Constructor_Hash.indexOf("," , Start_Index);
			if(End_Index != -1)
				Face_Keys[Side_Index] = Constructor_Hash.substring(Start_Index, End_Index);
			else
				Face_Keys[Side_Index] = Constructor_Hash.substring(Start_Index, Constructor_Hash.length());
			
			Start_Index = End_Index + 1;
		}
		
		
		
		
		//Unit Display Constructor
		add(Unit_Box);
		Unit_Box.setBorder(BorderFactory.createLineBorder(Color.black));
		Unit_Box.setLayout(new BoxLayout(Unit_Box, BoxLayout.Y_AXIS));
		
		Set_Unit_Color();
		
		Dimension Unit_Size = new Dimension(100,150);
		Unit_Box.setPreferredSize(Unit_Size);
		
		Unit_Box.add(Name_Label);
		Name_Label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Battle Mode
		Battle_Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		try{
		    Image img = ImageIO.read(getClass().getResource("Icons/Blank.bmp"));
		    Active_Display.setIcon(new ImageIcon(img));
		} 
		catch(Exception ex){System.out.println(ex);}
		Battle_Pane.add(Active_Display);
		
		
		Battle_Pane.add(Battle_Btn_Pane);
		Battle_Btn_Pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,0);
		Battle_Btn_Pane.add(Damage_Btn, c);
		try{
		    Image img = ImageIO.read(getClass().getResource("Icons/Sword.bmp"));
		    Damage_Btn.setIcon(new ImageIcon(img));
		} 
		catch(Exception ex){System.out.println(ex);}
		Damage_Btn.setMargin(new Insets(0,0,0,0));
		Damage_Btn.addActionListener(this);
		Damage_Btn.setBackground(Color.BLACK);
		Damage_Btn.setBorder(UnSelected);

		c.gridx = 1;
		c.gridy = 0;
		Battle_Btn_Pane.add(Select_Btn, c);
			Select_Btn.addActionListener(this);
		try{
		    Image img = ImageIO.read(getClass().getResource("Icons/Select.bmp"));
		    Select_Btn.setIcon(new ImageIcon(img));
		} 
		catch(Exception ex){System.out.println(ex);}
		Select_Btn.setMargin(new Insets(0,0,0,0));
		Select_Btn.setBackground(Color.BLACK);
		Select_Btn.setBorder(UnSelected);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = 0;
		c.gridy = 1;
		Battle_Btn_Pane.add(Information_Btn, c);
			Information_Btn.addActionListener(this);
		
		//View Mode
		//View_Pane is added in Unit_Display
		View_Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
		View_Pane.setLayout(new BoxLayout(View_Pane, BoxLayout.Y_AXIS));
		
		View_Pane.add(Face_Pane);
			Face_Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Face_Pane.setLayout(new GridBagLayout());
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		
		
		for(int Side_Index = 0; Side_Index < 6; Side_Index++)
		{
			c.gridx = Math.round((Side_Index)%2);
			c.gridy = Math.round((Side_Index)/2);
			Face_Icons[Side_Index] = new JLabel();
			String Icon = "Icons/" + Face_Keys[Side_Index] + ".bmp";
			
			try{
			    Image img = ImageIO.read(getClass().getResource(Icon));
			    Face_Icons[Side_Index].setIcon(new ImageIcon(img));
			} 
			catch(Exception ex){System.out.println(ex);}
			Face_Pane.add(Face_Icons[Side_Index], c);
		}
		
		
		View_Pane.add(View_Btn);
		View_Btn.addActionListener(this);
		View_Btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	}

	
	//This will be used to roll a Die to determine its Face
	//Returning a Hash Key
	//HashMap will be located in the 'Game' Class
	public void Roll()
	{
		Random rand = new Random();
		Active_Face = Face_Keys[rand.nextInt(6)];
	}
	
	public void Unit_Display(String Mode)
	{
		if(Mode == "Battle")
		{
			Unit_Box.remove(View_Pane);
			Unit_Box.add(Battle_Pane, BorderLayout.CENTER);
		}
		else
		{
			View_Btn.setText(Mode);
			Unit_Box.remove(Battle_Pane);
			Unit_Box.add(View_Pane, BorderLayout.CENTER);
		}
		Unit_Box.revalidate();
		Unit_Box.repaint();
	}
	
	public void Set_Unit_Color()
	{
		Color Target_Clr = Color.BLACK;
		if(Rarity == 'L')//Legendary
			Target_Clr = L_Clr;
		if(Rarity == 'R')//Rare
			Target_Clr = R_Clr;
		if(Rarity == 'U')//Uncommon
			Target_Clr = U_Clr;
		if(Rarity == 'C')//Common
			Target_Clr = C_Clr;
		
		
		Unit_Box.setBackground(Target_Clr);
		Battle_Pane.setBackground(Target_Clr);
		Battle_Btn_Pane.setBackground(Target_Clr);
		View_Pane.setBackground(Target_Clr);
		Face_Pane.setBackground(Target_Clr);
		
	}
	
	//Modular Display Box
	/*
	  In-BattleField Mode
	  Name
	  Active Side
	  Buttons:
	  	Damage
	  	Select
	  	Information
	  
	  View Mode
	  Name
	  Set
	  All 6 Sides
	  Buttons:
	  	Select/Throw/Craft/Add/Back/Remove
	  	
	  List of Places you see View Mode:
	  Collection	(Craft)
	  Bag Editing 	(Add) or (Remove)
	  Hand In-Game	(Throw)
	  Dieyard In-Game	(Nothing)
	  Information Button 'In-BattleField Mode' switches it to this	(Back)
	*/


	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		if(e.getSource() == Damage_Btn)
		{
			if(Damage_Btn.getBorder() == UnSelected)
				Damage_Btn.setBorder(Selected);
			else
				Damage_Btn.setBorder(UnSelected);
			
			//Debug! Dice roll updating Active_Face
			Roll();
			String Icon = "Icons/" + Active_Face + ".bmp";
			try{
			    Image img = ImageIO.read(getClass().getResource(Icon));
			    Active_Display.setIcon(new ImageIcon(img));
			} 
			catch(Exception ex){System.out.println(ex);}
			
		}
		if(e.getSource() == Select_Btn)
		{
			if(Select_Btn.getBorder() == UnSelected)
				Select_Btn.setBorder(Selected);
			else
				Select_Btn.setBorder(UnSelected);
		}
		
		if(e.getSource() == Information_Btn)
		{
			this.Unit_Display("Back");
		}
		
		if(e.getSource() == View_Btn)
		{
			if(View_Btn.getText() == "Back")
			{
				this.Unit_Display("Battle");
			}
			if(View_Btn.getText() == "Craft")
			{
				
			}
			if(View_Btn.getText() == "Add")
			{
				
			}
			if(View_Btn.getText() == "Remove")
			{
				
			}
			
			//In_Game Hand option. Switch to "Select when you Throw a Legendary Unit.
			if(View_Btn.getText() == "Throw")
			{
				
			}
			
			
			//used for the discard for Throwing a Legendary Unit
			if(View_Btn.getText() == "Select")
			{
				
			}
		}
		
		
		
	}


	
	
	
}
