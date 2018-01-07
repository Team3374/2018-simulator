import javax.swing.JFrame;

public interface GameElement  {
	
	public void init();
	public void tick(GameState s); 
	public int getScore() throws Exception; 
	public void reset(); 
}
