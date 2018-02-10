package net.OnePoundd.Patches;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.MPlayerColl;
import com.massivecraft.factions.event.EventFactionsCreate;
import com.massivecraft.factions.event.EventFactionsMembershipChange;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class NameTags implements Listener{
	
	
	public static void startScoreboard() {

	}
	
//	@EventHandler
//	public void onFactionMemberChange(EventFactionsMembershipChange e) {
//		for(Team team : board.getTeams()) {
//			if(team.getName().equals(e.getNewFaction().getName())) {
//				 Team factionTeam = board.registerNewTeam(f.getName());
//				 for(Player player : e.getNewFaction().getOnlinePlayers()) {
//					 factionTeam.addPlayer(player);
//				 }
//				 factionTeam.setSuffix(f.getName());
//				 factionTeam.setCanSeeFriendlyInvisibles(true);
//				 factionTeam.setDisplayName(f.getName());
//				return;
//			}
//		}
//		for(Faction f : FactionColl.get().getAll()) {
//			 Team factionTeam = board.registerNewTeam(f.getName());
//			 for(Player player : f.getOnlinePlayers()) {
//				 factionTeam.addPlayer(player);
//			 }
//			 factionTeam.setSuffix(f.getName());
//			 factionTeam.setCanSeeFriendlyInvisibles(true);
//			 factionTeam.setDisplayName(f.getName());
//		}
//	}
	
	public char getColorChar(Rel relation) {
		char c = 'N';
		if(relation.getName().equals("Recruit") || relation.getName().equals("Member") || relation.getName().equals("Officer") || relation.getName().equals("Leader")) {
			c = 'a';
		}else if(relation.getName().equals("Neutral")) {
			c = 'f';
		}else if(relation.getName().equals("Truce")) {
			c = 'd';
		}else if(relation.getName().equals("Ally")) {
			c = '5';
		}else if(relation.getName().equals("Enemy")) {
			c = '4';
		}
		return c;
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		MPlayer mPlayer = MPlayer.get(event.getPlayer());
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

		// Creates a health objective which is displayed under the players name
		Objective health = board.registerNewObjective("Health", "health");
		health.setDisplayName(" / 20");
		health.setDisplaySlot(DisplaySlot.BELOW_NAME);
		
		// Creates a faction objective which gets displayed in the player list
		Objective faction = board.registerNewObjective("Faction", "dummy");
		faction.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		faction.setDisplayName("Faction");
		
		// makes a team for the player who logged in so that they appear in green to themselves
		Team yourTeam = board.registerNewTeam(mPlayer.getName());
		yourTeam.addPlayer(event.getPlayer());
		yourTeam.setPrefix("§a" + mPlayer.getFaction().getName() + " §f");
		
		// makes a team for every other faction on the server so that they appear in the correct colours for the player who logged in
		for(Faction otherFaction : FactionColl.get().getAll()) {
			Team theirTeam = board.registerNewTeam(otherFaction.getName());
			for(MPlayer otherPlayer : otherFaction.getMPlayersWhereOnline(true)) {
				if(otherPlayer.getPlayer() != null) {
					theirTeam.addPlayer(otherPlayer.getPlayer());
				}//otherwise npc
			}
			theirTeam.setPrefix("§"+ getColorChar(mPlayer.getRelationTo(otherFaction)) + otherFaction.getName() + " §f");
		}

		// sets the scoreboard for the new player
		event.getPlayer().setScoreboard(board);
		
		// updates the scoreboard for the other already online players
		for(Player player : Bukkit.getOnlinePlayers()) {
			try {
				Scoreboard theirBoard = player.getScoreboard();
				Team team = theirBoard.getTeam(mPlayer.getFaction().getName());
				team.addPlayer(mPlayer.getPlayer());
				team.setPrefix("§" + getColorChar(MPlayer.get(player).getRelationTo(mPlayer)) + mPlayer.getFaction().getName() + " §f");
				player.setScoreboard(theirBoard);
			}catch(IllegalArgumentException e) {
				//NPC
			}
		}
		
		for(Player online : Bukkit.getOnlinePlayers()){
			  online.setHealth(online.getHealth()); //Update their health
		}
	}
	
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event) {
		// updates the scoreboard for the other already online players
		for(Player player : Bukkit.getOnlinePlayers()) {
			try {
				MPlayer mPlayer = MPlayer.get(event.getPlayer());
				Scoreboard theirBoard = player.getScoreboard();
				theirBoard.getTeam(mPlayer.getFaction().getName()).removePlayer(mPlayer.getPlayer());
				player.setScoreboard(theirBoard);
			}catch(IllegalArgumentException e) {
				//NPC
			}
		}
	}	
}
