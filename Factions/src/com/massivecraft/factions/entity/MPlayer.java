package com.massivecraft.factions.entity;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.FactionsIndex;
import com.massivecraft.factions.FactionsParticipator;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.RelationParticipator;
import com.massivecraft.factions.event.EventFactionsChunkChangeType;
import com.massivecraft.factions.event.EventFactionsChunksChange;
import com.massivecraft.factions.event.EventFactionsDisband;
import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.factions.event.EventFactionsMembershipChange.MembershipChangeReason;
import com.massivecraft.factions.event.EventFactionsRemovePlayerMillis;
import com.massivecraft.factions.mixin.PowerMixin;
import com.massivecraft.factions.util.RelationUtil;
import com.massivecraft.massivecore.collections.MassiveMapDef;
import com.massivecraft.massivecore.mixin.MixinSenderPs;
import com.massivecraft.massivecore.mixin.MixinTitle;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.ps.PSFormatHumanSpace;
import com.massivecraft.massivecore.store.SenderEntity;
import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivecore.xlib.gson.annotations.SerializedName;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.OnePoundd.Essentials.Teleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class MPlayer extends SenderEntity<MPlayer> implements FactionsParticipator {
	public static final transient String NOTITLE = Txt.parse("<em><silver>no title set");

	public MPlayer() {
	}

	public static MPlayer get(Object oid) {
		return (MPlayer) MPlayerColl.get().get(oid);
	}

	public MPlayer load(MPlayer that) {
		setLastActivityMillis(that.lastActivityMillis);
		setFactionId(that.factionId);
		setRole(that.role);
		setTitle(that.title);
		setPowerBoost(that.powerBoost);
		setPower(that.power);
		setMapAutoUpdating(that.mapAutoUpdating);
		setOverriding(that.overriding);
		setTerritoryInfoTitles(that.territoryInfoTitles);
		setExperience(that.experience);
		setExperienceOnFriday(that.experienceOnFriday);
		setLevel(that.Level);
		setStealth(that.stealth);
		setBoost(that.boost);
		setBoostEndMillis(that.boostEndMillis);

		setTokens(that.tokens);
		setSkillArcher(that.Archer);
		setSkillBard(that.Bard);
		setSkillCombatMcMMO(that.CombatMcMMO);
		setSkillFlight(that.Flight);
		setSkillGenBuckets(that.GenBuckets);
		setSkillMiner(that.Miner);
		setSkillMobDamage(that.MobDamage);
		setSkillPlayerDamage(that.PlayerDamage);
		setSkillSpeed(that.Speed);
		setSkillTntFill(that.TntFill);
		for (Map.Entry<String, PS> entry : that.homes.entrySet()) {
			setHome((PS) entry.getValue(), (String) entry.getKey());
		}
		return this;
	}

	public boolean isDefault() {
		return false;
	}

	public void postAttach(String id) {
		FactionsIndex.get().update(this);
	}

	public void preDetach(String id) {
		FactionsIndex.get().update(this);
	}

	private long lastActivityMillis = System.currentTimeMillis();
	private String factionId = null;
	private Rel role = null;
	private String title = null;
	private Double powerBoost = null;
	private Double power = null;
	private Boolean mapAutoUpdating = null;
	@SerializedName("usingAdminMode")
	private Boolean overriding = null;
	private Boolean territoryInfoTitles = null;
	private transient WeakReference<Faction> autoClaimFaction = new WeakReference(null);

	public Faction getAutoClaimFaction() {
		if (isFactionOrphan()) {
			return null;
		}
		Faction ret = (Faction) autoClaimFaction.get();
		if (ret == null) {
			return null;
		}
		if (ret.detached()) {
			return null;
		}
		return ret;
	}

	public void setAutoClaimFaction(Faction autoClaimFaction) {
		this.autoClaimFaction = new WeakReference(autoClaimFaction);
	}

	private transient boolean seeingChunk = false;

	public boolean isSeeingChunk() {
		return seeingChunk;
	}

	public void setSeeingChunk(boolean seeingChunk) {
		this.seeingChunk = seeingChunk;
	}

	public void resetFactionData() {
		setFactionId(null);
		setRole(null);
		setTitle(null);
		setAutoClaimFaction(null);
	}

	public long getLastActivityMillis() {
		return lastActivityMillis;
	}

	public void setLastActivityMillis(long lastActivityMillis) {
		long target = lastActivityMillis;
		if (MUtil.equals(Long.valueOf(this.lastActivityMillis), Long.valueOf(target))) {
			return;
		}
		this.lastActivityMillis = target;

		changed();
	}

	public void setLastActivityMillis() {
		setLastActivityMillis(System.currentTimeMillis());
	}

	private Faction getFactionInternal() {
		String effectiveFactionId = (String) convertGet(factionId, MConf.get().defaultPlayerFactionId);
		return Faction.get(effectiveFactionId);
	}

	public boolean isFactionOrphan() {
		return getFactionInternal() == null;
	}

	@Deprecated
	public String getFactionId() {
		return getFaction().getId();
	}

	public Faction getFaction() {
		Faction ret = getFactionInternal();
		if (ret == null) {
			ret = FactionColl.get().getNone();
		}
		return ret;
	}

	public boolean hasFaction() {
		return !getFaction().isNone();
	}

	public void setFactionId(String factionId) {
		String beforeId = this.factionId;

		String afterId = factionId;
		if (MUtil.equals(beforeId, afterId)) {
			return;
		}
		this.factionId = afterId;

		FactionsIndex.get().update(this);

		changed();
	}

	public void setFaction(Faction faction) {
		setFactionId(faction.getId());
	}

	public Rel getRole() {
		if (isFactionOrphan()) {
			return Rel.RECRUIT;
		}
		if (role == null) {
			return MConf.get().defaultPlayerRole;
		}
		return role;
	}

	public void setRole(Rel role) {
		Rel target = role;
		if (MUtil.equals(this.role, target)) {
			return;
		}
		this.role = target;

		changed();
	}

	public boolean hasTitle() {
		return (!isFactionOrphan()) && (title != null);
	}

	public String getTitle() {
		if (isFactionOrphan()) {
			return NOTITLE;
		}
		if (hasTitle()) {
			return title;
		}
		return NOTITLE;
	}

	public void setTitle(String title) {
		String target = Faction.clean(title);
		if (MUtil.equals(this.title, target)) {
			return;
		}
		this.title = target;

		changed();
	}

	public double getPowerBoost() {
		Double ret = powerBoost;
		if (ret == null) {
			ret = Double.valueOf(0.0D);
		}
		return ret.doubleValue();
	}

	public void setPowerBoost(Double powerBoost) {
		Double target = powerBoost;
		if ((target == null) || (target.doubleValue() == 0.0D)) {
			target = null;
		}
		if (MUtil.equals(this.powerBoost, target)) {
			return;
		}
		this.powerBoost = target;

		changed();
	}

	public boolean hasPowerBoost() {
		return getPowerBoost() != 0.0D;
	}

	public double getPowerMaxUniversal() {
		return PowerMixin.get().getMaxUniversal(this);
	}

	public double getPowerMax() {
		return PowerMixin.get().getMax(this);
	}

	public double getPowerMin() {
		return PowerMixin.get().getMin(this);
	}

	public double getPowerPerHour() {
		return PowerMixin.get().getPerHour(this);
	}

	public double getPowerPerDeath() {
		return PowerMixin.get().getPerDeath(this);
	}

	public double getLimitedPower(double power) {
		power = Math.max(power, getPowerMin());
		power = Math.min(power, getPowerMax());

		return power;
	}

	public int getPowerMaxRounded() {
		return (int) Math.round(getPowerMax());
	}

	public int getPowerMinRounded() {
		return (int) Math.round(getPowerMin());
	}

	public int getPowerMaxUniversalRounded() {
		return (int) Math.round(getPowerMaxUniversal());
	}

	@Deprecated
	public double getDefaultPower() {
		return MConf.get().defaultPlayerPower;
	}

	public double getPower() {
		Double ret = power;
		if (ret == null) {
			ret = Double.valueOf(MConf.get().defaultPlayerPower);
		}
		ret = Double.valueOf(getLimitedPower(ret.doubleValue()));
		return ret.doubleValue();
	}

	public void setPower(Double power) {
		Double target = power;
		if (MUtil.equals(this.power, target)) {
			return;
		}
		this.power = target;

		changed();
	}

	public int getPowerRounded() {
		return (int) Math.round(getPower());
	}

	public boolean isMapAutoUpdating() {
		if (mapAutoUpdating == null) {
			return false;
		}
		if (!mapAutoUpdating.booleanValue()) {
			return false;
		}
		return true;
	}

	public void setMapAutoUpdating(Boolean mapAutoUpdating) {
		Boolean target = mapAutoUpdating;
		if (MUtil.equals(target, Boolean.valueOf(false))) {
			target = null;
		}
		if (MUtil.equals(this.mapAutoUpdating, target)) {
			return;
		}
		this.mapAutoUpdating = target;

		changed();
	}

	public boolean isOverriding() {
		if (overriding == null) {
			return false;
		}
		if (!overriding.booleanValue()) {
			return false;
		}
		if (!hasPermission(Perm.OVERRIDE, Boolean.valueOf(true)).booleanValue()) {
			setOverriding(Boolean.valueOf(false));
			return false;
		}
		return true;
	}

	public void setOverriding(Boolean overriding) {
		Boolean target = overriding;
		if (MUtil.equals(target, Boolean.valueOf(false))) {
			target = null;
		}
		if (MUtil.equals(this.overriding, target)) {
			return;
		}
		this.overriding = target;

		changed();
	}

	public boolean isTerritoryInfoTitles() {
		if (!MixinTitle.get().isAvailable()) {
			return false;
		}
		if (territoryInfoTitles == null) {
			return MConf.get().territoryInfoTitlesDefault;
		}
		return territoryInfoTitles.booleanValue();
	}

	public void setTerritoryInfoTitles(Boolean territoryInfoTitles) {
		Boolean target = territoryInfoTitles;
		if (MUtil.equals(target, Boolean.valueOf(MConf.get().territoryInfoTitlesDefault))) {
			target = null;
		}
		if (MUtil.equals(this.territoryInfoTitles, target)) {
			return;
		}
		this.territoryInfoTitles = target;

		changed();
	}

	public String getFactionName() {
		Faction faction = getFaction();
		if (faction.isNone()) {
			return "";
		}
		return faction.getName();
	}

	public String getNameAndSomething(String color, String something) {
		String ret = "";
		ret = ret + color;
		ret = ret + getRole().getPrefix();
		if ((something != null) && (something.length() > 0)) {
			ret = ret + something;
			ret = ret + " ";
			ret = ret + color;
		}
		ret = ret + getName();
		return ret;
	}

	public String getNameAndFactionName() {
		return getNameAndSomething("", getFactionName());
	}

	public String getNameAndTitle(String color) {
		if (hasTitle()) {
			return getNameAndSomething(color, getTitle());
		}
		return getNameAndSomething(color, null);
	}

	public String getNameAndTitle(Faction faction) {
		return getNameAndTitle(getColorTo(faction).toString());
	}

	public String getNameAndTitle(MPlayer mplayer) {
		return getNameAndTitle(getColorTo(mplayer).toString());
	}

	public String describeTo(RelationParticipator observer, boolean ucfirst) {
		return RelationUtil.describeThatToMe(this, observer, ucfirst);
	}

	public String describeTo(RelationParticipator observer) {
		return RelationUtil.describeThatToMe(this, observer);
	}

	public Rel getRelationTo(RelationParticipator observer) {
		return RelationUtil.getRelationOfThatToMe(this, observer);
	}

	public Rel getRelationTo(RelationParticipator observer, boolean ignorePeaceful) {
		return RelationUtil.getRelationOfThatToMe(this, observer, ignorePeaceful);
	}

	public ChatColor getColorTo(RelationParticipator observer) {
		return RelationUtil.getColorOfThatToMe(this, observer);
	}

	public void heal(int amnt) {
		Player player = getPlayer();
		if (player == null) {
			return;
		}
		player.setHealth(player.getHealth() + amnt);
	}

	public boolean isInOwnTerritory() {
		PS ps = MixinSenderPs.get().getSenderPs(getId());
		if (ps == null) {
			return false;
		}
		return BoardColl.get().getFactionAt(ps) == getFaction();
	}

	public boolean isInEnemyTerritory() {
		PS ps = MixinSenderPs.get().getSenderPs(getId());
		if (ps == null) {
			return false;
		}
		return BoardColl.get().getFactionAt(ps).getRelationTo(this) == Rel.ENEMY;
	}

	public long getRemovePlayerMillis(boolean async) {
		EventFactionsRemovePlayerMillis event = new EventFactionsRemovePlayerMillis(async, this);
		event.run();
		return event.getMillis();
	}

	public boolean considerRemovePlayerMillis(boolean async) {
		if (detached()) {
			return false;
		}
		long lastActivityMillis = getLastActivityMillis();

		long toleranceMillis = getRemovePlayerMillis(async);
		if (System.currentTimeMillis() - lastActivityMillis <= toleranceMillis) {
			return false;
		}
		if ((MConf.get().logFactionLeave) || (MConf.get().logFactionKick)) {
			Factions.get().log(new Object[] { "Player " + getName() + " was auto-removed due to inactivity." });
		}
		if (getRole() == Rel.LEADER) {
			Faction faction = getFaction();
			if (faction != null) {
				getFaction().promoteNewLeader();
			}
		}
		leave();
		detach();

		return true;
	}

	public void leave() {
		Faction myFaction = getFaction();

		boolean permanent = myFaction.getFlag(MFlag.getFlagPermanent());
		if (myFaction.getMPlayers().size() > 1) {
			if ((!permanent) && (getRole() == Rel.LEADER)) {
				msg("<b>You must give the leader role to someone else first.");
				return;
			}
			if ((!MConf.get().canLeaveWithNegativePower) && (getPower() < 0.0D)) {
				msg("<b>You cannot leave until your power is positive.");
				return;
			}
		}
		EventFactionsMembershipChange membershipChangeEvent = new EventFactionsMembershipChange(getSender(), this,
				myFaction, EventFactionsMembershipChange.MembershipChangeReason.LEAVE);
		membershipChangeEvent.run();
		if (membershipChangeEvent.isCancelled()) {
			return;
		}
		if (myFaction.isNormal()) {
			for (MPlayer mplayer : myFaction.getMPlayersWhereOnline(true)) {
				mplayer.msg("%s<i> left %s<i>.",
						new Object[] { describeTo(mplayer, true), myFaction.describeTo(mplayer) });
			}
			if (MConf.get().logFactionLeave) {
				Factions.get().log(new Object[] { getName() + " left the faction: " + myFaction.getName() });
			}
		}
		resetFactionData();
		if ((myFaction.isNormal()) && (!permanent) && (myFaction.getMPlayers().isEmpty())) {
			EventFactionsDisband eventFactionsDisband = new EventFactionsDisband(getSender(), myFaction);
			eventFactionsDisband.run();
			if (!eventFactionsDisband.isCancelled()) {
				msg("%s <i>was disbanded since you were the last player.",
						new Object[] { myFaction.describeTo(this, true) });
				if (MConf.get().logFactionDisband) {
					Factions.get().log(new Object[] { "The faction " + myFaction.getName() + " (" + myFaction.getId()
							+ ") was disbanded due to the last player (" + getName() + ") leaving." });
				}
				myFaction.detach();
			}
		}
	}

	public boolean tryClaim(Faction newFaction, Collection<PS> pss) {
		return tryClaim(newFaction, pss, null, null);
	}

	public boolean tryClaim(Faction newFaction, Collection<PS> pss, String formatOne, String formatMany) {
		if (formatOne == null) {
			formatOne = "<h>%s<i> %s <h>%d <i>chunk %s<i>.";
		}
		if (formatMany == null) {
			formatMany = "<h>%s<i> %s <h>%d <i>chunks near %s<i>.";
		}
		if (newFaction == null) {
			throw new NullPointerException("newFaction");
		}
		if (pss == null) {
			throw new NullPointerException("pss");
		}
		Set<PS> chunks = PS.getDistinctChunks(pss);

		Iterator<PS> iter = chunks.iterator();
		while (iter.hasNext()) {
			PS chunk = (PS) iter.next();
			Faction oldFaction = BoardColl.get().getFactionAt(chunk);
			if (newFaction == oldFaction) {
				iter.remove();
			}
		}
		if (chunks.isEmpty()) {
			msg("§c§l(!)§7 This land is already claimed by the faction, %s.", new Object[] { newFaction.getName() });
			return true;
		}
		CommandSender sender = getSender();
		if (sender == null) {
			msg("<b>ERROR: Your \"CommandSender Link\" has been severed.");
			msg("<b>It's likely that you are using Cauldron.");
			msg("<b>We do currently not support Cauldron.");
			msg("<b>We would love to but lack time to develop support ourselves.");
			msg("<g>Do you know how to code? Please send us a pull request <3, sorry.");
			return false;
		}
		EventFactionsChunksChange event = new EventFactionsChunksChange(sender, chunks, newFaction);
		event.run();
		if (event.isCancelled()) {
			return false;
		}
		if (!newFaction.getName().equals("Wilderness")) {
			int totalChunksNearWarzone = getFaction().getChunksNearWarZone();
			PS ps;
			for (Iterator localIterator1 = chunks.iterator(); localIterator1.hasNext();) {
				ps = (PS) localIterator1.next();
				Faction northFaction = BoardColl.get().getFactionAt(PS.valueOf(
						ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(0.0D, 0.0D, 16.0D)));
				Faction eastFaction = BoardColl.get().getFactionAt(PS.valueOf(
						ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(16.0D, 0.0D, 0.0D)));
				Faction southFaction = BoardColl.get().getFactionAt(PS.valueOf(
						ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(0.0D, 0.0D, -16.0D)));
				Faction westFaction = BoardColl.get().getFactionAt(PS.valueOf(
						ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(-16.0D, 0.0D, 0.0D)));
				if ((northFaction.getName().equals("WarZone")) || (eastFaction.getName().equals("WarZone"))
						|| (southFaction.getName().equals("WarZone")) || (westFaction.getName().equals("WarZone"))) {
					totalChunksNearWarzone++;
					if (totalChunksNearWarzone > 4) {
						msg("§c§l(!)§7 You can only claim a maximum of 4 chunks touching warzone!");
						return true;
					}
				} else if ((ps.getChunkX().intValue() >= getFaction().getCapitalChunkX() - 30)
						&& (ps.getChunkX().intValue() <= getFaction().getCapitalChunkX() + 30)
						&& (ps.getChunkZ().intValue() >= getFaction().getCapitalChunkZ() - 30)
						&& (ps.getChunkZ().intValue() <= getFaction().getCapitalChunkZ() + 30)
						&& ((ps.getChunkX().intValue() < getFaction().getCapitalChunkX() - 20)
								|| (ps.getChunkX().intValue() > getFaction().getCapitalChunkX() + 20)
								|| (ps.getChunkZ().intValue() < getFaction().getCapitalChunkZ() - 20)
								|| (ps.getChunkZ().intValue() > getFaction().getCapitalChunkZ() + 20))) {
					msg("§c§l(!)§7 You cannot claim here!");
					msg("§b§l(!)§7 Incase you didn't know, you are allowed to claim a maximum of 20 chunks in each direction from your capital chunk. After this you will not be able to claim for a further 10 chunks. This will allow other factions to build and claim cannons whilst also preventing you from exeeding the 20 chunk buffer limit!");

					return true;
				}
			}
			getFaction().setChunksNearWarZone(totalChunksNearWarzone);
		} else {
			for (PS ps : chunks) {
				if ((ps.getChunkX().intValue() == getFaction().getCapitalChunkX())
						&& (ps.getChunkZ().intValue() == getFaction().getCapitalChunkZ())) {
					msg("§c§l(!)§7 You can not unclaim your capital chunk!");
					msg("§b§l(!)§7 If you've placed your capital and want to move it, you can do so by running the command /f capital at the new location as long as your faction is not under attack!");

					return true;
				}
				Faction northFaction = BoardColl.get().getFactionAt(PS.valueOf(
						ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(0.0D, 0.0D, 16.0D)));
				Faction eastFaction = BoardColl.get().getFactionAt(PS.valueOf(
						ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(16.0D, 0.0D, 0.0D)));
				Faction southFaction = BoardColl.get().getFactionAt(PS.valueOf(
						ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(0.0D, 0.0D, -16.0D)));
				Faction westFaction = BoardColl.get().getFactionAt(PS.valueOf(
						ps.getChunk().asBukkitChunk().getBlock(6, 60, 6).getLocation().add(-16.0D, 0.0D, 0.0D)));
				if ((northFaction.getName().equals("WarZone")) || (eastFaction.getName().equals("WarZone"))
						|| (southFaction.getName().equals("WarZone")) || (westFaction.getName().equals("WarZone"))) {
					BoardColl.get().getFactionAt(ps)
							.setChunksNearWarZone(BoardColl.get().getFactionAt(ps).getChunksNearWarZone() - 1);
				}
			}
		}
		for (PS chunk : chunks) {
			BoardColl.get().setFactionAt(chunk, newFaction);
		}
		for (Map.Entry<Faction, Set<PS>> entry : event.getOldFactionChunks().entrySet()) {
			final Faction oldFaction = entry.getKey();
			final Set<PS> oldChunks = entry.getValue();
			final PS oldChunk = oldChunks.iterator().next();
			final Set<MPlayer> informees = getClaimInformees(this, oldFaction, newFaction);
			final EventFactionsChunkChangeType type = EventFactionsChunkChangeType.get(oldFaction, newFaction, this.getFaction());

			String chunkString = oldChunk.toString(PSFormatHumanSpace.get());
			String typeString = type.past;

			for (MPlayer informee : informees)
			{
				informee.msg((oldChunks.size() == 1 ? formatOne : formatMany), this.describeTo(informee, true), typeString, oldChunks.size(), chunkString);
				informee.msg("  <h>%s<i> --> <h>%s", oldFaction.describeTo(informee, true), newFaction.describeTo(informee, true));
			}
			if (!getFaction().hasCapital()) {
				msg("§b§l(!)§7 You do not yet have a capital chunk! These are required to gain victory points which are what determine your position on /f top! Other factions can steal some of your victory points by exploding tnt in this chunk, so make sure that it's well defended! Run the command /f capital to set your capital chunk!");
			}
		}
		return true;
	}

	public static Set<MPlayer> getClaimInformees(MPlayer msender, Faction... factions) {
		Set<MPlayer> ret = new HashSet();
		if (msender != null) {
			ret.add(msender);
		}
		Faction[] arrayOfFaction;
		int j = (arrayOfFaction = factions).length;
		for (int i = 0; i < j; i++) {
			Faction faction = arrayOfFaction[i];
			if ((faction != null) && (!faction.isNone())) {
				ret.addAll(faction.getMPlayers());
			}
		}
		if (MConf.get().logLandClaims) {
			ret.add(get(IdUtil.getConsole()));
		}
		return ret;
	}

	public boolean isEnemyNearby(int x, int y, int z) {
		List<Entity> entityList = getPlayer().getNearbyEntities(x, y, z);
		for (Entity entity : entityList) {
			if (entity.getType().equals(EntityType.PLAYER)) {
				MPlayer mPlayerEntity = get(entity);
				if ((getRelationTo(mPlayerEntity).equals(Rel.ENEMY))
						|| (getRelationTo(mPlayerEntity).equals(Rel.NEUTRAL))) {
					return true;
				}
			}
		}
		return false;
	}

	private int Level = 0;

	public void setLevel(int newLevel) {
		Level = newLevel;
		changed();
	}

	public int getLevel() {
		return Level;
	}

	private int experience = 0;

	public void setExperience(int newExperience) {
		experience = newExperience;
		changed();
	}

	public int getExperience() {
		return experience;
	}

	private int tokens = 0;

	public void setTokens(int newTokens) {
		tokens = newTokens;
	}

	public int getTokens() {
		return tokens;
	}

	public void addExperience(int amountToAdd) {
		experience += amountToAdd;
		if (experience >= 45000 + Level * 5000) {
			experience = 0;
			Level += 1;
			ItemStack Crate = new ItemStack(Material.CHEST);
			ItemMeta CrateMeta = Crate.getItemMeta();
			if (Level % 10 == 0) {
				CrateMeta.setDisplayName("§eExotic Crate");
				ArrayList<String> CrateLore = new ArrayList();
				CrateLore.add("§7Right click to open!");
				CrateMeta.setLore(CrateLore);
				Crate.setItemMeta(CrateMeta);
				if (getPlayer().getInventory().firstEmpty() == -1) {
					msg("§b§l(!)§7 An exotic crate has been dropped at your feet!");
					getPlayer().getWorld().dropItem(getPlayer().getLocation(), Crate);
				} else {
					msg("§b§l(!)§7 An exotic crate has been added to your inventory!");
					getPlayer().getInventory().addItem(new ItemStack[] { Crate });
				}
				getPlayer().setLevel(getPlayer().getLevel() + 100);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "credits add " + getName() + " 50");
			} else if (Level % 5 == 0) {
				CrateMeta.setDisplayName("§5Legendary Crate");
				ArrayList<String> CrateLore = new ArrayList();
				CrateLore.add("§7Right click to open!");
				CrateMeta.setLore(CrateLore);
				Crate.setItemMeta(CrateMeta);
				if (getPlayer().getInventory().firstEmpty() == -1) {
					msg("§b§l(!)§7 A legendary crate has been dropped at your feet!");
					getPlayer().getWorld().dropItem(getPlayer().getLocation(), Crate);
				} else {
					msg("§b§l(!)§7 A legendary crate has been added to your inventory!");
					getPlayer().getInventory().addItem(new ItemStack[] { Crate });
				}
				getPlayer().setLevel(getPlayer().getLevel() + 50);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "credits add " + getName() + " 25");
			} else {
				getPlayer().setLevel(getPlayer().getLevel() + 30);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "credits add " + getName() + " 10");
			}
			tokens += 1;
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.sendMessage("§f§l(!)§7 " + getColorTo(get(player)) + player.getName()
						+ " §7just reached level " + Level + "!");
			}
		}
		changed();
	}
	
	private int experienceOnFriday = 0;
	public void setExperienceOnFriday(int exp) {
		experienceOnFriday = exp;
	}
	public int getWeeklyExperience() {
		return experience - experienceOnFriday;
	}
	

	private MassiveMapDef<String, PS> homes = new MassiveMapDef();
	private long lastTeleportReceivedMillis;
	private MPlayer lastTeleportReceivedPlayer;
	private MPlayer lastTeleportedTo;
	private boolean MPlayerFlight;
	private long noFallDamageStartTimeMillis;

	public void setHome(PS ps, String name) {
		if (homes.containsKey(name)) {
			message("§c§l(!)§7 You already have a home with that name.");
		} else {
			message("§a§l(!)§7 The home has been set successfully!");
			homes.put(name, ps);
			changed();
		}
	}

	public Location getHome(String name) {
		if (homes.containsKey(name)) {
			return ((PS) homes.get(name)).asBukkitLocation();
		}
		return null;
	}

	public void deleteHome(String name) {
		if (homes.containsKey(name)) {
			message("§a§l(!)§7 The home has been deleted successfully!");
			homes.remove(name);
			changed();
		} else {
			message("§c§l(!)§7 You have no home with that name!");
		}
	}

	public ArrayList<String> getAllHomes() {
		ArrayList<String> home = new ArrayList();
		for (Map.Entry<String, PS> entry : homes.entrySet()) {
			home.add((String) entry.getKey());
		}
		return home;
	}

	public void tpHome(String name) {
		Faction factionTo = BoardColl.get().getFactionAt(PS.valueOf(getHome(name)));
		Rel rel = factionTo.getRelationTo(this);
		if ((rel.equals(Rel.RECRUIT)) || (rel.equals(Rel.MEMBER)) || (rel.equals(Rel.OFFICER))
				|| (rel.equals(Rel.LEADER)) || (factionTo.getName().equalsIgnoreCase("Wilderness"))) {
			Teleport.tryTeleport(getPlayer(), getHome(name));
		} else {
			message("§c§l(!)§7 That home is now claimed by " + factionTo.getName(this)
					+ " §7and can no longer be teleported to. Type /delhome " + name + " to remove it!");
		}
	}

	public void setLastTeleportReceivedMillis(long currentTimeMillis) {
		lastTeleportReceivedMillis = currentTimeMillis;
	}

	public long getLastTeleportReceivedMillis() {
		return lastTeleportReceivedMillis;
	}

	public void setLastTeleportReceivedPlayer(MPlayer player) {
		lastTeleportReceivedPlayer = player;
	}

	public MPlayer getLastTeleportReceivedPlayer() {
		return lastTeleportReceivedPlayer;
	}

	public void setLastTeleportedTo(MPlayer player) {
		lastTeleportedTo = player;
	}

	public MPlayer getLastTeleportedTo() {
		return lastTeleportedTo;
	}

	public boolean getMPlayerFlight() {
		return MPlayerFlight;
	}

	public void setMPlayerFlight(boolean toggle) {
		MPlayerFlight = toggle;
	}

	public void setNoFallDamageStartTimeMillis(long currentTimeMillis) {
		noFallDamageStartTimeMillis = currentTimeMillis;
	}

	public boolean getNoFallDamage() {
		return System.currentTimeMillis() - noFallDamageStartTimeMillis <= 20000L;
	}

	private boolean stealth = false;
	private PS locationBeforeTeleport;
	private boolean lastTeleportWasTPA;
	private Location backLocation;
	private double boost;
	private long boostEndMillis;

	public boolean getStealth() {
		return stealth;
	}

	public void setStealth(boolean arg) {
		stealth = arg;
		changed();
	}

	public Location getLocationBeforeTeleport() {
		return locationBeforeTeleport.asBukkitLocation();
	}

	public void setLocationBeforeTeleport(Location location) {
		PS ps = PS.valueOf(location);
		locationBeforeTeleport = ps;
	}

	public void setlastTeleportWasTPA(boolean toggle) {
		lastTeleportWasTPA = toggle;
	}

	public boolean getLastTeleportWasTPA() {
		return lastTeleportWasTPA;
	}

	public Location getBackLocation() {
		return backLocation;
	}

	public void setBackLocation(Location location) {
		backLocation = location;
	}

	public double getBoost() {
		return boost;
	}

	public void setBoost(double newBoost) {
		boost = newBoost;
	}

	public double getBoostEndMillis() {
		return boostEndMillis;
	}

	public void setBoostEndMillis(long endMillis) {
		boostEndMillis = endMillis;
	}

	private boolean Flight = false;

	public void setSkillFlight(boolean bool) {
		Flight = bool;
	}

	public boolean getSkillFlight() {
		return Flight;
	}

	private boolean Speed = false;

	public void setSkillSpeed(boolean bool) {
		Speed = bool;
	}

	public boolean getSkillSpeed() {
		return Speed;
	}

	private boolean MobDamage = false;

	public void setSkillMobDamage(boolean bool) {
		MobDamage = bool;
	}

	public boolean getSkillMobDamage() {
		return MobDamage;
	}

	private boolean CombatMcMMO = false;

	public void setSkillCombatMcMMO(boolean bool) {
		CombatMcMMO = bool;
	}

	public boolean getSkillCombatMcMMO() {
		return CombatMcMMO;
	}

	private boolean PlayerDamage = false;

	public void setSkillPlayerDamage(boolean bool) {
		PlayerDamage = bool;
	}

	public boolean getSkillPlayerDamage() {
		return PlayerDamage;
	}

	private boolean GenBuckets = false;

	public void setSkillGenBuckets(boolean bool) {
		GenBuckets = bool;
	}

	public boolean getSkillGenBuckets() {
		return GenBuckets;
	}

	private boolean TntFill = false;

	public void setSkillTntFill(boolean bool) {
		TntFill = bool;
	}

	public boolean getSkillTntFill() {
		return TntFill;
	}

	private boolean Miner = false;

	public void setSkillMiner(boolean bool) {
		Miner = bool;
	}

	public boolean getSkillMiner() {
		return Miner;
	}

	private boolean Archer = false;

	public void setSkillArcher(boolean bool) {
		Archer = bool;
	}

	public boolean getSkillArcher() {
		return Archer;
	}

	private boolean Bard = false;

	public void setSkillBard(boolean bool) {
		Bard = bool;
	}

	public boolean getSkillBard() {
		return Bard;
	}	
	
}

/*
 * Location:
 * C:\Users\Alan\Desktop\Factions.jar!\com\massivecraft\factions\entity\MPlayer.
 * class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */