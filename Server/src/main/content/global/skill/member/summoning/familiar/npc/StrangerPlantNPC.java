package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.summoning.familiar.Familiar;
import content.global.skill.member.summoning.familiar.FamiliarSpecial;
import content.global.skill.member.summoning.familiar.Forager;
import core.game.node.entity.Entity;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Initializable;
import core.tools.RandomFunction;

import static core.api.ContentAPIKt.applyPoison;

/**
 * Represents the Stranger Plant familiar.
 */
@Initializable
public class StrangerPlantNPC extends Forager {

	/**
	 * Constructs a new {@code StrangerPlantNPC} {@code Object}.
	 */
	public StrangerPlantNPC() {
		this(null, 6827);
	}

	/**
	 * Constructs a new {@code StrangerPlantNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public StrangerPlantNPC(Player owner, int id) {
		super(owner, id, 4900, 12045, 6, new Item(464));
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new StrangerPlantNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		if (!canCombatSpecial(special.getTarget())) {
			return false;
		}
		Entity target = special.getTarget();
		if (RandomFunction.random(2) == 1) {
			applyPoison(target, owner, 20);
		}
		animate(Animation.create(8211));
		Projectile.ranged(this, target, 1508, 50, 40, 1, 45).send();
		target.graphics(Graphics.create(1511));
		sendFamiliarHit(target, 2);
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6827, 6828 };
	}

}
