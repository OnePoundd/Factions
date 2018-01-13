package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.ps.PS;
import java.util.LinkedHashSet;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CmdFactionsCapital extends FactionsCommand {
	public CmdFactionsCapital() {
		addAliases(new String[] { "capital" });
	}

	public void perform() throws MassiveException {
		for (PS ps : getChunks()) {
			Faction factionAt = BoardColl.get().getFactionAt(ps);
			if ((factionAt.hasCapital()) && (factionAt != msenderFaction)
					&& (factionAt.getCapitalChunkX() == ps.getChunkX().intValue())
					&& (factionAt.getCapitalChunkZ() == ps.getChunkZ().intValue())) {
				msg("§c§l(!)§7 You cannot place your capital within 50 chunks of another!");
				msg("§b§l(!)§7 Incase you don't know, 50 chunks is the minimum distance between two capitals so that each faction can have 20 chunks worth of 'buffer' claims and still leave a 10 chunk gap for other factions to build cannons in to raid from!");

				return;
			}
		}
		if (msenderFaction.isUnderAttack()) {
			msg("§c§l(!)§7 You cannot move your faction's capital whilst under attack!");
			return;
		}
		if (MPerm.getPermSetCapital().has(msender, msenderFaction, true)) {
			if (BoardColl.get().getFactionAt(PS.valueOf(msender.getPlayer().getLocation())).equals(msenderFaction)) {
				msenderFaction.msg("§a§l(!)§7 Your faction's capital has successfully been set!");
				msenderFaction.msg(
						"§b§l(!)§7 Your faction can now begin to gain victory points! Attend and win events such as conquest or koth, kill mob bosses in warzone, capture and hold the castle, grind your way up /f wealth or even raid another faction to start collecting victory points and climb your way up /f top!");

				msenderFaction.setCapitalChunkX(msender.getPlayer().getLocation().getChunk().getX());
				msenderFaction.setCapitalChunkZ(msender.getPlayer().getLocation().getChunk().getZ());
				msenderFaction.setHasCapital(true);
				return;
			}
			msg("§c§l(!)§7 Your faction must own this chunk before making it the capital!");
		}
	}

	public Set<PS> getChunks() throws MassiveException {
		PS chunk = PS.valueOf(me.getLocation()).getChunk(true);
		Set<PS> chunks = new LinkedHashSet();

		chunks.add(chunk);

		Integer radiusZero = getRadiusZero();
		for (int dx = -radiusZero.intValue(); dx <= radiusZero.intValue(); dx++) {
			for (int dz = -radiusZero.intValue(); dz <= radiusZero.intValue(); dz++) {
				int x = chunk.getChunkX().intValue() + dx;
				int z = chunk.getChunkZ().intValue() + dz;

				chunks.add(chunk.withChunkX(Integer.valueOf(x)).withChunkZ(Integer.valueOf(z)));
			}
		}
		return chunks;
	}

	public Integer getRadius() throws MassiveException {
		return Integer.valueOf(50);
	}

	public Integer getRadiusZero() throws MassiveException {
		Integer ret = getRadius();
		return Integer.valueOf(ret.intValue() - 1);
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsCapital.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */