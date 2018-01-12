package com.massivecraft.factions.chat.modifier;

import com.massivecraft.factions.chat.ChatModifier;
import org.bukkit.command.CommandSender;

public class ChatModifierLp extends ChatModifier {
	private ChatModifierLp() {
		super("lp");
	}

	private static ChatModifierLp i = new ChatModifierLp();

	public static ChatModifierLp get() {
		return i;
	}

	public String getModified(String subject, CommandSender sender, CommandSender recipient) {
		if (subject.equals("")) {
			return subject;
		}
		return " " + subject;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\chat\modifier\
 * ChatModifierLp.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */