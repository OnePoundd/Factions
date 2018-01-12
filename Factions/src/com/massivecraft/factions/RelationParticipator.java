package com.massivecraft.factions;

import org.bukkit.ChatColor;

public abstract interface RelationParticipator {
	public abstract String describeTo(RelationParticipator paramRelationParticipator);

	public abstract String describeTo(RelationParticipator paramRelationParticipator, boolean paramBoolean);

	public abstract Rel getRelationTo(RelationParticipator paramRelationParticipator);

	public abstract Rel getRelationTo(RelationParticipator paramRelationParticipator, boolean paramBoolean);

	public abstract ChatColor getColorTo(RelationParticipator paramRelationParticipator);
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\
 * RelationParticipator.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */