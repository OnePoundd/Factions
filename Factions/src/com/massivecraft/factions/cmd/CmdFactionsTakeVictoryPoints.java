package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsntPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CmdFactionsTakeVictoryPoints extends FactionsCommand {
	public CmdFactionsTakeVictoryPoints() {
		addAliases(new String[] { "vptake", "victorypointstake" });
		addParameter(TypeInteger.get(), "amountofpoints");
		addParameter(TypeString.get(), "faction");
		addRequirements(new Requirement[] { RequirementIsntPlayer.get() });
	}

	public void perform() throws MassiveException {
		try {
			FactionColl.get().getByName((String) getArgs().get(1))
					.takeVicotryPoints(Integer.parseInt((String) getArgs().get(0)));
			sender.sendMessage("§b§lNotice§3- §aSuccessfully took points from the faction!");
		} catch (NumberFormatException e) {
			sender.sendMessage("§b§lNotice§3- §cYou must give an integer number of points!");
		} catch (NullPointerException e) {
			sender.sendMessage("§b§lNotice§3- §cThe faction stated could not be found!");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsTakeVictoryPoints.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */