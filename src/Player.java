

public class Player 
{
	//Player Represents a User that has entered a Battle/Game
	//Holding all Game Stats
	
	
	String Name;
	
	int Hit_Points = 20;
	
	int Damage_Points = 0;
	
	int Block_Points = 0;
	
	
	Bag Potential = new Bag();
	
	Unit[] Hand = new Unit[0];
	
	Unit[] Dieyard = new Unit[0];
	
	Unit[] BattleField = new Unit[0];
	
	
	
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

}
