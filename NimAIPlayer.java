import java.util.Random;

public class NimAIPlayer extends NimPlayer implements Testable
{
	public NimAIPlayer()
	{
		super();
	}
	
	public NimAIPlayer(String uName, String fName, String gName)
	{
		super(uName, fName, gName);
	}
	
	public int removeStone(int stoneNumber, int validRemove)
	{
		int k, remove;
		
		for (k=0; (k * (validRemove + 1) +1) <= stoneNumber; k++)
		{
			
		}
		
		k -= 1;
		
		if ((k * (validRemove + 1) +1) == stoneNumber)
		{
			Random a = new Random();
			remove = a.nextInt(validRemove) + 1;
		}
		
		else
		{
			remove = stoneNumber - (k * (validRemove + 1) +1);
		}
		
		return remove;
		
	}
	public String advancedMove(boolean[] available, String lastMove) 
	{
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
}
