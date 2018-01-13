package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CmdFactionsTntTake extends FactionsCommand {
	public CmdFactionsTntTake() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
		addParameter(TypeInteger.get(), "amount");
	}

	public void perform() throws MassiveException {
		int level = msenderFaction.getTntUpgradeLevel();
		if (level == 0) {
			msender.msg("§c§l(!)§7 Your faction has not yet unlocked the tnt storage upgrade!");
		} else if ((level > 0) && (MPerm.getPermTntTake().has(msender, msenderFaction, true))) {
			int amountToTake = ((Integer) readArgAt(0)).intValue();
			if (msenderFaction.getTntCount() - amountToTake >= 0) {
				Inventory inv = msender.getPlayer().getInventory();
				int emptyslots = 0;
				for (int i = 0; i < inv.getSize(); i++) {
					if ((inv.getItem(i) == null) || (inv.getItem(i).getType().equals(Material.AIR))) {
						emptyslots++;
					}
				}
				if (emptyslots * 64 >= amountToTake) {
					msenderFaction.setTntCount(msenderFaction.getTntCount() - amountToTake);
					int amountToAddToInventory = amountToTake;
					while (amountToAddToInventory > 0) {
						if (amountToAddToInventory >= 64) {
							msender.getPlayer().getInventory()
									.addItem(new ItemStack[] { new ItemStack(Material.TNT, 64) });
							amountToAddToInventory -= 64;
						} else {
							msender.getPlayer().getInventory()
									.addItem(new ItemStack[] { new ItemStack(Material.TNT, amountToAddToInventory) });
							amountToAddToInventory = 0;
						}
					}
					msender.msg("§a§l(!)§7 Successfully withdrew §l" + amountToTake
							+ " §7tnt from your factions storage!");
				} else {
					msender.msg("§c§l(!)§7 You do not have enough inventory space to do that!");
				}
			} else {
				msender.msg("§c§l(!)§7 Your faction does not have that much tnt stored!");
			}
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsTntTake.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */