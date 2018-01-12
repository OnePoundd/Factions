package com.massivecraft.factions.entity;

import com.massivecraft.factions.Factions;
import com.massivecraft.massivecore.store.SenderColl;
import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class MPlayerColl extends SenderColl<MPlayer> {
	private static MPlayerColl i = new MPlayerColl();

	public MPlayerColl() {
	}

	public static MPlayerColl get() {
		return i;
	}

	public void onTick() {
		super.onTick();
	}

	public void considerRemovePlayerMillis() {
		if (MConf.get().removePlayerMillisDefault <= 0.0D) {
			return;
		}
		final Collection<MPlayer> mplayersOffline = getAllOffline();

		Bukkit.getScheduler().runTaskAsynchronously(Factions.get(), new Runnable() {
			public void run() {
				for (MPlayer mplayer : mplayersOffline) {
					mplayer.considerRemovePlayerMillis(true);
				}
			}
		});
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\entity\
 * MPlayerColl.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */