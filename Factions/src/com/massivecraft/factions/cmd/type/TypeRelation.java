package com.massivecraft.factions.cmd.type;

import com.massivecraft.factions.Rel;

public class TypeRelation extends TypeRel {
	private static TypeRelation i = new TypeRelation();

	public static TypeRelation get() {
		return i;
	}

	public TypeRelation() {
		setAll(new Rel[] { Rel.NEUTRAL, Rel.TRUCE, Rel.ALLY, Rel.ENEMY });
	}

	public String getName() {
		return "relation";
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\type\
 * TypeRelation.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */