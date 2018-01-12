package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.Faction;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsMotdChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private String newMotd;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Faction getFaction() {
		return faction;
	}

	public String getNewMotd() {
		return newMotd;
	}

	public void setNewMotd(String newMotd) {
		this.newMotd = newMotd;
	}

	public EventFactionsMotdChange(CommandSender sender, Faction faction, String newMotd) {
		super(sender);
		this.faction = faction;
		this.newMotd = newMotd;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsMotdChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */