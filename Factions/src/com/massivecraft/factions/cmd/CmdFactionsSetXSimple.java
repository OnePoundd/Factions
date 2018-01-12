package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;

public abstract class CmdFactionsSetXSimple extends CmdFactionsSetX {
	public CmdFactionsSetXSimple(boolean claim) {
		super(claim);
		if (claim) {
			addParameter(TypeFaction.get(), "faction", "you");
			setFactionArgIndex(0);
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsSetXSimple.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */