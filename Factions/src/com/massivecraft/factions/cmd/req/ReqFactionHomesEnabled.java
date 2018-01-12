package com.massivecraft.factions.cmd.req;

import com.massivecraft.factions.entity.MConf;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementAbstract;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.command.CommandSender;

public class ReqFactionHomesEnabled extends RequirementAbstract {
	private static final long serialVersionUID = 1L;
	private static ReqFactionHomesEnabled i = new ReqFactionHomesEnabled();

	public ReqFactionHomesEnabled() {
	}

	public static ReqFactionHomesEnabled get() {
		return i;
	}

	public boolean apply(CommandSender sender, MassiveCommand command) {
		return MConf.get().homesEnabled;
	}

	public String createErrorMessage(CommandSender sender, MassiveCommand command) {
		return Txt.parse("<b>Homes must be enabled on the server to %s.", new Object[] { getDesc(command) });
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\req\
 * ReqFactionHomesEnabled.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */