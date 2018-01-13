package net.OnePoundd.Patches;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MFlag;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class BlockManipulation implements Listener {
	ArrayList<CastleBlock> CastleBlocks = new ArrayList();

	public BlockManipulation() {
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			Block block = event.getClickedBlock();
			Material material = block.getType();
			if ((material.equals(Material.SPONGE)) || (material.equals(Material.GLASS))
					|| (material.equals(Material.STAINED_GLASS)) || (material.equals(Material.WOOL))) {
				Faction faction = BoardColl.get().getFactionAt(PS.valueOf(block.getLocation()));
				if (MPerm.getPermBuild().has(MPlayer.get(event.getPlayer()), faction, true)) {
					block.breakNaturally();
				}
			}
		}
	}

	@EventHandler
	public void onPlaceEvent(BlockPlaceEvent event) {
		if (!BoardColl.get().getFactionAt(PS.valueOf(event.getBlock())).getFlag("permanent")) {
			final Faction faction = BoardColl.get().getFactionAt(PS.valueOf(event.getBlock()));
			Material m = event.getBlock().getType();
			if (m.equals(Material.MOB_SPAWNER)) {
				final CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
				Bukkit.getScheduler().runTaskLater(Factions.get(), new Runnable() {
					public void run() {
						CreatureSpawner waitspawner = (CreatureSpawner) spawner.getLocation().getBlock().getState();
						System.out.println("spawner: " + waitspawner.getCreatureTypeName());
						if (waitspawner.getCreatureTypeName().equalsIgnoreCase("Enderman")) {
							faction.setWealth(faction.getWealth() + MConf.get().endermanSpawnerPrice);
						} else if (waitspawner.getCreatureTypeName().equalsIgnoreCase("Creeper")) {
							faction.setWealth(faction.getWealth() + MConf.get().creeperSpawnerPrice);
						} else if (waitspawner.getCreatureTypeName().equalsIgnoreCase("Witch")) {
							faction.setWealth(faction.getWealth() + MConf.get().witchSpawnerPrice);
						} else if (waitspawner.getCreatureTypeName().equalsIgnoreCase("PigZombie")) {
							faction.setWealth(faction.getWealth() + MConf.get().pigmanSpawnerPrice);
						} else if (waitspawner.getCreatureTypeName().equalsIgnoreCase("Squid")) {
							faction.setWealth(faction.getWealth() + MConf.get().squidSpawnerPrice);
						} else if (waitspawner.getCreatureTypeName().equalsIgnoreCase("Skeleton")) {
							faction.setWealth(faction.getWealth() + MConf.get().skeletonSpawnerPrice);
						} else if (waitspawner.getCreatureTypeName().equalsIgnoreCase("Zombie")) {
							faction.setWealth(faction.getWealth() + MConf.get().zombieSpawnerPrice);
						}
					}
				}, 10L);
			} else if (m.equals(Material.BEACON)) {
				faction.setWealth(faction.getWealth() + MConf.get().beaconPrice);
			} else if (m.equals(Material.HOPPER)) {
				faction.setWealth(faction.getWealth() + MConf.get().hopperPrice);
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Faction faction = BoardColl.get().getFactionAt(PS.valueOf(event.getBlock()));
		if (faction.getFlag(MFlag.getFlagPermanent())) {
			if ((faction.getName().equals("Castle")) && (!event.getPlayer().hasPermission("server.admin"))) {
				event.setCancelled(true);
				if (event.getBlock().getType().equals(Material.COBBLESTONE)) {
					for (CastleBlock block : CastleBlocks) {
						if (block.getLocation().equals(event.getBlock().getLocation())) {
							int newDurability = block.getDurability() - 1;
							if (newDurability == 0) {
								CastleBlocks.remove(block);
								event.getPlayer().sendMessage("§5§l(!)§7 This block has shattered to pieces!");
								event.getBlock().setType(Material.AIR);
								return;
							}
							event.getPlayer()
									.sendMessage("§5§l(!)§7 This block is now at " + newDurability + " durability!");
							block.setDurability(newDurability);
							return;
						}
					}
					CastleBlocks.add(new CastleBlock(event.getBlock(), 100));
				}
			}
		} else {
			Material m = event.getBlock().getType();
			if (m.equals(Material.MOB_SPAWNER)) {
				CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
				if (spawner.getCreatureTypeName().equalsIgnoreCase("Enderman")) {
					faction.setWealth(faction.getWealth() - MConf.get().endermanSpawnerPrice);
				} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Creeper")) {
					faction.setWealth(faction.getWealth() - MConf.get().creeperSpawnerPrice);
				} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Witch")) {
					faction.setWealth(faction.getWealth() - MConf.get().witchSpawnerPrice);
				} else if (spawner.getCreatureTypeName().equalsIgnoreCase("PigZombie")) {
					faction.setWealth(faction.getWealth() - MConf.get().pigmanSpawnerPrice);
				} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Squid")) {
					faction.setWealth(faction.getWealth() - MConf.get().squidSpawnerPrice);
				} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Skeleton")) {
					faction.setWealth(faction.getWealth() - MConf.get().skeletonSpawnerPrice);
				} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Zombie")) {
					faction.setWealth(faction.getWealth() - MConf.get().zombieSpawnerPrice);
				}
			} else if (m.equals(Material.BEACON)) {
				faction.setWealth(faction.getWealth() - MConf.get().beaconPrice);
			} else if (m.equals(Material.HOPPER)) {
				faction.setWealth(faction.getWealth() - MConf.get().hopperPrice);
			}
		}
		MPlayer player = MPlayer.get(event.getPlayer());
		Material m = event.getBlock().getType();
		if (!event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
			if (m == Material.DIAMOND_ORE) {
				player.addExperience(18);
			} else if (m == Material.GOLD_ORE) {
				player.addExperience(12);
			} else if (m == Material.REDSTONE_ORE) {
				player.addExperience(8);
			} else if (m == Material.IRON_ORE) {
				player.addExperience(4);
			} else if (m == Material.COAL) {
				player.addExperience(3);
			} else {
				player.addExperience(2);
			}
		} else {
			player.addExperience(2);
		}
	}

	@EventHandler
	public void onBlockExplode(EntityExplodeEvent event) {
		if (!BoardColl.get().getFactionAt(PS.valueOf(event.getLocation())).getFlag("permanent")) {
			Faction faction = BoardColl.get().getFactionAt(PS.valueOf(event.getLocation()));
			List<Block> blocks = event.blockList();
			for (Block block : blocks) {
				Material m = block.getType();
				if (m.equals(Material.MOB_SPAWNER)) {
					CreatureSpawner spawner = (CreatureSpawner) block.getState();
					if (spawner.getCreatureTypeName().equalsIgnoreCase("Enderman")) {
						faction.setWealth(faction.getWealth() - MConf.get().endermanSpawnerPrice);
					} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Creeper")) {
						faction.setWealth(faction.getWealth() - MConf.get().creeperSpawnerPrice);
					} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Witch")) {
						faction.setWealth(faction.getWealth() - MConf.get().witchSpawnerPrice);
					} else if (spawner.getCreatureTypeName().equalsIgnoreCase("PigZombie")) {
						faction.setWealth(faction.getWealth() - MConf.get().pigmanSpawnerPrice);
					} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Squid")) {
						faction.setWealth(faction.getWealth() - MConf.get().squidSpawnerPrice);
					} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Skeleton")) {
						faction.setWealth(faction.getWealth() - MConf.get().skeletonSpawnerPrice);
					} else if (spawner.getCreatureTypeName().equalsIgnoreCase("Zombie")) {
						faction.setWealth(faction.getWealth() - MConf.get().zombieSpawnerPrice);
					}
				} else if (m.equals(Material.BEACON)) {
					faction.setWealth(faction.getWealth() - MConf.get().beaconPrice);
				} else if (m.equals(Material.HOPPER)) {
					faction.setWealth(faction.getWealth() - MConf.get().hopperPrice);
				}
			}
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\BlockManipulation.
 * class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */