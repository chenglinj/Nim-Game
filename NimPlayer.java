/***************************************************************************
 *
 *   File        : NimPlayer.java
 *   Student ID  : 909134
 *   Name        : Chenglin Jing
 *   			   
 ***************************************************************************/

import java.io.*;

/**
 * This class stores all the information and statistics of players.
 * Along with the basic operation of the player information and statistics
 * @author Chenglin Jing
 *
 */
public class NimPlayer 
{
	private String userName, givenName, familyName;
	private int gamePlayed, gameWon;
	private double winRatio = 0;
	
	/**
	 * The default constructor.
	 */
	public NimPlayer()
	{
		
	}
	
	/**
	 * Constructor to create a new player with basic information.
	 * @param uName
	 * @param fName
	 * @param gName
	 */
	public NimPlayer(String uName, String fName, String gName)
	{
		userName = uName;
		givenName = gName;
		familyName = fName;
		gamePlayed = 0;
		gameWon = 0;
	}
	
	
	public String getUserName()
	{
		return userName;
	}
	
	
	public String getGivenName()
	{
		return givenName;
	}
	

	public String getFamilyName()
	{
		return familyName;
	}
	
	
	public void setGivenName(String name)
	{
		givenName = name;
	}
	
	
	public void setFamilyName(String name)
	{
		familyName = name;
	}
	
	
	public int getGamePlayed()
	{
		return gamePlayed;
	}
	
	
	public int getGameWon()
	{
		return gameWon;
	}
	
	
	public double getWinRatio()
	{
		return winRatio;
	}
	
	
	public void updateGamePlayed()
	{
		gamePlayed++;
	}
	
	
	public void updateGameWon()
	{
		gameWon++;
	}
	
	
	/**
	 * Calculate the winning ratio after each game.
	 */
	public void updateWinRatio()
	{
		winRatio = (gamePlayed == 0)? 0: (double) gameWon*100/gamePlayed;
	}
	
	/**
	 * Allow users to reset statistics.
	 */
	public void resetStats()
	{
		gamePlayed = 0;
		gameWon = 0;
		winRatio = 0;
	}
	
	
	/**
	 * Display the basic information and statistics of each player.
	 */
	public void displayPlayer()
	{
		System.out.println(userName + "," + givenName + "," + 
	    familyName + "," + gamePlayed + " games," + gameWon + " wins");
	}
	
	/**
	 * Display the winning ratio, games played and full name for each player.
	 */
	public void displayRanking()
	{
		System.out.printf("%-5s| %02d games | %s %s",
				Math.round(winRatio) + "%", gamePlayed, givenName, familyName);
		System.out.println();
	}
	
   }
