package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Perm;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import java.util.List;
import mkremins.fanciful.FancyMessage;
import org.bukkit.command.CommandSender;

public class CmdFactionsFaction extends FactionsCommand {
	public CmdFactionsFaction() {
		addAliases(new String[] { "f", "show", "who" });

		addParameter(TypeFaction.get(), "faction", "you");

		addRequirements(new Requirement[] { RequirementHasPerm.get(Perm.FACTION) });
	}

	public void perform() throws MassiveException {
		Faction faction = (Faction) readArg(msenderFaction);
		CommandSender sender = this.sender;
		
		System.out.println("Faction Looked Up: " + msenderFaction.getName());

		int size = faction.getName().length();
		String name = faction.getColorTo(msenderFaction) + faction.getName();
		if (size == 3) {
			msg("§8§l§m-------------------§7§l[ §d" + name + "§7§l ]§8§l§m---------------------");
		} else if (size == 4) {
			msg("§8§l§m-------------------§7§l[ §d" + name + "§7§l ]§8§l§m--------------------");
		} else if (size == 5) {
			msg("§8§l§m-------------------§7§l[ §d" + name + "§7§l ]§8§l§m-------------------");
		} else if (size == 6) {
			msg("§8§l§m------------------§7§l[ §d" + name + "§7§l ]§8§l§m-------------------");
		} else if (size == 7) {
			msg("§8§l§m------------------§7§l[ §d" + name + "§7§l ]§8§l§m------------------");
		} else if (size == 8) {
			msg("§8§l§m-----------------§7§l[ §d" + name + "§7§l ]§8§l§m------------------");
		} else if (size == 9) {
			msg("§8§l§m-----------------§7§l[ §d" + name + "§7§l ]§8§l§m-----------------");
		} else if (size == 10) {
			msg("§8§l§m----------------§7§l[ §d" + name + "§7§l ]§8§l§m-----------------");
		} else if (size == 11) {
			msg("§8§l§m---------------§7§l[ §d" + name + "§7§l ]§8§l§m-----------------");
		} else {
			msg("§8§l§m---------------§7§l[ §dWilderness§7§l ]§8§l§m-----------------");
		}
		int minutes;
		if (!faction.isRaidCooldownOver()) {
			int TimeInMinutes = (int) (faction.getTimeTillRaidCoolownOverMillis() / 60000L);
			if (TimeInMinutes > 0) {
				int hours = TimeInMinutes / 60;
				minutes = TimeInMinutes - hours * 60;
				msg("§e§lRaid Cooldown: §7" + hours + " hours, " + minutes + " minutes.");
			}
		}
		if (faction.getDescription() == null) {
			msg("§eDecription: §7 the description is yet to be set!");
		} else {
			msg("§eDecription: §7" + faction.getDescription());
		}
		msg("§eLand/Power/MaxPower: §7" + faction.getLandCount() + " / " + faction.getPowerRounded() + " / "
				+ faction.getPowerMaxRounded());
		msg("§eF Top Position: §7" + faction.getFactionsTopPosition() + "th, with " + faction.getVictoryPoints()
				+ " victory points.");
		msg("§eF Wealth Position: §7" + faction.getFactionsWealthPosition() + "th, with $" + faction.getWealth() + ".");
		if (faction.getRecruiting()) {
			if (faction.getUrl() != null) {
				new FancyMessage().text("§eRecruitment: §7Open, click here to apply!").link(faction.getUrl())
						.send(sender);
			} else {
				msg("§eRecruitment Status: §7Open, message an officer for info!");
			}
		} else {
			msg("§eRecruitment Status: §7Closed!");
		}
		String allies = "§eAllies: §5";
		for (Faction otherFaction : FactionColl.get().getAll()) {
			if (faction.getRelationTo(otherFaction).equals(Rel.ALLY)) {
				if (allies == "§eAllies: §5") {
					allies = allies + otherFaction.getName();
				} else {
					allies = allies + ", " + otherFaction.getName();
				}
			}
		}
		msg(allies);

		String truces = "§eTruces: §d";
		for (Faction otherFaction : FactionColl.get().getAll()) {
			if ((faction.getRelationTo(otherFaction).equals(Rel.TRUCE)) && (!otherFaction.getName().equals("WarZone"))
					&& (!otherFaction.getName().equals("SafeZone")) && (!otherFaction.getName().equals("Castle"))) {
				if (truces == "§eTruces: §d") {
					truces = truces + otherFaction.getName();
				} else {
					truces = truces + ", " + otherFaction.getName();
				}
			}
		}
		msg(truces);

		int onlineCount = faction.getOnlinePlayers().size();
		int offlineCount = faction.getMPlayers().size() - faction.getOnlinePlayers().size();
		String onlinePlayers = "§eMembers Online (" + onlineCount + "): §a";
		String offlinePlayers = "§eMembers Offline (" + offlineCount + "): §c";
		String leaderson = "";
		String officerson = "";
		String memberson = "";
		String recruitson = "";
		String leadersoff = "";
		String officersoff = "";
		String membersoff = "";
		String recruitsoff = "";
		for (MPlayer player : faction.getMPlayers()) {
			boolean leader = false;
			boolean officer = false;
			boolean member = false;
			boolean recruit = false;

			boolean online = false;
			String currentPlayer;
			if (player.isOnline()) {
				online = true;
				if (player.getRole().equals(Rel.LEADER)) {
					currentPlayer = "§a**";
					leader = true;
				} else if (player.getRole().equals(Rel.OFFICER)) {
					currentPlayer = "§a*";
					officer = true;
				} else if (player.getRole().equals(Rel.MEMBER)) {
					currentPlayer = "§a+";
					member = true;
				} else {
					currentPlayer = "§a-";
					recruit = true;
				}
			} else if (player.getRole().equals(Rel.LEADER)) {
				currentPlayer = "§c**";
				leader = true;
			} else if (player.getRole().equals(Rel.OFFICER)) {
				currentPlayer = "§c*";
				officer = true;
			} else if (player.getRole().equals(Rel.MEMBER)) {
				currentPlayer = "§c+";
				member = true;
			} else {
				currentPlayer = "§c-";
				recruit = true;
			}
			if (player.getTitle().contains("no title set")) {
				if (online) {
					currentPlayer = currentPlayer + "§a" + player.getName();
				} else {
					currentPlayer = currentPlayer + "§c" + player.getName();
				}
			} else if (online) {
				currentPlayer = currentPlayer + player.getTitle() + " §a" + player.getName();
			} else {
				currentPlayer = currentPlayer + player.getTitle() + " §c" + player.getName();
			}
			if (player.isOnline()) {
				if (leader) {
					leaderson = currentPlayer + ", ";
				} else if (officer) {
					officerson = officerson + currentPlayer + ", ";
				} else if (member) {
					memberson = memberson + currentPlayer + ", ";
				} else if (recruit) {
					recruitson = recruitson + currentPlayer + ", ";
				}
			} else if (leader) {
				leadersoff = currentPlayer + ", ";
			} else if (officer) {
				officersoff = officersoff + currentPlayer + ", ";
			} else if (member) {
				membersoff = membersoff + currentPlayer + ", ";
			} else if (recruit) {
				recruitsoff = recruitsoff + currentPlayer + ", ";
			}
		}
		onlinePlayers = onlinePlayers + leaderson + officerson + memberson + recruitson;
		offlinePlayers = offlinePlayers + leadersoff + officersoff + membersoff + recruitsoff;

		msg(onlinePlayers.substring(0, onlinePlayers.length() - 2));
		msg(offlinePlayers.substring(0, offlinePlayers.length() - 2));

		msg("§8§l§m---------------------------------------------");
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsFaction.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */