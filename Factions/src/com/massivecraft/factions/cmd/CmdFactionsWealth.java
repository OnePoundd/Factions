package com.massivecraft.factions.cmd;

import com.google.common.collect.Lists;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CmdFactionsWealth extends FactionsCommand {
	private static Map<Faction, Integer> currentTop = new HashMap();
	private static long rewardedTimeMillis = MConf.get().lastWealthRewardTimeMillis;

	public CmdFactionsWealth() {
		addAliases(new String[] { "wealth" });
	}

	public void perform() throws MassiveException {
		msg("§8§l§m---------------§7§l[ §dTop 10 Factions§7§l ]§8§l§m---------------");
		msg("§7A list of the top 10 factions by their faction wealth!");
		int i = 1;
		for (Map.Entry<Faction, Integer> entry : currentTop.entrySet()) {
			if (i <= 10) {
				msg("§d§l " + i + ") §b" + ((Faction) entry.getKey()).getName() + ", $" + entry.getValue() + ".");
				i++;
			} else {
				return;
			}
		}
		msg("§8§l§m--------------------------------------------");
	}

	public static void updateFactionsWealth() {
		HashMap<Faction, Integer> entries = new HashMap();
		for (Faction faction : FactionColl.get().getAll()) {
			entries.put(faction, Integer.valueOf(faction.getWealth()));
		}
		Map<Faction, Integer> sorted = sortByValue(entries);
		int i = 0;
		for (Map.Entry<Faction, Integer> entry : sorted.entrySet()) {
			i++;
			((Faction) entry.getKey()).setFactionsWealthPosition(i);
		}
		currentTop = sorted;
		Date date = new Date();
		if ((date.getHours() == 0) && (date.getMinutes() <= 15)
				&& (System.currentTimeMillis() - rewardedTimeMillis >= 86400000L)) {
			MConf.get().lastWealthRewardTimeMillis = System.currentTimeMillis();
			rewardedTimeMillis = System.currentTimeMillis();
			int j = 1;
			for (Map.Entry<Faction, Integer> entry : currentTop.entrySet()) {
				if (j <= 5) {
					int points = 0;
					if (j == 1) {
						points = 10;
					} else if (j == 2) {
						points = 8;
					} else if (j == 3) {
						points = 6;
					} else if (j == 4) {
						points = 4;
					} else if (j == 5) {
						points = 2;
					}
					((Faction) entry.getKey()).addVicotryPoints(points);
					j++;
				} else {
					return;
				}
			}
			System.out.println("[Factions] Successfully rewarded the top 5 factions with victory points!");
		}
	}

    public static <K, V extends Comparable<? super V>> Map<K, V> 
    sortByValue(Map<K, V> map) {
    List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
    Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            return (o1.getValue()).compareTo( o2.getValue() );
        }
    });

    Map<K, V> result = new LinkedHashMap<K, V>();
    for (Map.Entry<K, V> entry : list) {
        result.put(entry.getKey(), entry.getValue());
    }
    return result;
}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsWealth.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */