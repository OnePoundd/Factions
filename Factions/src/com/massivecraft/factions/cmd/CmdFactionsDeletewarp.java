package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;

public class CmdFactionsDeletewarp extends FactionsCommand {
	public CmdFactionsDeletewarp() {
		addAliases(new String[] { "deletewarp", "delwarp", "unsetwarp" });

		addParameter(TypeString.get(), "name");
		addParameter(TypeFaction.get(), "faction", "you");
	}

	public void perform() throws MassiveException {
		Faction faction = (Faction) readArgAt(1, msenderFaction);
		if (!MPerm.getPermDeleteWarp().has(msender, faction, true)) {
			msender.msg("§c§l(!)§7 Your faction does not allow you to delete warps.");
			return;
		}
		boolean deleted = faction.deleteWarp((String) readArgAt(0));
		if (deleted) {
			msender.msg("§d§l(!)§7 The warp has successfully been deleted!");
		} else {
			msender.msg("§c§l(!)§7 That is not a valid warp name!");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsDeletewarp.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */