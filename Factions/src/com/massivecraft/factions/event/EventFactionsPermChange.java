package com.massivecraft.factions.event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsPermChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private final MPerm perm;
	private final Rel rel;
	private boolean newValue;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Faction getFaction() {
		return faction;
	}

	public MPerm getPerm() {
		return perm;
	}

	public Rel getRel() {
		return rel;
	}

	public boolean getNewValue() {
		return newValue;
	}

	public void setNewValue(boolean newValue) {
		this.newValue = newValue;
	}

	public EventFactionsPermChange(CommandSender sender, Faction faction, MPerm perm, Rel rel, boolean newValue) {
		super(sender);
		this.faction = faction;
		this.perm = perm;
		this.rel = rel;
		this.newValue = newValue;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsPermChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */