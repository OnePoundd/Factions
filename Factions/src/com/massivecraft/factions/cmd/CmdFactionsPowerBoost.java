package com.massivecraft.factions.cmd;

public class CmdFactionsPowerBoost extends FactionsCommand {
	public CmdFactionsPowerBoostPlayer cmdFactionsPowerBoostPlayer = new CmdFactionsPowerBoostPlayer();
	public CmdFactionsPowerBoostFaction cmdFactionsPowerBoostFaction = new CmdFactionsPowerBoostFaction();

	public CmdFactionsPowerBoost() {
		addChild(cmdFactionsPowerBoostPlayer);
		addChild(cmdFactionsPowerBoostFaction);
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsPowerBoost.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */