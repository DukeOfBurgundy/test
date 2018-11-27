// Sklavenitis Dimitrios
// AEM: 9455
// Phone Number: 6940064840
// email: skladimi@ece.auth.gr
// 
// Ourdas Antonios
// AEM: 9358
// Phone Number: 6980561699
// email: ourdasav@ece.auth.gr

import java.util.Random;

public class Board {
	private int N, M;
	private int[][] tiles;
	private Snake[] snakes;
	private Ladder[] ladders;
	private Apple[] apples;
	
	// Constructors
	public Board()
	{
		N = 0;
		M = 0;
	}
	
	public Board(int N, int M, int snakesNo, int laddersNo, int applesNo)
	{
		this.N = N;
		this.M = M;
		
		// Allocate memory for all arrays
		tiles = new int[N][M];
		snakes = new Snake[snakesNo];
		ladders = new Ladder[laddersNo];
		apples = new Apple[applesNo];
		
		// Initialize every element (object) of all three arrays
		for(int i = 0; i < snakesNo; ++i)
			snakes[i] = new Snake();
		
		for(int i = 0; i < laddersNo; ++i)
			ladders[i] = new Ladder();
		
		for(int i = 0; i < applesNo; ++i)
			apples[i] = new Apple();
	}
	
	public Board(Board b)
	{
		// Create an object same as b
		this(b.N, b.M, b.snakes.length, b.ladders.length, b.apples.length);
	}
	
	// Getters
	public int getN() {return N;}
	public int getM() {return M;}
	public int[][] getTiles() {return tiles;}
	public Snake[] getSnakes() {return snakes;}
	public Ladder[] getLadders() {return ladders;}
	public Apple[] getApples() {return apples;}
	
	// Setters
	public void setN(int N) {this.N = N;}
	public void setM(int M) {this.M = M;}
	
	// Array setters set a specific element of array
	
	// Tiles
	public void setTiles(int id, int i, int j) 
	{
		if((i > -1 && i < N) && (j > -1 && j < M))
		{
			tiles[i][j] = id;
		}
	}
	
	// Snakes
	public void setSnakes(Snake s, int i)
	{
		if(i > -1 && i < N)
		{
			snakes[i] = s;
		}
	}
	
	// Ladders
	public void setLadders(Ladder l, int i)
	{
		if(i > -1 && i < N)
		{
			ladders[i] = l;
		}
	}
	
	// Apples
	public void setApples(Apple a, int i)
	{
		if(i > -1 && i < N)
		{
			apples[i] = a;
		}
	}
	
