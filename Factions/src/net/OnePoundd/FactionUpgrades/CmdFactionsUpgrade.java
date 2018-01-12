package net.OnePoundd.FactionUpgrades;

import com.massivecraft.factions.cmd.FactionsCommand;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.integration.Econ;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CmdFactionsUpgrade extends FactionsCommand implements Listener {
	public CmdFactionsUpgrade() {
		aliases.add("upgrade");
	}

	public void perform() {
		if (!MPerm.getPermUpgrade().has(msender, msenderFaction, true)) {
			return;
		}
		if (msender.getFaction().getName().equalsIgnoreCase("wilderness")) {
			msender.getPlayer().sendMessage("§b§l(!)§7 You cannot upgrade wilderness!");
		} else {
			openUpgradeInventory(msender);
		}
	}

	public void openUpgradeInventory(MPlayer mPlayer) {
		Inventory UpgradeGUI = Bukkit.createInventory(null, 9, "§c§l>> §8Faction Upgrades §c§l<<");
		Faction faction = msenderFaction;

		ItemStack Spawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta SpawnerMeta = Spawner.getItemMeta();
		SpawnerMeta.setDisplayName("§c§l>> §fSpawner Rate Upgrade §c§l<<");
		ArrayList<String> SpawnerLore = new ArrayList();
		SpawnerLore.add("§c§l> §7§lIncreases spawner rates in your territory!");
		int SpawnerUpgradeLevel = faction.getSpawnerUpgradeLevel();
		SpawnerLore.add("§c§l> §7Current Level: §c" + SpawnerUpgradeLevel);
		if (SpawnerUpgradeLevel == 0) {
			SpawnerLore.add("§c§l> §7This Level: §cRates increased by 0%");
			SpawnerLore.add("§c§l> §7Next Level: §cRates increased by 25%!");
			SpawnerLore.add("§bCost: §6$1,000,000");
		} else if (SpawnerUpgradeLevel == 1) {
			SpawnerLore.add("§c§l> §7This Level: §cRates increased by 25%");
			SpawnerLore.add("§c§l> §7Next Level: §cRates increased by 50%!");
			SpawnerLore.add("§bCost: §6$2,500,000");
		} else if (SpawnerUpgradeLevel == 2) {
			SpawnerLore.add("§c§l> §7This Level: §cRates increased by 50%");
			SpawnerLore.add("§c§l> §7Next Level: §cRates increased by 75%!");
			SpawnerLore.add("§bCost: §6$5,000,000");
		} else if (SpawnerUpgradeLevel == 3) {
			SpawnerLore.add("§c§l> §7This Level: §cRates increased by 75%");
			SpawnerLore.add("§c§l> §7Next Level: §cRates increased by 100%!");
			SpawnerLore.add("§bCost: §6$7,500,000");
		} else if (SpawnerUpgradeLevel == 4) {
			SpawnerLore.add("§c§l> §7This Level: §cRates increased by 100%");
			SpawnerLore.add("§a§lMax Level Reached!");
		}
		SpawnerMeta.setLore(SpawnerLore);
		Spawner.setItemMeta(SpawnerMeta);

		ItemStack CropGrowth = new ItemStack(Material.SUGAR_CANE);
		ItemMeta CropGrowthMeta = CropGrowth.getItemMeta();
		CropGrowthMeta.setDisplayName("§c§l>> §fSugarcane Growth Upgrade §c§l<<");
		ArrayList<String> CropLore = new ArrayList();
		int CropGrowthLevel = faction.getCropGrowthUpgradeLevel();
		CropLore.add("§c§l> §7§lIncreases sugarcane growth rates in your territory!");
		CropLore.add("§c§l> §7Current Level: §c" + CropGrowthLevel);
		if (CropGrowthLevel == 0) {
			CropLore.add("§c§l> §7This Level: §cRates increased by 0%");
			CropLore.add("§c§l> §7Next Level: §cRates increased by 25%!");
			CropLore.add("§bCost: §6$500,000");
		} else if (CropGrowthLevel == 1) {
			CropLore.add("§c§l> §7This Level: §cRates increased by 25%");
			CropLore.add("§c§l> §7Next Level: §cRates increased by 50%!");
			CropLore.add("§bCost: §6$1,000,000");
		} else if (CropGrowthLevel == 2) {
			CropLore.add("§c§l> §7This Level: §cRates increased by 50%");
			CropLore.add("§c§l> §7Next Level: §cRates increased by 75%!");
			CropLore.add("§bCost: §6$2,500,000");
		} else if (CropGrowthLevel == 3) {
			CropLore.add("§c§l> §7This Level: §cRates increased by 75%");
			CropLore.add("§c§l> §7Next Level: §cRates increased by 100%!");
			CropLore.add("§bCost: §6$5,000,000");
		} else if (CropGrowthLevel == 4) {
			CropLore.add("§c§l> §7This Level: §cRates increased by 100%");
			CropLore.add("§a§lMax Level Reached!");
		}
		CropGrowthMeta.setLore(CropLore);
		CropGrowth.setItemMeta(CropGrowthMeta);

		ItemStack DamageUpgrade = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta DamageMeta = DamageUpgrade.getItemMeta();
		DamageMeta.setDisplayName("§c§l>> §fDamage Reduction Upgrade §c§l<<");
		ArrayList<String> DamageLore = new ArrayList();
		int DamageUpgradeLevel = faction.getDamageUpgradeLevel();
		DamageLore.add("§c§l> §7§lReduces damage from enemies in your territory!");
		DamageLore.add("§c§l> §7Current Level: §c" + DamageUpgradeLevel);
		if (DamageUpgradeLevel == 0) {
			DamageLore.add("§c§l> §7This Level: §c0% damage reduction!");
			DamageLore.add("§c§l> §7Next Level: §c5% damage reduction!");
			DamageLore.add("§bCost: §6$250,000");
		} else if (DamageUpgradeLevel == 1) {
			DamageLore.add("§c§l> §7This Level: §c5% damage reduction!");
			DamageLore.add("§c§l> §7Next Level: §c10% damage reduction!");
			DamageLore.add("§bCost: §6$500,000");
		} else if (DamageUpgradeLevel == 2) {
			DamageLore.add("§c§l> §7This Level: §c10% damage reduction!");
			DamageLore.add("§c§l> §7Next Level: §c15% damage reduction!");
			DamageLore.add("§bCost: §6$51,000,000");
		} else if (DamageUpgradeLevel == 3) {
			DamageLore.add("§c§l> §7This Level: §c15% damage reduction!");
			DamageLore.add("§c§l> §7Next Level: §c20% damage reduction!");
			DamageLore.add("§bCost: §6$1,500,000");
		} else if (DamageUpgradeLevel == 4) {
			DamageLore.add("§c§l> §7This Level: §c20% damage reduction!");
			DamageLore.add("§c§l> §7Next Level: §c25% damage reduction!");
			DamageLore.add("§bCost: §6$2,000,000");
		} else if (DamageUpgradeLevel == 5) {
			DamageLore.add("§c§l> §7This Level: §c25% damage reduction!");
			DamageLore.add("§a§lMax Level Reached!");
		}
		DamageMeta.setLore(DamageLore);
		DamageUpgrade.setItemMeta(DamageMeta);

		ItemStack WarpUpgrade = new ItemStack(Material.ENDER_PEARL);
		ItemMeta WarpMeta = WarpUpgrade.getItemMeta();
		WarpMeta.setDisplayName("§c§l>> §fFaction Warps Upgrade §c§l<<");
		ArrayList<String> WarpLore = new ArrayList();
		int WarpUpgradeLevel = faction.getMaxWarps();
		WarpLore.add("§c§l> §7§lIncreases the max amount of faction warps!");
		WarpLore.add("§c§l> §7Current Level: §c" + WarpUpgradeLevel);
		if (WarpUpgradeLevel == 0) {
			WarpLore.add("§c§l> §7This Level: §c0 Faction Warps!");
			WarpLore.add("§c§l> §7Next Level: §c1 Faction Warp!");
			WarpLore.add("§bCost: §6$150,000");
		} else if (WarpUpgradeLevel == 1) {
			WarpLore.add("§c§l> §7This Level: §c1 Faction Warp!");
			WarpLore.add("§c§l> §7Next Level: §c2 Faction Warps!");
			WarpLore.add("§bCost: §6$150,000");
		} else if (WarpUpgradeLevel == 2) {
			WarpLore.add("§c§l> §7This Level: §c2 Faction Warps!");
			WarpLore.add("§c§l> §7Next Level: §c3 Faction Warps!");
			WarpLore.add("§bCost: §6$150,000");
		} else if (WarpUpgradeLevel == 3) {
			WarpLore.add("§c§l> §7This Level: §c3 Faction Warps!");
			WarpLore.add("§c§l> §7Next Level: §c4 Faction Warps!");
			WarpLore.add("§bCost: §6$150,000");
		} else if (WarpUpgradeLevel == 4) {
			WarpLore.add("§c§l> §7This Level: §c4 Faction Warps!");
			WarpLore.add("§c§l> §7Next Level: §c5 Faction Warps!");
			WarpLore.add("§bCost: §6$150,000");
		} else if (WarpUpgradeLevel == 5) {
			WarpLore.add("§c§l> §7This Level: §c5 Faction Warps!");
			WarpLore.add("§a§lMax Level Reached!");
		}
		WarpMeta.setLore(WarpLore);
		WarpUpgrade.setItemMeta(WarpMeta);

		ItemStack HungerUpgrade = new ItemStack(Material.COOKED_BEEF);
		ItemMeta HungerMeta = HungerUpgrade.getItemMeta();
		HungerMeta.setDisplayName("§c§l>> §fHunger Loss Upgrade §c§l<<");
		ArrayList<String> HungerLore = new ArrayList();
		int HungerUpgradeLevel = faction.getHungerUpgradeLevel();
		HungerLore.add("§c§l> §7§lPrevents hunger loss in your factions territory!");
		HungerLore.add("§c§l> §7Current Level: §c" + HungerUpgradeLevel);
		if (HungerUpgradeLevel == 0) {
			HungerLore.add("§c§l> §7This Level: §cHunger is lost in your territory!");
			HungerLore.add("§c§l> §7Next Level: §cHunger is not lost in your territory!");
			HungerLore.add("§bCost: §6$1,000,000");
		} else if (HungerUpgradeLevel == 1) {
			HungerLore.add("§c§l> §7This Level: §cHunger is not lost in your territory!");
			HungerLore.add("§a§lMax Level Reached!");
		}
		HungerMeta.setLore(HungerLore);
		HungerUpgrade.setItemMeta(HungerMeta);

		ItemStack SmeltingUpgrade = new ItemStack(Material.FURNACE);
		ItemMeta SmeltingMeta = SmeltingUpgrade.getItemMeta();
		SmeltingMeta.setDisplayName("§c§l>> §fTnt Smelting Upgrade §c§l<<");
		ArrayList<String> SmeltingLore = new ArrayList();
		int SmeltingUpgradeLevel = faction.getSmeltingUpgradeLevel();
		SmeltingLore.add("§c§l> §7§lAllows gunpowder to be smelted into tnt!");
		SmeltingLore.add("§c§l> §7Current Level: §c" + SmeltingUpgradeLevel);
		if (SmeltingUpgradeLevel == 0) {
			SmeltingLore.add("§c§l> §7This Level: §c0% chance!");
			SmeltingLore.add("§c§l> §7Next Level: §c10% chance!");
			SmeltingLore.add("§bCost: §6$500,000");
		} else if (SmeltingUpgradeLevel == 1) {
			SmeltingLore.add("§c§l> §7This Level: §c10% chance!");
			SmeltingLore.add("§c§l> §7Next Level: §c20% chance!");
			SmeltingLore.add("§bCost: §6$1,000,000");
		} else if (SmeltingUpgradeLevel == 2) {
			SmeltingLore.add("§c§l> §7This Level: §c20% chance!");
			SmeltingLore.add("§c§l> §7Next Level: §c30% chance!");
			SmeltingLore.add("§bCost: §6$2,000,000");
		} else if (SmeltingUpgradeLevel == 3) {
			SmeltingLore.add("§c§l> §7This Level: §c30% chance!");
			SmeltingLore.add("§a§lMax Level Reached!");
		}
		SmeltingMeta.setLore(SmeltingLore);
		SmeltingUpgrade.setItemMeta(SmeltingMeta);

		ItemStack ExpUpgrade = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta ExpMeta = ExpUpgrade.getItemMeta();
		ExpMeta.setDisplayName("§c§l>> §fExperience Gain Upgrade §c§l<<");
		ArrayList<String> ExpLore = new ArrayList();
		int ExpUpgradeLevel = faction.getExpUpgradeLevel();
		ExpLore.add("§c§l> §7§lIncreases the amount of experience you gain!");
		ExpLore.add("§c§l> §7Current Level: §c" + ExpUpgradeLevel);
		if (ExpUpgradeLevel == 0) {
			ExpLore.add("§c§l> §7This Level: §cExp gain increased by 0%!");
			ExpLore.add("§c§l> §7Next Level: §cExp gain increased by 5%!");
			ExpLore.add("§bCost: §6$250,000");
		} else if (ExpUpgradeLevel == 1) {
			ExpLore.add("§c§l> §7This Level: §cExp gain increased by 5%!");
			ExpLore.add("§c§l> §7Next Level: §cExp gain increased by 10%!");
			ExpLore.add("§bCost: §6$500,000");
		} else if (ExpUpgradeLevel == 2) {
			ExpLore.add("§c§l> §7This Level: §cExp gain increased by 10%!");
			ExpLore.add("§c§l> §7Next Level: §cExp gain increased by 15%!");
			ExpLore.add("§bCost: §6$750,000");
		} else if (ExpUpgradeLevel == 3) {
			ExpLore.add("§c§l> §7This Level: §cExp gain increased by 15%!");
			ExpLore.add("§c§l> §7Next Level: §cExp gain increased by 20%!");
			ExpLore.add("§bCost: §6$1,000,000");
		} else if (ExpUpgradeLevel == 4) {
			ExpLore.add("§c§l> §7This Level: §cExp gain increased by 20%!");
			ExpLore.add("§c§l> §7Next Level: §cExp gain increased by 25%!");
			ExpLore.add("§bCost: §6$1,250,000");
		} else if (ExpUpgradeLevel == 5) {
			ExpLore.add("§c§l> §7This Level: §cExp gain increased by 25%!");
			ExpLore.add("§a§lMax Level Reached!");
		}
		ExpMeta.setLore(ExpLore);
		ExpUpgrade.setItemMeta(ExpMeta);

		ItemStack TntUpgrade = new ItemStack(Material.TNT);
		ItemMeta TntMeta = TntUpgrade.getItemMeta();
		TntMeta.setDisplayName("§c§l>> §fTnt Storage Upgrade §c§l<<");
		ArrayList<String> TntLore = new ArrayList();
		int TntUpgradeLevel = faction.getTntUpgradeLevel();
		TntLore.add("§c§l> §7§lIncreases your virtual tnt storage (/f tnt)!");
		TntLore.add("§c§l> §7Current Level: §c" + TntUpgradeLevel);
		if (TntUpgradeLevel == 0) {
			TntLore.add("§c§l> §7This Level: §cCapacity = 0!");
			TntLore.add("§c§l> §7Next Level: §cCapacity = 50,000!");
			TntLore.add("§bCost: §6$200,000");
		} else if (TntUpgradeLevel == 1) {
			TntLore.add("§c§l> §7This Level: §cCapacity = 50,000!");
			TntLore.add("§c§l> §7Next Level: §cCapacity = 100,000!");
			TntLore.add("§bCost: §6$200,000");
		} else if (TntUpgradeLevel == 2) {
			TntLore.add("§c§l> §7This Level: §cCapacity = 100,000!");
			TntLore.add("§c§l> §7Next Level: §cCapacity = 150,000!");
			TntLore.add("§bCost: §6$200,000");
		} else if (TntUpgradeLevel == 3) {
			TntLore.add("§c§l> §7This Level: §cCapacity = 150,000!");
			TntLore.add("§c§l> §7Next Level: §cCapacity = 200,000!");
			TntLore.add("§bCost: §6$200,000");
		} else if (TntUpgradeLevel == 4) {
			TntLore.add("§c§l> §7This Level: §cCapacity = 200,000!");
			TntLore.add("§c§l> §7Next Level: §cCapacity = 250,000!");
			TntLore.add("§bCost: §6$200,000");
		} else if (TntUpgradeLevel == 5) {
			TntLore.add("§c§l> §7This Level: §cCapacity =  250,000!");
			TntLore.add("§a§lMax Level Reached!");
		}
		TntMeta.setLore(TntLore);
		TntUpgrade.setItemMeta(TntMeta);

		ItemStack McmmoUpgrade = new ItemStack(Material.DIAMOND_SPADE);
		ItemMeta McmmoMeta = McmmoUpgrade.getItemMeta();
		McmmoMeta.setDisplayName("§c§l>> §fMcMMO Gain Upgrade §c§l<<");
		ArrayList<String> McmmoLore = new ArrayList();
		int McmmoUpgradeLevel = faction.getMcmmoUpgradeLevel();
		McmmoLore.add("§c§l> §7§lIncreases the amount of McMMO xp your faction gains!");
		McmmoLore.add("§c§l> §7Current Level: §c" + McmmoUpgradeLevel);
		if (McmmoUpgradeLevel == 0) {
			McmmoLore.add("§c§l> §7This Level: §c0% Increase!");
			McmmoLore.add("§c§l> §7Next Level: §c10% Increase!");
			McmmoLore.add("§bCost: §6$500,000");
		} else if (McmmoUpgradeLevel == 1) {
			McmmoLore.add("§c§l> §7This Level: §c10% Increase!");
			McmmoLore.add("§c§l> §7Next Level: §c20% Increase!");
			McmmoLore.add("§bCost: §6$1,000,000");
		} else if (McmmoUpgradeLevel == 2) {
			McmmoLore.add("§c§l> §7This Level: §c20% Increase!");
			McmmoLore.add("§c§l> §7Next Level: §c30% Increase!");
			McmmoLore.add("§bCost: §6$2,000,000");
		} else if (McmmoUpgradeLevel == 3) {
			McmmoLore.add("§c§l> §7This Level: §c30% Increase!");
			McmmoLore.add("§a§lMax Level Reached!");
		}
		McmmoMeta.setLore(McmmoLore);
		McmmoUpgrade.setItemMeta(McmmoMeta);

		UpgradeGUI.setItem(0, Spawner);
		UpgradeGUI.setItem(1, CropGrowth);
		UpgradeGUI.setItem(2, DamageUpgrade);
		UpgradeGUI.setItem(3, WarpUpgrade);
		UpgradeGUI.setItem(4, HungerUpgrade);
		UpgradeGUI.setItem(5, SmeltingUpgrade);
		UpgradeGUI.setItem(6, ExpUpgrade);
		UpgradeGUI.setItem(6, TntUpgrade);
		UpgradeGUI.setItem(7, McmmoUpgrade);
		mPlayer.getPlayer().openInventory(UpgradeGUI);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if ((event.getClickedInventory() != null)
				&& (event.getClickedInventory().getName().equals("§c§l>> §8Faction Upgrades §c§l<<"))) {
			ItemStack itemClicked = event.getCurrentItem();
			event.setCancelled(true);
			if ((itemClicked != null) && (!itemClicked.getType().equals(Material.AIR)) && (itemClicked.hasItemMeta())) {
				Player player = (Player) event.getWhoClicked();
				MPlayer mPlayer = MPlayer.get(player);
				Faction faction = mPlayer.getFaction();
				String upgrade = itemClicked.getItemMeta().getDisplayName();
				if (upgrade.equals("§c§l>> §fSpawner Rate Upgrade §c§l<<")) {
					int currentLevel = faction.getSpawnerUpgradeLevel();
					if (currentLevel == 0) {
						if (Econ.payForAction(1000000.0D, mPlayer, "upgrade spawner rates")) {
							faction.setSpawnerUpgradeLevel(1);
							faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 1st spawner upgrade!");
							faction.setWealth(faction.getWealth() + 1000000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 1) {
						if (Econ.payForAction(2500000.0D, mPlayer, "upgrade spawner rates")) {
							faction.setSpawnerUpgradeLevel(2);
							faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 2nd spawner upgrade!");
							faction.setWealth(faction.getWealth() + 2500000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 2) {
						if (Econ.payForAction(5000000.0D, mPlayer, "upgrade spawner rates")) {
							faction.setSpawnerUpgradeLevel(3);
							faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 3rd spawner upgrade!");
							faction.setWealth(faction.getWealth() + 5000000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if ((currentLevel == 3)
							&& (Econ.payForAction(7500000.0D, mPlayer, "upgrade spawner rates"))) {
						faction.setSpawnerUpgradeLevel(4);
						faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 4th spawner upgrade!");
						faction.setWealth(faction.getWealth() + 7500000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				} else if (upgrade.equals("§c§l>> §fCrop Growth Upgrade §c§l<<")) {
					int currentLevel = faction.getCropGrowthUpgradeLevel();
					if (currentLevel == 0) {
						if (Econ.payForAction(500000.0D, mPlayer, "upgrade crop growth rates")) {
							faction.setCropGrowthUpgradeLevel(1);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 1st crop growth upgrade!");
							faction.setWealth(faction.getWealth() + 500000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 1) {
						if (Econ.payForAction(1000000.0D, mPlayer, "upgrade crop growth rates")) {
							faction.setCropGrowthUpgradeLevel(2);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 2nd crop growth upgrade!");
							faction.setWealth(faction.getWealth() + 1000000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 2) {
						if (Econ.payForAction(2500000.0D, mPlayer, "upgrade crop growth rates")) {
							faction.setCropGrowthUpgradeLevel(3);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 3rd crop growth upgrade!");
							faction.setWealth(faction.getWealth() + 2500000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if ((currentLevel == 3)
							&& (Econ.payForAction(5000000.0D, mPlayer, "upgrade crop growth rates"))) {
						faction.setCropGrowthUpgradeLevel(4);
						faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 4th crop growth upgrade!");
						faction.setWealth(faction.getWealth() + 5000000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				} else if (upgrade.equals("§c§l>> §fDamage Reduction Upgrade §c§l<<")) {
					int currentLevel = faction.getDamageUpgradeLevel();
					if (currentLevel == 0) {
						if (Econ.payForAction(250000.0D, mPlayer, "increase damage reduction")) {
							faction.setDamageUpgradeLevel(1);
							faction.msg("§b§l(!)§7 " + player.getName()
									+ " just puchased the 1st damage reduction upgrade!");
							faction.setWealth(faction.getWealth() + 250000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 1) {
						if (Econ.payForAction(500000.0D, mPlayer, "increase damage reduction")) {
							faction.setDamageUpgradeLevel(2);
							faction.msg("§b§l(!)§7 " + player.getName()
									+ " just puchased the 2nd damage reduction upgrade!");
							faction.setWealth(faction.getWealth() + 500000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 2) {
						if (Econ.payForAction(1000000.0D, mPlayer, "increase damage reduction")) {
							faction.setDamageUpgradeLevel(3);
							faction.msg("§b§l(!)§7 " + player.getName()
									+ " just puchased the 3rd damage reduction upgrade!");
							faction.setWealth(faction.getWealth() + 1000000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 3) {
						if (Econ.payForAction(1500000.0D, mPlayer, "increase damage reduction")) {
							faction.setDamageUpgradeLevel(4);
							faction.msg("§b§l(!)§7 " + player.getName()
									+ " just puchased the 4th damage reduction upgrade!");
							faction.setWealth(faction.getWealth() + 1500000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if ((currentLevel == 4)
							&& (Econ.payForAction(2000000.0D, mPlayer, "increase damage reduction"))) {
						faction.setDamageUpgradeLevel(5);
						faction.msg("§b§l(!)§7 " + player.getName()
								+ " just puchased the 5th damage reduction upgrade!");
						faction.setWealth(faction.getWealth() + 2000000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				} else if (upgrade.equals("§c§l>> §fFaction Warps Upgrade §c§l<<")) {
					int currentLevel = faction.getMaxWarps();
					if (currentLevel == 0) {
						if (Econ.payForAction(150000.0D, mPlayer, "increase max faction warps")) {
							faction.setMaxWarps(1);
							faction.msg("§b§l(!)§7 " + player.getName()
									+ " just puchased the 1st faction warp upgrade!");
							faction.setWealth(faction.getWealth() + 150000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 1) {
						if (Econ.payForAction(150000.0D, mPlayer, "increase max faction warps")) {
							faction.setMaxWarps(2);
							faction.msg("§b§l(!)§7 " + player.getName()
									+ " just puchased the 2nd faction warp upgrade!");
							faction.setWealth(faction.getWealth() + 150000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 2) {
						if (Econ.payForAction(150000.0D, mPlayer, "increase max faction warps")) {
							faction.setMaxWarps(3);
							faction.msg("§b§l(!)§7 " + player.getName()
									+ " just puchased the 3rd faction warp upgrade!");
							faction.setWealth(faction.getWealth() + 150000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 3) {
						if (Econ.payForAction(150000.0D, mPlayer, "increase max faction warps")) {
							faction.setMaxWarps(4);
							faction.msg("§b§l(!)§7 " + player.getName()
									+ " just puchased the 4th faction warp upgrade!");
							faction.setWealth(faction.getWealth() + 150000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if ((currentLevel == 4)
							&& (Econ.payForAction(150000.0D, mPlayer, "increase max faction warps"))) {
						faction.setMaxWarps(5);
						faction.msg(
								"§b§l(!)§7 " + player.getName() + " just puchased the 5th faction warp upgrade!");
						faction.setWealth(faction.getWealth() + 150000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				} else if (upgrade.equals("§c§l>> §fHunger Loss Upgrade §c§l<<")) {
					int currentLevel = faction.getHungerUpgradeLevel();
					if ((currentLevel == 0)
							&& (Econ.payForAction(1000000.0D, mPlayer, "prevent hunger loss in territory"))) {
						faction.setHungerUpgradeLevel(1);
						faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the hunger upgrade!");
						faction.setWealth(faction.getWealth() + 1000000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				} else if (upgrade.equals("§c§l>> §fTnt Smelting Upgrade §c§l<<")) {
					int currentLevel = faction.getSmeltingUpgradeLevel();
					if (currentLevel == 0) {
						if (Econ.payForAction(500000.0D, mPlayer, "increase chance of smelting tnt")) {
							faction.setSmeltingUpgradeLevel(1);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 1st tnt smelter upgrade!");
							faction.setWealth(faction.getWealth() + 500000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 1) {
						if (Econ.payForAction(1000000.0D, mPlayer, "increase chance of smelting tnt")) {
							faction.setSmeltingUpgradeLevel(2);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 2nd tnt smelter upgrade!");
							faction.setWealth(faction.getWealth() + 1000000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if ((currentLevel == 2)
							&& (Econ.payForAction(2000000.0D, mPlayer, "increase chance of smelting tnt"))) {
						faction.setSmeltingUpgradeLevel(3);
						faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 3rd tnt smelter upgrade!");
						faction.setWealth(faction.getWealth() + 2000000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				} else if (upgrade.equals("§c§l>> §fExperience Gain Upgrade §c§l<<")) {
					int currentLevel = faction.getExpUpgradeLevel();
					if (currentLevel == 0) {
						if (Econ.payForAction(250000.0D, mPlayer, "increase experience gain")) {
							faction.setExpUpgradeLevel(1);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 1st experience upgrade!");
							faction.setWealth(faction.getWealth() + 250000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 1) {
						if (Econ.payForAction(500000.0D, mPlayer, "increase experience gain")) {
							faction.setExpUpgradeLevel(2);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 2nd experience upgrade!");
							faction.setWealth(faction.getWealth() + 500000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 2) {
						if (Econ.payForAction(750000.0D, mPlayer, "increase experience gain")) {
							faction.setExpUpgradeLevel(3);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 3rd experience upgrade!");
							faction.setWealth(faction.getWealth() + 750000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 3) {
						if (Econ.payForAction(1000000.0D, mPlayer, "increase expreience gain")) {
							faction.setExpUpgradeLevel(4);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 4th experience upgrade!");
							faction.setWealth(faction.getWealth() + 1000000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if ((currentLevel == 4)
							&& (Econ.payForAction(1250000.0D, mPlayer, "increase experiecne gain"))) {
						faction.setExpUpgradeLevel(5);
						faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 5th experience upgrade!");
						faction.setWealth(faction.getWealth() + 1250000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				} else if (upgrade.equals("§c§l>> §fTnt Storage Upgrade §c§l<<")) {
					int currentLevel = faction.getTntUpgradeLevel();
					if (currentLevel == 0) {
						if (Econ.payForAction(200000.0D, mPlayer, "increase tnt storage")) {
							faction.setTntUpgradeLevel(1);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 1st tnt storage upgrade!");
							faction.setWealth(faction.getWealth() + 200000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 1) {
						if (Econ.payForAction(200000.0D, mPlayer, "increase tnt storage")) {
							faction.setTntUpgradeLevel(2);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 2nd tnt storage upgrade!");
							faction.setWealth(faction.getWealth() + 200000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 2) {
						if (Econ.payForAction(200000.0D, mPlayer, "increase tnt storage")) {
							faction.setTntUpgradeLevel(3);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 3rd tnt storage upgrade!");
							faction.setWealth(faction.getWealth() + 200000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 3) {
						if (Econ.payForAction(200000.0D, mPlayer, "increase tnt storage")) {
							faction.setTntUpgradeLevel(4);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 4th tnt storage upgrade!");
							faction.setWealth(faction.getWealth() + 200000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if ((currentLevel == 4) && (Econ.payForAction(200000.0D, mPlayer, "increase tnt storage"))) {
						faction.setTntUpgradeLevel(5);
						faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 5th tnt storage upgrade!");
						faction.setWealth(faction.getWealth() + 200000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				} else if (upgrade.equals("§c§l>> §fMcMMO Gain Upgrade §c§l<<")) {
					int currentLevel = faction.getMcmmoUpgradeLevel();
					if (currentLevel == 0) {
						if (Econ.payForAction(500000.0D, mPlayer, "increase mcmmo gain")) {
							faction.setMcmmoUpgradeLevel(1);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 1st mcmmo gain upgrade!");
							faction.setWealth(faction.getWealth() + 500000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if (currentLevel == 1) {
						if (Econ.payForAction(1000000.0D, mPlayer, "increase mcmmo gain")) {
							faction.setMcmmoUpgradeLevel(2);
							faction.msg(
									"§b§l(!)§7 " + player.getName() + " just puchased the 2nd mcmmo gain upgrade!");
							faction.setWealth(faction.getWealth() + 1000000);
							player.closeInventory();
							player.performCommand("f upgrade");
						}
					} else if ((currentLevel == 2) && (Econ.payForAction(2000000.0D, mPlayer, "increase mcmmo gain"))) {
						faction.setMcmmoUpgradeLevel(3);
						faction.msg("§b§l(!)§7 " + player.getName() + " just puchased the 3rd mcmmo gain upgrade!");
						faction.setWealth(faction.getWealth() + 2000000);
						player.closeInventory();
						player.performCommand("f upgrade");
					}
				}
			}
		}
	}
}

/*
 * Location: C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\FactionUpgrades\
 * CmdFactionsUpgrade.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */