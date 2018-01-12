package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsInvitedChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final MPlayer mplayer;
	private final Faction faction;
	private boolean newInvited;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public MPlayer getMPlayer() {
		return mplayer;
	}

	public Faction getFaction() {
		return faction;
	}

	public boolean isNewInvited() {
		return newInvited;
	}

	public void setNewInvited(boolean newInvited) {
		this.newInvited = newInvited;
	}

	public EventFactionsInvitedChange(CommandSender sender, MPlayer mplayer, Faction faction, boolean newInvited) {
		super(sender);
		this.mplayer = mplayer;
		this.faction = faction;
		this.newInvited = newInvited;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsInvitedChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */