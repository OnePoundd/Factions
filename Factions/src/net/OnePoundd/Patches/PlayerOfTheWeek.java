package net.OnePoundd.Patches;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitScheduler;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.MPlayerColl;

import net.minecraft.server.v1_12_R1.EntityPlayer;

public class PlayerOfTheWeek implements Listener{
	
	private static Map<MPlayer, Integer> currentTop = new HashMap<MPlayer, Integer>();
	public static List<NPC> npcs;
	
	
	public static void startPlayerOfTheWeekProcesses() {
		npcs = new ArrayList<NPC>();	
		// causes the player of the week leaderboard to every 5 mins
		BukkitScheduler scheduler = Factions.get().getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(Factions.get(), new Runnable() {
			public void run() {
				updatePlayerOfTheWeek();
			}
		}, 0L, 1000L); // 6000L for 5 mins 
	}
	
	
	public static void updatePlayerOfTheWeek() {
		// Firstly checks to see if the time is between 00:00 and 00:06 and the day is friday
		// if so, it resets every players weekly experience. This updater is called every 5
		// minutes to update the leaderboard in spawn, so this update is garunteed to be run.
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.DAY_OF_WEEK) == 6) {
			if(cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) <= 6) {
				for(MPlayer mplayer : MPlayerColl.get().getAll()) {
					mplayer.setExperienceOnFriday(mplayer.getExperience());
				}
				System.out.println("[Factions] Successfully Reset Weekly Player Experiences");
			}
		}
		
		// This sorts the leaderboard every time this method is called (every 5 mins)
		HashMap<MPlayer, Integer> entries = new HashMap<MPlayer, Integer>();
		for (MPlayer player : MPlayerColl.get().getAll()) {
			entries.put(player, Integer.valueOf(player.getWeeklyExperience()));
		}
		
		Map<MPlayer, Integer> sorted = sortByValue(entries);
		currentTop = sorted;
		
		// makes sure that all npc locations are defined for all positions, and then removes old npcs and replaces them with new ones.
		if(MConf.get().WeeklyPlayer1Location != null && MConf.get().WeeklyPlayer2Location != null && MConf.get().WeeklyPlayer3Location != null && currentTop.size() >= 3) {
			for(NPC npc : npcs) {
				npc.delete();
			}
			int i = 1;
			for (Map.Entry<MPlayer, Integer> entry : currentTop.entrySet()) {
				if(i == 1) {
					NPC leaderboard1 = new NPC(entry.getKey().getName(), MConf.get().WeeklyPlayer1Location.asBukkitLocation());
					npcs.add(leaderboard1);
				}else if(i == 2) {
					NPC leaderboard2 = new NPC(entry.getKey().getName(), MConf.get().WeeklyPlayer2Location.asBukkitLocation());
					npcs.add(leaderboard2);
				}else if(i == 3) {
					NPC leaderboard3 = new NPC(entry.getKey().getName(), MConf.get().WeeklyPlayer3Location.asBukkitLocation());
					npcs.add(leaderboard3);
				}else {
					break;
				}
				i ++;
			}
		}else {
			System.out.println("[Factions] Failed to create NPCS for the player leaderboard as not all positions have been set or filled!");
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		for(NPC npc : npcs) {
			EntityPlayer player = npc.get();
			if(event.getPlayer().getWorld().getName().equals(player.getWorld().getWorldData().getName())){
				double distance = event.getPlayer().getLocation().distance(new Location(Bukkit.getWorld(player.getWorld().getWorldData().getName()), player.getX(), player.getY(), player.getZ()));
				if(distance < 50) {
					npc.showToPlayer(event.getPlayer());
				}
			}
		}
	}
	
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
}
