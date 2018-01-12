package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.Faction;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsDescriptionChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private String newDescription;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Faction getFaction() {
		return faction;
	}

	public String getNewDescription() {
		return newDescription;
	}

	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}

	public EventFactionsDescriptionChange(CommandSender sender, Faction faction, String newDescription) {
		super(sender);
		this.faction = faction;
		this.newDescription = newDescription;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsDescriptionChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */