
public class InvalidMoveException extends Exception
{
	InvalidMoveException(int validRemove)
	{
		super("\nInvalid move. You must remove between 1 and " 
				+ validRemove + " stones.");
	}

}
