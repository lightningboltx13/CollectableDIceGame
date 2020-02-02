import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;


public class Collection_Menu extends JFrame implements ActionListener
{
	
	JPanel Main_Pane = new JPanel();
	
	
	//Card Catalog
	JPanel Card_Catalog = new JPanel();
	
	int Catalog_Size = Catalog_Counter();
	Unit[] Catalog = new Unit[Catalog_Size];
	int Visable_Index = 0;
	JButton View_Left_Btn = new JButton("<<");
	JButton View_Right_Btn = new JButton(">>");
	int[][] Owned_Amount = new int[2][Catalog_Size];
	
	
	JButton Back_Btn = new JButton("Main" + "\n" + "Menu");

	GridBagConstraints c = new GridBagConstraints();
	//test
	
	//Bag List
	JPanel Bag_Selector_Pane = new JPanel();
	
	public Collection_Menu(int X_loc, int Y_loc) 
	{
		// TODO Auto-generated constructor stub
		
		setTitle("Collectable Dice Game");
		setVisible(true);
		setResizable(false);
	    setBounds(X_loc, Y_loc, 700, 600);
	    //setLocationRelativeTo(null);
		    
	    addWindowListener(new WindowAdapter()
	    {
	      public void windowClosing(WindowEvent e)
	      {
	        System.exit(0);
	      }
	    });
		
	    add(Main_Pane);
	    
	    
	    Main_Pane.add(Starter.Current_Player);
	    
		Catalog_Loader(Catalog_Size);
		Main_Pane.add(Card_Catalog);
	    Card_Catalog.setLayout(new GridBagLayout());
	    Catalog_Scroller('S');
	    
	    Card_Catalog.add(View_Left_Btn);
	    View_Left_Btn.addActionListener(this);
	    Card_Catalog.add(View_Right_Btn);
	    View_Right_Btn.addActionListener(this);
	}

	public JPanel Bag_Display(Bag Target)
	{
		JPanel Bag_Box = new JPanel();
		
		return Bag_Box;
		
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource() == View_Left_Btn & Visable_Index > 0)
			Catalog_Scroller('L');
		if(e.getSource() == View_Right_Btn & (Visable_Index + 5) < Catalog_Size)//5 units are visable at a time
			Catalog_Scroller('R');
	}

	public void Catalog_Scroller(char LeftRight)
	{
		//'L' = Left
		//'R' = Right
		//'S' = Starter
		if(LeftRight != 'S')
		{
			for(int index = 0; index < 5; index++)
		    	Card_Catalog.remove(Catalog[Visable_Index + index]);
			
			if(LeftRight == 'L')
				Visable_Index--;
			if(LeftRight == 'R')
				Visable_Index++;
		}
		
		Card_Catalog.revalidate();
		c.gridheight =0;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		
		for(int index = 0; index < 5; index++)
	    {
	    	c.gridx = index;
	    	Card_Catalog.add(Catalog[Visable_Index + index], c);
	    }
		Card_Catalog.revalidate();

	}
	
	
	public void Catalog_Loader(int Size)
	{
		BufferedReader reader;
		
	    try {
			reader = new BufferedReader(new FileReader("Unit_Catalog.txt"));
			String Input_Row = "";
			
			for(int index = 0; index < Size; index++)
			{
				Input_Row = reader.readLine();
				Catalog[index] = new Unit(Input_Row);
				Catalog[index].Unit_Display("Craft");
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int Catalog_Counter()
	{
		BufferedReader reader;
		boolean Debugger = true;
		int counter = 0;
		
	    try {
			reader = new BufferedReader(new FileReader("Unit_Catalog.txt"));
			String Input_Row = "";
			
			while(Debugger)
			{
				Input_Row = reader.readLine();
				
				if(Input_Row == null)
					Debugger = false;
				else
					counter++;
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return counter;
		
	}
}
