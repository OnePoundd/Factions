package net.OnePoundd.factionsfly;

import com.massivecraft.factions.cmd.FactionsCommand;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;

public class CmdFactionsStealth extends FactionsCommand {
	public CmdFactionsStealth() {
		addParameter(TypeString.get(), "on/off");
	}

	public void perform() throws MassiveException {
		if (argAt(0).equals("on")) {
			msender.setStealth(true);
			msg("§a§l(!)§7 Stealth enabled, you will not effect other's flight!");
		} else if (argAt(0).equals("off")) {
			msender.setStealth(false);
			msg("§a§l(!)§7 Stealth disabled, you will now effect other's flight!");
		} else {
			msg("§c§l(!)§7 Usage: /f stealth <on/off>");
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\factionsfly\
 * CmdFactionsStealth.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */