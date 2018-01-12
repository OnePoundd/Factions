package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.req.ReqHasFaction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.requirement.Requirement;

public class CmdFactionsLeave extends FactionsCommand {
	public CmdFactionsLeave() {
		addRequirements(new Requirement[] { ReqHasFaction.get() });
	}

	public void perform() {
		msender.leave();
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsLeave.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */