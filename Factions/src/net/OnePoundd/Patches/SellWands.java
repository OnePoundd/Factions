package net.OnePoundd.Patches;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class SellWands implements Listener {
	public SellWands() {
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && (event.getItem() != null)
				&& (event.getItem().hasItemMeta()) && (event.getItem().getItemMeta().getDisplayName() != null)
				&& (event.getItem().getItemMeta().getDisplayName().equals("§c§lSell Wand"))
				&& ((event.getClickedBlock().getType().equals(Material.CHEST))
						|| (event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)))) {
			if (BoardColl.get().getFactionAt(PS.valueOf(event.getClickedBlock().getLocation()))
					.equals(MPlayer.get(event.getPlayer()).getFaction())) {
				Chest chest = (Chest) event.getClickedBlock().getState();
				ItemStack[] chestcontents = chest.getInventory().getContents();
				ItemStack[] arrayOfItemStack1;
				int j = (arrayOfItemStack1 = chestcontents).length;
				for (int i = 0; i < j; i++) {
					ItemStack item = arrayOfItemStack1[i];
					if ((event.getPlayer().getInventory().firstEmpty() != -1) && (item != null)) {
						event.getPlayer().getInventory().addItem(new ItemStack[] { item });
						chest.getInventory().remove(item);
					}
				}
				event.getPlayer().performCommand("sell all");
				event.setCancelled(true);
			} else {
				event.getPlayer().sendMessage("§c§l(!)§7 You cannot use sell wands on other faction's chests!");
			}
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\SellWands.class
 * Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */