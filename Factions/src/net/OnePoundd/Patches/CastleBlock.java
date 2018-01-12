package net.OnePoundd.Patches;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class CastleBlock {
	Block block;
	Location location;
	int durability;

	public CastleBlock(Block b, int d) {
		block = b;
		durability = d;
		location = block.getLocation();
	}

	protected int getDurability() {
		return durability;
	}

	protected void setDurability(int newDurability) {
		durability = newDurability;
	}

	protected Location getLocation() {
		return location;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\CastleBlock.class
 * Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */