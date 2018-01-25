package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.MConf;
import com.massivecraft.massivecore.command.MassiveCommandDeprecated;
import java.util.List;
import net.OnePoundd.FactionUpgrades.CmdFactionsUpgrade;
import net.OnePoundd.factionsfly.CmdFactionsStealth;

public class CmdFactions extends FactionsCommand {
	private static CmdFactions i = new CmdFactions();

	public static CmdFactions get() {
		return i;
	}

	public CmdFactionsFind cmdFactionsList = new CmdFactionsFind();
	public CmdFactionsFaction cmdFactionsFaction = new CmdFactionsFaction();
	public CmdFactionsPlayer cmdFactionsPlayer = new CmdFactionsPlayer();
	public CmdFactionsJoin cmdFactionsJoin = new CmdFactionsJoin();
	public CmdFactionsLeave cmdFactionsLeave = new CmdFactionsLeave();
	public CmdFactionsHome cmdFactionsHome = new CmdFactionsHome();
	public CmdFactionsMap cmdFactionsMap = new CmdFactionsMap();
	public CmdFactionsCreate cmdFactionsCreate = new CmdFactionsCreate();
	public CmdFactionsName cmdFactionsName = new CmdFactionsName();
	public CmdFactionsMotd cmdFactionsMotd = new CmdFactionsMotd();
	public CmdFactionsSethome cmdFactionsSethome = new CmdFactionsSethome();
	public CmdFactionsUnsethome cmdFactionsUnsethome = new CmdFactionsUnsethome();
	public CmdFactionsInvite cmdFactionsInvite = new CmdFactionsInvite();
	public CmdFactionsKick cmdFactionsKick = new CmdFactionsKick();
	public CmdFactionsTitle cmdFactionsTitle = new CmdFactionsTitle();
	public CmdFactionsRank cmdFactionsRank = new CmdFactionsRank();
	public CmdFactionsRankOld cmdFactionsRankOldLeader = new CmdFactionsRankOld("leader");
	public CmdFactionsRankOld cmdFactionsRankOldOwner = new CmdFactionsRankOld("owner");
	public CmdFactionsRankOld cmdFactionsRankOldOfficer = new CmdFactionsRankOld("officer");
	public CmdFactionsRankOld cmdFactionsRankOldModerator = new CmdFactionsRankOld("moderator");
	public CmdFactionsRankOld cmdFactionsRankOldPromote = new CmdFactionsRankOld("promote");
	public CmdFactionsRankOld cmdFactionsRankOldDemote = new CmdFactionsRankOld("demote");
	public CmdFactionsMoney cmdFactionsMoney = new CmdFactionsMoney();
	public CmdFactionsSeeChunk cmdFactionsSeeChunk = new CmdFactionsSeeChunk();
	public CmdFactionsTerritorytitles cmdFactionsTerritorytitles = new CmdFactionsTerritorytitles();
	public CmdFactionsStatus cmdFactionsStatus = new CmdFactionsStatus();
	public CmdFactionsClaim cmdFactionsClaim = new CmdFactionsClaim();
	public CmdFactionsUnclaim cmdFactionsUnclaim = new CmdFactionsUnclaim();
	public CmdFactionsAccess cmdFactionsAccess = new CmdFactionsAccess();
	public CmdFactionsRelation cmdFactionsRelation = new CmdFactionsRelation();
	public CmdFactionsRelationOld cmdFactionsRelationOldAlly = new CmdFactionsRelationOld("ally");
	public CmdFactionsRelationOld cmdFactionsRelationOldTruce = new CmdFactionsRelationOld("truce");
	public CmdFactionsRelationOld cmdFactionsRelationOldNeutral = new CmdFactionsRelationOld("neutral");
	public CmdFactionsRelationOld cmdFactionsRelationOldEnemy = new CmdFactionsRelationOld("enemy");
	public CmdFactionsPerm cmdFactionsPerm = new CmdFactionsPerm();
	public CmdFactionsFlag cmdFactionsFlag = new CmdFactionsFlag();
	public CmdFactionsUnstuck cmdFactionsUnstuck = new CmdFactionsUnstuck();
	public CmdFactionsExpansions cmdFactionsExpansions = new CmdFactionsExpansions();
	public CmdFactionsOverride cmdFactionsOverride = new CmdFactionsOverride();
	public CmdFactionsDisband cmdFactionsDisband = new CmdFactionsDisband();
	public CmdFactionsPowerBoost cmdFactionsPowerBoost = new CmdFactionsPowerBoost();
	public CmdFactionsSetpower cmdFactionsSetpower = new CmdFactionsSetpower();
	public CmdFactionsStealth cmdFactionsStealth = new CmdFactionsStealth();
	public CmdFactionsRecruitment cmdFactionsRecruiting = new CmdFactionsRecruitment();
	public CmdFactionsUrl cmdFactionsUrl = new CmdFactionsUrl();
	public CmdFactionsUpgrade cmdFactionsUpgrade = new CmdFactionsUpgrade();
	public CmdFactionsWild cmdFactionsWild = new CmdFactionsWild();
	public CmdFactionsWarp cmdFactionsWarp = new CmdFactionsWarp();
	public CmdFactionsListwarps cmdFactionsListwarps = new CmdFactionsListwarps();
	public CmdFactionsSetwarp cmdFactionsSetwarp = new CmdFactionsSetwarp();
	public CmdFactionsDeletewarp cmdFactionsDeletewarp = new CmdFactionsDeletewarp();
	public CmdFactionsTop cmdFactionsTop = new CmdFactionsTop();
	public CmdFactionsAddVictoryPoints cmdFactionsAddVictoryPoints = new CmdFactionsAddVictoryPoints();
	public CmdFactionsTakeVictoryPoints cmdFactionsTakeVictoryPoints = new CmdFactionsTakeVictoryPoints();
	public CmdFactionsWealth cmdFactionsWealth = new CmdFactionsWealth();
	public CmdFactionsCapital cmdFactionsCapital = new CmdFactionsCapital();
	public CmdFactionsDescription cmdFactionsDescription = new CmdFactionsDescription();
	public CmdFactionsTnt cmdFactionsTnt = new CmdFactionsTnt();
	public CmdFactionsBoost cmdFactionsBoost = new CmdFactionsBoost();
	public CmdFactionsCastle cmdFactionsCastle = new CmdFactionsCastle();
	public CmdFactionsWeeklyTop cmdFactionsWeeklyTop = new CmdFactionsWeeklyTop();
	public CmdFactionsBanner cmdFactionsBanner = new CmdFactionsBanner();
	public CmdFactionsAssist cmdFactionsAssist = new CmdFactionsAssist();

	public CmdFactions() {
		addChild(new MassiveCommandDeprecated(cmdFactionsClaim.cmdFactionsClaimAuto, new String[] { "autoclaim" }));
		addChild(new MassiveCommandDeprecated(cmdFactionsUnclaim.cmdFactionsUnclaimAll, new String[] { "unclaimall" }));
		addChild(new MassiveCommandDeprecated(cmdFactionsFlag, new String[] { "open" }));
		addChild(new MassiveCommandDeprecated(cmdFactionsFaction, new String[0]));
	}

	public List<String> getAliases() {
		return MConf.get().aliasesF;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\CmdFactions
 * .class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */