package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.teleport.Destination;
import com.massivecraft.massivecore.teleport.DestinationSimple;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.OnePoundd.Essentials.Teleport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CmdFactionsWild extends FactionsCommand implements Listener {
	private int maxX = MConf.get().wildMaxX;
	private int maxY = MConf.get().wildMaxY;
	List<String> enabledWorlds = (List) MConf.get().wildEnabledWorlds.get("worlds");
	private int warmup = MConf.get().wildWarmupSeconds;
	private int cooldown = MConf.get().wildCooldownSeconds;
	public static Map<UUID, Long> commandCooldown = new HashMap();

	public CmdFactionsWild() {
		aliases.add("wild");
	}

	public void perform() throws MassiveException {
		if (!enabledWorlds.contains(me.getWorld().getName())) {
			msg(ChatColor.RED + "This world has wilderness teleporting disabled!");
			return;
		}
		if ((commandCooldown.containsKey(me.getUniqueId()))
				&& (((Long) commandCooldown.get(me.getUniqueId())).longValue() > System.currentTimeMillis())) {
			int timeLeft = (int) ((((Long) commandCooldown.get(me.getUniqueId())).longValue()
					- System.currentTimeMillis()) / 1000L);

			msg(ChatColor.RED + "You cannot use this command yet wait " + formatTime(timeLeft));
			return;
		}
		msg(ChatColor.YELLOW + "Looking for wilderness!");

		boolean successful = false;
		int trys = 0;

		Location loc = null;
		while (!successful) {
			int randomX = (int) (Math.random() * maxX);
			int randomY = (int) (Math.random() * maxY);

			loc = new Location(me.getWorld(), randomX, 0.0D, randomY);

			PS ps = PS.valueOf(loc);
			trys++;
			if (BoardColl.get().getFactionAt(ps).isNone()) {
				successful = true;
			}
			if (trys > 9) {
				msg(ChatColor.RED + "Could not find wilderness. Try again!");
				return;
			}
		}
		Location b = loc.getWorld().getHighestBlockAt(loc).getLocation();

		Destination destination = new DestinationSimple(PS.valueOf(b));
		Teleport.tryTeleport(msender.getPlayer(), b);
		commandCooldown.put(me.getUniqueId(), Long.valueOf(System.currentTimeMillis() + cooldown * 1000));
	}

	public String formatTime(int secondsCount) {
		int seconds = secondsCount % 60;
		secondsCount -= seconds;

		long minutesCount = secondsCount / 60;
		long minutes = minutesCount % 60L;
		minutesCount -= minutes;

		long hoursCount = minutesCount / 60L;
		if (hoursCount == 0L) {
			return minutes + " minutes " + seconds + " seconds";
		}
		if (minutes == 0L) {
			return seconds + " seconds";
		}
		return hoursCount + " hours " + minutes + " minutes " + seconds + " seconds";
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsWild.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */