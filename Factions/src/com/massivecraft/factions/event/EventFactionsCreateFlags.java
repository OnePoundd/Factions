package com.massivecraft.factions.event;

import org.bukkit.event.HandlerList;

public class EventFactionsCreateFlags extends EventFactionsAbstract {
	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public EventFactionsCreateFlags() {
	}

	public EventFactionsCreateFlags(boolean isAsync) {
		super(isAsync);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsCreateFlags.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */