package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.req.ReqFactionHomesEnabled;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.massivecore.command.Visibility;
import com.massivecraft.massivecore.command.requirement.Requirement;

public class FactionsCommandHome extends FactionsCommand {
	public FactionsCommandHome() {
		addRequirements(new Requirement[] { ReqFactionHomesEnabled.get() });
	}

	public Visibility getVisibility() {
		return MConf.get().homesEnabled ? super.getVisibility() : Visibility.INVISIBLE;
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * FactionsCommandHome.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */