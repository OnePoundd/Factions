package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CmdFactionsCastleSet extends FactionsCommand {
	public CmdFactionsCastleSet() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
		addParameter(TypeString.get(), "1/2");
	}

	public void perform() throws MassiveException {
		if (msender.getPlayer().hasPermission("factions.admin")) {
			String point = readArgAt(0).toString();
			if (point.equals("1")) {
				Location loc = msender.getPlayer().getLocation();
				MConf.get().setCastleBound1(PS.valueOf(loc));
				msender.message("§a§l(!)§7 Successfully set the first point of the castle!");
			} else if (point.equals("2")) {
				Location loc = msender.getPlayer().getLocation();
				MConf.get().setCastleBound2(PS.valueOf(loc));
				msender.message("§a§l(!)§7 Successfully set the second point of the castle!");
			} else {
				msender.message("§c§l(!)§7 Command usage /f castle set 1/2");
			}
		} else {
			msender.message("§c§l(!)§7 You do not have permission to use this command!");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsCastleSet.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */