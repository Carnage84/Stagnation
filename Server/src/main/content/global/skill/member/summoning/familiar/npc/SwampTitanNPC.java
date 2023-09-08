package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.summoning.familiar.Familiar;
import content.global.skill.member.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Swamp Titan familiar.
 */
@Initializable
public class SwampTitanNPC extends Familiar {

	/**
	 * Constructs a new {@code SwampTitanNPC} {@code Object}.
	 */
	public SwampTitanNPC() {
		this(null, 7329);
	}

	/**
	 * Constructs a new {@code SwampTitanNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public SwampTitanNPC(Player owner, int id) {
		super(owner, id, 5600, 12776, 6, WeaponInterface.STYLE_ACCURATE);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new SwampTitanNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7329, 7330 };
	}

}
