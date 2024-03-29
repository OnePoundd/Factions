package com.massivecraft.factions.cmd.type;

import com.massivecraft.factions.comparator.ComparatorMPlayerInactivity;
import com.massivecraft.factions.comparator.ComparatorMPlayerPower;
import com.massivecraft.factions.comparator.ComparatorMPlayerRole;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.type.TypeAbstractChoice;
import java.util.Comparator;

public class TypeSortMPlayer extends TypeAbstractChoice<Comparator<MPlayer>> {
	private static TypeSortMPlayer i = new TypeSortMPlayer();

	public static TypeSortMPlayer get() {
		return i;
	}

	public TypeSortMPlayer() {
		super(Comparator.class);
		setAll(new Comparator[] { ComparatorMPlayerRole.get(), ComparatorMPlayerPower.get(),
				ComparatorMPlayerInactivity.get() });
	}

	public String getName() {
		return "player sorter";
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\type\
 * TypeSortMPlayer.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */