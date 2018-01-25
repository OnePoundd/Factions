package com.massivecraft.factions.cmd;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;

import net.OnePoundd.Essentials.Teleport;

public class CmdFactionsAssist extends FactionsCommand{
	
	public CmdFactionsAssist() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
	}

	public void perform() throws MassiveException {
		if(msender.hasFaction()) {
			if(msenderFaction.getPlacedBanner() != null){
				msg("§6§l(!)§7 Wait 5 Seconds for teleport process to commence!");
				Player player = msender.getPlayer();
				Bukkit.getScheduler().runTaskLater(Factions.get(), new Runnable() {
					public void run() {
						Teleport.tryTeleport(player, MPlayer.get(player).getFaction().getPlacedBanner().getBlock().getLocation().add(0.5,0,0.5));
					}
				}, 20*5);
			}else {
				msg("§c§l(!)§7 Your faction has not got a banner placed!");
			}
		}else {
			msg("§c§l(!)§7 You must belong to a faction to teleport to a banner!");
		}
	}

}
