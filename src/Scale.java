import java.util.Random;

public class Scale implements GameElement {
	
	private Side redSide = null; 
	private Side blueSide = null;
	
	private int blueCount = 0; 
	private int redCount = 0; 
	
	private int blueScore = 0; 
	private int redScore = 0; 

	private int boostUntilWhen = -1;
	private int forceUntilWhen = -1;
	private Side forcingFor = null; 
	
	public Scale() {
		Random r = new Random();
		int rr = r.nextInt(2);
		
		if (rr == 0) {
			redSide = Side.TOP;
			blueSide = Side.BOTTOM;
		}
		else {
			redSide = Side.BOTTOM;
			blueSide = Side.TOP;
		}
	}
	
	public void addBlock(Block b, Side s) {
		if (redSide == s) {
			redCount++;
		}
		else {
			blueCount++; 
		}
	}
		
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(GameState s) {
		int tickValue = 1; 
		
		if (isBoosting()) {
			tickValue = 2; 
		}
		else {
			tickValue = 1; 
		}
		
		if (isForcing(Side.TOP)) {
			if (redIsTop() ) {
				redScore += tickValue; 
			}
			else {
				blueScore += tickValue;
			}
			return;
		}
		
		if (isForcing(Side.BOTTOM)) {
			if (redIsTop() ) {
				blueScore += tickValue; 
			}
			else {
				redScore += tickValue;
			}
			return;
		}
		
		
		if (blueCount == redCount) {
			return;
		}
		else if (blueCount > redCount) {
			blueScore += tickValue; 
		}
		else {
			redScore += tickValue;
		}
	}

	@Override
	public int getScore() throws Exception {
		throw new Exception("use get<top|bottom>Score()"); 
	}
	
	public int getTopScore() {
		if (redSide == Side.TOP) {
			return redCount;
		}
		else {
			return blueCount; 
		}
	}
	
	public int getRedScore() {
		return redScore; 
	}
	
	public int getBlueScore() {
		return blueScore; 
	}
	
	public int getBottomScore() {
		if (redSide == Side.BOTTOM) {
			return redCount;
		}
		else {
			return blueCount; 
		}
	}

	public boolean redIsTop() {
		return redSide == Side.TOP;
	}
	
	private boolean isBoosting() {
		return boostUntilWhen > 0 && GameState.currentTick() >= boostUntilWhen; 
	}
	
	private boolean isForcing(Side s) {
		return forceUntilWhen > 0 && GameState.currentTick() >= forceUntilWhen && forcingFor == s; 
	}
	
	public void setBoosting(int untilWhen) {
		boostUntilWhen = untilWhen; 
	}

	public void setForcing(int untilWhen, Side s) {
		forceUntilWhen = untilWhen; 
		forcingFor = s; 		
	}
	
	public Side getRedSide() {
		return redIsTop() ? Side.TOP : Side.BOTTOM;
	}

	public Side getBlueSide() {
		return redIsTop() ? Side.BOTTOM : Side.TOP;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}


	
	
	
}
