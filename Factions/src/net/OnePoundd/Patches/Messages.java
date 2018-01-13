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

public class Messages implements Listener {
	public Messages() {
	}

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
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
				new FancyMessage(
						"§b§l(!)§7 You have no faction! Click this message to find factions which are looking for new players, otherwise run /f create to get started!")
								.command("/f find").color(ChatColor.GRAY).send(player.getPlayer());
			}
			if (!player.hasPlayedBefore()) {
				if (MConf.get().getWarp("introduction") != null) {
					player.getPlayer().teleport(MConf.get().getWarp("introduction"));
					player.msg(
							"§b§l(!)§7 Welcome to Icewynd Factions! To help get you firmiliar with all the custom features our server has to offer, we have put together a quick introduction world for you to explore. We hope you enjoy your stay, and should you ever get confused you can always type /help or return here by typing /warp introduction!");

					ItemStack kit = new ItemStack(Material.CHEST);
					ItemMeta kitmeta = kit.getItemMeta();
					kitmeta.setDisplayName("§e§lKit Member");
					kit.setItemMeta(kitmeta);
					player.getPlayer().getInventory().addItem(new ItemStack[] { kit });
				} else {
					System.out.println("[Factions] ERROR: You must set /warp introduction!");
				}
			}
		} catch (NullPointerException e) {
			System.out.println("**A NPC HAS JOINED**");
		}
	}

	@EventHandler
	public void onQuitEvent(PlayerQuitEvent event) {
		event.setQuitMessage("");
		MPlayer player = MPlayer.get(event.getPlayer());
		if (!player.getFaction().getName().equals("Wilderness")) {
			player.getFaction().msg("§c§l(!)§7 " + player.getName() + " is now offline!");
		}
	}

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		event.setDeathMessage("");
		if ((event.getEntity().getKiller() != null)
				&& (event.getEntity().getPlayer().getKiller().getType().equals(EntityType.PLAYER))) {
			Player killed = event.getEntity();
			Player killer = killed.getKiller();
			ItemStack item = killer.getItemInHand();
			if ((item.hasItemMeta()) && (item.getItemMeta().hasDisplayName())
					&& (item.getItemMeta().getDisplayName().contains(""))) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					MPlayer mplayer = MPlayer.get(player);
					player.sendMessage("§8§l>> " + mplayer.getColorTo(MPlayer.get(killed)) + killed.getName()
							+ "§8 was killed by " + mplayer.getColorTo(MPlayer.get(killer)) + killer.getName()
							+ "§8 with a " + item.getItemMeta().getDisplayName() + "§7!");
				}
			} else {
				for (Player player : Bukkit.getOnlinePlayers()) {
					MPlayer mplayer = MPlayer.get(player);
					player.sendMessage("§8§l>> " + mplayer.getColorTo(MPlayer.get(killed)) + killed.getName()
							+ "§8 was killed by " + mplayer.getColorTo(MPlayer.get(killer)) + killer.getName()
							+ "§8!");
				}
			}
		}
	}

	@EventHandler
	public void onCommandPreProcessEvent(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if ((!player.hasPermission("server.admin"))
				&& (player.getLocation().getWorld().getName().equals("world_introduction"))) {
			if (!event.getMessage().equals("/spawn")) {
				player.sendMessage("§c§l(!)§7 You must leave this area to use commands, type /spawn!");
				event.setCancelled(true);
			} else if (MPlayer.get(player).getLevel() < 1) {
				player.sendMessage(
						"§c§l(!)§7 You must complete the introduction by talking to the Skill Master before using /spawn!");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent event) {
		Player sender = event.getPlayer();
		if (sender.getLocation().getWorld().getName().equals("world_introduction")) {
			sender.sendMessage("§c§l(!)§7 You must complete the introduction to use chat!");
			event.setCancelled(true);
		} else {
			for (Player receiver : event.getRecipients()) {
				if (receiver.getLocation().getWorld().getName().equals("world_introduction")) {
					event.setCancelled(true);
				}
			}
		}
	}
}
