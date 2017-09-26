package mastermind;
import java.util.ArrayList;
import java.util.List;

class MasterMindAIConsistent implements MasterMindAI
{
	private MasterMind master_mind;
	private List<Guess> guesses;
	
	public MasterMindAIConsistent(MasterMind master_mind)
	{
		this.master_mind=master_mind;
		guesses=new ArrayList<Guess>();
	}
	
	public Guess nextGuess()
	{
		int guess_count=master_mind.getNumGuesses();
		
		//get the previous guesses
		if(guess_count!=guesses.size())
		{
			int difference=guess_count-guesses.size();
			
			for(int i=difference;i>0;i--)
			{
				Guess prev_guess=master_mind.getGuess((guess_count-i)+1);
				guesses.add(prev_guess);
			}
		}
		
		Guess rand=randGuess();
		
		if(guess_count==0)
		{
			guesses.add(rand);
			return rand;
		}
		
		int[] result=new int[2];
		Guess prev=guesses.get(guess_count-1);
		
		while(result[0]!=prev.getNumBlack() || result[1]!=prev.getNumWhite())
		{
			Guess curr=new Guess(guess_count+1);
			curr=randGuess();
			for(int i=0;i<guess_count;i++)
			{
				prev=guesses.get(i);
				result=master_mind.reportTestResult(curr,prev);
				if(result[0]!=prev.getNumBlack() || result[1]!=prev.getNumWhite())
				{
					break;
				}
			}
			rand=curr;
		}
		guesses.add(rand);
		return rand;
	}
	
	private Guess randGuess()
	{
		int guess_count=master_mind.getNumGuesses();
		util.Random random=util.Random.getRandomNumberGenerator();
		Guess rand_guess=new Guess(guess_count+1);
		for(int i=0;i<4;i++)
		{
			int rand_int=random.randomInt(1,7);
			rand_guess.addColor(rand_int);
		}
		return rand_guess;
	}
}