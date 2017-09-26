package mastermind;

class MasterMindAIRandom implements MasterMindAI
{
	private MasterMind master_mind;
	
	MasterMindAIRandom(MasterMind master_mind)
	{
		this.master_mind=master_mind;
	}
	
	public Guess nextGuess()
	{
		util.Random random=util.Random.getRandomNumberGenerator();
		int index=master_mind.getNumGuesses();
		Guess rand_guess=new Guess(index+1);
		for(int i=0;i<4;i++)
		{
			int rand_int=random.randomInt(1,7);
			rand_guess.addColor(rand_int);
		}
		return rand_guess;
	}
}