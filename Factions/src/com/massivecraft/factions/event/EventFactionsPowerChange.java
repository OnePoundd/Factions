package com.massivecraft.factions.event;

import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventFactionsPowerChange extends EventFactionsAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	private final MPlayer mplayer;
	private final PowerChangeReason reason;
	private double newPower;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public MPlayer getMPlayer() {
		return mplayer;
	}

	public PowerChangeReason getReason() {
		return reason;
	}

	public double getNewPower() {
		return newPower;
	}

	public void setNewPower(double newPower) {
		this.newPower = newPower;
	}

	public EventFactionsPowerChange(CommandSender sender, MPlayer mplayer, PowerChangeReason reason, double newPower) {
		super(sender);
		this.mplayer = mplayer;
		this.reason = reason;
		this.newPower = mplayer.getLimitedPower(newPower);
	}

	public static enum PowerChangeReason {
		TIME, DEATH, COMMAND, UNDEFINED;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\event\
 * EventFactionsPowerChange.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */