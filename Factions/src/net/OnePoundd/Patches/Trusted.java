package net.OnePoundd.Patches;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Trusted implements Listener {
	public Trusted() {
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Material material = event.getBlock().getType();
		if ((material.equals(Material.OBSIDIAN)) || (material.equals(Material.MOB_SPAWNER))
				|| (material.equals(Material.HOPPER)) || (material.equals(Material.CHEST))
				|| (material.equals(Material.TRAPPED_CHEST)) || (material.equals(Material.BEACON))) {
			MPlayer player = MPlayer.get(event.getPlayer());
			Faction faction = BoardColl.get().getFactionAt(PS.valueOf(event.getBlock().getLocation()));
			if ((!faction.getName().equalsIgnoreCase("wilderness")) && (faction.equals(player.getFaction()))
					&& (!MPerm.getPermTrusted().has(player, faction, true))) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Material material = event.getBlock().getType();
		if ((material.equals(Material.DISPENSER)) || (material.equals(Material.TNT))) {
			MPlayer player = MPlayer.get(event.getPlayer());
			Faction faction = BoardColl.get().getFactionAt(PS.valueOf(event.getBlock().getLocation()));
			if ((!faction.getName().equalsIgnoreCase("wilderness")) && (faction.equals(player.getFaction()))
					&& (!MPerm.getPermTrusted().has(player, faction, true))) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerInterract(PlayerInteractEvent event) {
		if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& (event.getMaterial().equals(Material.MONSTER_EGG))) {
			MPlayer player = MPlayer.get(event.getPlayer());
			Faction faction = BoardColl.get().getFactionAt(PS.valueOf(event.getClickedBlock().getLocation()));
			if ((!faction.getName().equalsIgnoreCase("wilderness")) && (faction.equals(player.getFaction()))
					&& (!MPerm.getPermTrusted().has(player, faction, true))) {
				event.setCancelled(true);
			}
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\Trusted.class Java
 * compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */