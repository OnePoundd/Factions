package com.massivecraft.factions.cmd.type;

import com.massivecraft.factions.event.EventFactionsChunkChangeType;
import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;

public class TypeFactionChunkChangeType extends TypeEnum<EventFactionsChunkChangeType> {
	private static TypeFactionChunkChangeType i = new TypeFactionChunkChangeType();

	public static TypeFactionChunkChangeType get() {
		return i;
	}

	public TypeFactionChunkChangeType() {
		super(EventFactionsChunkChangeType.class);
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\type\
 * TypeFactionChunkChangeType.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */