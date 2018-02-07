package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MFlag;
import com.massivecraft.factions.entity.MFlagColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivecore.pager.Stringifier;
import com.massivecraft.massivecore.predicate.Predicate;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class CmdFactionsFlagList extends FactionsCommand {

	// -------------------------------------------- //

	// CONSTRUCT

	// -------------------------------------------- //

	

	public CmdFactionsFlagList()

	{

		// Parameters

		this.addParameter(Parameter.getPage());

	}

	

	// -------------------------------------------- //

	// OVERRIDE

	// -------------------------------------------- //

	

	@Override

	public void perform() throws MassiveException

	{

		// Parameter

		final int page = this.readArg();

		final MPlayer mplayer = msender;

		

		// Pager create

		String title = "Flag List for " + msenderFaction.describeTo(mplayer);

		final Pager<MFlag> pager = new Pager<>(this, title, page, new Stringifier<MFlag>()

		{

			@Override

			public String toString(MFlag mflag, int index)

			{

				return mflag.getStateDesc(false, false, true, true, true, false);

			}

		});

		

		Bukkit.getScheduler().runTaskAsynchronously(Factions.get(), new Runnable()

		{

			@Override

			public void run()

			{

				// Get items

				List<MFlag> items = MFlagColl.get().getAll(mplayer.isOverriding() ? null : new Predicate<MFlag>()

				{

					@Override

					public boolean apply(MFlag mflag)

					{

						return mflag.isVisible();

					}

				});

				

				// Pager items

				pager.setItems(items);

			

				// Pager message

				pager.message();

			}

		});

	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\cmd\
 * CmdFactionsFlagList.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */