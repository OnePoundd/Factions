package com.massivecraft.factions.chat.modifier;

import com.massivecraft.factions.chat.ChatModifier;
import org.bukkit.command.CommandSender;

public class ChatModifierLc extends ChatModifier {
	private ChatModifierLc() {
		super("lc");
	}

	private static ChatModifierLc i = new ChatModifierLc();

	public static ChatModifierLc get() {
		return i;
	}

	public String getModified(String subject, CommandSender sender, CommandSender recipient) {
		return subject.toLowerCase();
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\chat\modifier\
 * ChatModifierLc.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */