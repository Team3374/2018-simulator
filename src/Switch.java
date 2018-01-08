import java.util.Random;

public class Switch implements GameElement {

	private Side litSide; 
	private Side unlitSide;
	private int litCount = 0; 
	private int unlitCount = 0; 
	
	private int myScore = 0; 
	
	private int boostUntilWhen = -1;
	private int forceUntilWhen = -1;
	
	public Switch() {
		Random r = new Random();
		int rr = r.nextInt(2);
		
		if (rr == 0) {
			litSide = Side.TOP;
			unlitSide = Side.BOTTOM;
		}
		else {
			litSide = Side.BOTTOM;
			unlitSide = Side.TOP;
		}
		
	}
	
	public void addBlock(Block b, Side s) { 
		
		if (litSide == s) {
			litCount++;
		}
		else {
			unlitCount++;
		}
	}
	
	@Override
	public void tick(GameState s) {
		
		if (litCount > unlitCount) {
			if (isBoosting()) {
				myScore += 2; 
			}
			else {
				myScore++; 
			}
		}
		
		if (isForcing()) {
			myScore++;
		}
				
	}

	
	@Override
	public void init() {
		
		
	}

	@Override
	public int getScore() {
		
		return myScore;
	}

	public String getPartialScore(Side s) {
		if (unlitSide == s) {
			return Integer.toString(unlitCount);
		}
		else {
			return Integer.toString(litCount);
		}
	}

	public Side litSide() {
		return litSide;
	}
	
	private boolean isBoosting() {
		return boostUntilWhen > 0 && GameState.currentTick() >= boostUntilWhen; 
	}
	
	private boolean isForcing() {
		return forceUntilWhen > 0 && GameState.currentTick() >= forceUntilWhen; 
	}
	
	public void setBoosting(int untilWhen) {
		boostUntilWhen = untilWhen; 
	}

	public void setForcing(int untilWhen) {
		forceUntilWhen = untilWhen; 
				
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
