package net.OnePoundd.Patches;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.factions.event.EventFactionsMembershipChange.MembershipChangeReason;
import com.massivecraft.massivecore.ps.PS;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class Raiding implements Listener {
	public Raiding() {
	}

	@EventHandler
	public void onTntExplode(EntityExplodeEvent event) {
		if (event.getEntityType().equals(EntityType.PRIMED_TNT)) {
			TNTPrimed tnt = (TNTPrimed) event.getEntity();
			Faction raiders = BoardColl.get().getFactionAt(PS.valueOf(tnt.getSourceLoc()));
			Faction defenders = BoardColl.get().getFactionAt(PS.valueOf(tnt.getLocation()));
			Rel relation = raiders.getRelationTo(defenders);
			Chunk explodingChunk = event.getLocation().getChunk();
			if (defenders.hasCapital()) {
				if ((explodingChunk.getX() == defenders.getCapitalChunkX())
						&& (explodingChunk.getZ() == defenders.getCapitalChunkZ())) {
					if (!raiders.equals(defenders)) {
						if (relation.equals(Rel.ENEMY)) {
							if (defenders.isRaidCooldownOver()) {
								int y = tnt.getLocation().getBlockY();
								if ((y >= 100) && (y <= 149)) {
									int points = (int) (defenders.getVictoryPoints() * 0.1D);
									points = getPointsAfterSizeComparison(raiders, defenders, points);
									defenders.takeVicotryPoints(points);
									raiders.addVicotryPoints(points);
									defenders.setTimeRaidedAtMillis(System.currentTimeMillis());
									for (Player p : Bukkit.getOnlinePlayers()) {
										p.sendMessage("§c§l[§4§lALERT§c§l] §4§l" + raiders.getName()
												+ " §c§lhas breached §4§l" + defenders.getName()
												+ "§c§l and stole §4§l" + points + " points§c§l!");
									}
									defenders.msg("§b§l(!)§7 You have been breached by " + raiders.getName()
											+ "! Your remaining victory points will be "
											+ " protected for 12 hours. Use this time to clear out the remaining enemies and repair your base!");
								} else if ((y >= 150) && (y <= 199)) {
									int points = (int) (defenders.getVictoryPoints() * 0.2D);
									points = getPointsAfterSizeComparison(raiders, defenders, points);
									defenders.takeVicotryPoints(points);
									raiders.addVicotryPoints(points);
									defenders.setTimeRaidedAtMillis(System.currentTimeMillis());
									for (Player p : Bukkit.getOnlinePlayers()) {
										p.sendMessage("§c§l[§4§lALERT§c§l] §4§l" + raiders.getName()
												+ " §c§lhas breached §4§l" + defenders.getName()
												+ "§c§l and stole §4§l" + points + " points§c§l!");
									}
									defenders.msg("§b§l(!)§7 You have been breached by " + raiders.getName()
											+ "! Your remaining victory points will be "
											+ " protected for 12 hours. Use this time to clear out the remaining enemies and repair your base!");
								} else if ((y >= 200) && (y <= 256)) {
									int points = (int) (defenders.getVictoryPoints() * 0.3D);
									points = getPointsAfterSizeComparison(raiders, defenders, points);
									defenders.takeVicotryPoints(points);
									raiders.addVicotryPoints(points);
									defenders.setTimeRaidedAtMillis(System.currentTimeMillis());
									for (Player p : Bukkit.getOnlinePlayers()) {
										p.sendMessage("§c§l[§4§lALERT§c§l] §4§l" + raiders.getName()
												+ " §c§lhas breached §4§l" + defenders.getName()
												+ "§c§l and stole §4§l" + points + " points§c§l!");
									}
									defenders.msg("§b§l(!)§7 You have been breached by " + raiders.getName()
											+ "! Your remaining victory points will be "
											+ " protected for 12 hours. Use this time to clear out the remaining enemies and repair your base!");
								}
							} else {
								for (Player p : raiders.getOnlinePlayers()) {
									p.sendMessage("§c§l(!)§7 That faction is on a raid cooldown!");
								}
							}
						} else {
							for (Player p : raiders.getOnlinePlayers()) {
								p.sendMessage("§c§l(!)§7 You must be enemied to steal victory points!");
							}
						}
					}
				} else if (!raiders.equals(defenders)) {
					PS ps = PS.valueOf(explodingChunk);
					Faction northFaction = BoardColl.get().getFactionAt(PS.valueOf(
							ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(0.0D, 0.0D, 16.0D)));
					Faction eastFaction = BoardColl.get().getFactionAt(PS.valueOf(
							ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(16.0D, 0.0D, 0.0D)));
					Faction southFaction = BoardColl.get().getFactionAt(PS.valueOf(
							ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(0.0D, 0.0D, -16.0D)));
					Faction westFaction = BoardColl.get().getFactionAt(PS.valueOf(
							ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(-16.0D, 0.0D, 0.0D)));
					if ((!northFaction.getName().equals("Wilderness")) && (!eastFaction.getName().equals("Wilderness"))
							&& (!southFaction.getName().equals("Wilderness"))
							&& (!westFaction.getName().equals("Wilderness"))
							&& (!northFaction.getName().equals(raiders.getName()))
							&& (!eastFaction.getName().equals(raiders.getName()))
							&& (!southFaction.getName().equals(raiders.getName()))
							&& (!westFaction.getName().equals(raiders.getName()))) {
						if (tnt.getLocation().getBlockY() >= 150) {
							if (!defenders.isUnderAttack()) {
								defenders.setUnderAttack();
								raiders.msg("§b§l(!)§7 The faction, " + defenders.getName()
										+ " is now marked as under attack! "
										+ "For the next ten minutes the faction will not be able to change its members or the location of"
										+ " their capital chunk. Every time you land TNT in their chunks it will reset the timer! Remember, "
										+ "in order to steal any points you must explode tnt above y=150 in their capital chunk, but you earn more the higher you go!");
							} else {
								defenders.setUnderAttack();
							}
						} else if (!defenders.isUnderAttack()) {
							raiders.msg(
									"§b§l(!)§7 Remember, in order to steal any victory points you must explode tnt above y=150 in the capital chunk. Furthermore, the higher you explode tnt, the more points you will earn!");
						}
					}
				}
			}
		}
	}

	public int getPointsAfterSizeComparison(Faction raiders, Faction defenders, int basePoints) {
		int raidersize = raiders.getMPlayers().size();
		int defendersize = defenders.getMPlayers().size();
		if (raidersize > defendersize) {
			basePoints /= raidersize / defendersize;
		}
		return basePoints;
	}

	@EventHandler
	public void onMembershipChange(EventFactionsMembershipChange event) {
		if (event.getReason().equals(EventFactionsMembershipChange.MembershipChangeReason.KICK)) {
			if (event.getMPlayer().getFaction().isUnderAttack()) {
				event.getMPlayer().getFaction().msg("§b§l(!)§7 The player, " + event.getMPlayer().getName()
						+ " was attempted to be kicked but  can't"
						+ " because you are under attack! Prevent enemy players from exploding tnt in your chunks for 10 minutes to remove this limitation!");
				event.setCancelled(true);
			}
		} else if ((event.getReason().equals(EventFactionsMembershipChange.MembershipChangeReason.LEAVE))
				&& (event.getMPlayer().getFaction().isUnderAttack())) {
			event.getMPlayer().getFaction().msg("§b§l(!)§7 The player, " + event.getMPlayer().getName()
					+ " attempted to leave the faction but"
					+ " can't because you are under attack! Prevent enemy players from exploding tnt in your chunks for 10 "
					+ "minutes to remove this limitation!");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onTransferEvent(InventoryMoveItemEvent event) {
		if (event.getDestination().getType().equals(InventoryType.CHEST)) {
			final Inventory inventory = event.getDestination();
			if ((inventory.getName().equals("§cTNT Chest")) && (event.getItem().getType().equals(Material.TNT))) {
				final int amount = event.getItem().getAmount();
				Faction faction = BoardColl.get().getFactionAt(PS.valueOf(inventory.getLocation()));
				if (faction.getTntCount() + amount > faction.getTntUpgradeLevel() * 50000) {
					return;
				}
				faction.setTntCount(faction.getTntCount() + amount);
				Bukkit.getScheduler().runTaskLater(Factions.get(), new Runnable() {
					public void run() {
						ItemStack[] arrayOfItemStack;
						int j = (arrayOfItemStack = inventory.getContents()).length;
						for (int i = 0; i < j; i++) {
							ItemStack item = arrayOfItemStack[i];
							if ((item != null) && (item.getType().equals(Material.TNT))) {
								item.setAmount(item.getAmount() - amount);
							}
						}
					}
				}, 1L);
			}
		}
	}

	@EventHandler
	public void onHopperPickupItemEvent(InventoryPickupItemEvent event) {
		if ((event.getInventory().getType().equals(InventoryType.HOPPER))
				&& (event.getItem().getItemStack().getType().equals(Material.MOB_SPAWNER))) {
			event.setCancelled(true);
		}
	}
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\net\OnePoundd\Patches\Raiding.class Java
 * compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */