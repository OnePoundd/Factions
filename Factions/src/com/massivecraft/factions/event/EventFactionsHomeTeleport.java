package com.massivecraft.factions.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsHomeTeleport extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public EventFactionsHomeTeleport(CommandSender sender) {
		super(sender);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsHomeTeleport.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */