package com.massivecraft.factions.cmd;

import com.google.common.collect.Lists;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.massivecore.MassiveException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CmdFactionsTop extends FactionsCommand {
	private static Map<Faction, Integer> currentTop = new HashMap();

	public CmdFactionsTop() {
		addAliases(new String[] { "top" });
	}

	public void perform() throws MassiveException {
		msg("§8§l§m---------------§7§l[ §dTop 10 Factions§7§l ]§8§l§m---------------");
		msg("§7A list of the top 10 factions by their amount of victory points!");
		int i = 1;
		for (Map.Entry<Faction, Integer> entry : currentTop.entrySet()) {
			if (i <= 10) {
				msg("§d§l " + i + ") §b" + ((Faction) entry.getKey()).getName() + ", " + entry.getValue() + ".");
				i++;
			} else {
				return;
			}
		}
		msg("§8§l§m--------------------------------------------");
	}

	public static void updateFactionsTop() {
		HashMap<Faction, Integer> entries = new HashMap();
		for (Faction faction : FactionColl.get().getAll()) {
			entries.put(faction, Integer.valueOf(faction.getVictoryPoints()));
		}
		Map<Faction, Integer> sorted = sortByValue(entries);
		int i = 0;
		for (Map.Entry<Faction, Integer> entry : sorted.entrySet()) {
			i++;
			((Faction) entry.getKey()).setFactionsTopPosition(i);
		}
		currentTop = sorted;
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