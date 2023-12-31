package content.global.skill.free.runecrafting.abyss;

import content.global.skill.free.runecrafting.RunePouch;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.tools.RandomFunction;

/**
 * Handles an abyssal npc.
 */
public final class AbyssalNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code AbyssalNPC} {@code Object}.
	 */
	public AbyssalNPC() {
		super(0, null, true);
		setAggressive(true);
	}

	/**
	 * Constructs a new {@code AbyssalNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public AbyssalNPC(int id, Location location) {
		super(id, location, true);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new AbyssalNPC(id, location);
	}

	@Override
	public void init() {
		super.init();
		this.setDefaultBehavior();
	}

	@Override
	public void handleTickActions() {
		super.handleTickActions();
	}

	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			Player p = killer.asPlayer();
			if (RandomFunction.random(750) < 12) {
				Item pouch = getPouch(p);
				if (pouch != null) {
					getDefinition().getDropTables().createDrop(pouch, p, this, getLocation());
				}
			}
		}
	}

	/**
	 * Gets the next pouch item.
	 * @param player the player.
	 * @return the pouch.
	 */
	private Item getPouch(Player player) {
		if (!player.hasItem(RunePouch.SMALL.getPouch())) {
			return RunePouch.SMALL.getPouch();
		}
		if (!player.hasItem(RunePouch.MEDIUM.getPouch()) && !player.hasItem(RunePouch.MEDIUM.getDecayedPouch())) {
			return RunePouch.MEDIUM.getPouch();
		}
		if (!player.hasItem(RunePouch.LARGE.getPouch()) && !player.hasItem(RunePouch.LARGE.getDecayedPouch())) {
			return RunePouch.LARGE.getPouch();
		}
		if (!player.hasItem(RunePouch.GIANT.getPouch()) && !player.hasItem(RunePouch.GIANT.getDecayedPouch())) {
			return RunePouch.GIANT.getPouch();
		}
		return null;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2263, 2264, 2265 };
	}

}
