import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;



// Sklavenitis Dimitrios
// AEM: 9455
// Phone Number: 6940064840
// email: skladimi@ece.auth.gr
// 
// Ourdas Antonios
// AEM: 9358
// Phone Number: 6980561699
// email: ourdasav@ece.auth.gr



public class Game {
	
	private int round;
	
	// Constructors
	public Game() {round = 0;}
	
	public Game(int round) {this.round = round;}
	
	// Getter
	public int getRound() {return round;}
	
	// Setter
	public void setRound(int round) {this.round = round;}
	
	// Dice roll function
	public static int roll()
	{ 
		return (int)(1+Math.random()*6) ;
	}
	
	
	public static Map<Integer,Integer> setTurns(ArrayList<Object> players){
		int rollNo=0;
		Map<Integer,Integer> map=new TreeMap<Integer,Integer>();
		int[]cArray =new int[players.size()];
		for(int i=0;i<cArray.length;i++) {
			cArray[i]=0;
		}
		
		for(int i=0;i<players.size();i++) {
			rollNo=roll();
			map.put(rollNo,i);
			/// Restarts the process if two players roll the same number
			for(int j=0;j<cArray.length;j++) {          
				if(rollNo==cArray[j]) {
					map.clear();
					i=0;
					for(int k=0;k<cArray.length;k++) {
						cArray[i]=0;
					}
					rollNo=0;
				}
			}
			cArray[i]=rollNo;
			
		}
		return map;
	}
	

//	public static void sort(Player players[], int m[])
//	{
//		Player tempP = new Player();
//		
//		int temp = 0;
//		
//		for(int j = 0; j < m.length; ++j)
//		{
//			for(int i = 1; i < (m.length - j); ++i)
//			{
//				if(m[i] < m[i-1]) ///Re arranging array
//				{
//					// Swap dice rolls
//					temp = m[i - 1];
//					m[i - 1] = m[i];
//					m[i] = temp;
//					
//					// Swap players
//					tempP = players[i - 1];
//					players[i - 1] = players[i];
//					players[i] = tempP;
//					
//				}
//				else if(m[i] == m[i-1])
//				{
//					/// If the dice results are equal the players play in alphabetical order
//					if(players[i].getName().charAt(0)<players[i-1].getName().charAt(0))	
//					{
//						tempP=players[i-1];
//						players[i-1]=players[i];
//						players[i]=tempP;
//					}
//				}
//			}
//		}	
//	}
	
	// Function used to find which player plays first according to initial dice roll
	public static  Player[] initialRoll(Player players[])
	{
		int diceRes[] = new int[players.length];
		
		// First roll
		for(int i = 0; i < players.length; ++i)
		{
			diceRes[i] = roll();
		}
		
	
	
		return players;
	}
	
	
	public static void main(String[] args) 
	{
		
		Game g = new Game(0);
		
		Board board = new Board(20, 10, 3, 3, 6);
		
		board.createBoard();
		
		board.createElementBoard();
		
		// Array used for players
		ArrayList<Object>players=new ArrayList<Object>();
		players.add(new Player(0, 0, "Henry", board));
		players.add(new Player(1, 0, "Francois", board));
		Player temp=new Player();
		Map<Integer,Integer> turnMap=new TreeMap<Integer,Integer>();
		turnMap=setTurns(players);
		// Array with each player's Tile Id (position on board)
		int playerTileIds[] = new int[players.size()];
		
		// Initialize all players
		// Put them in 0 position
		for(int i = 0; i < playerTileIds.length; i++)
		{
			playerTileIds[i] = 0;
		}
		
		
		int gRound = 0; // for getRound
		
		boolean completed = false;
		
		//Round start
		for(Map.Entry<Integer,Integer> entry:turnMap.entrySet())
		{
			// Move player
			if(players.get(entry.getValue()) instanceof HeuristicPlayer) {
				
			}
			else {
				playerTileIds[entry.getValue()] = players.get(entry.getValue()).
						}
			

			// Check if player has reached the end (or greater)
			if(playerTileIds[playerNo] >= board.getM() * board.getN())
			{
		    	completed = true;
		    	break;
		    }
			
			
		    
		
		    }
		
		
		System.out.println("Rounds: " + g.getRound());
		
		for(int i = 0; i < players.length; ++i)
		{
			System.out.println("Player " + (i + 1));
			System.out.println("Score: " + players[i].getScore());
		}

		System.out.println("The winner is " + players[playerNo].getName());
	}
}
