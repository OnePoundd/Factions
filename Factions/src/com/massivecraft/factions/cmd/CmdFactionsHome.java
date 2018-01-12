package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.ps.PS;
import net.OnePoundd.Essentials.Teleport;

public class CmdFactionsHome extends FactionsCommandHome {
	public CmdFactionsHome() {
		addAliases(new String[] { "home" });

		addParameter(TypeFaction.get(), "faction", "you");

		addRequirements(new Requirement[] { RequirementHasPerm.get(Perm.HOME) });
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
	}

	public void perform() throws MassiveException {
		if (msenderFaction.hasHome()) {
			Teleport.tryTeleport(me, msenderFaction.getHome().asBukkitLocation());
		} else {
			msg("§c§l(!)§7 Your faction does not have a home, use /f sethome.");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsHome.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */