package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeString;

public class CmdFactionsAnnounce extends FactionsCommand{

	//MPerm.getPermPerms().has(msender, faction, true)
	
	public CmdFactionsAnnounce() {
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
		addParameter(TypeString.get(), "message", true);
	}
	
	public void perform() throws MassiveException {
		if(msender.hasFaction() && MPerm.getPermAnnounce().has(msender, msenderFaction, true)) {
			String message = (String) readArg();
			msenderFaction.msg("§7§l[§a§l" + msenderFaction.getName() + "§7§;]§7§l " + message);
		}else {
			msg("§c§l(!)§7 Your faction does not permit you to broadcast messages!");
		}
	}
	
}
