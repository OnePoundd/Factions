package com.massivecraft.factions.entity;

import com.massivecraft.massivecore.store.Coll;

public class MConfColl extends Coll<MConf> {
	private static MConfColl i = new MConfColl();

	public MConfColl() {
	}

	public static MConfColl get() {
		return i;
	}

	public void onTick() {
		super.onTick();
	}

	public void setActive(boolean active) {
		super.setActive(active);
		if (!active) {
			return;
		}
		MConf.i = (MConf) get("instance", true);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\entity\
 * MConfColl.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */