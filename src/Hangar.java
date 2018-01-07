import javax.swing.JFrame;

public class Hangar implements GameElement {
	
	private int myScore = 0; 
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(GameState s) {
				
	}

	@Override
	public int getScore()  {
		// TODO Auto-generated method stub
		return myScore;
	}

	public void setLevitating() {
		myScore += 30; 		
	}

	@Override
	public void reset() {
		myScore = 0; 
		
	}

}
