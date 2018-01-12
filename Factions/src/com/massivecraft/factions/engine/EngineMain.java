package com.massivecraft.factions.engine;

import com.massivecraft.massivecore.ps.PS;

public class EngineMain {
	public EngineMain() {
	}

	/**
	 * @deprecated
	 */
	public static boolean canPlayerBuildAt(Object senderObject, PS ps, boolean verboose) {
		return EnginePermBuild.canPlayerBuildAt(senderObject, ps, verboose);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\engine\
 * EngineMain.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */