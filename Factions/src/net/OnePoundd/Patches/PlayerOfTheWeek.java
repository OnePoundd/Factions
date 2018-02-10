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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitScheduler;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.MPlayerColl;
import com.src.main.NPCManager;
import com.src.main.NPCS;
import com.src.main.NPCSpawn;

import net.minecraft.server.v1_12_R1.EntityPlayer;

public class PlayerOfTheWeek implements Listener{
	
	private static Map<MPlayer, Integer> currentTop = new HashMap<MPlayer, Integer>();
	
	public static void startPlayerOfTheWeekProcesses() {	
		// causes the player of the week leaderboard to every 5 mins
		BukkitScheduler scheduler = Factions.get().getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(Factions.get(), new Runnable() {
			public void run() {
				updatePlayerOfTheWeek();
			}
		}, 0L, 1000*60*5); // 6000L for 5 mins 
	}
	
	
	public static void updatePlayerOfTheWeek() {
		// This sorts the leaderboard every time this method is called (every 5 mins)
		HashMap<MPlayer, Integer> entries = new HashMap<MPlayer, Integer>();
		for (MPlayer player : MPlayerColl.get().getAll()) {
			entries.put(player, Integer.valueOf(player.getWeeklyExperience()));
		}
		currentTop = sortByValue(entries);
		
		// checks to see if the time is between 00:00 and 00:06 and the day is friday and if
		// so, it resets every players weekly experience. This updater is called every 5
		// minutes to update the leaderboard in spawn, so this update is garunteed to be run.
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.DAY_OF_WEEK) == 6) {
			if(cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) <= 6) {
				for(MPlayer mplayer : MPlayerColl.get().getAll()) {
					mplayer.setExperienceOnFriday(mplayer.getExperience());
				}
				for (Map.Entry<MPlayer, Integer> entry : currentTop.entrySet()) {
					// entry.getKey().getName() give money to
					//break;
				}
				System.out.println("[Factions] Successfully reset the weekly experience gain leaderboard and rewarded the number 1 player!");
			}
		}
		
		// makes sure that all npc location is defined and removed old npc
		if(MConf.get().WeeklyPlayer1Location != null && currentTop.size() >= 1) {
			int i = 0;
			for (Map.Entry<MPlayer, Integer> entry : currentTop.entrySet()) {
				if(i >= (currentTop.size() - 1)) {
					Location location =  MConf.get().WeeklyPlayer1Location.asBukkitLocation();
					location.setYaw(91.0f);
					location.setPitch(-1.4f);
					NPCS.npcspawn.spawnNPC(entry.getKey().getName(), entry.getKey().getName(), location);
					Bukkit.getScheduler().runTaskLater(Factions.get(), new Runnable() {
						public void run() {
							if(NPCManager.entityByName.containsKey(entry.getKey().getName().toLowerCase())) {
								for(Object obj : NPCManager.entityByName.get(entry.getKey().getName().toLowerCase())){
									NPCS.npcspawn.deleteNPC(obj);
								}
							}
						}
					}, (1000*60*5)-20);
					break;
				}
				i ++;
			}
		}else {
			System.out.println("[Factions] Failed to create NPC for the player leaderboard!");
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
