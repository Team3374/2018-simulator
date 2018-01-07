import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

public class GameState {
	
	private static int runForSeconds = 0;
	private static ArrayList<GameElement> elems = null;
		
	protected static GameState instance = null;
	private int redScore;
	private int blueScore; 
	
	public static GameState getInstance() {
	      if(instance == null) {
	         instance = new GameState(135);
	      }
	      return instance;
	   }
	
	public int secondsLeft() {
		return runForSeconds;
	}
	
	protected GameState(int numSeconds) {
		runForSeconds = numSeconds;

		elems = new ArrayList<GameElement>();
		elems.add(new Scale());
		
		elems.add(new Switch()); //Red
		elems.add(new Switch()); //Blue
		
		elems.add(new Vault()); //Red
		elems.add(new Vault()); //Blue
		
		elems.add(new Hangar()); //Red
		elems.add(new Hangar()); //Blue
		
		
	}
	
	
	

	public void tick() {
				
		if (runForSeconds >= 0) {
			System.out.println("Ticking... " + runForSeconds);
			
			for (GameElement e: elems) {
				e.tick(this);
			}
			
			redScore = getRedSwitch().getScore() + 
							getScale().getRedScore() + 
							getRedVault().getScore() + 
							getRedHangar().getScore();
			
			blueScore = getBlueSwitch().getScore() + 
							getScale().getBlueScore()  + 
							getBlueVault().getScore()  + 
							getBlueHangar().getScore();
			
			
			runForSeconds--;
			
			return;
			
		}
	}
	
	public int getRedScore() {
		return redScore; 
	}
	
	public int getBlueScore() {
		return blueScore; 
	}
	
	public void reset(int runForSec) {
		redScore = 0; 
		blueScore = 0; 
		runForSeconds = runForSec;
		
		for (GameElement e: elems) {
			e.reset();
		}
		
	}

	public static Switch getRedSwitch() {
		return (Switch) elems.get(1);
	}
	
	public static Switch getBlueSwitch() {
		return (Switch) elems.get(2);
	}
	
	public static Scale getScale() {
		return ((Scale) elems.get(0));
	}
	
	public static Vault getRedVault() {
		return (Vault) elems.get(3); 
	}
	
	public static Vault getBlueVault() {
		return (Vault) elems.get(4); 
	}
	
	public static Hangar getRedHangar() {
		return (Hangar) elems.get(5); 
	}
	public static Hangar getBlueHangar() {
		return (Hangar) elems.get(6); 
	}
	public static int tenSeconds() {
		return runForSeconds - 10; 
	}
	
	public static int currentTick() {
		return runForSeconds; 
	}

		

}


