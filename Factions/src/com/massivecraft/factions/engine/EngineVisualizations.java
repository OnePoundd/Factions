package com.massivecraft.factions.engine;

import com.massivecraft.factions.util.VisualizeUtil;
import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

public class EngineVisualizations extends Engine {
	private static EngineVisualizations i = new EngineVisualizations();

	public EngineVisualizations() {
	}

	public static EngineVisualizations get() {
		return i;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerMoveClearVisualizations(PlayerMoveEvent event) {
		if (MUtil.isSameBlock(event)) {
			return;
		}
		VisualizeUtil.clear(event.getPlayer());
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\engine\
 * EngineVisualizations.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */