package content.region.island.tutorialisland.npc;

import config.NPCs;
import core.game.node.entity.npc.AbstractNPC;
import core.game.world.map.Location;
import core.plugin.Initializable;
import core.tools.RandomFunction;

/**
 * Handles the NPC skippy.
 */
@Initializable
public class SkippyNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code SkippyNPC} {@code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	public SkippyNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Constructs a new {@code SkippyNPC} {@code Object}
	 */
	public SkippyNPC() {
		this(NPCs.SKIPPY_2796, null);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new SkippyNPC(id, location);
	}

	@Override
	public void tick() {
		if (RandomFunction.random(100) < 15) {
			sendChat("You can skip the tutorial by talking to me!");
		}
	}

	@Override
	public int[] getIds() {
		return new int[] {NPCs.SKIPPY_2796 };
	}

}
