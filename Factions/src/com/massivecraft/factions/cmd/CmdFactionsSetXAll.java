package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;

public abstract class CmdFactionsSetXAll extends CmdFactionsSetX {
	public CmdFactionsSetXAll(boolean claim) {
		super(claim);

		addParameter(TypeString.get(), "all|map");
		addParameter(TypeFaction.get(), "faction");
		if (claim) {
			addParameter(TypeFaction.get(), "newfaction");
			setFactionArgIndex(2);
		}
	}

	public Faction getOldFaction() throws MassiveException {
		return (Faction) readArgAt(1);
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsSetXAll.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */