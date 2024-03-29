package com.massivecraft.factions.cmd.req;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementAbstract;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.command.CommandSender;

public class ReqRoleIsAtLeast extends RequirementAbstract {
	private static final long serialVersionUID = 1L;
	private final Rel rel;

	public Rel getRel() {
		return rel;
	}

	public static ReqRoleIsAtLeast get(Rel rel) {
		return new ReqRoleIsAtLeast(rel);
	}

	private ReqRoleIsAtLeast(Rel rel) {
		this.rel = rel;
	}

	public boolean apply(CommandSender sender, MassiveCommand command) {
		if (MUtil.isntSender(sender)) {
			return false;
		}
		MPlayer mplayer = MPlayer.get(sender);
		return mplayer.getRole().isAtLeast(getRel());
	}

	public String createErrorMessage(CommandSender sender, MassiveCommand command) {
		return Txt.parse("<b>You must be <h>%s <b>or higher to %s.",
				new Object[] { Txt.getNicedEnum(getRel()), getDesc(command) });
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\req\
 * ReqRoleIsAtLeast.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */