package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.summoning.familiar.Familiar;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Moss Titan familiar.
 */
@Initializable
public class MossTitanNPC extends ElementalTitanNPC {

	/**
	 * Constructs a new {@code MossTitanNPC} {@code Object}.
	 */
	public MossTitanNPC() {
		this(null, 7357);
	}

	/**
	 * Constructs a new {@code MossTitanNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public MossTitanNPC(Player owner, int id) {
		super(owner, id, 5800, 12804, 20, WeaponInterface.STYLE_AGGRESSIVE);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new MossTitanNPC(owner, id);
	}

	@Override
	public int[] getIds() {
		return new int[] { 7357, 7358 };
	}

}
