package com.massivecraft.factions.event;

import org.bukkit.event.HandlerList;

public class EventFactionsCreatePerms extends EventFactionsAbstract {
	private static final HandlerList handlers = new HandlerList();

	public EventFactionsCreatePerms() {
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsCreatePerms.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */