package net.OnePoundd.Patches;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.MPlayer;
import java.util.ArrayList;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class Vanish implements Listener {
	public static ArrayList<Player> vanishedPlayers = new ArrayList();

	public Vanish() {
	}

	@EventHandler
	public void onPlayerJoinEvent(final PlayerJoinEvent event) {
		if (event.getPlayer().hasPermission("server.admin")) {
			Bukkit.getScheduler().runTaskLater(Factions.get(), new Runnable() {
				public void run() {
					Vanish.vanishPlayer(event.getPlayer());
				}
			}, 1L);
		} else {
			for (Player playerToHide : vanishedPlayers) {
				PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(
						PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,
						new EntityPlayer[] { ((CraftPlayer) playerToHide).getHandle() });
				PlayerConnection connection = ((CraftPlayer) event.getPlayer()).getHandle().playerConnection;
				connection.sendPacket(packet);
			}
		}
	}

	public static void vanishPlayer(Player player) {
		MPlayer mPlayer = MPlayer.get(player);
		mPlayer.setStealth(true);
		PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(
				PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,
				new EntityPlayer[] { ((CraftPlayer) player).getHandle() });
		for (Player playerToHideFrom : Bukkit.getOnlinePlayers()) {
			if (!playerToHideFrom.hasPermission("server.admin")) {
				playerToHideFrom.hidePlayer(player);
				PlayerConnection connection = ((CraftPlayer) playerToHideFrom).getHandle().playerConnection;
				connection.sendPacket(packet);
			}
		}
		vanishedPlayers.add(player);
		mPlayer.msg("§a§l(!)§7 You have been completely hidden from other players. Type /vanish to re-appear!");
	}

	public static void unVanishPlayer(Player player) {
		MPlayer mPlayer = MPlayer.get(player);
		mPlayer.setStealth(false);
		PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(
				PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,
				new EntityPlayer[] { ((CraftPlayer) player).getHandle() });
		for (Player playerToHideFrom : Bukkit.getOnlinePlayers()) {
			playerToHideFrom.showPlayer(player);
			PlayerConnection connection = ((CraftPlayer) playerToHideFrom).getHandle().playerConnection;
			connection.sendPacket(packet);
		}
		vanishedPlayers.remove(player);
		mPlayer.msg("§a§l(!)§7 You can now be seen by other players!");
	}
}
