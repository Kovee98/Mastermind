package mastermind;

class MasterMindAIState implements MasterMindState,MasterMindAI
{
	private MasterMind master_mind;
	private int ai;
	private MasterMindAI curr;
	private MasterMindAI[] ai_array;
	
	public MasterMindAIState(MasterMind master_mind)
	{
		this.master_mind=master_mind;
		ai=1;
		ai_array=new MasterMindAI[3];
		ai_array[0]=new MasterMindAIRandom(master_mind);
		ai_array[1]=new MasterMindAIConsistent(master_mind);
		ai_array[2]=new MasterMindAIMiniMax(master_mind);
		curr=ai_array[0];
	}
	
	public void changeAIState(int ai)
	{
		this.ai=ai;
		curr=ai_array[ai-1];
	}
	
	public Guess nextGuess()
	{
		Guess guess=curr.nextGuess();
		master_mind.addGuess(guess);
		return guess;
	}
	
	public void mouseClicked(int x_click, int y_click)
	{
		if(master_mind.isGameOver())
		{
			master_mind.changeState(master_mind.getGameOverState());
			return;
		}
		ai=master_mind.changeAI(x_click,y_click);
		
		if(ai==0)
		{
			master_mind.changeState(master_mind.getPlayerState());
			return;
		}
		else if(ai==1 && curr!=ai_array[0])
		{
			curr=ai_array[0];
			return;
		}
		else if(ai==2 && curr!=ai_array[1])
		{
			curr=ai_array[1];
			return;
		}
		else if(ai==3 && curr!=ai_array[2])
		{
			curr=ai_array[2];
			return;
		}
		nextGuess();
	}
}