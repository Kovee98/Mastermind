package mastermind;

public class MasterMindDriver
{
	//entry point
	public static void main(String[] args)
	{
		try
		{
			MasterMind mm = new MasterMind(Integer.parseInt(args[0]));
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Need an integer.");
			MasterMind mm = new MasterMind(0);
		}
	}
}
   