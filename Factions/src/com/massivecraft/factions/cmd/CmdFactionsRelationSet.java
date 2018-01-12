package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.cmd.type.TypeRelation;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MFlag;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.event.EventFactionsRelationChange;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.mson.Mson;
import org.bukkit.ChatColor;

public class CmdFactionsRelationSet extends FactionsCommand {
	public CmdFactionsRelationSet() {
		addParameter(TypeFaction.get(), "faction");
		addParameter(TypeRelation.get(), "relation");
	}

	public void perform() throws MassiveException {
		Faction otherFaction = (Faction) readArg();
		Rel newRelation = (Rel) readArg();
		if (!MPerm.getPermRel().has(msender, msenderFaction, true)) {
			return;
		}
		if (otherFaction == msenderFaction) {
			throw new MassiveException().setMsg("§c§l(!)§7 You cannot declare a relation to yourself!");
		}
		if (msenderFaction.getRelationWish(otherFaction) == newRelation) {
			throw new MassiveException().setMsg("§c§l(!)§7 You've already requested or already have that relation!");
		}
		for (Faction faction : FactionColl.get().getAll()) {
			if ((newRelation.equals(Rel.ALLY)) && (faction.getRelationTo(msenderFaction).equals(Rel.ALLY))) {
				if ((!faction.getName().equals("WarZone")) && (!faction.getName().equals("SafeZone"))) {
					throw new MassiveException()
							.setMsg("§c§l(!)§7 Your faction already has the maximum of one ally!");
				}
			} else if ((newRelation.equals(Rel.TRUCE)) && (faction.getRelationTo(msenderFaction).equals(Rel.TRUCE))
					&& (!faction.getName().equals("WarZone")) && (!faction.getName().equals("SafeZone"))) {
				throw new MassiveException().setMsg("§c§l(!)§7 Your faction already has the maximum of one truce!");
			}
		}
		EventFactionsRelationChange event = new EventFactionsRelationChange(sender, msenderFaction, otherFaction,
				newRelation);
		event.run();
		if (event.isCancelled()) {
			return;
		}
		newRelation = event.getNewRelation();

		msenderFaction.setRelationWish(otherFaction, newRelation);
		Rel currentRelation = msenderFaction.getRelationTo(otherFaction, true);
		if (newRelation == currentRelation) {
			otherFaction.msg("%s<i> is now %s.",
					new Object[] { msenderFaction.describeTo(otherFaction, true), newRelation.getDescFactionOne() });
			msenderFaction.msg("%s<i> is now %s.",
					new Object[] { otherFaction.describeTo(msenderFaction, true), newRelation.getDescFactionOne() });
		} else {
			MassiveCommand command = CmdFactions.get().cmdFactionsRelation.cmdFactionsRelationSet;
			String colorOne = newRelation.getColor() + newRelation.getDescFactionOne();

			Mson factionsRelationshipChange = mson(new Object[] {
					Mson.parse("%s<i> wishes to be %s.",
							new Object[] { msenderFaction.describeTo(otherFaction, true), colorOne }),
					Mson.SPACE, mson(new Object[] { "[Accept]" }).color(ChatColor.AQUA).command(command,
							new String[] { msenderFaction.getName(), newRelation.name() }) });

			otherFaction.sendMessage(factionsRelationshipChange);
			msenderFaction.msg("%s<i> were informed that you wish to be %s<i>.",
					new Object[] { otherFaction.describeTo(msenderFaction, true), colorOne });
		}
		if ((newRelation != Rel.TRUCE) && (otherFaction.getFlag(MFlag.getFlagPeaceful()))) {
			otherFaction.msg("<i>This will have no effect while your faction is peaceful.");
			msenderFaction.msg("<i>This will have no effect while their faction is peaceful.");
		}
		if ((newRelation != Rel.TRUCE) && (msenderFaction.getFlag(MFlag.getFlagPeaceful()))) {
			otherFaction.msg("<i>This will have no effect while their faction is peaceful.");
			msenderFaction.msg("<i>This will have no effect while your faction is peaceful.");
		}
		msenderFaction.changed();
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsRelationSet.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */