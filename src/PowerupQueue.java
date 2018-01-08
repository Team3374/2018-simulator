import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PowerupQueue {
	
	
	private int queueCount = 0;
	private int lastInsert;
	
	public void addPowerUp(Team team, VaultType type, int modifier) {

		if (queueCount == 0) {
			lastInsert = GameState.currentTick();
			queueCount++;
			Switch gameSwitch = (team == Team.BLUE ? GameState.getBlueSwitch() : GameState.getRedSwitch());
			Side side = (team == Team.BLUE ? GameState.getScale().getBlueSide() : GameState.getScale().getRedSide());
			Vault vault = (team == Team.BLUE ? GameState.getBlueVault() : GameState.getRedVault());
			if (type == VaultType.FORCE) {
				if (modifier == 1) {
					
					gameSwitch.setForcing(GameState.tenSeconds());
				}

				if (modifier == 2) {
					GameState.getScale().setForcing(GameState.tenSeconds(), side);
				}

				if (modifier == 3) {
					GameState.getScale().setForcing(GameState.tenSeconds(), side);
					gameSwitch.setForcing(GameState.tenSeconds());
				}

			} else if (type == VaultType.BOOST) {
				if (modifier == 1) {
					gameSwitch.setBoosting(GameState.tenSeconds());
				}

				if (modifier == 2) {
					GameState.getScale().setBoosting(GameState.tenSeconds());
				}

				if (modifier == 3) {
					GameState.getScale().setBoosting(GameState.tenSeconds());
					gameSwitch.setBoosting(GameState.tenSeconds());
				}

			}
			//decrement the queue count after 10 seconds
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					queueCount--;
				}
			};
			timer.schedule(task, 10000);
		} else {
			//run this power up at a later time
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					addPowerUp(team, type, modifier);
				}
			};
			//-500 is to make sure it starts on the next tick
			timer.schedule(task, queueCount * 10000 - 1000 * (lastInsert - GameState.currentTick()) - 500);
		}

	}

}
