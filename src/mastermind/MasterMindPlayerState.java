package mastermind;

class MasterMindPlayerState implements MasterMindState
{
	private MasterMind master_mind;
	
	public MasterMindPlayerState(MasterMind master_mind)
	{
		this.master_mind=master_mind;
	}
	
	public void mouseClicked(int x_click, int y_click)
	{
		int count=master_mind.getNumGuesses();
		
		if (master_mind.isGameOver())
		{
			master_mind.changeState(master_mind.getGameOverState());
			return;
		}
		
		int state_int=master_mind.changeAI(x_click,y_click);
		
		//keeping track of count lets us not switch states during a players guess
		if(state_int>0 && count==1)
		{
			count=0;
			MasterMindState state=master_mind.getAIState();
			master_mind.changeState(state);
			((MasterMindAIState)state).changeAIState(state_int);
		}
		
		int color_selected=master_mind.isColorSelected(x_click,y_click);
		if(color_selected>0)
		{
			master_mind.addGuess(color_selected);
		}
	}
}