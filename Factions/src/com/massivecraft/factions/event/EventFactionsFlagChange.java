package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MFlag;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsFlagChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final Faction faction;
	private final MFlag flag;
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

	public MFlag getFlag() {
		return flag;
	}

	public boolean isNewValue() {
		return newValue;
	}

	public void setNewValue(boolean newValue) {
		this.newValue = newValue;
	}

	public EventFactionsFlagChange(CommandSender sender, Faction faction, MFlag flag, boolean newValue) {
		super(sender);
		this.faction = faction;
		this.flag = flag;
		this.newValue = newValue;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsFlagChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */