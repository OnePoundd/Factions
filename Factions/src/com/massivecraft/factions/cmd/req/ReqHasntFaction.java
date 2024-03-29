package com.massivecraft.factions.cmd.req;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementAbstract;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.command.CommandSender;

public class ReqHasntFaction extends RequirementAbstract {
	private static final long serialVersionUID = 1L;
	private static ReqHasntFaction i = new ReqHasntFaction();

	public ReqHasntFaction() {
	}

	public static ReqHasntFaction get() {
		return i;
	}

	public boolean apply(CommandSender sender, MassiveCommand command) {
		if (MUtil.isntSender(sender)) {
			return true;
		}
		MPlayer mplayer = MPlayer.get(sender);
		return !mplayer.hasFaction();
	}

	public String createErrorMessage(CommandSender sender, MassiveCommand command) {
		return Txt.parse("<b>You must leave your current faction before you %s.", new Object[] { getDesc(command) });
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\req\
 * ReqHasntFaction.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */