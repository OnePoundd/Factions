package com.massivecraft.factions.cmd.type;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.MPlayerColl;
import com.massivecraft.massivecore.command.type.Type;

public class TypeMPlayer {
	public TypeMPlayer() {
	}

	public static Type<MPlayer> get() {
		return MPlayerColl.get().getTypeEntity();
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\type\
 * TypeMPlayer.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */