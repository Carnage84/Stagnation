package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.summoning.familiar.Familiar;
import content.global.skill.member.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.SkillBonus;
import core.game.node.entity.skill.Skills;
import core.plugin.Initializable;

/**
 * Represents the Lava Titan familiar.
 */
@Initializable
public class LavaTitanNPC extends Familiar {

	/**
	 * Constructs a new {@code LavaTitanNPC} {@code Object}.
	 */
	public LavaTitanNPC() {
		this(null, 7341);
	}

	/**
	 * Constructs a new {@code LavaTitanNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public LavaTitanNPC(Player owner, int id) {
		super(owner, id, 6100, 12788, 4, WeaponInterface.STYLE_AGGRESSIVE);
		boosts.add(new SkillBonus(Skills.MINING, 10));
		boosts.add(new SkillBonus(Skills.FIREMAKING, 10));		
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new LavaTitanNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7341, 7342 };
	}

}
