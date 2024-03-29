package com.massivecraft.factions.cmd;

public class CmdFactionsXPlaceholder extends FactionsCommand {
	public String extensionName;

	public CmdFactionsXPlaceholder(String extensionName, String... aliases) {
		this.extensionName = extensionName;
		setSetupEnabled(false);

		addAliases(aliases);

		setDesc("Use " + extensionName);

		setOverflowSensitive(false);
	}

	public void perform() {
		msg("<b>The extension <h>%s <b>isn't installed.", new Object[] { extensionName });
		msg("<g>Learn more and download the extension here:");
		msg("<aqua>https://www.massivecraft.com/%s", new Object[] { extensionName.toLowerCase() });
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsXPlaceholder.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */