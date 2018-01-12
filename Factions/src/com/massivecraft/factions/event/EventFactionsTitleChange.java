package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsTitleChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final MPlayer mplayer;
	private String newTitle;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public MPlayer getMPlayer() {
		return mplayer;
	}

	public String getNewTitle() {
		return newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

	public EventFactionsTitleChange(CommandSender sender, MPlayer mplayer, String newTitle) {
		super(sender);
		this.mplayer = mplayer;
		this.newTitle = newTitle;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsTitleChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */