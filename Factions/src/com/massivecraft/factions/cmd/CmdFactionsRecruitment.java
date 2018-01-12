package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;

public class CmdFactionsRecruitment extends FactionsCommand {
	public CmdFactionsRecruitment() {
		addAliases(new String[] { "recruiting", "r" });
	}

	public void perform() throws MassiveException {
		if (MPerm.getPermRecruitment().has(msender, msenderFaction, true)) {
			if (msender.getFaction().getRecruiting()) {
				msg("§c§l(!)§7 Your faction's recruitment status is now marked as closed!");
				msender.getFaction().setRecruiting(false);
			} else {
				msg("§a§l(!)§7 You have opened recruitment for your faction! Your faction will now be displayed on /f find so that new players can find you. Run /f url to add a link to a recruitment post on our forums, otherwise they will be prompted to message you instead!");

				msender.getFaction().setRecruiting(true);
			}
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsRecruitment.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */