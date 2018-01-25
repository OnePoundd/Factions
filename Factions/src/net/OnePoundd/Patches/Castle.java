package net.OnePoundd.Patches;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class Castle implements Listener {
	static ArrayList<Player> PlayersInside = new ArrayList();
	static Location Castle1;
	static Location Castle2;
	static Faction Owner = null;
	static Faction Capturing = null;
	static double Percentage = 0.0D;
	static boolean Contested = false;

	public Castle() {
	}

	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (((event.getFrom().getBlockX() != event.getTo().getBlockX())
				|| (event.getFrom().getBlockZ() != event.getTo().getBlockZ())) && (Castle1 != null)
				&& (Castle2 != null)) {
			if (isInsideCastle(event.getTo())) {
				if (!isInsideCastle(event.getFrom())) {
					PlayersInside.add(event.getPlayer());
					event.getPlayer().sendMessage("§5§l(!)§7 You have entered the Castle's capture zone!");
				}
			} else if (PlayersInside.contains(event.getPlayer())) {
				PlayersInside.remove(event.getPlayer());
				event.getPlayer().sendMessage("§5§l(!)§7 You have left the Castle's capture zone!");
			}
		}
	}

	public boolean isInsideCastle(Location playerLocation) {
		int playerX = playerLocation.getBlockX();
		int playerY = playerLocation.getBlockY();
		int playerZ = playerLocation.getBlockZ();
		if (((playerX >= Castle1.getBlockX()) && (playerX <= Castle2.getBlockX())) || ((playerX <= Castle1.getBlockX())
				&& (playerX >= Castle2.getBlockX())
				&& (((playerY >= Castle1.getBlockY()) && (playerY <= Castle2.getBlockY()))
						|| ((playerY <= Castle1.getBlockY()) && (playerY >= Castle2.getBlockY())
								&& (((playerZ >= Castle1.getBlockZ()) && (playerZ <= Castle2.getBlockZ()))
										|| ((playerZ <= Castle1.getBlockZ()) && (playerZ >= Castle2.getBlockZ()))))))) {
			return true;
		}
		return false;
	}

	public static void startCastle(){
		if (MConf.get().getCastleLocation1() != null) {
			Castle1 = MConf.get().getCastleLocation1().asBukkitLocation();
		}
		if (MConf.get().getCastleLocation2() != null) {
			Castle2 = MConf.get().getCastleLocation2().asBukkitLocation();
		}
		if (MConf.get().getCastleOwner() != null) {
			Owner = MConf.get().getCastleOwner();
		}
		BukkitScheduler scheduler = Factions.get().getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(Factions.get(), new Runnable(){
			public void run(){
				if (Capturing == null){
					if ((PlayersInside.size() > 0) && (!MPlayer.get(PlayersInside.get(0)).getFaction().getName().equals("Wilderness"))) {
						Capturing = MPlayer.get(PlayersInside.get(0)).getFaction();
					}
				}else if (Capturing != Owner){
					int amountOfCapturers = amountOfCapturers();
					if (amountOfCapturers > 0){
						if (PlayersInside.size() - amountOfCapturers <= 0){
							Contested = false;
							double percentageToAdd = 1.5D + amountOfCapturers * 0.1D;
							if (Percentage + percentageToAdd >= 100.0D){
								if (Owner == null){
									captureEvent();
								}else{
									for (Player player : Bukkit.getOnlinePlayers()){
										MPlayer mplayer = MPlayer.get(player);
										mplayer.message("§5§l(!)§7§l The faction " + Capturing.getName(mplayer) + " §7§l has neutralised the castle!");
									}
									Owner = null;
									Percentage = 0.0D;
									Faction.get("Castle").setDescription("Owner: Wilderness");
								}
							} else {
								Percentage += percentageToAdd;
							}
						} else {
							Contested = false;
						}
					} else if (Percentage - 3.0D > 0.0D) {
						Percentage -= 3.0D;
					} else {
						Percentage = 0.0D;
						Capturing = null;
					}
					DecimalFormat df2 = new DecimalFormat(".##");
					for (Player player : PlayersInside) {
						if (Contested) {
							player.sendMessage("§5§l(!)§7 The capture point is contested!");
						} else if (Owner == null) {
							player.sendMessage("§5§l(!)§7 The faction " + Capturing.getName(MPlayer.get(player))
									+ "§7 is capturing the castle! (" + df2.format(Percentage) + "%)");
						} else if (Capturing != null) {
							player.sendMessage("§5§l(!)§7 The faction " + Capturing.getName(MPlayer.get(player))
									+ "§7 is neutralising the castle! (" + df2.format(Percentage) + "%)");
						}
					}
				}
			}
		}, 0L, 100L);

		scheduler.scheduleSyncRepeatingTask(Factions.get(), new Runnable() {
			public void run() {
				if (Owner != null) {
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ecoadmin give " + Owner.getLeader().getName() + " 40000");
					Owner.msg("§5§l(!)§7 The castle has given your faction leader $40,000!");
				}
			}
		}, 0L, 12000L);
		
		scheduler.scheduleSyncRepeatingTask(Factions.get(), new Runnable() {
			public void run() {
				if (Owner != null) {
					Owner.addVicotryPoints(1);
					Owner.msg("§5§l(!)§7 The castle has given your faction 1 victory point!");
				}
			}
		}, 0L, 36000L);
	}

	public static int amountOfCapturers() {
		int count = 0;
		for (Player player : PlayersInside) {
			if (MPlayer.get(player).getFaction() == Capturing) {
				count++;
			}
		}
		return count;
	}
	
	private static void captureEvent() {
		for (Player player : Bukkit.getOnlinePlayers()){
			MPlayer mplayer = MPlayer.get(player);
			mplayer.message("§5§l(!)§7§l The faction " + Capturing.getName(mplayer) + " §7§l has captured the castle!");
		}
		Owner.setOwnsCastle(false);
		Owner = Capturing;
		Owner.setOwnsCastle(true);
		MConf.get().setCastleOwner(Owner);
		Capturing = null;
		Percentage = 0.0;
		for(Player player : PlayersInside) {
			if(MPlayer.get(player).getFaction().equals(Owner)) {
				if(MConf.get().getWarp("castle") != null) {
					player.teleport(MConf.get().getWarp("castle"));
					player.sendMessage("§5§l(!)§7 Your faction has captured the castle and you have been teleported to the reward zone! You can return here at any time by typing /warp castle");
				}else {
					System.out.println("[Factions] Error: YOU MUST SET THE CASTLE WARP!");
				}
			}else {
				player.teleport(Bukkit.getWorld("world").getSpawnLocation());
				player.sendMessage("§5§l(!)§7 The faction " + Owner.getName() + " has captured the castle and you have been sent to spawn!");
			}
		}
	}
	
}