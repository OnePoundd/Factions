package com.massivecraft.factions.event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsRankChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final MPlayer mplayer;
	private Rel newRank;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public MPlayer getMPlayer() {
		return mplayer;
	}

	public Rel getNewRank() {
		return newRank;
	}

	public void setNewRank(Rel newRole) {
		newRank = newRole;
	}

	public EventFactionsRankChange(CommandSender sender, MPlayer mplayer, Rel newRank) {
		super(sender);
		this.mplayer = mplayer;
		this.newRank = newRank;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsRankChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */