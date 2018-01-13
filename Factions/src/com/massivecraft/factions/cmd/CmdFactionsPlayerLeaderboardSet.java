package com.massivecraft.factions.cmd;

import org.bukkit.Location;

import com.massivecraft.factions.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.ps.PS;

public class CmdFactionsPlayerLeaderboardSet extends FactionsCommand{
	
	public CmdFactionsPlayerLeaderboardSet() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
		addParameter(TypeString.get(), "1/2/3");
	}

	public void perform() throws MassiveException {
		if (msender.getPlayer().hasPermission("server.admin")) {
			String point = readArgAt(0).toString();
			if (point.equals("1")) {
				Location loc = msender.getPlayer().getLocation();
				MConf.get().WeeklyPlayer1Location = PS.valueOf(loc);
				msender.message("§a§l(!)§7 Successfully set the first leaderboard location!");
			} else if (point.equals("2")) {
				Location loc = msender.getPlayer().getLocation();
				MConf.get().WeeklyPlayer2Location = PS.valueOf(loc);
				msender.message("§a§l(!)§7 Successfully set the second leaderboard location!");
			} else if (point.equals("3")) {
				Location loc = msender.getPlayer().getLocation();
				MConf.get().WeeklyPlayer3Location = PS.valueOf(loc);
				msender.message("§a§l(!)§7 Successfully set the third leaderboard location!");
			} else {
				msender.message("§c§l(!)§7 Command usage /f playerleaderboardset 1/2/3");
			}
		} else {
			msender.message("§c§l(!)§7 You do not have permission to use this command!");
		}
	}

}
