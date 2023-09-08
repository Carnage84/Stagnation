package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.farming.FarmingPatch;
import content.global.skill.member.farming.Patch;
import content.global.skill.member.summoning.familiar.Familiar;
import content.global.skill.member.summoning.familiar.FamiliarSpecial;
import core.game.node.Node;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.scenery.Scenery;
import core.plugin.Initializable;

/**
 * Represents the Hydra familiar.
 */
@Initializable
public class HydraNPC extends Familiar {

	/**
	 * Constructs a new {@code HydraNPC} {@code Object}.
	 */
	public HydraNPC() {
		this(null, 6811);
	}

	/**
	 * Constructs a new {@code HydraNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public HydraNPC(Player owner, int id) {
		super(owner, id, 4900, 12025, 6, WeaponInterface.STYLE_RANGE_ACCURATE);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new HydraNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
        Node node = special.getNode();
        if(node instanceof Scenery) {
            Scenery scenery = (Scenery)node;
            FarmingPatch farmingPatch = FarmingPatch.forObject(scenery);
            if(farmingPatch != null) {
                Patch patch = farmingPatch.getPatchFor(owner);
                patch.regrowIfTreeStump();
                return true;
            }
        }
        
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6811, 6812 };
	}

}
