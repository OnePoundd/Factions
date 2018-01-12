package com.massivecraft.factions.chat.tag;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.chat.ChatTag;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.command.CommandSender;

public class ChatTagRoleprefixforce extends ChatTag {
	private ChatTagRoleprefixforce() {
		super("factions_roleprefixforce");
	}

	private static ChatTagRoleprefixforce i = new ChatTagRoleprefixforce();

	public static ChatTagRoleprefixforce get() {
		return i;
	}

	public String getReplacement(CommandSender sender, CommandSender recipient) {
		MPlayer usender = MPlayer.get(sender);

		return usender.getRole().getPrefix();
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\chat\tag\
 * ChatTagRoleprefixforce.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */