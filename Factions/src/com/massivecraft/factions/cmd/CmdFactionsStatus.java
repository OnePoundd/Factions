package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.comparator.ComparatorMPlayerInactivity;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.money.Money;
import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class CmdFactionsStatus extends FactionsCommand {
	public CmdFactionsStatus() {
		addParameter(Parameter.getPage());
		addParameter(TypeFaction.get(), "faction", "you");
	}

	public void perform() throws MassiveException {
		int page = ((Integer) readArg()).intValue();
		Faction faction = (Faction) readArg(msenderFaction);
		Comparator<MPlayer> sortedBy = (Comparator) readArg(ComparatorMPlayerInactivity.get());
		if (!MPerm.getPermStatus().has(msender, faction, true)) {
			return;
		}
		List<MPlayer> mplayers = faction.getMPlayers();
		Collections.sort(mplayers, sortedBy);

		int size = faction.getName().length();
		String name = faction.getName(msender);
		String title = "";
		if (size == 3) {
			title = "§8§l§m-------------------§7§l[ §d" + name + "§7§l ]§8§l§m---------------------";
		} else if (size == 4) {
			title = "§8§l§m-------------------§7§l[ §d" + name + "§7§l ]§8§l§m--------------------";
		} else if (size == 5) {
			title = "§8§l§m-------------------§7§l[ §d" + name + "§7§l ]§8§l§m-------------------";
		} else if (size == 6) {
			title = "§8§l§m------------------§7§l[ §d" + name + "§7§l ]§8§l§m-------------------";
		} else if (size == 7) {
			title = "§8§l§m------------------§7§l[ §d" + name + "§7§l ]§8§l§m------------------";
		} else if (size == 8) {
			title = "§8§l§m-----------------§7§l[ §d" + name + "§7§l ]§8§l§m------------------";
		} else if (size == 9) {
			title = "§8§l§m-----------------§7§l[ §d" + name + "§7§l ]§8§l§m-----------------";
		} else if (size == 10) {
			title = "§8§l§m----------------§7§l[ §d" + name + "§7§l ]§8§l§m-----------------";
		} else if (size == 11) {
			title = "§8§l§m---------------§7§l[ §d" + name + "§7§l ]§8§l§m-----------------";
		} else {
			title = "§8§l§m---------------§7§l[ §dWilderness§7§l ]§8§l§m-----------------";
		}
		msg(title);
		for (MPlayer mplayer : faction.getMPlayers()) {
			String displayName;
			if (mplayer.isOnline()) {
				displayName = "§7 - §a" + mplayer.getName();
			} else {
				displayName = "§7 - §c" + mplayer.getName();
			}
			double currentPower = mplayer.getPower();
			double maxPower = mplayer.getPowerMax();
			String power = Txt.parse("§7Power: §b%s/%s", new Object[] {
					String.format("%.1f", new Object[] { Double.valueOf(currentPower) }), Double.valueOf(maxPower) });

			double bal = Money.get(mplayer);
			String balance = "§7Balance: §b" + Money.format(bal);

			long lastActiveMillis = mplayer.getLastActivityMillis() - System.currentTimeMillis();
			LinkedHashMap<TimeUnit, Long> activeTimes = TimeDiffUtil
					.limit(TimeDiffUtil.unitcounts(lastActiveMillis, TimeUnit.getAllButMillis()), 3);
			String lastActive = "";
			if (!mplayer.isOnline()) {
				lastActive = Txt.parse("§7Last active: §b" + TimeDiffUtil.formatedMinimal(activeTimes, "<i>"));
			}
			msg("%s %s %s %s", new Object[] { displayName, balance, power, lastActive });
		}
		msg("§8§l§m---------------------------------------------");
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsStatus.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */