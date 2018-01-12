package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.ps.PS;
import java.util.LinkedHashSet;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class CmdFactionsSetLine extends CmdFactionsSetX {
	public static final BlockFace[] axis = { BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST };

	public CmdFactionsSetLine(boolean claim) {
		super(claim);

		addAliases(new String[] { "line" });

		addParameter(TypeInteger.get(), "amount");

		addParameter(null, TypeString.get(), "direction");
		if (claim) {
			addParameter(TypeFaction.get(), "faction", "you");
			setFactionArgIndex(2);
		}
	}

	public Integer getRadius() throws MassiveException {
		int radius = ((Integer) readArgAt(0)).intValue();
		if (radius < 1) {
			msg("<b>If you specify an amount, it must be at least 1.");
			return null;
		}
		if ((radius > MConf.get().setLineMax) && (!msender.isOverriding())) {
			msg("<b>The maximum amount allowed is <h>%s<b>.", new Object[] { Integer.valueOf(MConf.get().setLineMax) });
			return null;
		}
		return Integer.valueOf(radius);
	}

	public BlockFace getDirection() throws MassiveException {
		String direction = (String) readArgAt(1);
		BlockFace blockFace;
		if (direction == null) {
			blockFace = axis[(Math.round(me.getLocation().getYaw() / 90.0F) & 0x3)];
		} else {
			if (direction.equalsIgnoreCase("north")) {
				blockFace = BlockFace.NORTH;
			} else {
				if (direction.equalsIgnoreCase("east")) {
					blockFace = BlockFace.EAST;
				} else {
					if (direction.equalsIgnoreCase("south")) {
						blockFace = BlockFace.SOUTH;
					} else {
						if (direction.equalsIgnoreCase("west")) {
							blockFace = BlockFace.WEST;
						} else {
							msender.msg(
									ChatColor.LIGHT_PURPLE + direction + ChatColor.RED + " is not a valid direction!");
							return null;
						}
					}
				}
			}
		}
		return blockFace;
	}

	public Set<PS> getChunks() throws MassiveException {
		if ((getDirection() == null) || (getRadius() == null)) {
			return null;
		}
		PS chunk = PS.valueOf(me.getLocation()).getChunk(true);
		Set<PS> chunks = new LinkedHashSet();

		chunks.add(chunk);

		Location location = me.getLocation();
		for (int i = 0; i < getRadius().intValue() - 1; i++) {
			location = location.add(getDirection().getModX() * 16, 0.0D, getDirection().getModZ() * 16);
			chunks.add(PS.valueOf(location).getChunk(true));
		}
		return chunks;
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsSetLine.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */