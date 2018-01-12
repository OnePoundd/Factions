package net.OnePoundd.Patches;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Experience implements Listener {
	public Experience() {
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity Entity = event.getEntity();
		if ((Entity.getKiller() != null) && (Entity.getKiller().getType().equals(EntityType.PLAYER))) {
			MPlayer player = MPlayer.get(event.getEntity().getKiller());
			if (event.getEntity().getType().equals(EntityType.PLAYER)) {
				Player killed = (Player) event.getEntity();
				if (BoardColl.get().getFactionAt(PS.valueOf(event.getEntity().getLocation().getBlock())).getName()
						.equals("WarZone")) {
					player.addExperience(25);
				}
			} else if ((Entity.getType().equals(EntityType.ZOMBIE)) || (Entity.getType().equals(EntityType.SKELETON))) {
				if (Entity.getCustomName() != null) {
					if (Entity.getCustomName().equals("§cHyperion's Minion")) {
						player.addExperience(30);
					} else if (Entity.getCustomName().equals("§c§lHyperion's Disciple")) {
						player.addExperience(75);
					} else {
						player.addExperience(3);
					}
				} else {
					player.addExperience(3);
				}
			} else {
				player.addExperience(3);
			}
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\Experience.class
 * Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */