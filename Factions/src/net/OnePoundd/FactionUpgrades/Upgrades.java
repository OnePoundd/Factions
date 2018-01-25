package net.OnePoundd.FactionUpgrades;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class Upgrades implements Listener {
	public Upgrades() {
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event) {
		if ((event.getEntity().getType().equals(EntityType.PLAYER))
				&& (event.getDamager().getType().equals(EntityType.PLAYER))) {
			MPlayer mPlayerAttacked = MPlayer.get((Player) event.getEntity());
			if ((mPlayerAttacked.hasFaction()) && (mPlayerAttacked.isInOwnTerritory())) {
				int damageUpgradeLevel = mPlayerAttacked.getFaction().getDamageUpgradeLevel();
				if (damageUpgradeLevel == 1) {
					event.setDamage(event.getDamage() * 0.95D);
				} else if (damageUpgradeLevel == 2) {
					event.setDamage(event.getDamage() * 0.9D);
				} else if (damageUpgradeLevel == 3) {
					event.setDamage(event.getDamage() * 0.85D);
				} else if (damageUpgradeLevel == 4) {
					event.setDamage(event.getDamage() * 0.8D);
				} else if (damageUpgradeLevel == 5) {
					event.setDamage(event.getDamage() * 0.75D);
				}
			}
		}
	}

	@EventHandler
	public void onHungerLoss(FoodLevelChangeEvent event) {
		if (event.getEntityType().equals(EntityType.PLAYER)) {
			MPlayer player = MPlayer.get(event.getEntity());
			if ((player.getFaction().getHungerUpgradeLevel() == 1) && (BoardColl.get()
					.getFactionAt(PS.valueOf(player.getPlayer().getLocation())).equals(player.getFaction()))) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void CropGrowEvent(final BlockGrowEvent event) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Factions.get(), new Runnable() {
			public void run() {
				if (event.getBlock().getType().equals(Material.SUGAR_CANE_BLOCK)) {
					int cropUpgradeLevel = BoardColl.get()
							.getFactionAt(PS.valueOf(event.getBlock().getLocation().getChunk()))
							.getCropGrowthUpgradeLevel();
					Location old = event.getBlock().getLocation();
					Location newloc = new Location(old.getWorld(), old.getX(), old.getY() + 1.0D, old.getZ());
					Random rand = new Random();
					int index = rand.nextInt(4) + 0;
					if (newloc.getBlock().getType().equals(Material.AIR)) {
						if (cropUpgradeLevel == 1) {
							if (index == 1) {
								newloc.getBlock().setType(Material.SUGAR_CANE_BLOCK);
							}
						} else if (cropUpgradeLevel == 2) {
							if ((index == 1) || (index == 2)) {
								newloc.getBlock().setType(Material.SUGAR_CANE_BLOCK);
							}
						} else if (cropUpgradeLevel == 3) {
							if ((index == 1) || (index == 2) || (index == 3)) {
								newloc.getBlock().setType(Material.SUGAR_CANE_BLOCK);
							}
						} else if (cropUpgradeLevel == 4) {
							newloc.getBlock().setType(Material.SUGAR_CANE_BLOCK);
						}
					}
				}
			}
		}, 1L);
	}
	
	@EventHandler
	public void onSpawnerSpawnEvent(final SpawnerSpawnEvent event) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Factions.get(), new Runnable() { //waits one tick for the new delay value
			public void run() {
				CreatureSpawner spawner = (CreatureSpawner) event.getSpawner().getBlock().getState();
				Faction faction = BoardColl.get().getFactionAt(PS.valueOf(spawner.getLocation().getChunk()));
				int spawnerUpgradeLevel = faction.getSpawnerUpgradeLevel();
				
				int castleFactor = 1;
				if (faction.getOwnsCastle()) {
					castleFactor = 2;
				}
				if (spawnerUpgradeLevel == 0) {
					double newDelay = (spawner.getDelay() * 2) / castleFactor;
					spawner.setDelay((int) newDelay);
					spawner.update();
				} else if (spawnerUpgradeLevel == 1) {
					double newDelay = ((spawner.getDelay() * 1.5) / 1.25) / castleFactor;
					spawner.setDelay((int) newDelay);
					spawner.update();
				} else if (spawnerUpgradeLevel == 2) {
					double newDelay = ((spawner.getDelay() * 1.5) / 1.5) / castleFactor;
					System.out.println("New Delay: " + newDelay);
					spawner.setDelay((int) newDelay);
					spawner.update();
				} else if (spawnerUpgradeLevel == 3) {
					double newDelay = ((spawner.getDelay() * 1.5) / 1.75) / castleFactor;
					spawner.setDelay((int) newDelay);
					spawner.update();
				} else if (spawnerUpgradeLevel == 4) {
					double newDelay = ((spawner.getDelay() * 1.5) / 2) / castleFactor;
					spawner.setDelay((int) newDelay);
					spawner.update();
				}
			}
		}, 1L);
	}

	@EventHandler
	public void onFurnaceSmelt(FurnaceSmeltEvent event) {
		if (event.getSource().getType().equals(Material.SULPHUR)) {
			Faction faction = BoardColl.get().getFactionAt(PS.valueOf(event.getBlock().getLocation()));
			if (faction.getSmeltingUpgradeLevel() == 0) {
				event.setResult(new ItemStack(Material.AIR));
			} else {
				Random rand = new Random();
				int index = rand.nextInt(100) + 1;
				if (faction.getSmeltingUpgradeLevel() == 1) {
					if (index < 10) {
						event.setResult(new ItemStack(Material.AIR));
					}
				} else if ((faction.getSmeltingUpgradeLevel() == 2) && (index < 20)) {
					event.setResult(new ItemStack(Material.AIR));
				}
				if ((faction.getSmeltingUpgradeLevel() == 3) && (index < 30)) {
					event.setResult(null);
				}
			}
		}
	}

	@EventHandler
	public void onExperienceGain(PlayerExpChangeEvent event) {
		if (event.getAmount() > 0) {
			Faction faction = MPlayer.get(event.getPlayer()).getFaction();
			int level = faction.getExpUpgradeLevel();
			if (level > 0) {
				if (level == 1) {
					event.setAmount((int) (event.getAmount() * 1.05D));
				} else if (level == 2) {
					event.setAmount((int) (event.getAmount() * 1.1D));
				} else if (level == 3) {
					event.setAmount((int) (event.getAmount() * 1.15D));
				} else if (level == 4) {
					event.setAmount((int) (event.getAmount() * 1.2D));
				} else if (level == 5) {
					event.setAmount((int) (event.getAmount() * 1.25D));
				}
			}
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\FactionUpgrades\Upgrades.
 * class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */