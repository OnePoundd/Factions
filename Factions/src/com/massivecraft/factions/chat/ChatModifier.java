package com.massivecraft.factions.chat;

import com.massivecraft.massivecore.collections.MassiveMap;
import java.util.Map;
import org.bukkit.command.CommandSender;

public abstract class ChatModifier extends ChatActive {
	private static final Map<String, ChatModifier> idToModifier = new MassiveMap();

	public static ChatModifier getModifier(String modifierId) {
		return (ChatModifier) idToModifier.get(modifierId);
	}

	public ChatModifier(String id) {
		super(id);
	}

	public boolean isActive() {
		return idToModifier.containsKey(getId());
	}

	public void setActive(boolean active) {
		if (active) {
			idToModifier.put(getId(), this);
		} else {
			idToModifier.remove(getId());
		}
	}

	public abstract String getModified(String paramString, CommandSender paramCommandSender1,
			CommandSender paramCommandSender2);
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\chat\
 * ChatModifier.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */