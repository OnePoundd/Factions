package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.entity.Player;

public class CmdFactionsSethome extends FactionsCommandHome {
	public CmdFactionsSethome() {
		addAliases(new String[] { "sethome" });

		addParameter(TypeFaction.get(), "faction", "you");

		addRequirements(new Requirement[] { RequirementHasPerm.get(Perm.SETHOME) });
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
	}

	public void perform() throws MassiveException {
		Faction faction = (Faction) readArg(msenderFaction);
		PS newHome = PS.valueOf(me.getLocation());
		if (!MPerm.getPermSethome().has(msender, faction, true)) {
			return;
		}
		if ((!msender.isOverriding()) && (!faction.isValidHome(newHome))) {
			msender.msg("§c§l(!)§7 Faction homes can only be set inside your own territory.");
			return;
		}
		faction.setHome(newHome);
		faction.msg("§d§l(!)§7 " + msender.getName() + " has set the faction home!");
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsSethome.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */