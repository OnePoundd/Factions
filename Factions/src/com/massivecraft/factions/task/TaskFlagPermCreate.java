package com.massivecraft.factions.task;

import com.massivecraft.factions.entity.MFlag;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.massivecore.ModuloRepeatTask;

public class TaskFlagPermCreate extends ModuloRepeatTask {
	private static final long MILLIS_INTERVAL = 3000L;
	private static TaskFlagPermCreate i = new TaskFlagPermCreate();

	public static TaskFlagPermCreate get() {
		return i;
	}

	public TaskFlagPermCreate() {
		super(3000L);
	}

	public void setDelayMillis(long delayMillis) {
	}

	public void invoke(long now) {
		MPerm.getAll();
		MFlag.getAll();
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\task\
 * TaskFlagPermCreate.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */