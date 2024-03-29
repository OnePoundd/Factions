package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.MassiveCommand;

public class FactionsCommand extends MassiveCommand {
	public MPlayer msender;
	public Faction msenderFaction;

	public FactionsCommand() {
		setSetupEnabled(true);
	}

	public void senderFields(boolean set) {
		msender = (set ? MPlayer.get(sender) : null);
		msenderFaction = (set ? msender.getFaction() : null);
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * FactionsCommand.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */