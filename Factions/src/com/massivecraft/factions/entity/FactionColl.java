package com.massivecraft.factions.entity;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.integration.Econ;
import com.massivecraft.factions.util.MiscUtil;
import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.util.Txt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.ChatColor;

public class FactionColl extends Coll<Faction> {
	private static FactionColl i = new FactionColl();

	public FactionColl() {
	}

	public static FactionColl get() {
		return i;
	}

	public void onTick() {
		super.onTick();
	}

	public void setActive(boolean active) {
		super.setActive(active);
		if (!active) {
			return;
		}
		createSpecialFactions();
	}

	public void createSpecialFactions() {
		getNone();
		getSafezone();
		getWarzone();
		getCastle();
	}

	public Faction getNone() {
		String id = "none";
		Faction faction = (Faction) get(id);
		if (faction != null) {
			return faction;
		}
		faction = (Faction) create(id);

		faction.setName("Wilderness");
		faction.setDescription("It's dangerous to go alone.");

		faction.setFlag(MFlag.getFlagOpen(), false);
		faction.setFlag(MFlag.getFlagPermanent(), true);
		faction.setFlag(MFlag.getFlagPeaceful(), false);
		faction.setFlag(MFlag.getFlagInfpower(), true);
		faction.setFlag(MFlag.getFlagPowerloss(), true);
		faction.setFlag(MFlag.getFlagPvp(), true);
		faction.setFlag(MFlag.getFlagFriendlyire(), false);
		faction.setFlag(MFlag.getFlagMonsters(), true);
		faction.setFlag(MFlag.getFlagAnimals(), true);
		faction.setFlag(MFlag.getFlagExplosions(), true);
		faction.setFlag(MFlag.getFlagOfflineexplosions(), true);
		faction.setFlag(MFlag.getFlagFirespread(), true);
		faction.setFlag(MFlag.getFlagEndergrief(), true);
		faction.setFlag(MFlag.getFlagZombiegrief(), true);

		faction.setPermittedRelations(MPerm.getPermBuild(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermDoor(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT,
				Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermContainer(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermButton(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermLever(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermDeposit(), new Rel[] { Rel.LEADER, Rel.OFFICER });

		return faction;
	}

	public Faction getSafezone() {
		String id = "safezone";
		Faction faction = (Faction) get(id);
		if (faction != null) {
			return faction;
		}
		faction = (Faction) create(id);

		faction.setName("SafeZone");
		faction.setDescription("Free from PVP and monsters");

		faction.setFlag(MFlag.getFlagOpen(), false);
		faction.setFlag(MFlag.getFlagPermanent(), true);
		faction.setFlag(MFlag.getFlagPeaceful(), true);
		faction.setFlag(MFlag.getFlagInfpower(), true);
		faction.setFlag(MFlag.getFlagPowerloss(), false);
		faction.setFlag(MFlag.getFlagPvp(), false);
		faction.setFlag(MFlag.getFlagFriendlyire(), false);
		faction.setFlag(MFlag.getFlagMonsters(), false);
		faction.setFlag(MFlag.getFlagAnimals(), true);
		faction.setFlag(MFlag.getFlagExplosions(), false);
		faction.setFlag(MFlag.getFlagOfflineexplosions(), false);
		faction.setFlag(MFlag.getFlagFirespread(), false);
		faction.setFlag(MFlag.getFlagEndergrief(), false);
		faction.setFlag(MFlag.getFlagZombiegrief(), false);

		faction.setPermittedRelations(MPerm.getPermDoor(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT,
				Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermContainer(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermButton(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermLever(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermTerritory(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER });

		return faction;
	}

	public Faction getWarzone() {
		String id = "warzone";
		Faction faction = (Faction) get(id);
		if (faction != null) {
			return faction;
		}
		faction = (Faction) create(id);

		faction.setName("WarZone");
		faction.setDescription("Not the safest place to be");

		faction.setFlag(MFlag.getFlagOpen(), false);
		faction.setFlag(MFlag.getFlagPermanent(), true);
		faction.setFlag(MFlag.getFlagPeaceful(), true);
		faction.setFlag(MFlag.getFlagInfpower(), true);
		faction.setFlag(MFlag.getFlagPowerloss(), false);
		faction.setFlag(MFlag.getFlagPvp(), true);
		faction.setFlag(MFlag.getFlagFriendlyire(), false);
		faction.setFlag(MFlag.getFlagMonsters(), true);
		faction.setFlag(MFlag.getFlagAnimals(), true);
		faction.setFlag(MFlag.getFlagExplosions(), false);
		faction.setFlag(MFlag.getFlagOfflineexplosions(), false);
		faction.setFlag(MFlag.getFlagFirespread(), false);
		faction.setFlag(MFlag.getFlagEndergrief(), false);
		faction.setFlag(MFlag.getFlagZombiegrief(), false);

		faction.setPermittedRelations(MPerm.getPermDoor(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT,
				Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermContainer(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermButton(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermLever(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermTerritory(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER });

		return faction;
	}

	public Faction getCastle() {
		String id = "castle";
		Faction faction = (Faction) get(id);
		if (faction != null) {
			return faction;
		}
		faction = (Faction) create(id);

		faction.setName("Castle");
		faction.setDescription("capture for rewards and victory points!");

		faction.setFlag(MFlag.getFlagOpen(), false);
		faction.setFlag(MFlag.getFlagPermanent(), true);
		faction.setFlag(MFlag.getFlagPeaceful(), true);
		faction.setFlag(MFlag.getFlagInfpower(), true);
		faction.setFlag(MFlag.getFlagPowerloss(), false);
		faction.setFlag(MFlag.getFlagPvp(), true);
		faction.setFlag(MFlag.getFlagFriendlyire(), false);
		faction.setFlag(MFlag.getFlagMonsters(), true);
		faction.setFlag(MFlag.getFlagAnimals(), true);
		faction.setFlag(MFlag.getFlagExplosions(), false);
		faction.setFlag(MFlag.getFlagOfflineexplosions(), false);
		faction.setFlag(MFlag.getFlagFirespread(), false);
		faction.setFlag(MFlag.getFlagEndergrief(), false);
		faction.setFlag(MFlag.getFlagZombiegrief(), false);

		faction.setPermittedRelations(MPerm.getPermDoor(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT,
				Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermContainer(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermButton(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermLever(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });
		faction.setPermittedRelations(MPerm.getPermTerritory(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER });
		faction.setPermittedRelations(MPerm.getPermBuild(), new Rel[] { Rel.LEADER, Rel.OFFICER, Rel.MEMBER,
				Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY });

		return faction;
	}

	public void econLandRewardRoutine()
	{
		// If econ is enabled ...
		if (!Econ.isEnabled()) return;
		
		// ... and the land reward is non zero ...
		double econLandReward = MConf.get().econLandReward;
		if (econLandReward == 0.0) return;
		
		// ... log initiation ...
		Factions.get().log("Running econLandRewardRoutine...");
		MFlag flagPeaceful = MFlag.getFlagPeaceful();
		
		// ... and for each faction ...
		for (Faction faction : this.getAll())
		{
			// ... get the land count ...
			int landCount = faction.getLandCount();
			
			// ... and if the faction isn't peaceful and has land ...
			if (faction.getFlag(flagPeaceful) || landCount > 0) continue;
			
			// ... get the faction's members ...
			List<MPlayer> players = faction.getMPlayers();
			
			// ... calculate the reward ...
			int playerCount = players.size();
			double reward = econLandReward * landCount / playerCount;
			
			// ... and grant the reward.
			String description = String.format("own %s faction land divided among %s members", landCount, playerCount);
			for (MPlayer player : players)
			{
				Econ.modifyMoney(player, reward, description);
			}
			
		}
	}

	public ArrayList<String> validateName(String str) {
		ArrayList<String> errors = new ArrayList();
		if (MiscUtil.getComparisonString(str).length() < MConf.get().factionNameLengthMin) {
			errors.add(Txt.parse("<i>The faction name can't be shorter than <h>%s<i> chars.",
					new Object[] { Integer.valueOf(MConf.get().factionNameLengthMin) }));
		}
		if (str.length() > MConf.get().factionNameLengthMax) {
			errors.add(Txt.parse("<i>The faction name can't be longer than <h>%s<i> chars.",
					new Object[] { Integer.valueOf(MConf.get().factionNameLengthMax) }));
		}
		char[] arrayOfChar;
		int k = (arrayOfChar = str.toCharArray()).length;
		for (int j = 0; j < k; j++) {
			char c = arrayOfChar[j];
			if (!MiscUtil.substanceChars.contains(String.valueOf(c))) {
				errors.add(Txt.parse("<i>Faction name must be alphanumeric. \"<h>%s<i>\" is not allowed.",
						new Object[] { Character.valueOf(c) }));
			}
		}
		return errors;
	}

	public Faction getByName(String name) {
		String compStr = MiscUtil.getComparisonString(name);
		for (Faction faction : getAll()) {
			if (faction.getComparisonName().equals(compStr)) {
				return faction;
			}
		}
		return null;
	}

	public Map<Rel, List<String>> getRelationNames(Faction faction, Set<Rel> rels) {
		Map<Rel, List<String>> ret = new LinkedHashMap();
		MFlag flagPeaceful = MFlag.getFlagPeaceful();
		boolean peaceful = faction.getFlag(flagPeaceful);
		for (Rel rel : rels) {
			ret.put(rel, new ArrayList());
		}
		for (Faction fac : get().getAll()) {
			if (!fac.getFlag(flagPeaceful)) {
				Rel rel = fac.getRelationTo(faction);
				List<String> names = (List) ret.get(rel);
				if (names != null) {
					String name = fac.describeTo(faction, true);
					names.add(name);
				}
			}
		}
		if (!peaceful) {
			return ret;
		}
		List<String> names = (List) ret.get(Rel.TRUCE);
		if (names == null) {
			return ret;
		}
		ret.put(Rel.TRUCE, Collections.singletonList(MConf.get().colorTruce.toString() + Txt.parse("<italic>*EVERYONE*")));

		return ret;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\entity\
 * FactionColl.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */