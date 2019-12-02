import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer
{
	public NimHumanPlayer()
	{
		super();
	}
	
	public NimHumanPlayer(String uName, String fName, String gName)
	{
		super(uName, fName, gName);
	}
	
	public int removeStone(int validRemove, Scanner keyboard)
	{
		try
		{
		int remove = keyboard.nextInt();
		
		if ((remove<1) || (remove>validRemove))
		{
			throw new InvalidMoveException(validRemove);
		}
		else
		{
			return remove;
		}
		}
		
		catch (InvalidMoveException e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
	}
}
