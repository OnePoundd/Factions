package com.massivecraft.factions.cmd;

import org.bukkit.Location;

import com.massivecraft.factions.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.ps.PS;

public class CmdFactionsWeeklyTop extends FactionsCommand{
	
	public CmdFactionsWeeklyTop() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
	}

	public void perform() throws MassiveException {
		if (msender.getPlayer().hasPermission("server.admin")) {
			Location loc = msender.getPlayer().getLocation();
			MConf.get().WeeklyPlayer1Location = PS.valueOf(loc);
			msender.message("§a§l(!)§7 Successfully set the location for the player of the week NPC!");
		} else {
			msender.message("§c§l(!)§7 You do not have permission to use this command!");
		}
	}

}
