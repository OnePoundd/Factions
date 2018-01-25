package com.massivecraft.factions.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.integration.Econ;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.Requirement;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;

import net.OnePoundd.Patches.Banner;

public class CmdFactionsBanner extends FactionsCommand implements Listener{
	
	public static List<Banner> banners = new ArrayList<Banner>();
	
	public CmdFactionsBanner() {
		addAliases(new String[] { "getbanner" });
		addRequirements(new Requirement[] { RequirementIsPlayer.get() });
	}

	public void perform() throws MassiveException {
		if(msender.hasFaction()) {
			if(Econ.payForAction(1500, msender, "buy a faction banner")){
				msg("§a§l(!)§7 You have just purchased a banner! Shift-place the banner on the ground to allow other faction members "
				+ "to assist you in combat!");
				
				ItemStack banner = new ItemStack(Material.BANNER);
				ItemMeta bannerMeta = banner.getItemMeta();
				bannerMeta.setDisplayName("§bFaction Banner");
				List<String> lore = new ArrayList<String>();
				lore.add("§7Place this in any territory to allow faction");
				lore.add("§7members to teleport and assist you in combat!");
				lore.add("§7NOTE: If the banner gets destroyed, members");
				lore.add("§7will not be able to teleport to the banner!");
				bannerMeta.setLore(lore);
				banner.setItemMeta(bannerMeta);
				
				msender.getPlayer().getInventory().addItem(banner);
			}
		}else {
			msg("§c§l(!)§7 You must belong to a faction to retreive a banner!");
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getPlayer().isSneaking()) {
			Material m = event.getBlock().getType();
			if(m.equals(Material.STANDING_BANNER) || m.equals(Material.WALL_BANNER)) {
				if(event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() ) {
					if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bFaction Banner")) {
						MPlayer player = MPlayer.get(event.getPlayer());
						Material replaced = event.getBlockReplacedState().getType();
						if(replaced.equals(Material.WATER) || replaced.equals(Material.STATIONARY_WATER)
						|| replaced.equals(Material.LAVA) || replaced.equals(Material.STATIONARY_LAVA)
						|| replaced.equals(Material.FIRE) || replaced.equals(Material.SNOW) || replaced.equals(Material.YELLOW_FLOWER)
						|| replaced.equals(Material.LONG_GRASS) || replaced.equals(Material.CHORUS_FLOWER)) {
							player.msg("§c§l(!)§7 You cannot place a banner there!");
						}else {
							if(player.getFaction().getPlacedBanner() == null) {
								Banner banner = new Banner(player.getFaction(), event.getBlock());
								player.getFaction().setPlacedBanner(banner);
								banners.add(banner);
								player.msg("§6§l(!)§7 You have placed a faction banner! Faction members will be able to teleport to it until destroyed by another player, or automatically in 1 minute.");
								player.getFaction().msg("§6§l(!)§7 " + player.getName() + " has placed a banner, type /f assist to help!");
							}else {
								player.msg("§c§l(!)§7 Your faction already has a banner placed!");
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		for(Banner banner : banners) {
			if(event.getBlock().equals(banner.getBlock())) {
				banner.getOwner().setPlacedBanner(null);
				banners.remove(banner);
				event.setDropItems(false);
				break;
			}
		}
	}
	
}
