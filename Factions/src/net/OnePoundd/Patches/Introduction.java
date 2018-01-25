package net.OnePoundd.Patches;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import java.io.PrintStream;
import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Introduction implements Listener {

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		boolean alt = false;
		//adds ip to list of previously connected players
		if(MConf.get().previouslyUsedIPs.contains(event.getPlayer().getAddress().getHostName())){ // if another account has used IP before
			alt = true;
		}else {
			MConf.get().previouslyUsedIPs.add(event.getPlayer().getAddress().getHostName());
		}
		event.setJoinMessage("");
		MPlayer player = MPlayer.get(event.getPlayer());
		try {
			if (!player.getFaction().getName().equals("Wilderness")) {
				for (MPlayer mp : player.getFaction().getMPlayers()) {
					if (!mp.getName().equals(player.getName())) {
						mp.msg("§a§l(!)§7 " + player.getName() + " is now online!");
					}
				}
			} else {
				new FancyMessage("§b§l(!)§7 You have no faction! Click this message to find factions which are looking for new players, otherwise run /f create to get started!").command("/f find").color(ChatColor.GRAY).send(player.getPlayer());
			}
			if (!player.hasPlayedBefore()) {
				if (MConf.get().getWarp("introduction") != null) {
					if(alt == false) {
						player.getPlayer().teleport(MConf.get().getWarp("introduction"));
						player.msg("§b§l(!)§7 Welcome to Icewynd Factions! To help get you firmiliar with all the custom features our server has to offer, we have put together a quick introduction world for you to explore. We hope you enjoy your stay, and should you ever get confused you can always type /help or return here by typing /warp introduction!");
						ItemStack kit = new ItemStack(Material.CHEST);
						ItemMeta kitmeta = kit.getItemMeta();
						kitmeta.setDisplayName("§e§lKit Member");
						kit.setItemMeta(kitmeta);
						player.getPlayer().getInventory().addItem(kit);
					}else {
						new FancyMessage("§b§l(!)§7 We have detected that another account has connected on your IP address before, and so have let you skip the introduction world. If this is a mistake,"
						+ " just click this message to teleport to the introduction world and start fresh.").command("/warp introduction").color(ChatColor.GRAY).send(player.getPlayer());
						player.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
					}
				} else {
					System.out.println("[Factions] ERROR: You must set /warp introduction!");
					System.out.println("[Factions] ERROR: You must set /warp introduction!");
					System.out.println("[Factions] ERROR: You must set /warp introduction!");
				}
			}
		} catch (NullPointerException e) {
			System.out.println("**A NPC HAS JOINED**");
		}
	}

	@EventHandler
	public void onCommandPreProcessEvent(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("server.admin")){ // if the player isn't an admin
			if(player.getLocation().getWorld().getName().equals("world_introduction")) { //if the player is in the introduction world
				if (!event.getMessage().equals("/spawn")) { // prevents the player using commands
					player.sendMessage("§c§l(!)§7 You must leave this area to use commands, type /spawn!");
					event.setCancelled(true);
				} else if (MPlayer.get(player).getLevel() < 1) { // allows the user to type /spawn if ip connected before or completed tutorial
					
					player.sendMessage("§c§l(!)§7 You must complete the introduction by talking to the Skill Master before using /spawn!");
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent event) {
		Player sender = event.getPlayer();
		if (sender.getLocation().getWorld().getName().equals("world_introduction") && !sender.hasPermission("server.admin")) { //if in the introduction world and not an admin
			sender.sendMessage("§c§l(!)§7 You must complete the introduction to use chat!");
			event.setCancelled(true);
		} else { // if not in the introduction world, remove players in the introduction world from participants, unless they are admins
			for (Player receiver : event.getRecipients()) {
				if (receiver.getLocation().getWorld().getName().equals("world_introduction") && !receiver.hasPermission("server.admin")) {
					event.getRecipients().remove(receiver);
				}
			}
		}
	}
}
