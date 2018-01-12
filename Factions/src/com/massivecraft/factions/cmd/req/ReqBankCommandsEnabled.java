package com.massivecraft.factions.cmd.req;

import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.integration.Econ;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementAbstract;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.command.CommandSender;

public class ReqBankCommandsEnabled extends RequirementAbstract {
	private static final long serialVersionUID = 1L;
	private static ReqBankCommandsEnabled i = new ReqBankCommandsEnabled();

	public ReqBankCommandsEnabled() {
	}

	public static ReqBankCommandsEnabled get() {
		return i;
	}

	public boolean apply(CommandSender sender, MassiveCommand command) {
		return (MConf.get().bankEnabled) && (Econ.isEnabled());
	}

	public String createErrorMessage(CommandSender sender, MassiveCommand command) {
		String what = !MConf.get().bankEnabled ? "banks" : "economy features";
		return Txt.parse("<b>Faction %s are disabled.", new Object[] { what });
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\req\
 * ReqBankCommandsEnabled.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */