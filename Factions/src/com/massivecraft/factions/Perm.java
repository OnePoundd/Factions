package com.massivecraft.factions;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum Perm implements Identified {
	BASECOMMAND, POWER_BOOST_PLAYER, POWER_BOOST_FACTION, ACCESS, ACCESS_VIEW, ACCESS_PLAYER, ACCESS_FACTION, OVERRIDE, CLAIM, CLAIM_ONE, CLAIM_AUTO, CLAIM_FILL, CLAIM_SQUARE, CLAIM_CIRCLE, CLAIM_ALL, CREATE, DESCRIPTION, DISBAND, EXPANSIONS, FACTION, FLAG, FLAG_LIST, FLAG_SET, FLAG_SHOW, HOME, INVITE, INVITE_LIST, INVITE_LIST_OTHER, INVITE_ADD, INVITE_REMOVE, JOIN, JOIN_OTHERS, KICK, LEAVE, FIND, MAP, MONEY, MONEY_BALANCE, MONEY_BALANCE_ANY, MONEY_DEPOSIT, MONEY_F2F, MONEY_F2P, MONEY_P2F, MONEY_WITHDRAW, MOTD, OPEN, PERM, PERM_LIST, PERM_SET, PERM_SHOW, PLAYER, POWERBOOST, RANK, RANK_SHOW, RANK_ACTION, RELATION, RELATION_SET, RELATION_LIST, RELATION_WISHES, SEECHUNK, SEECHUNKOLD, SETHOME, SETPOWER, STATUS, NAME, TITLE, TITLE_COLOR, TERRITORYTITLES, UNCLAIM, UNCLAIM_ONE, UNCLAIM_AUTO, UNCLAIM_FILL, UNCLAIM_SQUARE, UNCLAIM_CIRCLE, UNCLAIM_ALL, UNSETHOME, UNSTUCK, VERSION, URL, RECRUITMENT, TRUSTED, UPGRADE, SETWARP, DELETEWARP, WARP, STEALTH, WILD, MERCHANT, CONFIG, POWERBOOST_SET, LISTWARPS, TOP, ADD_VICTORY_POINTS, TAKE_VICTORY_POINTS, CAPITAL, CLASS, SET_CLASS, ADD_COINS, TAKE_COINS, COINS, WEALTH, TNT, TNT_ADD, TNT_TAKE, TNT_FILL, TNT_AMOUNT, BOOST, BOOST_VIEW, BOOST_SET_FACTION, CASTLE, CASTLE_SET, WEEKLY_TOP;

	private final String id;

	public String getId() {
		return id;
	}

	private Perm() {
		id = PermissionUtil.createPermissionId(Factions.get(), this);
	}

	public boolean has(Permissible permissible, boolean verboose) {
		return PermissionUtil.hasPermission(permissible, this, verboose);
	}

	public boolean has(Permissible permissible) {
		return PermissionUtil.hasPermission(permissible, this);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\Perm.class Java
 * compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */