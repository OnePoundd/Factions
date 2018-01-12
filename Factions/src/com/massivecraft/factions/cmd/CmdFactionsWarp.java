package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.ps.PS;
import net.OnePoundd.Essentials.Teleport;
import org.bukkit.ChatColor;

public class CmdFactionsWarp extends FactionsCommand {
	public CmdFactionsWarp() {
		addAliases(new String[] { "warp" });

		addParameter(TypeString.get(), "name");
		addParameter(TypeFaction.get(), "faction", "you");
		addParameter(null, TypeString.get(), "password");
	}

	public void perform() throws MassiveException {
		Faction faction = (Faction) readArgAt(1, msenderFaction);

		String password = (String) readArgAt(2, null);
		if (!MPerm.getPermWarp().has(msender, faction, true)) {
			return;
		}
		PS loc = faction.getWarp((String) readArgAt(0));
		if (!MPerm.getPermWarp().has(msender, faction, true)) {
			return;
		}
		if (loc == null) {
			msender.msg(ChatColor.RED + "This is not a valid warp");
			return;
		}
		if (faction.getWarpPassword((String) readArgAt(0)) == null) {
			Teleport.tryTeleport(msender.getPlayer(), loc.asBukkitLocation());
			return;
		}
		if (faction.getWarpPassword((String) readArgAt(0)).equals(password)) {
			Teleport.tryTeleport(msender.getPlayer(), loc.asBukkitLocation());
		} else {
			msender.msg(ChatColor.RED + "Incorrect Password!");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsWarp.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */