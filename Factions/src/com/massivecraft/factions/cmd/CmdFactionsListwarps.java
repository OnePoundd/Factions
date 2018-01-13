package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.MassiveException;
import java.util.ArrayList;
import org.bukkit.command.CommandSender;

public class CmdFactionsListwarps extends FactionsCommand {
	public CmdFactionsListwarps() {
		addAliases(new String[] { "listwarps" });

		addParameter(TypeFaction.get(), "faction", "you");
	}

	public void perform() throws MassiveException {
		Faction fac = (Faction) readArg(msenderFaction);
		if (msenderFaction.getAllWarps().size() == 0) {
			sender.sendMessage("§c§l(!)§7 Your faction has no warps. Use: /f setwarp <name>");
		} else {
			String warpList = "  ";
			for (String s : msenderFaction.getAllWarps()) {
				warpList = warpList + s + ", ";
			}
			sender.sendMessage("§6§l(!)§7 Warps [" + msenderFaction.getAllWarps().size() + "/"
					+ msenderFaction.getMaxWarps() + "]: " + warpList.substring(0, warpList.length() - 2).trim() + ".");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsListwarps.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */