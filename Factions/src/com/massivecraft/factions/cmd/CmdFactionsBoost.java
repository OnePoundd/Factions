package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import java.io.PrintStream;
import org.bukkit.command.CommandSender;

public class CmdFactionsBoost extends FactionsCommand {
	public CmdFactionsBoost() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
	}

	public void perform() {
		double boost = 1.0D;

		msg("§8§l§m-----------------§7§l[ §dMcMMO XP Boost§7§l ]§8§l§m-----------------");
		if (sender.hasPermission("mcmmo.boost.100")) {
			boost += 1.0D;
		} else if (sender.hasPermission("mcmmo.boost.50")) {
			boost += 0.5D;
		}
		msg("§f> §7Your rank increases your mcmmo xp gains by §b" + (boost - 1.0D) + "§7x!");

		int upgradeLevel = msenderFaction.getMcmmoUpgradeLevel();
		double factionUpgradeMultiplier = 0.0D;
		if (upgradeLevel == 1) {
			boost += 0.1D;
			factionUpgradeMultiplier = 0.1D;
		} else if (upgradeLevel == 2) {
			boost += 0.2D;
			factionUpgradeMultiplier = 0.2D;
		} else if (upgradeLevel == 3) {
			boost += 0.3D;
			factionUpgradeMultiplier = 0.3D;
		}
		msg("§f> §7Your faction upgrade increases your mcmmo xp gains by §b" + factionUpgradeMultiplier + "§7x!");

		double factionBoost = msenderFaction.getMcmmoBoost();
		if ((factionBoost > 0.0D) && (System.currentTimeMillis() < msenderFaction.getBoostEndMillis())) {
			boost += factionBoost;
			msg("§f> §7Your faction booster increases your mcmmo xp gains by §b" + (factionBoost + 1.0D) + "§7x!");
		}
		System.out.println("FACTION END MILLIS " + msenderFaction.getBoostEndMillis());

		double playerBoost = msender.getBoost();
		if ((playerBoost > 0.0D) && (System.currentTimeMillis() < msender.getBoostEndMillis())) {
			boost += playerBoost;
			msg("§f> §7Your personal booster increases your mcmmo xp gains by §b" + (playerBoost + 1.0D) + "§7x!");
		}
		msg("§6> §7Your total mcmmo gain is multiplied by §b" + boost + "§7x!");
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsBoost.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */