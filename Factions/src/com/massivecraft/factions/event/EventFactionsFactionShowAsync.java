package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.PriorityLines;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsFactionShowAsync extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private final Map<String, PriorityLines> idPriorityLiness;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Faction getFaction() {
		return faction;
	}

	public Map<String, PriorityLines> getIdPriorityLiness() {
		return idPriorityLiness;
	}

	public EventFactionsFactionShowAsync(CommandSender sender, Faction faction) {
		super(true, sender);
		this.faction = faction;
		idPriorityLiness = new HashMap();
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsFactionShowAsync.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */