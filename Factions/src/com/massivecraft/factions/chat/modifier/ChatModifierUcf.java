package com.massivecraft.factions.chat.modifier;

import com.massivecraft.factions.chat.ChatModifier;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.command.CommandSender;

public class ChatModifierUcf extends ChatModifier {
	private ChatModifierUcf() {
		super("ucf");
	}

	private static ChatModifierUcf i = new ChatModifierUcf();

	public static ChatModifierUcf get() {
		return i;
	}

	public String getModified(String subject, CommandSender sender, CommandSender recipient) {
		return Txt.upperCaseFirst(subject);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\chat\modifier\
 * ChatModifierUcf.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */