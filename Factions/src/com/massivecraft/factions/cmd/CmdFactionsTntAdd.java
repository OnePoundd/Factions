package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CmdFactionsTntAdd extends FactionsCommand {
	public CmdFactionsTntAdd() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
	}

	public void perform() {
		int level = msenderFaction.getTntUpgradeLevel();
		if (level == 0) {
			msender.msg("§c§l(!)§7 Your faction has not yet unlocked the tnt storage upgrade!");
		} else if (level > 0) {
			int tntInInventory = 0;
			ItemStack[] arrayOfItemStack;
			int j = (arrayOfItemStack = msender.getPlayer().getInventory().getContents()).length;
			for (int i = 0; i < j; i++) {
				ItemStack item = arrayOfItemStack[i];
				if ((item != null) && (item.getType().equals(Material.TNT))) {
					tntInInventory += item.getAmount();
				}
			}
			int maxTnt = msenderFaction.getTntUpgradeLevel() * 50000;
			if (msenderFaction.getTntCount() + tntInInventory > maxTnt) {
				msender.msg("§c§l(!)§7 Your faction's virtual tnt storage cannot fit your tnt in!");
			} else if (tntInInventory > 0) {
				msender.getPlayer().getInventory().remove(Material.TNT);
				msenderFaction.setTntCount(msenderFaction.getTntCount() + tntInInventory);
				msender.msg("§a§l(!)§7 Your tnt has been moved to the factions virtual storage!");
			} else {
				msender.msg("§c§l(!)§7 You must have tnt in your inventory to perform this command!");
			}
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsTntAdd.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */