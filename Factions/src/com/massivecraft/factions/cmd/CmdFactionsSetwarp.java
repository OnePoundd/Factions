package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.ps.PS;
import java.util.ArrayList;
import org.bukkit.entity.Player;

public class CmdFactionsSetwarp extends FactionsCommand {
	public CmdFactionsSetwarp() {
		addAliases(new String[] { "setwarp" });

		addParameter(TypeString.get(), "name");
		addParameter(TypeFaction.get(), "faction", "you");
		addParameter(null, TypeString.get(), "password");
	}

	public void perform() throws MassiveException {
		Faction faction = (Faction) readArgAt(1, msenderFaction);
		String password = (String) readArgAt(2, null);
		PS newHome = PS.valueOf(me.getLocation());
		if (!MPerm.getPermSetWarp().has(msender, faction, true)) {
			return;
		}
		if ((!msender.isOverriding()) && (!faction.isValidWarp(newHome))) {
			msender.msg("§c§l(!)§7 Faction warps can only be set inside your own territory.");
			return;
		}
		if (faction.getAllWarps().size() >= faction.getMaxWarps()) {
			msender.msg("§c§l(!)§7 You must upgrade your faction to unlock more warps!");
			return;
		}
		if (faction.setWarp(newHome, (String) readArgAt(0), password)) {
			faction.msg("§d§l(!)§7 " + msender.getName() + " has created a new warp named " + (String) readArgAt(0)
					+ "!");
		} else {
			msg("§d§l(!)§7 There is already a warp with that name!");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsSetwarp.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */