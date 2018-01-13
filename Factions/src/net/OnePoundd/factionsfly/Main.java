package net.OnePoundd.factionsfly;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Main implements Listener {
	public Main() {
	}

	public boolean canPlayerFly(MPlayer player) {
		Faction factionAt = BoardColl.get().getFactionAt(PS.valueOf(player.getPlayer().getLocation()));
		if ((player.getSkillFlight()) && (!isEnemyNearby(player.getPlayer(), 50, 256, 50))) {
			if (factionAt.getName().equals("Wilderness")) {
				if (player.getPlayer().hasPermission("factions.flywilderness")) {
					return true;
				}
				return false;
			}
			if (factionAt.getName().equals("SafeZone")) {
				if (player.getPlayer().hasPermission("factions.flysafezone")) {
					return true;
				}
				return false;
			}
			if (MPerm.getPermFly().has(player, factionAt, false)) {
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean isEnemyNearby(Player player, int x, int y, int z) {
		MPlayer mPlayer = MPlayer.get(player);
		List<Entity> entityList = player.getNearbyEntities(x, y, z);
		for (Entity entity : entityList) {
			if (entity.getType().equals(EntityType.PLAYER)) {
				try {
					MPlayer mPlayerEntity = MPlayer.get(entity);
					if ((mPlayer.getRelationTo(mPlayerEntity).equals(Rel.ENEMY))
							|| (mPlayer.getRelationTo(mPlayerEntity).equals(Rel.NEUTRAL))) {
						if (mPlayerEntity.getStealth()) {
							return false;
						}
						return true;
					}
				} catch (Exception localException) {
				}
			}
		}
		return false;
	}

	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		MPlayer mplayer = MPlayer.get(player);
		if ((player.getGameMode().equals(GameMode.SURVIVAL)) && (mplayer.getMPlayerFlight())) {
			if (!canPlayerFly(mplayer)) {
				player.sendMessage("§c§l(!)§7 Your flight has been disabled!");
				mplayer.setMPlayerFlight(false);
				mplayer.setNoFallDamageStartTimeMillis(System.currentTimeMillis());
				player.setAllowFlight(false);
			}
		} else if ((player.getGameMode().equals(GameMode.SURVIVAL)) && (!mplayer.getMPlayerFlight())
				&& (canPlayerFly(mplayer))) {
			player.sendMessage("§a§l(!)§7 Your flight has been enabled!");
			mplayer.setMPlayerFlight(true);
			player.setAllowFlight(true);
		}
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		MPlayer player = MPlayer.get(event.getPlayer());
		player.setMPlayerFlight(false);
	}

	@EventHandler
	public void entityDamageEvent(EntityDamageEvent event) {
		if ((event.getEntity().getType().equals(EntityType.PLAYER))
				&& (event.getCause().equals(EntityDamageEvent.DamageCause.FALL))) {
			if (MPlayer.get((Player) event.getEntity()).getNoFallDamage()) {
				event.setCancelled(true);
			} else if (MPlayer.get((Player) event.getEntity()).getMPlayerFlight()) {
				event.setCancelled(true);
			}
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\factionsfly\Main.class Java
 * compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */