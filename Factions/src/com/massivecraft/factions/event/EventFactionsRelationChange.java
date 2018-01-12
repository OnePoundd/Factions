package com.massivecraft.factions.event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsRelationChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private final Faction otherFaction;
	private Rel newRelation;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Faction getFaction() {
		return faction;
	}

	public Faction getOtherFaction() {
		return otherFaction;
	}

	public Rel getNewRelation() {
		return newRelation;
	}

	public void setNewRelation(Rel newRelation) {
		this.newRelation = newRelation;
	}

	public EventFactionsRelationChange(CommandSender sender, Faction faction, Faction otherFaction, Rel newRelation) {
		super(sender);
		this.faction = faction;
		this.otherFaction = otherFaction;
		this.newRelation = newRelation;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsRelationChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */