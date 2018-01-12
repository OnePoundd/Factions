package com.massivecraft.factions.chat;

import com.massivecraft.massivecore.Active;
import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.MassivePlugin;

public abstract class ChatActive implements Active, Identified {
	private MassivePlugin activePlugin;
	private final String id;

	public ChatActive(String id) {
		this.id = id.toLowerCase();
	}

	public String getId() {
		return id;
	}

	public MassivePlugin setActivePlugin(MassivePlugin plugin) {
		activePlugin = plugin;
		return plugin;
	}

	public MassivePlugin getActivePlugin() {
		return activePlugin;
	}

	public void setActive(MassivePlugin plugin) {
		setActive(plugin != null);
		setActivePlugin(plugin);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\chat\ChatActive
 * .class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */