/***************************************************************************
 *
 *   File        : Nimsys.java
 *   Student ID  : 909134
 *   Name        : Chenglin Jing
 *   			   
 ***************************************************************************/


import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

/**
 * The class maintains the collection, sorting methods of players and the main method.
 * This is the core of the project.
 * @author Chenglin Jing
 *
 */
public class Nimsys 
{
    
	/**
	 * Initialize the array of players and the countf, set the maxvalue to 100.
	 */
	public static final int MAX_PLAYER = 100;
	private static NimPlayer[] player = new NimPlayer[MAX_PLAYER];
	private static int playerNum = 0;
	
	/**
	 * Method to sort the array by user name using selection sort.
	 * @param a
	 */
	private static void sortPlayer(NimPlayer[] a)
	{
		int index, indexOfNextSmallest;
		for (index = 0; index < playerNum - 1; index++)
		{
			indexOfNextSmallest = indexOfSmallest(a, index, playerNum);
			swap(a, index, indexOfNextSmallest);
		}
	}
	
	private static int indexOfSmallest(NimPlayer[] a, int startIndex,int maxIndex)
	{
		String min = a[startIndex].getUserName();
		int indexOfMin = startIndex;
		int index;
		
		for (index = startIndex + 1; index < maxIndex; index++)
			if ((a[index].getUserName().compareTo(min))<0)
			{
				min = a[index].getUserName();
				indexOfMin = index;
			}
		
		return indexOfMin;
	}
	
	/**
	 * To sort the array in ascending order of winning ratios using selection sort.
	 * @param a
	 */
	private static void sortRankingAsc(NimPlayer[] a)
	{
		int index, indexOfNextSmallest;
		for (index = 0; index < playerNum - 1; index++)
		{
			indexOfNextSmallest = indexOfRatioAsc(a, index, playerNum);
			swap(a, index, indexOfNextSmallest);
		}
	}
	
	private static int indexOfRatioAsc(NimPlayer[] a, int startIndex,int maxIndex)
	{
		double min = a[startIndex].getWinRatio();
		int indexOfMin = startIndex;
		int index;
		
		for (index = startIndex + 1; index < maxIndex; index++)
			if (a[index].getWinRatio() < min)
			{
				min = a[index].getWinRatio();
				indexOfMin = index;
			}
			else if (a[index].getWinRatio() == min)
			{
				if ((a[index].getUserName().compareTo(a[indexOfMin].getUserName())) <= 0)
				{
					min = a[index].getWinRatio();
					indexOfMin = index;
				}
			}
		
		return indexOfMin;
	}
	
	/**
	 * To sort the array in descending order of winning ratios using selection sort.
	 * @param a
	 */
	private static void sortRankingDesc(NimPlayer[] a)
	{
		int index, indexOfNextLargest;
		for (index = 0; index < playerNum - 1; index++)
		{
			indexOfNextLargest = indexOfRatioDesc(a, index, playerNum);
			swap(a, index, indexOfNextLargest);
		}
	}
	
	private static int indexOfRatioDesc(NimPlayer[] a, int startIndex,int maxIndex)
	{
		double max = a[startIndex].getWinRatio();
		int indexOfMax = startIndex;
		int index;
		
		for (index = startIndex + 1; index < maxIndex; index++)
			if (a[index].getWinRatio() > max)
			{
				max = a[index].getWinRatio();
				indexOfMax = index;
			}
			else if (a[index].getWinRatio() == max)
			{
				if ((a[index].getUserName().compareTo(a[indexOfMax].getUserName())) <= 0)
				{
					max = a[index].getWinRatio();
					indexOfMax = index;
				}
			}
		
		return indexOfMax;
	}
	
	private static void swap(NimPlayer[] a, int i, int j)
	{
		NimPlayer temp = new NimPlayer();
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void readFromFile(File file)
	{
		FileInputStream fIS;
		ObjectInputStream oIS;
		
		try
		{
			fIS = new FileInputStream(file);
			
			oIS = new ObjectInputStream(fIS);
			
			player = (NimPlayer[])oIS.readObject();
			
			oIS.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void writeToFile(File file)
	{
		FileOutputStream fOS;
		ObjectOutputStream oOS;
		try
		{
			fOS = new FileOutputStream(file);
			oOS = new ObjectOutputStream(fOS); 
			oOS.writeObject(player);
			oOS.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	/**
	 * Main method of the project.
	 * Make responses to different kinds of valid commands.
	 * @param args
	 */
	public static void main(String[] args)
	{
		String userName, givenName, familyName, commands;
		int flag;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Welcome to Nim");
		System.out.print("\n$");
		
		commands = keyboard.nextLine();
		
		/**
		 * Make responses to non-exit commands.
		 */
		while (!(commands.equals("exit")))
		{
			StringTokenizer s = new StringTokenizer(commands," ,");
			
			/**
			 * Select the keyword of the commands.
			 */
			String command = s.nextToken();
			
			try
			{
			
			/**
			 * Add new player.
			 */
			if (command.equals("addplayer")||command.equals("addaiplayer"))
			{
				if (s.countTokens() < 3)
				{
					throw new InsufficientArgumentException();
				}
				userName = s.nextToken();
				familyName = s.nextToken();
				givenName = s.nextToken();
				flag = 0;
				
				/**
				 * To see whether the added player already exist.
				 */
				for (int i = 0; i < playerNum; i++)
				{
					if (player[i].getUserName().equals(userName))
					{
						System.out.println("The player already exists.");
						
						flag = 1;
						break;
					}					
				}
				
				/**
				 * Add a new player to the array.
				 */
				if (flag == 0)
				{
					if (command.equals("addplayer"))
					{
						NimHumanPlayer human =  new NimHumanPlayer(userName,familyName,givenName);
					    player[playerNum] = human;
					    playerNum += 1;
					}
					
					else
					{
						NimAIPlayer ai = new NimAIPlayer(userName,familyName,givenName);
						player[playerNum] = ai;
						playerNum += 1;
					}
				}		
			}
			
			/**
			 * Delete existing players.
			 */
			else if (command.equals("removeplayer"))
			{
				
				/**
				 * To see if it is a single delete or delete all the players.
				 * If single, then check whether the player exist.
				 */
				if (s.hasMoreTokens())
				{
					userName = s.nextToken();
					flag = 0;
					
					for (int i = 0; i < playerNum; i++)
					{
						if (player[i].getUserName().equals(userName))
						{
							//player[i] = null;
							player[i] = player[playerNum - 1];
							player[playerNum - 1] = null;
							playerNum -= 1;
							flag = 1;
							break;
						}
					}
					
					if (flag == 0)
						System.out.println("The player does not exist.");
				}
				
				/**
				 * Get confirmation from user to delete all the players.
				 */
				else
				{
					System.out.println("Are you sure you want to remove all players? (y/n)");
					
					String confirm = keyboard.nextLine();
					
					if (confirm.equals("y"))
					{
						//player = null;
						playerNum = 0;
					}
				}
			}
			
			/**
			 * Edit existing players.
			 */
			else if (command.equals("editplayer"))
			{
				if (s.countTokens() < 3)
				{
					throw new InsufficientArgumentException();
				}
				
				userName = s.nextToken();
				familyName = s.nextToken();
				givenName = s.nextToken();
				flag = 0;
				
				/**
				 * To check whether the player exist.
				 */
				for (int i = 0; i < playerNum; i++)
				{
					if (player[i].getUserName().equals(userName))
					{
						player[i].setFamilyName(familyName);
						player[i].setGivenName(givenName);
						flag = 1;
						break;
					}
				}
				
				if (flag == 0)
				{
					System.out.println("The player does not exist.");
				}
			}
			
			/**
			 * Reset gaming records of existing players.
			 */
			else if (command.equals("resetstats"))
			{
				if (s.hasMoreTokens())
				{
					userName = s.nextToken();
                    flag = 0;
					
					for (int i = 0; i < playerNum; i++)
					{
						if (player[i].getUserName().equals(userName))
						{
							player[i].resetStats();
							flag = 1;
							break;
						}
					}
					
					if (flag == 0)
						System.out.println("The player does not exist.");
					
				}
				
				/**
				 * Get confirmation from user to reset all the statistics.
				 */
				else
				{
					System.out.println("Are you sure you want to reset all player statistics? (y/n)");
					
					String confirm = keyboard.nextLine();
					
					if (confirm.equals("y"))
					{
						for (int i = 0; i < playerNum; i++)
						{
							player[i].resetStats();
						}
					}
				}
			}
			
			/**
			 * Display players.
			 */
			else if (command.equals("displayplayer"))
			{
				
				/**
				 * To check whether display a single player or all the players.
				 */
				if (s.hasMoreTokens())
				{
					userName = s.nextToken();
					flag = 0;
							
					for (int i = 0; i < playerNum; i++)
					{
						if (player[i].getUserName().equals(userName))
						{
							player[i].displayPlayer();
							flag = 1;
							break;
						}
					}
					
					if (flag == 0)
						System.out.println("The player does not exist.");					
				}
				
				else
				{
					sortPlayer(player);
					for (int i = 0; i < playerNum; i++)
						player[i].displayPlayer();
				}
			}
			
			/**
			 * Show the winning ratios and some other information by ranking.
			 */
			else if (command.equals("rankings"))
			{
				
				/**
				 * Decide the ranking order
				 */
				if (s.hasMoreTokens())
				{
					String order = s.nextToken();
					
					if (order.equals("asc"))
					{
						sortRankingAsc(player);
						//Show only 10 players if there are more than 10.
						for (int i = 0; i < ((playerNum <= 10) ? playerNum: 10); i++)
							player[i].displayRanking();
					}
					
					if (order.equals("desc"))
					{
						sortRankingDesc(player);
						//Show only 10 players if there are more than 10.
						for (int i = 0; i < ((playerNum <= 10) ? playerNum: 10); i++)
							player[i].displayRanking();
					}
				}
			
				else
				{
					sortRankingDesc(player);
					//Show only 10 players if there are more than 10.
					for (int i = 0; i < ((playerNum <= 10) ? playerNum: 10); i++)
						player[i].displayRanking();
				}
			}
			
			
			/**
			 * Start a game.
			 */
			else if (command.equals("startgame"))
			{
				if (s.countTokens() < 4)
				{
					throw new InsufficientArgumentException();
				}
				
				int initialStones = Integer.parseInt(s.nextToken()),
					upperBound = Integer.parseInt(s.nextToken()),
					playerCheck1 = 0, playerCheck2 = 0,
					index1 = -1 , index2 = -1;
				String player1 = s.nextToken(),
					   player2 = s.nextToken();
				
				/**
				 * Check whether both players exist.
				 */
				for (int i = 0; i < playerNum; i++)
				{
					if (player[i].getUserName().equals(player1))
					{
						index1 = i;
						playerCheck1 = 1;
					}
					
					if (player[i].getUserName().equals(player2))
					{
						index2 = i;
						playerCheck2 = 1;
					}
				}
				
				if ((playerCheck1 == 0)||(playerCheck2 ==0))
				{
					System.out.println("One of the players does not exist.");
				}
				
				else
				{
					NimGame game = new NimGame(initialStones, upperBound,
							player[index1], player[index2]);
					game.gameStart(keyboard, player[index1], player[index2]);
				}
				
			}
			
			else
			{
				throw new InvalidCommandException(command);
			}
				
		
				
			}
			
			catch (InvalidCommandException e)
			{
				String message = e.getMessage();
				System.out.println("'" + message + "'" + " is not a valid command.");
			}
			
			catch (InsufficientArgumentException e)
			{
				String message = e.getMessage();
				System.out.println(message);
			}
			
			finally
			{
		    System.out.print("\n$");
		    commands = keyboard.nextLine();
			}
			
		}
		
		
		/**
		 * Exit the system if given the "exit" command.
		 */
		keyboard.close();
		
		System.out.println();
        System.exit(0);		
	}

}