	public void createBoard()
	{
		// Filling in tiles board
		// We start from Nth line and move up
		// Size variable is used to fill in ids in tiles table
		// flag is used as an indicator when to fill every row in ascending or descending order

		int Size = 1;
		boolean flag = true;

		for(int i = N - 1; i > -1; --i)
		{
			if(flag)
			{
				for(int j = 0; j < M; ++j)
				{
					tiles[i][j] = Size;
					++Size;
				}
				flag = !flag; // switch flag
			} else
			{
				for(int j = M - 1; j > -1; --j)
				{
					tiles[i][j] = Size;
					++Size;
				}
				flag = !flag; // switch flag
			}
		}
		
		// End of filling tiles array
		
		
		// Create board with random snakes, ladders and apples
		// Also check for overlaps
		
		
		// Initialize snakes of board
		
		// Number of snakes initialized and id for snakes
		int snakesInitialized = 0, snakesId = 0;
		
		// Flags to check if snake is valid
		boolean snakeExists = true, tailAboveHead = false;
		
		// Do it for all snakes[] array elements (Snake objects)
		for(int i = 0; i < snakes.length; ++i)
		{
			// We don't know if the snake exists
			// So at first we assume the new snake does exist
			// This way the program enters the while loop th first time
			snakeExists = true;
			
			// Create a random snake until it is valid
			while(snakeExists || tailAboveHead)
			{
				// Here we assume the new random snake doesn't exists
				// And its head is above tail
				snakeExists = false;
				tailAboveHead = false;
				
				// Create a new snake
				snakes[i] = new Snake(snakesId, (int)(Math.random() * (N * M - 1) + 1), (int)(Math.random() * (N * M - 1) + 1));
				
				// Check if head of snake is above tail (if not then tailAboveHead = true)
				if(snakes[i].getHeadId() <= snakes[i].getTailId())
				{
					tailAboveHead = true;
					continue;
				}

				// Check if snake already exists (same head or tail (at least))
				for(int j = 0; j < snakesInitialized; ++j)
				{
					if(snakes[j].getHeadId() == snakes[i].getHeadId() || snakes[j].getTailId() == snakes[i].getTailId())
					{
						snakeExists = true;
						break;
					}
				}
			}
			
			// At this point snakes[i] has been initialized and is valid
			// So we increase snakesInitialized and snakesId
			++snakesId;
			++snakesInitialized;
		}
		
		
		// Initialize ladders of board
		
		// Number of ladders initialized and id for ladders
		int laddersInitialized = 0, laddersId = 0;
				
		// Flags to check if ladder if valid
		boolean ladderExists = true, upStepAboveDownStep = false, ladderSnakeOverlap = false;
				
		// Do it for all ladders[] array elements (Ladder objects)
		for(int i = 0; i < ladders.length; ++i)
		{
			// Same thing as above in snakes initialization
			ladderExists = true;
					
			// Create a random ladder until it is valid
			while(ladderExists || upStepAboveDownStep || ladderSnakeOverlap)
			{
				ladderExists = false;
				upStepAboveDownStep = false;
				ladderSnakeOverlap = false;
						
				// Create a new ladder
				ladders[i] = new Ladder(laddersId, (int)(Math.random() * (N * M - 1) + 1), (int)(Math.random() * (N * M - 1) + 1), false);
				
				// Check if the downStep of ladder is above upStep (if not then upStepAboveDownStep = true)
				if(ladders[i].getDownStepId() <= ladders[i].getUpStepId())
				{
					upStepAboveDownStep = true;
					continue;
				}

				// Check if ladder already exists (same upStep or downStep (at least))
				for(int j = 0; j < laddersInitialized; ++j)
				{
					if(ladders[j].getUpStepId() == ladders[i].getUpStepId() || ladders[j].getDownStepId() == ladders[i].getDownStepId())
					{
						ladderExists = true;
						break;
					}
				}
						
				if(ladderExists)
					continue;
				
				// Check if head of snake is the same as ladder base
				// Because in that case the player won't be able to decide where to go
				// Also, if snake tail is the same with ladder top there is no problem
				for(int j = 0; j < snakes.length; ++j)
				{
					if(ladders[i].getUpStepId() == snakes[j].getHeadId())
					{
						ladderSnakeOverlap = true;
						break;
					}
				}
			}

			
			// At this point ladders[i] has been initialized and is valid
			// So we increase laddersInitialized and laddersId
			++laddersId;
			++laddersInitialized;
		}
		
		
		
		
		// Same thing with apples
		int applesInitialized = 0, applesId = 0;
		boolean appleExists = true, snakeOverlap = false;
			
		// Do it for all apples
		for(int i = 0; i < apples.length; ++i)
		{
			appleExists = true;
				
			while(appleExists || snakeOverlap)
			{
				// Initialize only appleId and appleTileId
				// Then we get to initialize color and points
				apples[i] = new Apple(applesId, (int)(Math.random() * (N * M - 1) + 1), "", 0);
					
				appleExists = false;
				snakeOverlap = false;
		
				
				for(int j = 0; j < applesInitialized; ++j)
				{
					// Check if appleTaleId already exists
					if(apples[j].getAppleTileId() == apples[i].getAppleTileId())
					{
						appleExists = true;
						break;
					}
				}
				
				if(appleExists)
					continue;
				
				for(int j = 0; j < snakes.length; ++j)
				{
					// Check if apple is upon snake head
					if(snakes[j].getHeadId() == apples[i].getAppleTileId())
					{
						snakeOverlap = true;
						break;
					}
				}
			}
				
			// Once appleTaleId is valid
			
			Random r = new Random();
			
			// Pick a random color from red or black
			if(r.nextInt(2) == 0)
			{
				apples[i].setColor("red");
			}
			else
			{
				apples[i].setColor("black");
			}
				
			// Set points according to color
			if(apples[i].getColor() == "red")
			{
				apples[i].setPoints((int)(Math.random() * 5 + 5));
			}
			else
			{
				apples[i].setPoints(- (int)(Math.random() * 5 + 5));
			}
			
			// At this point apples[i] has been initialized and is valid
			// So we increase applesInitialized and applesId
			++applesInitialized;
			++applesId;
		}
	}
	
	
	public void createElementBoard()
	{
		// Allocate memory  for String arrays
		String[][] elementBoardSnakes = new String[N][M];
		String[][] elementBoardLadders = new String[N][M];
		String[][] elementBoardApples = new String[N][M];
		
		// Fill in every element with "___" character
		for(int i = 0; i < N; ++i)
		{
			for(int j = 0; j < M; ++j)
			{
				elementBoardSnakes[i][j] = "___";
				elementBoardLadders[i][j] = "___";
				elementBoardApples[i][j] = "___";
			}
		}
		
		// Here we fill in the String arrays with snakes, ladders and apples
		// An efficient way to do so is to develop an algorithm to give us directly the position (x, y)
		// of an element (snake head or tail, ladder top or base, apple) depended on its id
		// Note: x represents column and y represents row
		 
		int x = 0, y = 0, id = 0;
		
		// Snakes
		for(int i = 0; i < snakes.length; ++i)
		{
			// Once for head
			id = snakes[i].getHeadId();
			y = (id - 1) / M;
			
			if(y % 2 == 0)
			{
				x = (id - 1) % M + 1;
			} else
			{
				x = M - (id - 1) % M;
			}
			y = N - y;
			elementBoardSnakes[y - 1][x - 1] = "SH" + snakes[i].getSnakeId();
			
			// And once for tail
			id = snakes[i].getTailId();
			y = (id - 1) / M;
			
			if(y % 2 == 0)
			{
				x = (id - 1) % M + 1;
			} else
			{
				x = M - (id - 1) % M;
			}
			
			y = N - y;
			elementBoardSnakes[y - 1][x - 1] = "ST" + snakes[i].getSnakeId();
		}
		
		
		// Snakes
		for(int i = 0; i < ladders.length; ++i)
		{
			// Once for top
			id = ladders[i].getDownStepId();
			y = (id - 1) / M;
			
			if(y % 2 == 0)
			{
				x = (id - 1) % M + 1;
			} else
			{
				x = M - (id - 1) % M;
			}
			
			y = N - y;
			elementBoardLadders[y - 1][x - 1] = "LU" + ladders[i].getLadderId();
			
			// Once for base
			id = ladders[i].getUpStepId();
			y = (id - 1) / M;
			
			if(y % 2 == 0)
			{
				x = (id - 1) % M + 1;
			} else
			{
				x = M - (id - 1) % M;
			}
			
			y = N - y;
			elementBoardLadders[y - 1][x - 1] = "LD" + ladders[i].getLadderId();
		}
		
		// Apples
		for(int i = 0; i < apples.length; ++i)
		{
			id = apples[i].getAppleTileId();
			y = (id - 1) / M;
			
			if(y % 2 == 0)
			{
				x = (id - 1) % M + 1;
			} else
			{
				x = M - (id - 1) % M;
			}
			
			y = N - y;
			
			// Check for color
			if(apples[i].getColor() == "red")
			{
				elementBoardApples[y - 1][x - 1] = "AR" + apples[i].getAppleId();
			}
			else if(apples[i].getColor() == "black")
			{
				elementBoardApples[y - 1][x - 1] = "AB" + apples[i].getAppleId();
			}
			
		}

		// Print arrays next to each other
		for(int i = 0; i < N; ++i)
		{
			for(int j = 0; j < M; ++j)
			{
				System.out.print(elementBoardSnakes[i][j] + " ");
			}
			System.out.print("   ");
			
			for(int j = 0; j < M; ++j)
			{
				System.out.print(elementBoardLadders[i][j] + " ");
			}
			System.out.print("   ");
			
			for(int j = 0; j < M; ++j)
			{
				System.out.print(elementBoardApples[i][j] + " ");
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
}
