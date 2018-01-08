public class Vault implements GameElement {
	
	private int myScore = 0; 
	private int boostCount = 0; 
	private int forceCount = 0; 
	private int levitateCount = 0; 
	
	@Override
	public void tick(GameState s) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getScore() {
		return boostCount * 5 + forceCount * 5 + levitateCount * 5; 
	}

	public void addBlock(VaultType powerUp)  {
				
		switch(powerUp) {
			case BOOST:
				boostCount++;
				break;
			case FORCE:
				forceCount++;
				break;
			case LEVITATE:
				levitateCount++;
				break;
			default:

		}
	}

	public int getBoostCount() {
		return boostCount; 
	}
	
	public int getForceCount() {
		return forceCount; 
	}
	
	public int getLevitateCount() {
		return levitateCount; 
	}


	@Override
	public void reset() {
		myScore = 0; 
		boostCount = 0; 
		forceCount = 0; 
		levitateCount = 0; 
		
	}

	

}
