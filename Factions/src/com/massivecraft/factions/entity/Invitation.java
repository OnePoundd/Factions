package com.massivecraft.factions.entity;

import com.massivecraft.massivecore.store.EntityInternal;

public class Invitation extends EntityInternal<Invitation> {
	private String inviterId;
	private Long creationMillis;

	public Invitation load(Invitation that) {
		inviterId = inviterId;
		creationMillis = creationMillis;

		return this;
	}

	public String getInviterId() {
		return inviterId;
	}

	public void setInviterId(String inviterId) {
		this.inviterId = inviterId;
	}

	public Long getCreationMillis() {
		return creationMillis;
	}

	public void setCreationMillis(Long creationMillis) {
		this.creationMillis = creationMillis;
	}

	public Invitation() {
		this(null, null);
	}

	public Invitation(String inviterId, Long creationMillis) {
		this.inviterId = inviterId;
		this.creationMillis = creationMillis;
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\entity\
 * Invitation.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */