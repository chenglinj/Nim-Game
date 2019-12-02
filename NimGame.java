/***************************************************************************
 *
 *   File        : NimGame.java
 *   Student ID  : 909134
 *   Name        : Chenglin Jing
 *   			   
 ***************************************************************************/

import java.util.Scanner;

/**
 * This class maintains the process and rules of the game.
 * @author Chenglin Jing
 *
 */
public class NimGame 
{
	private int stoneNumber, maxRemove;
	private String player1, player2;
	
	/**
	 * The constructor to create a NimGame instance.
	 * @param initialStones
	 * @param upperBound
	 * @param player1
	 * @param player2
	 */
	public NimGame(int initialStones, int upperBound, 
			       NimPlayer player1, NimPlayer player2)
	{
		stoneNumber = initialStones;
		maxRemove = upperBound;
		this.player1 = player1.getGivenName();
		this.player2 = player2.getGivenName();
	}
	
	/**
	 * Method to show stones at the beginning of the game.
	 */
	private void showStone()
	{
		System.out.print("\n" + stoneNumber + " stones left: ");
		
		for (int i = 1; i < stoneNumber; i++) 
		{
			System.out.print("* ");
		}
		
		System.out.print("*\n");
	}
	
	/**
	 * Overloading of the method above. To show stones after each move.
	 * @param remove
	 */
	private void showStone(int remove)
	{
		stoneNumber -= remove;
		
		if (stoneNumber > 0)
		{
			System.out.print("\n" + stoneNumber + " stones left: ");
			for (int i = 1; i < stoneNumber; i++) 
			{
				System.out.print("* ");
			}
			
			System.out.print("*\n");
		}
	}
	
	/**
	 * Start the game by giving necessary parameters and input.
	 * @param keyboard
	 * @param player1
	 * @param player2
	 */
	public void gameStart(Scanner keyboard, NimPlayer player1, NimPlayer player2)
	{
		String winner = null, winnerName;
		int flag1 = 0, flag2 = 0;
		
		System.out.println("\nInitial stone count: " + stoneNumber);
		System.out.println("Maximum stone removal: " + maxRemove);
		System.out.println("Player 1: " + this.player1 
				+ " " + player1.getFamilyName());
		System.out.println("Player 2: " + this.player2 
				+ " " + player2.getFamilyName());
		
		showStone();
		
		while (stoneNumber > 0)
		{
			winner = this.player1;
			
			int validRemove;
			
			validRemove = (stoneNumber >= maxRemove)? maxRemove: stoneNumber;
			
			System.out.println(this.player1 + "'s turn - remove how many?");
			
			if (player1 instanceof NimAIPlayer)
			{
				showStone(((NimAIPlayer)player1).removeStone(stoneNumber, validRemove));
				
			}
			
			else if (player1 instanceof NimHumanPlayer)
			{
				int remove1 = ((NimHumanPlayer)player1).removeStone(validRemove, keyboard);
			
			    if (remove1 == 0)
				    flag1 = 1;
			
			/**
			 * Repeat the move until it is valid.
			 */
			    while (flag1 == 1)
			    {
				    if (remove1 > 0)
				    {
					    flag1 = 0;
				        break;
				    }
				
				    showStone(0);
				
				    System.out.println(this.player1 + "'s turn - remove how many?");
				
				    remove1 = ((NimHumanPlayer)player1).removeStone(validRemove, keyboard);
			    }
			
			    showStone(remove1);
			}
			
			if (stoneNumber<=0)
			{
				winner = this.player2;
			    break;
			}
			
			if (player2 instanceof NimAIPlayer)
			{
				showStone(((NimAIPlayer)player2).removeStone(stoneNumber, validRemove));
				
			}
			
			else if (player2 instanceof NimHumanPlayer)
			{
			    int remove2 = ((NimHumanPlayer)player2).removeStone(validRemove, keyboard);
				
			    if (remove2 == 0)
				    flag2 = 1;
			
			/**
			 * Repeat the move until it is valid.
			 */
			    while (flag2 == 1)
			    {
				    if (remove2 > 0)
				    {
					    flag2 = 0;
				        break;
				    }
				
				    showStone(0);
				
				    System.out.println(this.player2 + "'s turn - remove how many?");
				
				    remove2 = ((NimHumanPlayer)player2).removeStone(validRemove, keyboard);
			    }
			
			showStone(((NimHumanPlayer)player2).removeStone(validRemove, keyboard));
			
		    }
		}
		/**
		 * Update statistics of the two players after each game.
		 */
		player1.updateGamePlayed();
		player2.updateGamePlayed();
		
		if (winner.equals(this.player1))
		{
			player1.updateGameWon();
		    winnerName = (player1.getGivenName() + " " + player1.getFamilyName());
		}
		
		else
		{
			player2.updateGameWon();
		    winnerName = (player2.getGivenName() + " " + player2.getFamilyName());
		}
		
		player1.updateWinRatio();
		player2.updateWinRatio();
		
		System.out.println("\nGame Over");
		System.out.println(winnerName + " wins!");
		
		String junk = keyboard.nextLine();
		
		
	    }

}
