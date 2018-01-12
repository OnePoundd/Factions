package com.massivecraft.factions.comparator;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.Named;
import com.massivecraft.massivecore.comparator.ComparatorAbstract;

public class ComparatorMPlayerInactivity extends ComparatorAbstract<MPlayer> implements Named {
	private static ComparatorMPlayerInactivity i = new ComparatorMPlayerInactivity();

	public ComparatorMPlayerInactivity() {
	}

	public static ComparatorMPlayerInactivity get() {
		return i;
	}

	public String getName() {
		return "Time";
	}

	public int compareInner(MPlayer m1, MPlayer m2) {
		boolean o1 = m1.isOnline();
		boolean o2 = m2.isOnline();
		if ((o1) && (o2)) {
			return 0;
		}
		if (o1) {
			return -1;
		}
		if (o2) {
			return 1;
		}
		long r1 = m1.getLastActivityMillis();
		long r2 = m2.getLastActivityMillis();

		return (int) (r1 - r2);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\comparator\
 * ComparatorMPlayerInactivity.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */