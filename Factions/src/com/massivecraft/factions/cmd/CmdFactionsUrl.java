package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import java.util.List;

public class CmdFactionsUrl extends FactionsCommand {
	public CmdFactionsUrl() {
		addAliases(new String[] { "url" });

		addParameter(TypeString.get(), "url");
	}

	public void perform() throws MassiveException {
		if (MPerm.getPermURL().has(msender, msenderFaction, true)) {
			if (((String) getArgs().get(0)).contains("http://www.hyperionfactions.net/forums")) {
				msender.getFaction().setUrl((String) args.get(0));
				msg("§a§l(!)§7 Your faction's recruitment link has been updated! Players can now click to visit your recruitment thread on /f find!");
			} else {
				msg("§c§l(!)§7 You must link to a page on our forums!");
			}
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsUrl.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */