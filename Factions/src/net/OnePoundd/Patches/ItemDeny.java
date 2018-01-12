package net.OnePoundd.Patches;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class ItemDeny implements Listener {
	public ItemDeny() {
	}

	@EventHandler
	public void onBrewEvent(BrewEvent event) {
		if (event.getContents().contains(Material.FERMENTED_SPIDER_EYE)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onCraftEvent(PrepareItemCraftEvent event) {
		try {
			Material toCraft = event.getRecipe().getResult().getType();
			if (toCraft.equals(Material.HOPPER)) {
				event.getInventory().setResult(new ItemStack(Material.AIR));
			}
		} catch (NullPointerException localNullPointerException) {
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\ItemDeny.class Java
 * compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */