package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsHomeChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private PS newHome;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Faction getFaction() {
		return faction;
	}

	public PS getNewHome() {
		return newHome;
	}

	public void setNewHome(PS newHome) {
		this.newHome = newHome;
	}

	public EventFactionsHomeChange(CommandSender sender, Faction faction, PS newHome) {
		super(sender);
		this.faction = faction;
		this.newHome = newHome;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsHomeChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */