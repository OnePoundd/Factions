package net.OnePoundd.Patches;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.cmd.CmdFactionsBanner;
import com.massivecraft.factions.entity.Faction;

public class Banner implements Listener{
	
	Faction Owner;
	Block Block;
	
	public Banner(Faction owner, Block block) {
		Owner = owner;
		Block = block;
		
		Bukkit.getScheduler().runTaskLater(Factions.get(), new Runnable() {
			public void run() {
				if(!Block.isEmpty()) {
					Block.breakNaturally();
					owner.setPlacedBanner(null);
					CmdFactionsBanner.banners.remove(this);
				}
			}
		}, 20*60*1);
		
	}
	
	public Block getBlock() {
		return Block;
	}
	
	public Faction getOwner() {
		return Owner;
	}


}
