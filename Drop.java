package Miner;

import org.excobot.game.api.methods.tab.Inventory;
import org.excobot.game.api.wrappers.media.Item;

public class Drop extends Node {

	Item pickaxe = null;

	@Override
	public boolean isActive() {
		return Inventory.isFull();
	}

	@Override
	public void run() {
		Main.status = "Dropping ore";
		Inventory.dropAllExcept(Main.PICKAXES);
	}
}
