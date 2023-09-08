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
 * Represents the Bull Ant familiar.
 */
@Initializable
public class BullAntNPC extends BurdenBeast {

	/**
	 * Constructs a new {@code BullAntNPC} {@code Object}.
	 */
	public BullAntNPC() {
		this(null, 6867);
	}

	/**
	 * Constructs a new {@code BullAntNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public BullAntNPC(Player owner, int id) {
		super(owner, id, 3000, 12087, 12, 9, WeaponInterface.STYLE_CONTROLLED);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new BullAntNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		if (owner.getSettings().getRunEnergy() >= 100) {
			owner.getPacketDispatch().sendMessage("You already have full run energy.");
			return false;
		}
		int amount = owner.getSkills().getStaticLevel(Skills.AGILITY) / 2;
		visualize(Animation.create(7896), Graphics.create(1382));
		owner.getSettings().updateRunEnergy(-amount);
		return true;
	}

	@Override
	public void visualizeSpecialMove() {
		owner.visualize(new Animation(7660), new Graphics(1296));
	}

	@Override
	public int[] getIds() {
		return new int[] { 6867, 6868 };
	}

}
