package com.massivecraft.factions;

import com.massivecraft.factions.adapter.BoardAdapter;
import com.massivecraft.factions.adapter.BoardMapAdapter;
import com.massivecraft.factions.adapter.RelAdapter;
import com.massivecraft.factions.adapter.TerritoryAccessAdapter;
import com.massivecraft.factions.chat.ChatActive;
import com.massivecraft.factions.cmd.CmdFactionsBanner;
import com.massivecraft.factions.cmd.CmdFactionsTop;
import com.massivecraft.factions.cmd.CmdFactionsWealth;
import com.massivecraft.factions.cmd.type.TypeFactionChunkChangeType;
import com.massivecraft.factions.cmd.type.TypeRel;
import com.massivecraft.factions.engine.EngineEcon;
import com.massivecraft.factions.entity.Board;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MConfColl;
import com.massivecraft.factions.entity.MFlagColl;
import com.massivecraft.factions.entity.MPermColl;
import com.massivecraft.factions.entity.MPlayerColl;
import com.massivecraft.factions.event.EventFactionsChunkChangeType;
import com.massivecraft.factions.mixin.PowerMixin;
import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.command.type.RegistryType;
import com.massivecraft.massivecore.predicate.Predicate;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.xlib.gson.GsonBuilder;
import java.util.List;
import net.OnePoundd.Essentials.Teleport;
import net.OnePoundd.FactionUpgrades.CmdFactionsUpgrade;
import net.OnePoundd.FactionUpgrades.Upgrades;
import net.OnePoundd.Patches.Banner;
import net.OnePoundd.Patches.BlockManipulation;
import net.OnePoundd.Patches.Castle;
import net.OnePoundd.Patches.Experience;
import net.OnePoundd.Patches.Introduction;
import net.OnePoundd.Patches.McmmoBoosters;
import net.OnePoundd.Patches.NPC;
import net.OnePoundd.Patches.PlayerOfTheWeek;
import net.OnePoundd.Patches.Raiding;
import net.OnePoundd.Patches.Skills;
import net.OnePoundd.Patches.Trusted;
import net.OnePoundd.Patches.Vanish;
import net.OnePoundd.factionsfly.Main;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.WorldCreator;
import org.bukkit.command.PluginCommand;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

public class Factions extends MassivePlugin {
	//med
	public static final String FACTION_MONEY_ACCOUNT_ID_PREFIX = "faction-";
	public static final String ID_NONE = "none";
	public static final String ID_SAFEZONE = "safezone";
	public static final String ID_WARZONE = "warzone";
	public static final String ID_CASTLE = "castle";
	public static final String NAME_NONE_DEFAULT = "Wilderness";
	public static final String NAME_SAFEZONE_DEFAULT = "SafeZone";
	public static final String NAME_WARZONE_DEFAULT = "WarZone";
	public static final String NAME_CASTLE_DEFAULT = "Castle";
	private static Factions i;

	public static Factions get() {
		return i;
	}

	public Factions() {
		i = this;
	}

	@Deprecated
	public PowerMixin getPowerMixin() {
		return PowerMixin.get();
	}

	@Deprecated
	public void setPowerMixin(PowerMixin powerMixin) {
		PowerMixin.get().setInstance(powerMixin);
	}

	public void onEnableInner() {
		RegistryType.register(Rel.class, TypeRel.get());
		RegistryType.register(EventFactionsChunkChangeType.class, TypeFactionChunkChangeType.get());

		MUtil.registerExtractor(String.class, "accountId", ExtractorFactionAccountId.get());

		activateAuto();
		activate(new Object[] { getClassesActive("chat", ChatActive.class, new Predicate[0]) });

		getServer().getPluginManager().registerEvents(new Main(), this);
		getServer().getPluginManager().registerEvents(new Raiding(), this);
		getServer().getPluginManager().registerEvents(new Trusted(), this);
		getServer().getPluginManager().registerEvents(new BlockManipulation(), this);
		getServer().getPluginManager().registerEvents(new Introduction(), this);
		getServer().getPluginManager().registerEvents(new CmdFactionsUpgrade(), this);
		getServer().getPluginManager().registerEvents(new Upgrades(), this);
		getServer().getPluginManager().registerEvents(new Experience(), this);
		getServer().getPluginManager().registerEvents(new Teleport(), this);
		getServer().getPluginManager().registerEvents(new McmmoBoosters(), this);
		getServer().getPluginManager().registerEvents(new Castle(), this);
		getServer().getPluginManager().registerEvents(new Vanish(), this);
		getServer().getPluginManager().registerEvents(new Skills(), this);
		getServer().getPluginManager().registerEvents(new PlayerOfTheWeek(), this);
		getServer().getPluginManager().registerEvents(new CmdFactionsBanner(), this);
		
		getCommand("tpa").setExecutor(new Teleport());
		getCommand("tpahere").setExecutor(new Teleport());
		getCommand("tpyes").setExecutor(new Teleport());
		getCommand("tpaccept").setExecutor(new Teleport());
		getCommand("sethome").setExecutor(new Teleport());
		getCommand("delhome").setExecutor(new Teleport());
		getCommand("home").setExecutor(new Teleport());
		getCommand("back").setExecutor(new Teleport());
		getCommand("setspawn").setExecutor(new Teleport());
		getCommand("spawn").setExecutor(new Teleport());
		getCommand("setwarp").setExecutor(new Teleport());
		getCommand("warp").setExecutor(new Teleport());
		getCommand("delwarp").setExecutor(new Teleport());
		getCommand("vanish").setExecutor(new Teleport());
		getCommand("skills").setExecutor(new Teleport());

		BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				CmdFactionsTop.updateFactionsTop();
				CmdFactionsWealth.updateFactionsWealth();
			}
		}, 0L, 6000L);

		getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.TNT), Material.SULPHUR));

		getServer().createWorld(new WorldCreator("world_introduction"));

//		getServer().getPluginManager().addPermission(new Permission("factions.admin"));
//		getServer().getPluginManager().addPermission(new Permission("factions.flysafezone"));
//		getServer().getPluginManager().addPermission(new Permission("factions.flywilderness"));
//		getServer().getPluginManager().addPermission(new Permission("factions.back"));
//		getServer().getPluginManager().addPermission(new Permission("factions.homes.1"));
//		getServer().getPluginManager().addPermission(new Permission("factions.homes.3"));
//		getServer().getPluginManager().addPermission(new Permission("factions.homes.5"));
//		getServer().getPluginManager().addPermission(new Permission("factions.homes.10"));
//		getServer().getPluginManager().addPermission(new Permission("mcmmo.boost.50"));
//		getServer().getPluginManager().addPermission(new Permission("mcmmo.boost.100"));

		Castle.startCastle();
		PlayerOfTheWeek.startPlayerOfTheWeekProcesses();
	}

	public List<Class<?>> getClassesActiveColls() {
		return new MassiveList(new Class[] { MConfColl.class, MFlagColl.class, MPermColl.class, FactionColl.class,
				MPlayerColl.class, BoardColl.class });
	}

	public List<Class<?>> getClassesActiveEngines() {
		List<Class<?>> ret = super.getClassesActiveEngines();

		ret.remove(EngineEcon.class);
		ret.add(EngineEcon.class);

		return ret;
	}

	public GsonBuilder getGsonBuilder() {
		return super.getGsonBuilder()
		.registerTypeAdapter(TerritoryAccess.class, TerritoryAccessAdapter.get())
		.registerTypeAdapter(Board.class, BoardAdapter.get())
		.registerTypeAdapter(Board.MAP_TYPE, BoardMapAdapter.get())
		.registerTypeAdapter(Rel.class, RelAdapter.get())
		;
	}
}