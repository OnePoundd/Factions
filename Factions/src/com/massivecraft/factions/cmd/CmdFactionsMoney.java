package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.req.ReqBankCommandsEnabled;
import com.massivecraft.massivecore.command.requirement.Requirement;

public class CmdFactionsMoney extends FactionsCommand {
	public CmdFactionsMoneyBalance cmdMoneyBalance = new CmdFactionsMoneyBalance();
	public CmdFactionsMoneyDeposit cmdMoneyDeposit = new CmdFactionsMoneyDeposit();
	public CmdFactionsMoneyWithdraw cmdMoneyWithdraw = new CmdFactionsMoneyWithdraw();
	public CmdFactionsMoneyTransferFf cmdMoneyTransferFf = new CmdFactionsMoneyTransferFf();
	public CmdFactionsMoneyTransferFp cmdMoneyTransferFp = new CmdFactionsMoneyTransferFp();
	public CmdFactionsMoneyTransferPf cmdMoneyTransferPf = new CmdFactionsMoneyTransferPf();

	public CmdFactionsMoney() {
		addRequirements(new Requirement[] { ReqBankCommandsEnabled.get() });
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsMoney.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */