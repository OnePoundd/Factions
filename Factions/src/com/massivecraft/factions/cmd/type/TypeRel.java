package com.massivecraft.factions.cmd.type;

import com.massivecraft.factions.Rel;
import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;
import java.util.Set;

public class TypeRel extends TypeEnum<Rel> {
	private static TypeRel i = new TypeRel();

	public static TypeRel get() {
		return i;
	}

	public TypeRel() {
		super(Rel.class);
	}

	public String getName() {
		return "role";
	}

	public String getNameInner(Rel value) {
		return value.getName();
	}

	public Set<String> getNamesInner(Rel value) {
		return value.getNames();
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\type\
 * TypeRel.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */