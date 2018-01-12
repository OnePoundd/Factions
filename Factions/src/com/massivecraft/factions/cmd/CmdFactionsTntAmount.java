package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;

public class CmdFactionsTntAmount extends FactionsCommand {
	public CmdFactionsTntAmount() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
	}

	public void perform() throws MassiveException {
		int level = msenderFaction.getTntUpgradeLevel();
		if (level == 0) {
			msender.msg("§c§l(!)§7 Your faction has not yet unlocked the tnt storage upgrade!");
		} else if ((level > 0) && (MPerm.getPermTntView().has(msender, msenderFaction, true))) {
			msender.msg(
					"§b§l(!)§7 Your faction currently has §l" + msenderFaction.getTntCount() + "§7 tnt stored!");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsTntAmount.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */