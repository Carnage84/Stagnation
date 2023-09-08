package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.summoning.familiar.BurdenBeast;
import content.global.skill.member.summoning.familiar.Familiar;
import content.global.skill.member.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Initializable;

/**
 * Represents the Spirit Terrorbird familiar.
 */
@Initializable
public class SpiritTerrorbirdNPC extends BurdenBeast {

	/**
	 * Constructs a new {@code SpiritTerrorbirdNPC} {@code Object}.
	 */
	public SpiritTerrorbirdNPC() {
		this(null, 6794);
	}

	/**
	 * Constructs a new {@code SpiritTerrorbirdNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public SpiritTerrorbirdNPC(Player owner, int id) {
		super(owner, id, 3600, 12007, 8, 12, WeaponInterface.STYLE_CONTROLLED);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new SpiritTerrorbirdNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		visualize(Animation.create(1009), Graphics.create(1521));
		owner.getSkills().updateLevel(Skills.AGILITY, 2);
		owner.getSettings().updateRunEnergy(-owner.getSkills().getStaticLevel(Skills.AGILITY) / 2.0);
		return true;
	}

	@Override
	public void visualizeSpecialMove() {
		owner.visualize(new Animation(7660), new Graphics(1295));
	}

	@Override
	public int[] getIds() {
		return new int[] { 6794, 6795 };
	}

}
