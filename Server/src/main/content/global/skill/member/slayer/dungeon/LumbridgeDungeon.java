package content.global.skill.member.slayer.dungeon;

import content.global.skill.member.slayer.SlayerEquipmentFlags;
import core.cache.def.impl.NPCDefinition;
import core.game.interaction.Option;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.update.flag.context.Animation;
import core.plugin.ClassScanner;
import core.plugin.Initializable;
import core.plugin.Plugin;
import core.tools.RandomFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the lumbridge dungeon.
 */
@Initializable
public final class LumbridgeDungeon extends MapZone implements Plugin<Object> {

	/**
	 * The beast locations to check.
	 */
	private static final Map<Location, WallBeastNPC> BEASTS = new HashMap<>();

	/**
	 * Constructs a new {@code LumbridgeDungeon} {@code Object}.
	 */
	public LumbridgeDungeon() {
		super("lumbridge swamp dungeon", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		ClassScanner.definePlugin(new WallBeastNPC());
		return this;
	}

	/*
	 * @Override public boolean move(Entity e, Location loc, Location dest) { if
	 * (!e.getLocks().isMovementLocked() && e instanceof Player) { final
	 * WallBeastNPC npc = BEASTS.get(e.getLocation()); if (npc != null) {
	 * e.setDirection(Direction.getLogicalDirection(loc, dest)); return false; }
	 * } return super.move(e, loc, dest); }
	 */

	@Override
	public void locationUpdate(Entity entity, Location last) {
		if (entity instanceof Player) {
			final Player player = (Player) entity;
			final WallBeastNPC npc = BEASTS.get(last);
			if (npc != null && npc.canAttack(player)) {
				npc.trigger(player);
			}
		}
	}

	/**
	 * Checks if the player has the helmet.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	private boolean hasHelmet(final Player player) {
		return SlayerEquipmentFlags.hasSpinyHelmet(player);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		return super.interact(e, target, option);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3137, 9534, 3295, 9602));
	}

	/**
	 * Handles the wall beast npc.
	 */
	public final class WallBeastNPC extends AbstractNPC {

		/**
		 * Constructs a new {@code WallBeastNPC} {@code Object}.
		 * @param id the id.
		 * @param location the location.
		 */
		public WallBeastNPC(int id, Location location) {
			super(id, location,false);
		}

		/**
		 * Constructs a new {@code WallBeastNPC} {@code Object}.
		 */
		public WallBeastNPC() {
			super(7823, null,false);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new WallBeastNPC(id, location);
		}

		@Override
		public void init() {
			super.init();
			getLocks().lockMovement(Integer.MAX_VALUE);
			BEASTS.put(getLocation().transform(Direction.SOUTH, 1), this);
		}

		@Override
		public void handleTickActions() {
			super.handleTickActions();

		}

		/**
		 * Triggers the beast.
		 * @param player the player.
		 */
		public void trigger(final Player player) {
			boolean isProtected = hasHelmet(player);
			player.face(this);
			if (!isProtected) {
				animate(NPCDefinition.forId(7823).getCombatAnimation(3));
				player.animate(Animation.create(1810));
				GameWorld.getPulser().submit(new Pulse(8, player) {
					@Override
					public boolean pulse() {
						getAnimator().reset();
						player.getAnimator().reset();
						player.getImpactHandler().handleImpact(WallBeastNPC.this, RandomFunction.random(1, 18), CombatStyle.MELEE);
						return true;
					}
				});
				return;
			} else {
				transform(7823);
				attack(player);
			}
		}
		
		@Override
		public void finalizeDeath(Entity killer) {
			//TODO
		}

		@Override
		public boolean isPoisonImmune() {
			return true;
		}

		@Override
		public int[] getIds() {
			//return Tasks.WALL_BEASTS.getTask().getNpcs();
			int[] empty = {};
			return empty;
		}

	}

}
