package com.massivecraft.factions.chat.modifier;

import com.massivecraft.factions.chat.ChatModifier;
import org.bukkit.command.CommandSender;

public class ChatModifierUc extends ChatModifier {
	private ChatModifierUc() {
		super("uc");
	}

	private static ChatModifierUc i = new ChatModifierUc();

	public static ChatModifierUc get() {
		return i;
	}

	public String getModified(String subject, CommandSender sender, CommandSender recipient) {
		return subject.toUpperCase();
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\chat\modifier\
 * ChatModifierUc.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */