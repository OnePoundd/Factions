package com.massivecraft.factions.predicate;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.predicate.Predicate;
import com.massivecraft.massivecore.util.MUtil;
import java.io.Serializable;
import org.bukkit.command.CommandSender;

public class PredicateCommandSenderFaction implements Predicate<CommandSender>, Serializable {
	private static final long serialVersionUID = 1L;
	private final String factionId;

	public String getFactionId() {
		return factionId;
	}

	public PredicateCommandSenderFaction(Faction faction) {
		factionId = faction.getId();
	}

	public boolean apply(CommandSender sender) {
		if (MUtil.isntSender(sender)) {
			return false;
		}
		MPlayer mplayer = MPlayer.get(sender);
		return factionId.equals(mplayer.getFaction().getId());
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\predicate\
 * PredicateCommandSenderFaction.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */