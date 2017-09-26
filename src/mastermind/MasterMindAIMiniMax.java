package mastermind;
import java.util.ArrayList;
import java.util.List;

class MasterMindAIMiniMax implements MasterMindAI
{
	private MasterMind master_mind;
	private ArrayList<Guess> possible_guesses;
	private ArrayList<Guess> guesses;
	
	public MasterMindAIMiniMax(MasterMind master_mind)
	{
		int guess_count=1; //used to be 1
		this.master_mind=master_mind;
		possible_guesses=new ArrayList<Guess>();
		guesses=new ArrayList<Guess>();
		
		for(int i=1;i<=7;i++)
		{
			for(int j=1;j<=7;j++)
			{
				for(int k=1;k<=7;k++)
				{
					for(int l=1;l<=7;l++)
					{
						Guess guess=new Guess(guess_count);
						guess.addColor(i);
						guess.addColor(j);
						guess.addColor(k);
						guess.addColor(l);
						
						guesses.add(guess);
						possible_guesses.add(new Guess(guess));
					}
				}
			}
		}
	}
	
	public Guess firstGuess(Guess guess)
	{
		//first guess
		guess.addColor(1);
		guess.addColor(2);
		guess.addColor(3);
		guess.addColor(4);
		return guess;
	}
	
	public Guess finalGuess(Guess guess)
	{
		int guess_count=master_mind.getNumGuesses();
		Guess final_guess=new Guess(guess_count+1);
		
		List<Integer> id_list=guess.getGuessColorIDs();
		final_guess.addColor(id_list.get(0));
		final_guess.addColor(id_list.get(1));
		final_guess.addColor(id_list.get(2));
		final_guess.addColor(id_list.get(3));
		
		return final_guess;
	}
	
	public void removeGuesses()
	{
		int[] comp=new int[2];
		int guess_count=master_mind.getNumGuesses();
		
		Guess prev_guess=master_mind.getGuess(guess_count);
		
		int previous_black=prev_guess.getNumBlack();
		int previous_white=prev_guess.getNumWhite();
		
		for(int i=possible_guesses.size();i>0;i--)
		{
			Guess this_guess=possible_guesses.get(i-1);
			comp=master_mind.reportTestResult(this_guess,prev_guess);
			
			if(comp[0]!=previous_black || comp[1]!=previous_white)
			{
				possible_guesses.remove(i-1);
			}
		}
	}
	
	public int comparison(int index, int j, int k, Guess prev_guess)
	{
		int[] comp=new int[2];
		
		for(int o=0;o<possible_guesses.size();o++)
		{
			//get a comparison guess we've already made
			Guess new_guess=possible_guesses.get(o);
			
			//compare the two
			comp=master_mind.reportTestResult(new_guess,prev_guess);
			
			if(comp[0]!=j || comp[1]!=k)
			{
				index++;
			}
		}
		return index;
	}
	
	public Guess nextGuess()
	{
		int guess_count=master_mind.getNumGuesses();
		Guess guess=new Guess(guess_count+1);
		
		if(guess_count==0) return firstGuess(guess);
		
		removeGuesses();
		
		int min=2401;
		int max=0;
		int index=0;
		int[] comp=new int[2];
		
		for(int i=0;i<guesses.size();i++)
		{
			min=2401;
			Guess prev_guess=guesses.get(i);
			
			for(int j=0;j<=4;j++)	//black pegs
			{
				for(int k=0;k<=4-j;k++)		//white pegs
				{
					//try a new guess
					index=comparison(index,j,k,prev_guess);
					
					if(index<min && index!=0)
					{
						min=index;
					}
					
					index=0;
				}
			}
			
			if(min>max && i<possible_guesses.size())
			{
				max=min;
				guess=possible_guesses.get(i);
			}
		}
		return finalGuess(guess);
	}
}