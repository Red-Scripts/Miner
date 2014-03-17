package Miner;

import org.excobot.game.api.methods.media.animable.GameObjects;
import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.methods.scene.Camera;
import org.excobot.game.api.methods.scene.Movement;
import org.excobot.game.api.methods.tab.Inventory;
import org.excobot.game.api.util.Time;
import org.excobot.game.api.wrappers.media.animable.object.GameObject;

public class Mine extends Node {

	GameObject rock = null;

	@Override
	public boolean isActive() {
		rock = GameObjects.getNearest(Main.rockID);
		return !Inventory.isFull() && rock != null;
	}

	@Override
	public void run() {
		if (rock != null) {
			if (!rock.isOnScreen()) {
				if (Players.getLocal().getLocation().distance(rock.getLocation()) > 5) {
					Main.status = "Walking to rock";
					Movement.walkTileMM(rock.getLocation());
				} else {
					Main.status = "Turning Camera";
					Camera.turnTo(rock.getLocation());
				}
			}
			if (rock.interact("Mine")) {
				Main.status = "Mining Rock";
				Time.sleep(800, 1600);
				for (int i = 0; (i < 30) && (isBusy()); i++) {
					Time.sleep(200, 300);
				}
			}
		}
	}

	private boolean isBusy() {
		return Players.getLocal().getAnimation() != -1
				|| Players.getLocal().isMoving();
	}

}
