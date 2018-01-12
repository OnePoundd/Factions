package net.OnePoundd.Patches;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.datatypes.skills.XPGainReason;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.massivecraft.factions.entity.MPlayer;
import java.io.PrintStream;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Skills implements Listener {
	public Skills() {
	}

	public static void openInventory(Player player) {
		Inventory SkillsGUI = Bukkit.createInventory(null, 9, "§c§l>> §8Skills Master §c§l<<");

		ItemStack Info = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta InfoMeta = Info.getItemMeta();
		InfoMeta.setDisplayName("§c§l>> §fInformation §c§l<<");
		ArrayList<String> InfoLore = new ArrayList();
		InfoLore.add("§7Player Level: §c" + MPlayer.get(player).getLevel() + " §7Skill Tokens: §c"
				+ MPlayer.get(player).getTokens());
		InfoLore.add("§fEach time you level up your account, you will earn");
		InfoLore.add("§fone skill token. Use these tokens to unlock skills");
		InfoLore.add("§ffrom the various categories in this menu!");
		InfoMeta.setLore(InfoLore);
		Info.setItemMeta(InfoMeta);

		ItemStack Movement = new ItemStack(Material.FEATHER);
		ItemMeta MovementMeta = Movement.getItemMeta();
		MovementMeta.setDisplayName("§c§l>> §fMovement §c§l<<");
		ArrayList<String> MovementLore = new ArrayList();
		MovementLore.add("§c§l> §7Click to view the various movement skills!");
		MovementMeta.setLore(MovementLore);
		Movement.setItemMeta(MovementMeta);

		ItemStack Combat = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta CombatMeta = Combat.getItemMeta();
		CombatMeta.setDisplayName("§c§l>> §fCombat §c§l<<");
		ArrayList<String> CombatLore = new ArrayList();
		CombatLore.add("§c§l> §7Click to view the various combat skills!");
		CombatMeta.setLore(CombatLore);
		Combat.setItemMeta(CombatMeta);

		ItemStack Classes = new ItemStack(Material.ARMOR_STAND);
		ItemMeta ClassesMeta = Classes.getItemMeta();
		ClassesMeta.setDisplayName("§c§l>> §fClasses §c§l<<");
		ArrayList<String> ClassesLore = new ArrayList();
		ClassesLore.add("§c§l> §7Click to view the various class skills!");
		ClassesMeta.setLore(ClassesLore);
		Classes.setItemMeta(ClassesMeta);

		ItemStack Factions = new ItemStack(Material.TNT);
		ItemMeta FactionsMeta = Factions.getItemMeta();
		FactionsMeta.setDisplayName("§c§l>> §fFactions §c§l<<");
		ArrayList<String> FactionsLore = new ArrayList();
		FactionsLore.add("§c§l> §7Click to view the various factions related skills!");
		FactionsMeta.setLore(FactionsLore);
		Factions.setItemMeta(FactionsMeta);

		SkillsGUI.setItem(0, Movement);
		SkillsGUI.setItem(1, Combat);
		SkillsGUI.setItem(2, Classes);
		SkillsGUI.setItem(3, Factions);
		SkillsGUI.setItem(8, Info);
		player.openInventory(SkillsGUI);
	}

	public static void openMovementInventory(Player player) {
		Inventory SkillsGUI = Bukkit.createInventory(null, 9, "§c§l>> §8Movement Skills §c§l<<");

		ItemStack Flight = new ItemStack(Material.FEATHER);
		ItemMeta FlightMeta = Flight.getItemMeta();
		FlightMeta.setDisplayName("§c§l>> §fFactions Flight §c§l<<");
		ArrayList<String> FlightLore = new ArrayList();
		FlightLore.add("§7This skill will allow you to fly in your faction's");
		FlightLore.add("§7territory when no enemies are near!");
		FlightLore.add("§c§l> §7§lCost: §c1 Skill Token");
		FlightMeta.setLore(FlightLore);
		Flight.setItemMeta(FlightMeta);

		ItemStack Speed = new ItemStack(Material.SUGAR);
		ItemMeta SpeedMeta = Speed.getItemMeta();
		SpeedMeta.setDisplayName("§c§l>> §fSpeed §c§l<<");
		ArrayList<String> SpeedLore = new ArrayList();
		SpeedLore.add("§7This skill will increase your overall");
		SpeedLore.add("§7movement speed by 2.5%!");
		SpeedLore.add("§c§l> §7§lCost: §c5 Skill Tokens");
		SpeedMeta.setLore(SpeedLore);
		Speed.setItemMeta(SpeedMeta);

		ItemStack Back = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta BackMeta = Back.getItemMeta();
		BackMeta.setDisplayName("§c§l>> §fBack §c§l<<");
		ArrayList<String> BackLore = new ArrayList();
		BackLore.add("§c§l> §7Click to go back to the previous menu!");
		BackMeta.setLore(BackLore);
		Back.setItemMeta(BackMeta);

		SkillsGUI.setItem(0, Flight);
		SkillsGUI.setItem(1, Speed);
		SkillsGUI.setItem(8, Back);
		player.openInventory(SkillsGUI);
	}

	public static void openCombatInventory(Player player) {
		Inventory SkillsGUI = Bukkit.createInventory(null, 9, "§c§l>> §8Combat Skills §c§l<<");

		ItemStack MobDamage = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta MobDamageMeta = MobDamage.getItemMeta();
		MobDamageMeta.setDisplayName("§c§l>> §fMob Damage §c§l<<");
		ArrayList<String> MobDamageLore = new ArrayList();
		MobDamageLore.add("§7This skill will increase damage");
		MobDamageLore.add("§7dealt to all mobs by 25%!");
		MobDamageLore.add("§c§l> §7§lCost: §c2 Skill Tokens");
		MobDamageMeta.setLore(MobDamageLore);
		MobDamage.setItemMeta(MobDamageMeta);

		ItemStack McMMO = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta McMMOMeta = McMMO.getItemMeta();
		McMMOMeta.setDisplayName("§c§l>> §fCombat McMMO §c§l<<");
		ArrayList<String> McMMOLore = new ArrayList();
		McMMOLore.add("§7This skill will allow you to gain");
		McMMOLore.add("§7combat McMMO from monster spawners!");
		McMMOLore.add("§c§l> §7§lCost: §c5 Skill Tokens");
		McMMOMeta.setLore(McMMOLore);
		McMMO.setItemMeta(McMMOMeta);

		ItemStack Damage = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta DamageMeta = Damage.getItemMeta();
		DamageMeta.setDisplayName("§c§l>> §fPlayer Damage §c§l<<");
		ArrayList<String> DamageLore = new ArrayList();
		DamageLore.add("§7This skill will increase damage");
		DamageLore.add("§7dealt to all players by 2.5%!");
		DamageLore.add("§c§l> §7§lCost: §c10 Skill Tokens");
		DamageMeta.setLore(DamageLore);
		Damage.setItemMeta(DamageMeta);

		ItemStack Back = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta BackMeta = Back.getItemMeta();
		BackMeta.setDisplayName("§c§l>> §fBack §c§l<<");
		ArrayList<String> BackLore = new ArrayList();
		BackLore.add("§c§l> §7Click to go back to the previous menu!");
		BackMeta.setLore(BackLore);
		Back.setItemMeta(BackMeta);

		SkillsGUI.setItem(0, MobDamage);
		SkillsGUI.setItem(1, McMMO);
		SkillsGUI.setItem(2, Damage);
		SkillsGUI.setItem(8, Back);
		player.openInventory(SkillsGUI);
	}

	public static void openClassesInventory(Player player) {
		Inventory SkillsGUI = Bukkit.createInventory(null, 9, "§c§l>> §8Class Skills §c§l<<");

		ItemStack Miner = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta MinerMeta = Miner.getItemMeta();
		MinerMeta.setDisplayName("§c§l>> §fMiner Class §c§l<<");
		ArrayList<String> MinerLore = new ArrayList();
		MinerLore.add("§7This skill will grant you invisibility");
		MinerLore.add("§7and haste 2 when wearing full iron armor!");
		MinerLore.add("§c§l> §7§lCost: §c1 Skill Token");
		MinerMeta.setLore(MinerLore);
		Miner.setItemMeta(MinerMeta);

		ItemStack Archer = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta ArcherMeta = Archer.getItemMeta();
		ArcherMeta.setDisplayName("§c§l>> §fArcher Class §c§l<<");
		ArrayList<String> ArcherLore = new ArrayList();
		ArcherLore.add("§7This skill will grant you speed 3 and resistance");
		ArcherLore.add("§71 whilst wearing full leather armor!");
		ArcherLore.add("§c§l> §7§lCost: §c2 Skill Tokens");
		ArcherMeta.setLore(ArcherLore);
		Archer.setItemMeta(ArcherMeta);

		ItemStack Bard = new ItemStack(Material.GOLD_CHESTPLATE);
		ItemMeta BardMeta = Bard.getItemMeta();
		BardMeta.setDisplayName("§c§l>> §fBard Class §c§l<<");
		ArrayList<String> BardLore = new ArrayList();
		BardLore.add("§7This skill will grant you speed 3 and");
		BardLore.add("§7regen 2 when wearing full gold armor!");
		BardLore.add("§c§l> §7§lCost: §c5 Skill Tokens");
		BardMeta.setLore(BardLore);
		Bard.setItemMeta(BardMeta);

		ItemStack Back = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta BackMeta = Back.getItemMeta();
		BackMeta.setDisplayName("§c§l>> §fBack §c§l<<");
		ArrayList<String> BackLore = new ArrayList();
		BackLore.add("§c§l> §7Click to go back to the previous menu!");
		BackMeta.setLore(BackLore);
		Back.setItemMeta(BackMeta);

		SkillsGUI.setItem(0, Miner);
		SkillsGUI.setItem(1, Archer);
		SkillsGUI.setItem(2, Bard);
		SkillsGUI.setItem(8, Back);
		player.openInventory(SkillsGUI);
	}

	public static void openFactionsInventory(Player player) {
		Inventory SkillsGUI = Bukkit.createInventory(null, 9, "§c§l>> §8Factions Skills §c§l<<");

		ItemStack GenBuckets = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta GenBucketsMeta = GenBuckets.getItemMeta();
		GenBucketsMeta.setDisplayName("§c§l>> §fGen Buckets §c§l<<");
		ArrayList<String> GenBucketsLore = new ArrayList();
		GenBucketsLore.add("§7This skill will allow you to place gen-buckets,");
		GenBucketsLore.add("§7which can be collected from within the nether!");
		GenBucketsLore.add("§c§l> §7§lCost: §c1 Skill Token");
		GenBucketsMeta.setLore(GenBucketsLore);
		GenBuckets.setItemMeta(GenBucketsMeta);

		ItemStack TntFill = new ItemStack(Material.DISPENSER);
		ItemMeta TntFillMeta = TntFill.getItemMeta();
		TntFillMeta.setDisplayName("§c§l>> §fTNT Fill §c§l<<");
		ArrayList<String> TntFillLore = new ArrayList();
		TntFillLore.add("§7This skill will allow you to use /f tnt fill,");
		TntFillLore.add("§7providing that your faction permits you to do so!");
		TntFillLore.add("§c§l> §7§lCost: §c2 Skill Tokens");
		TntFillMeta.setLore(TntFillLore);
		TntFill.setItemMeta(TntFillMeta);

		ItemStack Back = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta BackMeta = Back.getItemMeta();
		BackMeta.setDisplayName("§c§l>> §fBack §c§l<<");
		ArrayList<String> BackLore = new ArrayList();
		BackLore.add("§c§l> §7Click to go back to the previous menu!");
		BackMeta.setLore(BackLore);
		Back.setItemMeta(BackMeta);

		SkillsGUI.setItem(0, GenBuckets);
		SkillsGUI.setItem(1, TntFill);
		SkillsGUI.setItem(8, Back);
		player.openInventory(SkillsGUI);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if (event.getClickedInventory() != null) {
			String inventoryName = event.getClickedInventory().getName();
			if (inventoryName.equals("§c§l>> §8Skills Master §c§l<<")) {
				event.setCancelled(true);
				if ((event.getCurrentItem() != null) && (event.getCurrentItem().hasItemMeta())
						&& (event.getCurrentItem().getItemMeta().hasDisplayName())) {
					String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
					if (itemName.equals("§c§l>> §fMovement §c§l<<")) {
						event.getWhoClicked().closeInventory();
						openMovementInventory((Player) event.getWhoClicked());
					} else if (itemName.equals("§c§l>> §fCombat §c§l<<")) {
						event.getWhoClicked().closeInventory();
						openCombatInventory((Player) event.getWhoClicked());
					} else if (itemName.equals("§c§l>> §fClasses §c§l<<")) {
						event.getWhoClicked().closeInventory();
						openClassesInventory((Player) event.getWhoClicked());
					} else if (itemName.equals("§c§l>> §fFactions §c§l<<")) {
						event.getWhoClicked().closeInventory();
						openFactionsInventory((Player) event.getWhoClicked());
					}
				}
			} else if (inventoryName.equals("§c§l>> §8Movement Skills §c§l<<")) {
				event.setCancelled(true);
				if ((event.getCurrentItem() != null) && (event.getCurrentItem().hasItemMeta())
						&& (event.getCurrentItem().getItemMeta().hasDisplayName())) {
					String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
					if (itemName.equals("§c§l>> §fFactions Flight §c§l<<")) {
						tryBuy("Flight", MPlayer.get((Player) event.getWhoClicked()), 1);
					} else if (itemName.equals("§c§l>> §fSpeed §c§l<<")) {
						tryBuy("Speed", MPlayer.get((Player) event.getWhoClicked()), 5);
					} else if (itemName.equals("§c§l>> §fBack §c§l<<")) {
						event.getWhoClicked().closeInventory();
						openInventory((Player) event.getWhoClicked());
					}
				}
			} else if (inventoryName.equals("§c§l>> §8Combat Skills §c§l<<")) {
				event.setCancelled(true);
				if ((event.getCurrentItem() != null) && (event.getCurrentItem().hasItemMeta())
						&& (event.getCurrentItem().getItemMeta().hasDisplayName())) {
					String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
					if (itemName.equals("§c§l>> §fMob Damage §c§l<<")) {
						tryBuy("MobDamage", MPlayer.get((Player) event.getWhoClicked()), 2);
					} else if (itemName.equals("§c§l>> §fCombat McMMO §c§l<<")) {
						tryBuy("CombatMcMMO", MPlayer.get((Player) event.getWhoClicked()), 5);
					} else if (itemName.equals("§c§l>> §fPlayer Damage §c§l<<")) {
						tryBuy("PlayerDamage", MPlayer.get((Player) event.getWhoClicked()), 10);
					} else if (itemName.equals("§c§l>> §fBack §c§l<<")) {
						event.getWhoClicked().closeInventory();
						openInventory((Player) event.getWhoClicked());
					}
				}
			} else if (inventoryName.equals("§c§l>> §8Class Skills §c§l<<")) {
				event.setCancelled(true);
				if ((event.getCurrentItem() != null) && (event.getCurrentItem().hasItemMeta())
						&& (event.getCurrentItem().getItemMeta().hasDisplayName())) {
					String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
					if (itemName.equals("§c§l>> §fMiner Class §c§l<<")) {
						tryBuy("Miner", MPlayer.get((Player) event.getWhoClicked()), 1);
					} else if (itemName.equals("§c§l>> §fArcher Class §c§l<<")) {
						tryBuy("Archer", MPlayer.get((Player) event.getWhoClicked()), 2);
					} else if (itemName.equals("§c§l>> §fBard Class §c§l<<")) {
						tryBuy("Bard", MPlayer.get((Player) event.getWhoClicked()), 5);
					} else if (itemName.equals("§c§l>> §fBack §c§l<<")) {
						event.getWhoClicked().closeInventory();
						openInventory((Player) event.getWhoClicked());
					}
				}
			} else if (inventoryName.equals("§c§l>> §8Factions Skills §c§l<<")) {
				event.setCancelled(true);
				if ((event.getCurrentItem() != null) && (event.getCurrentItem().hasItemMeta())
						&& (event.getCurrentItem().getItemMeta().hasDisplayName())) {
					String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
					if (itemName.equals("§c§l>> §fGen Buckets §c§l<<")) {
						tryBuy("GenBuckets", MPlayer.get((Player) event.getWhoClicked()), 1);
					} else if (itemName.equals("§c§l>> §fTnt Fill §c§l<<")) {
						tryBuy("TntFill", MPlayer.get((Player) event.getWhoClicked()), 2);
					} else if (itemName.equals("§c§l>> §fBack §c§l<<")) {
						event.getWhoClicked().closeInventory();
						openInventory((Player) event.getWhoClicked());
					}
				}
			}
		}
	}

	public void tryBuy(String skillName, MPlayer mplayer, int cost) {
		if (mplayer.getTokens() - cost >= 0) {
			if (skillName.equalsIgnoreCase("Flight")) {
				if (mplayer.getSkillFlight()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillFlight(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the flight skill!");
				}
			} else if (skillName.equalsIgnoreCase("Speed")) {
				if (mplayer.getSkillSpeed()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillSpeed(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.getPlayer().setWalkSpeed(0.205F);
					mplayer.message("§a§l(!)§7 You have aquired the speed skill!");
				}
			} else if (skillName.equalsIgnoreCase("MobDamage")) {
				if (mplayer.getSkillMobDamage()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillMobDamage(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the mob-damage skill!");
				}
			} else if (skillName.equalsIgnoreCase("CombatMcMMO")) {
				if (mplayer.getSkillCombatMcMMO()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillCombatMcMMO(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the combat mcmmo skill!");
				}
			} else if (skillName.equalsIgnoreCase("PlayerDamage")) {
				if (mplayer.getSkillPlayerDamage()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillPlayerDamage(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the player damage skill!");
				}
			} else if (skillName.equalsIgnoreCase("GenBuckets")) {
				if (mplayer.getSkillGenBuckets()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillGenBuckets(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the gen-buckets skill!");
				}
			} else if (skillName.equalsIgnoreCase("TntFill")) {
				if (mplayer.getSkillTntFill()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillTntFill(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the tnt fill skill!");
				}
			} else if (skillName.equalsIgnoreCase("Miner")) {
				if (mplayer.getSkillMiner()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillMiner(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the miner skill!");
				}
			} else if (skillName.equalsIgnoreCase("Archer")) {
				if (mplayer.getSkillArcher()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillArcher(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the archer skill!");
				}
			} else if (skillName.equalsIgnoreCase("Bard")) {
				if (mplayer.getSkillBard()) {
					mplayer.message("§c§l(!)§7 You have already purchased this skill!");
				} else {
					mplayer.setSkillBard(true);
					mplayer.setTokens(mplayer.getTokens() - cost);
					mplayer.message("§a§l(!)§7 You have aquired the bard skill!");
				}
			} else {
				System.out.println("[Factions] Error: Incorrect skill name used in net.OnePoundd.Patches.Skills.Java");
			}
		} else {
			mplayer.message("§c§l(!)§7 You do not have enough tokens to purchase this skill!");
		}
	}

	@EventHandler
	public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
		if ((event.getDamager() instanceof Player)) {
			Player player = (Player) event.getDamager();
			if (!(event.getEntity() instanceof Player)) {
				if (MPlayer.get(player).getSkillMobDamage()) {
					event.setDamage(event.getDamage() * 1.25D);
				}
			} else if (MPlayer.get(player).getSkillPlayerDamage()) {
				event.setDamage(event.getDamage() * 1.025D);
			}
		}
	}

	@EventHandler
	public void onMcMMOGainEvent(McMMOPlayerXpGainEvent event) {
		if (((event.getSkill().equals(SkillType.ARCHERY)) || (event.getSkill().equals(SkillType.AXES))
				|| (event.getSkill().equals(SkillType.SWORDS))) && (event.getXpGainReason().equals(XPGainReason.PVE))
				&& (!MPlayer.get(event.getPlayer()).getSkillCombatMcMMO())) {
			event.setRawXpGained(0.0F);
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\Skills.class Java
 * compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */