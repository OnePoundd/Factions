package com.massivecraft.factions.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsCreate extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final String factionId;
	private final String factionName;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public final String getFactionId() {
		return factionId;
	}

	public final String getFactionName() {
		return factionName;
	}

	public EventFactionsCreate(CommandSender sender, String factionId, String factionName) {
		super(sender);
		this.factionId = factionId;
		this.factionName = factionName;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsCreate.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */