package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CmdFactionsTntFill extends FactionsCommand {
	public CmdFactionsTntFill() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });

		addParameter(TypeInteger.get(), "radius");
		addParameter(TypeInteger.get(), "amount");
	}

	public void perform() throws MassiveException {
		if (msender.getSkillTntFill()) {
			if (msenderFaction.getTntCount() == 0) {
				msender.msg("§c§l(!)§7 Your faction does not have any tnt stored!");
			} else {
				int radius = ((Integer) readArgAt(0)).intValue();
				int amount = ((Integer) readArgAt(1)).intValue();
				if (radius > 50) {
					msender.msg("§c§l(!)§7 The maximum radius is 50 blocks!");
				} else {
					List<Block> dispensers = getNearbyDispensers(msender.getPlayer().getLocation(), radius);
					if (msenderFaction.getTntCount() >= dispensers.size() * amount) {
						if (dispensers.size() > 0) {
							msenderFaction.setTntCount(msenderFaction.getTntCount() - dispensers.size() * amount);
							int dispensersFilled = 0;
							int tntReturned = 0;
							for (Block block : dispensers) {
								Dispenser dispenser = (Dispenser) block.getState();
								Inventory inv = dispenser.getInventory();
								HashMap<Integer, ItemStack> returned = inv
										.addItem(new ItemStack[] { new ItemStack(Material.TNT, amount) });
								if (returned.isEmpty()) {
									dispensersFilled++;
								} else {
									for (Iterator localIterator2 = returned.keySet().iterator(); localIterator2
											.hasNext();) {
										int num = ((Integer) localIterator2.next()).intValue();
										if (((ItemStack) returned.get(Integer.valueOf(num))).getType()
												.equals(Material.TNT)) {
											tntReturned += ((ItemStack) returned.get(Integer.valueOf(num))).getAmount();
											dispensersFilled++;
										}
									}
								}
							}
							msender.message(
									"§a§l(!)§7 §l" + dispensersFilled + " §7dispenser(s) were filled with tnt!");
							msenderFaction.setTntCount(msenderFaction.getTntCount() + tntReturned);
						} else {
							msender.msg("§c§l(!)§7 There are no dispensers within that radius!");
						}
					} else {
						msender.msg("§c§l(!)§7 Your faction does not have enough tnt stored to do that!");
					}
				}
			}
		} else {
			msender.msg("§c§l(!)§7 You must unlock the tnt fill skill!");
		}
	}

	public static List<Block> getNearbyDispensers(Location location, int radius) {
		List<Block> blocks = new ArrayList();
		for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
			for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
				for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
					Block block = location.getWorld().getBlockAt(x, y, z);
					if (block.getType().equals(Material.DISPENSER)) {
						blocks.add(block);
					}
				}
			}
		}
		return blocks;
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsTntFill.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */