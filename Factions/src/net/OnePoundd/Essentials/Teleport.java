package net.OnePoundd.Essentials;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.factions.event.EventFactionsMembershipChange.MembershipChangeReason;
import com.massivecraft.massivecore.ps.PS;
import java.io.PrintStream;
import java.util.ArrayList;
import mkremins.fanciful.FancyMessage;
import net.OnePoundd.Patches.Skills;
import net.OnePoundd.Patches.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitScheduler;

public class Teleport implements Listener, CommandExecutor {
	public Teleport() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("tpa")) {
			if (args.length == 1) {
				try {					
					MPlayer playerTo = MPlayer.get(Bukkit.getPlayer(args[0]));
					MPlayer playerFrom = MPlayer.get(Bukkit.getPlayer(sender.getName()));
					if(playerTo.getName() != playerFrom.getName()) {
						if(!playerTo.getPreventTP()) {
							new FancyMessage().text("§6§l(!) " + playerTo.getColorTo(playerFrom) + sender.getName()
							+ " §7has requested to teleport to you. You have 20 seconds to click this message or type /tpyes!")
							.color(ChatColor.GRAY).command("/tpaccept " + playerFrom.getName())
							.send(playerTo.getPlayer());
							playerTo.setLastTeleportReceivedMillis(System.currentTimeMillis());
							playerTo.setLastTeleportReceivedPlayer(MPlayer.get(Bukkit.getPlayer(sender.getName())));
							playerFrom.setLastTeleportedTo(playerTo);
							playerFrom.setlastTeleportWasTPA(true);
							sender.sendMessage("§6§l(!)§7 A teleport request has been sent to " + playerTo.getColorTo(playerFrom) + playerTo.getName() + "§7.");
						}else {
							sender.sendMessage("§c§l(!)§7 That player is not accepting teleport requests!");
						}
					}else {
						sender.sendMessage("§c§l(!)§7 You cannot teleport to yourself!");
					}
				} catch (Exception e) {
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			} else if (args.length == 0) {
				sender.sendMessage("§c§l(!)§7 You must specify a player! Usage: /tpa <playername>");
			} else {
				sender.sendMessage("§c§l(!)§7 Too many arguments. Usage: /tpa <playername>");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("tpahere")) {
			if (args.length == 1) {
				try {
					MPlayer playerToTeleport = MPlayer.get(Bukkit.getPlayer(args[0]));
					MPlayer playerToTeleportTo = MPlayer.get(Bukkit.getPlayer(sender.getName()));
					new FancyMessage().text("§6§l(!) " + playerToTeleport.getColorTo(playerToTeleportTo) + sender.getName()
					+ " §7has requested that you teleport to them. You have 20 seconds to click this message or type /tpyes!")
					.color(ChatColor.GRAY).command("/tpaccept " + playerToTeleportTo.getName())
					.send(playerToTeleport.getPlayer());
					playerToTeleport.setLastTeleportReceivedMillis(System.currentTimeMillis());
					playerToTeleport.setLastTeleportReceivedPlayer(playerToTeleportTo);
					playerToTeleportTo.setLastTeleportedTo(playerToTeleport);
					playerToTeleportTo.setlastTeleportWasTPA(false);
					sender.sendMessage("§6§l(!)§7 A teleport request has been sent to "
					+ playerToTeleport.getColorTo(playerToTeleportTo) + playerToTeleport.getName() + "§7.");
				} catch (Exception e) {
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			} else if (args.length == 0) {
				sender.sendMessage("§c§l(!)§7 You must specify a player to teleport to you!");
			} else {
				sender.sendMessage("§c§l(!)§7 Too many arguments. Usage: /tpahere <playername>");
			}
		} else if ((cmd.getLabel().equalsIgnoreCase("tpyes")) || (cmd.getLabel().equalsIgnoreCase("tpaccept"))) {
			MPlayer player = MPlayer.get(Bukkit.getPlayer(sender.getName()));
			MPlayer playerBeingAccepted = null;
			if (args.length > 1) {
				sender.sendMessage("§c§l(!)§7 Too many arguments.");
				return true;
			}
			try {
				if (args.length == 0) {
					playerBeingAccepted = player.getLastTeleportReceivedPlayer();
				} else if (args.length == 1) {
					playerBeingAccepted = MPlayer.get(Bukkit.getPlayer(args[0]));
				}
				if (20000L <= System.currentTimeMillis() - player.getLastTeleportReceivedMillis()) {
					sender.sendMessage("§c§l(!)§7 That teleport request has expired!");
				}
				if (playerBeingAccepted.getLastTeleportedTo().equals(player)) {
					player.setLastTeleportReceivedMillis(100L);
					player.message("§6§l(!)§7 Teleport request accepted!");
					playerBeingAccepted.message("§6§l(!)§7 Teleport request accepted!");
					if (playerBeingAccepted.getLastTeleportWasTPA()) {
						tryTeleport(playerBeingAccepted.getPlayer(), player.getPlayer().getLocation());
					} else {
						tryTeleport(player.getPlayer(), playerBeingAccepted.getPlayer().getLocation());
					}
				} else {
					sender.sendMessage("§c§l(!)§7 That player has cancelled their request!");
				}
			} catch (Exception e) {
				sender.sendMessage("§c§l(!)§7 That player cannot be found!");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("tptoggle") || cmd.getLabel().equalsIgnoreCase("toggletp")) {
			MPlayer mplayer = MPlayer.get(Bukkit.getPlayer(sender.getName()));
			if(mplayer.getPreventTP()) {
				mplayer.setPreventTP(false);
				sender.sendMessage("§a§l(!)§7 You will now receive teleport requests!");
			}else {
				mplayer.setPreventTP(true);
				sender.sendMessage("§a§l(!)§7 You will no longer receive teleport requests!");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("sethome")) {
			if (args.length == 1) {
				Player player = Bukkit.getPlayer(sender.getName());
				int max = 0;
				if (player.hasPermission("factions.homes.1")) {
					max = 1;
				} else if (player.hasPermission("factions.homes.3")) {
					max = 3;
				} else if (player.hasPermission("factions.homes.5")) {
					max = 5;
				} else if (player.hasPermission("factions.homes.10")) {
					max = 10;
				}
				MPlayer mPlayer = MPlayer.get(player);
				if (mPlayer.getAllHomes().size() + 1 <= max) {
					mPlayer.setHome(PS.valueOf(player.getPlayer().getLocation()), args[0]);
				} else if (max == 10) {
					sender.sendMessage("§c§l(!)§7 You cannot set anymore homes!");
				} else {
					sender.sendMessage("§c§l(!)§7 You must have a higher rank in order to set more homes!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 Too many/few arguments. Usage: /sethome <name>");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("delhome")) {
			if (args.length == 1) {
				MPlayer player = MPlayer.get(Bukkit.getPlayer(sender.getName()));
				player.deleteHome(args[0]);
			} else {
				sender.sendMessage("§c§l(!)§7 Too many/few arguments. Usage: /sethome <name>");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("home")) {
			if (args.length == 1) {
				MPlayer player = MPlayer.get(Bukkit.getPlayer(sender.getName()));
				if (player.getAllHomes().contains(args[0])) {
					player.tpHome(args[0]);
				} else {
					sender.sendMessage("§c§l(!)§7 You have no home with that name!");
				}
			} else {
				MPlayer player = MPlayer.get(Bukkit.getPlayer(sender.getName()));
				if (player.getAllHomes().size() == 0) {
					sender.sendMessage("§c§l(!)§7 You have no homes. Use: /sethome <name>");
				} else {
					String homeList = "  ";
					for (String s : player.getAllHomes()) {
						homeList = homeList + s + ", ";
					}
					int max = 0;
					if (player.getPlayer().hasPermission("factions.homes.1")) {
						max = 1;
					} else if (player.getPlayer().hasPermission("factions.homes.3")) {
						max = 3;
					} else if (player.getPlayer().hasPermission("factions.homes.5")) {
						max = 5;
					} else if (player.getPlayer().hasPermission("factions.homes.10")) {
						max = 10;
					}
					sender.sendMessage("§6§l(!)§7 Homes [" + player.getAllHomes().size() + "/" + max + "]: "
							+ homeList.substring(0, homeList.length() - 2).trim() + ".");
				}
			}
		} else if (cmd.getLabel().equalsIgnoreCase("back")) {
			MPlayer player = MPlayer.get(Bukkit.getPlayer(sender.getName()));
			if(player.getPlayer().hasPermission("factions.back")) {
				if (player.getBackLocation() != null) {
					tryTeleport(player.getPlayer(), player.getBackLocation());
				} else {
					sender.sendMessage("§c§l(!)§7 There is no location to go back to!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 You must purchase a rank in order to use /back!");
			}
		} else if (cmd.getLabel().equals("spawn")) {
			MPlayer player = MPlayer.get(Bukkit.getPlayer(sender.getName()));
			tryTeleport(player.getPlayer(), Bukkit.getWorld("world").getSpawnLocation().add(0.5,0,0.5));
		} else if (cmd.getLabel().equals("setspawn")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (player.hasPermission("server.admin")) {
				player.getWorld().setSpawnLocation(player.getLocation());
				player.sendMessage("§a§l(!)§7 You have successfully set the /spawn location!");
			} else {
				player.sendMessage("§c§l(!)§7 You don't have permissions to do that!");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("setwarp")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (args.length == 1) {
				if (player.hasPermission("server.admin")) {
					if (MConf.get().setWarp(PS.valueOf(player.getLocation()), args[0])) {
						player.sendMessage("§a§l(!)§7 You have successfully set the warp " + args[0] + "!");
					} else {
						player.sendMessage("§c§l(!)§7 An error occured whilst setting the warp " + args[0] + "!");
					}
				} else {
					player.sendMessage("§c§l(!)§7 You don't have permissions to set a warp!");
				}
			} else {
				player.sendMessage("§c§l(!)§7 Not enough arguments!");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("warp")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (args.length == 1) {
				if (MConf.get().getWarp(args[0]) != null) {
					if(args[0].equalsIgnoreCase("castle")) {
						if(MPlayer.get(player).getFaction().getOwnsCastle()) {
							tryTeleport(player, MConf.get().getWarp(args[0]));
						}else {
							player.sendMessage("§c§l(!)§7 Your faction must own the castle to warp there!");
						}
					}else if(args[0].equalsIgnoreCase("jail")){
						player.sendMessage("§c§l(!)§7 You can't warp to the jail!");
					}else {
						tryTeleport(player, MConf.get().getWarp(args[0]));
					}
				} else {
					player.sendMessage("§c§l(!)§7 That warp does not exist!");
				}
			} else if (MConf.get().getAllWarps().size() == 0) {
				sender.sendMessage("§c§l(!)§7 There are currently no warps!");
			} else {
				String warpList = "  ";
				for (String s : MConf.get().getAllWarps()) {
					warpList = warpList + s + ", ";
				}
				sender.sendMessage("§6§l(!)§7 Warps: " + warpList.substring(0, warpList.length() - 2).trim() + ".");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("delwarp")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (args.length == 1) {
				if (player.hasPermission("server.admin")) {
					if (MConf.get().deleteWarp(args[0])) {
						player.sendMessage("§a§l(!)§7 You have successfully deleted the warp " + args[0] + "!");
					} else {
						player.sendMessage("§c§l(!)§7 An error occured whilst deleting the warp " + args[0] + "!");
					}
				} else {
					player.sendMessage("§c§l(!)§7 You don't have permission to delete a warp!");
				}
			} else {
				player.sendMessage("§c§l(!)§7 Not enough arguments!");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("vanish")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (player.hasPermission("server.admin")) {
				if (Vanish.vanishedPlayers.contains(player)) {
					Vanish.unVanishPlayer(player);
				} else {
					Vanish.vanishPlayer(player);
				}
			} else {
				player.sendMessage("§c§l(!)§7 You don't have permission to vanish!");
			}
		} else if (cmd.getLabel().equalsIgnoreCase("skills")) {
			if ((sender instanceof ConsoleCommandSender)) {
				if (args.length == 1) {
					if (Bukkit.getPlayer(args[0]).isOnline()) {
						Skills.openInventory(Bukkit.getPlayer(args[0]));
					} else {
						System.out.println("[Factions] Error: Player is not online.");
					}
				} else {
					System.out.println("[Factions] Error: Incorrect Usage.");
				}
			} else {
				Bukkit.getPlayer(sender.getName())
						.sendMessage("§c§l(!)§7 You don't have permission to run that command!");
			}
		}
		return true;
	}

	public static void tryTeleport(final Player player, final Location location) {
		try {
			Faction factionAtCurrent = BoardColl.get().getFactionAt(PS.valueOf(player.getLocation()));
			MPlayer mplayer = MPlayer.get(player);
			if ((factionAtCurrent.getName().equalsIgnoreCase("Castle")) || (factionAtCurrent.getName().equalsIgnoreCase("Warzone")) || (mplayer.isEnemyNearby(50, 256, 50))) {
				mplayer.setLocationBeforeTeleport(player.getLocation());
				mplayer.message("§6§l(!)§7 The teleport will commence in 5 seconds as there are some enemies nearby or you are in a warzone. Do not move!");
				Bukkit.getScheduler().runTaskLater(Factions.get(), new Runnable() {
					public void run() {
						if (MPlayer.get(player).getLocationBeforeTeleport().getBlock().equals(player.getLocation().getBlock())) {
							player.teleport(location.subtract(0, Teleport.getNumToSubtract(location, 0), 0), PlayerTeleportEvent.TeleportCause.COMMAND);
							player.sendMessage("§6§l(!)§7 Teleporting...");
						} else {
							player.sendMessage("§c§l(!)§7 The teleport was cancelled because you moved!");
						}
					}
				}, 100L);
			} else {
				player.teleport(location.subtract(0.0, getNumToSubtract(location, 0), 0.0), PlayerTeleportEvent.TeleportCause.COMMAND);
				player.sendMessage("§6§l(!)§7 Teleporting...");
			}
		} catch (NullPointerException localNullPointerException) {}
	}

	public static int getNumToSubtract(Location loc, int numToSubtract) {
		Location locBelow = loc.subtract(0.0, 1.0, 0.0);
		if (locBelow.getBlock().getType().equals(Material.AIR)) {
			getNumToSubtract(locBelow, numToSubtract + 1);
		}
		return numToSubtract - 1;
	}

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
			Location locationTo = event.getTo();
			String factionName = BoardColl.get().getFactionAt(PS.valueOf(locationTo)).getName();
			if ((factionName.equals("Castle")) || (factionName.equals("Safezone"))
					|| (!locationTo.add(0.0D, 1.0D, 0.0D).getBlock().getType().equals(Material.AIR))) {
				event.getPlayer().sendMessage("§c§l(!)§7 You cannot enderpeal there!");
				event.setCancelled(true);
			}
		} else if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.COMMAND)) {
			MPlayer.get(event.getPlayer()).setBackLocation(event.getFrom());
		}
	}

	@EventHandler
	public void onMemershipChange(EventFactionsMembershipChange event) {
		if ((!event.isCancelled()) && ((event.getReason().equals(EventFactionsMembershipChange.MembershipChangeReason.LEAVE))
		|| (event.getReason().equals(EventFactionsMembershipChange.MembershipChangeReason.KICK)))
		&& (BoardColl.get().getFactionAt(PS.valueOf(event.getMPlayer().getPlayer().getLocation())).equals(event.getMPlayer().getFaction()))) {
			event.getMPlayer().message("§6§l(!)§7 You have been teleported to spawn as you are no longer a part of the faction who's territory you were in.");
			event.getMPlayer().getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Rel relation = MPlayer.get(event.getPlayer())
				.getRelationTo(BoardColl.get().getFactionAt(PS.valueOf(event.getPlayer().getLocation())));
		if ((relation.equals(Rel.ENEMY)) || ((relation.equals(Rel.NEUTRAL)) && (!BoardColl.get().getFactionAt(PS.valueOf(event.getPlayer().getLocation())).getName().equals("Wilderness")))) {
			event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
			event.getPlayer().sendMessage("§6§l(!)§7 You have been teleported to spawn because you logged out in territory which is owned by a neutral or enemy faction!");
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Essentials\Teleport.class
 * Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */