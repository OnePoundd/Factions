package net.OnePoundd.Patches;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class McmmoBoosters implements Listener {
	public McmmoBoosters() {
	}

	@EventHandler
	public void onMcMMOGainEvent(McMMOPlayerXpGainEvent event) {
		double boost = 1.0D;
		Player player = event.getPlayer();
		MPlayer mPlayer = MPlayer.get(player);
		if (player.hasPermission("mcmmo.boost.100")) {
			boost += 1.0D;
		} else if (player.hasPermission("mcmmo.boost.50")) {
			boost += 0.5D;
		}
		int upgradeLevel = mPlayer.getFaction().getMcmmoUpgradeLevel();
		if (upgradeLevel == 1) {
			boost += 0.1D;
		} else if (upgradeLevel == 2) {
			boost += 0.2D;
		} else if (upgradeLevel == 3) {
			boost += 0.3D;
		}
		double factionBoost = mPlayer.getFaction().getMcmmoBoost();
		if ((factionBoost > 0.0D) && (System.currentTimeMillis() < mPlayer.getFaction().getBoostEndMillis())) {
			boost += factionBoost;
		}
		double playerBoost = mPlayer.getBoost();
		if ((playerBoost > 0.0D) && (System.currentTimeMillis() < mPlayer.getBoostEndMillis())) {
			boost += playerBoost;
		}
		if ((event.getSkill().equals(SkillType.FISHING)) && (BoardColl.get()
				.getFactionAt(PS.valueOf(player.getLocation())).getName().equalsIgnoreCase("Safezone"))) {
			boost += 0.5D;
		}
		event.setRawXpGained((float) (event.getRawXpGained() * boost));
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\McmmoBoosters.class
 * Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */