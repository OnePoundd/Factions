package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import mkremins.fanciful.FancyMessage;

public class CmdFactionsFind extends FactionsCommand {
	public CmdFactionsFind() {
		addAliases(new String[] { "l", "list", "finder" });

		addParameter(Parameter.getPage());
	}

	public void perform() throws MassiveException {
		msg("§8§l§m---------------§7§l[ §dFaction Finder§7§l ]§8§l§m---------------");
		msg("§7A list of factions which are currently looking for more players!");
		for (Faction f : FactionColl.get().getAll()) {
			if (f.getRecruiting()) {
				if (f.getUrl() != null) {
					new FancyMessage("§b" + f.getName() + " §7- F Top Posisiton: §b" + f.getVictoryPoints()
							+ " §7, Click this message to apply!").link(f.getUrl()).send(sender);
				} else {
					msg("§b" + f.getName() + " §7- F Top Posisiton: §b" + f.getVictoryPoints()
							+ " §7, Message an officer to apply!");
				}
			}
		}
		msg("§8§l§m--------------------------------------------");
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsFind.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */