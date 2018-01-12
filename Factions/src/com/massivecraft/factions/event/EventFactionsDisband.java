package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.Faction;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsDisband extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private final String factionId;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Faction getFaction() {
		return faction;
	}

	public String getFactionId() {
		return factionId;
	}

	public EventFactionsDisband(CommandSender sender, Faction faction) {
		super(sender);
		this.faction = faction;
		factionId = faction.getId();
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsDisband.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */