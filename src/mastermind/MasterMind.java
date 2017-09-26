package mastermind;

import java.awt.Graphics;
import java.awt.Image;

import gui.Drawable;
import util.Random;

class MasterMind implements Drawable
{
	private Image board_img;
	private Guesses player_guesses;
	
	private MasterMindState ai_state;
	private MasterMindState player_state;
	private MasterMindState game_over_state;
	private MasterMindState current_state;
	
	public void changeState(MasterMindState current_state)
	{
		this.current_state=current_state;
	}
	
	public MasterMindState getAIState()
	{
		return ai_state;
	}
	
	public MasterMindState getPlayerState()
	{
		return player_state;
	}
	
	public MasterMindState getGameOverState()
	{
		return game_over_state;
	}
	
	public MasterMindState getCurrentState()
	{
		return current_state;
	}
	
	public MasterMind(int state)
   {
	   ai_state=new MasterMindAIState(this);
	   player_state=new MasterMindPlayerState(this);
	   game_over_state=new MasterMindGameOverState(this);
	   // current_state=player_state;
	   if(state==0)
	   {
		   current_state=player_state;
	   }
	   else if(state>=1)
	   {
		   current_state=ai_state;
	   }
	   
	  board_img = gui.ImageLoader.getImageLoader().getImage("images/mastermind_board.jpg");
      player_guesses = new Guesses();

      gui.BasicGUI mm_gui = new gui.BasicGUI(457, 690, "Master Mind", this, null);
      mm_gui.setVisible(true);
   }
   
   public void mouseClicked(int x, int y) 
   {
		current_state.mouseClicked(x,y);
   }
	
	public void draw(Graphics g, int width, int height)
   {
		g.drawImage(board_img, 0, 0, null);
		player_guesses.draw(g);
   }
   
   public boolean isPlayerGuessComplete()
   {
	   return player_guesses.isPlayerGuessComplete();
   }

   //report result when comparing two guesses to each other (first parameter tested against the second parameter)
   public int[] reportTestResult(Guess one, Guess two)
   {
		assert (one != null && two != null) : "Guess is null.";
		return one.reportResult(two);
   }
   
   public Guess getGuess(int guess_num)
   {
		assert guess_num >= 1 : "Invalid guess_num";
		return player_guesses.getGuess(guess_num);
   }
   
   public int getNumGuesses()
   {
	   return player_guesses.getNumGuesses();
   }
   
   public boolean isGameOver()
   {
	   return player_guesses.isGameOver();
   }
   
   public int isColorSelected(int x, int y)
   {
	   return player_guesses.isColorSelected(x, y);
   }
   
   public void addGuess(int color)
   {
	   player_guesses.addGuess(color);
   }
   
   public void addGuess(Guess ai_guess)
   {
	   player_guesses.addGuess(ai_guess);
   }
   
   
   public int changeAI(int x, int y)
   {
	   int w_half = 12;
	   int h_half = 13;
	   
	   int center_x = 430;
	   
	   int center_0_y = 542;
	   int center_1_y = 571;
	   int center_2_y = 602;
	   int center_3_y = 630;
	   
	   if (Math.abs(x-center_x) < w_half)
	   {
		   if(Math.abs(y-center_0_y) < h_half)
		   {
			   return 0;
		   }
		   
		   else if(Math.abs(y-center_1_y) < h_half)
		   {
			   return 1;
		   }
		   
		   else if(Math.abs(y-center_2_y) < h_half)
		   {
			   return 2;
		   }
		   
		   else if(Math.abs(y-center_3_y) < h_half)
		   {
			   return 3;
		   }
	   }
	   
	   return -1;
   }
  
	public void keyPressed(char key)
   {
   }
}
