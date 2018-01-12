package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.Faction;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsNameChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private String newName;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Faction getFaction() {
		return faction;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public EventFactionsNameChange(CommandSender sender, Faction faction, String newName) {
		super(sender);
		this.faction = faction;
		this.newName = newName;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsNameChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */