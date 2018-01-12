package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.cmd.req.ReqHasntFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsCreate;
import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.factions.event.EventFactionsMembershipChange.MembershipChangeReason;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.store.MStore;
import java.util.ArrayList;

public class CmdFactionsCreate extends FactionsCommand {
	public CmdFactionsCreate() {
		addParameter(TypeString.get(), "name");

		addRequirements(new Requirement[] { ReqHasntFaction.get() });
	}

	public void perform() throws MassiveException {
		String newName = (String) readArg();
		if (FactionColl.get().isNameTaken(newName)) {
			msg("<b>That name is already in use.");
			return;
		}
		ArrayList<String> nameValidationErrors = FactionColl.get().validateName(newName);
		if (nameValidationErrors.size() > 0) {
			message(nameValidationErrors);
			return;
		}
		String factionId = MStore.createId();

		Faction faction = (Faction) FactionColl.get().create(factionId);
		faction.setName(newName);

		msender.setRole(Rel.LEADER);
		msender.setFaction(faction);

		EventFactionsMembershipChange joinEvent = new EventFactionsMembershipChange(sender, msender, faction,
				EventFactionsMembershipChange.MembershipChangeReason.CREATE);
		joinEvent.run();

		msg("§a§l(!)§7 You created a faction! Invite others by running /f invite!");
		msg("§b§l(!)§7 The next thing to do is make a base! Use /f wild to go to a random location in the world. Once you're there, run the command /f claim to mark your land! Oh, and if you want more players to make your faction more powerful, run /f recruitment!");

		EventFactionsCreate createEvent = new EventFactionsCreate(sender, factionId, newName);
		createEvent.run();
		if (createEvent.isCancelled()) {
			return;
		}
		if (MConf.get().logFactionCreate) {
			Factions.get().log(new Object[] { msender.getName() + " created a new faction: " + newName });
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsCreate.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */