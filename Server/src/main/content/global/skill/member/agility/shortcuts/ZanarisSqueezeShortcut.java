package content.global.skill.member.agility.shortcuts;

import content.global.skill.member.agility.AgilityShortcut;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.game.node.scenery.Scenery;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Initializable;

/**
 * Handles the zanaris cosmic altar shortcut.
 */
@Initializable
public class ZanarisSqueezeShortcut extends AgilityShortcut {

	/**
	 * Represents the squeeze animation.
	 */
	private static final Animation ANIMATION = new Animation(2240);

	public ZanarisSqueezeShortcut() {
		super(new int[] { 12127 }, 46, 0.0, "squeeze-past");
	}

	@Override
	public void run(Player player, Scenery object, String option, boolean failed) {
		if (player.getSkills().getLevel(Skills.AGILITY) < 66 && (object.getLocation().equals(new Location(2408, 4395)) || object.getLocation().equals(new Location(2415, 4402)))) {
			player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 66 to negotiate this obstacle.");
			return;
		}
		Location to = player.getLocation().getY() < object.getLocation().getY() ? object.getLocation().transform(0, 1, 0) : object.getLocation().transform(0, -1, 0);
		Direction dir = player.getLocation().getY() < object.getLocation().getY() ? Direction.NORTH : Direction.SOUTH;
		ForceMovement.run(player, player.getLocation(), to, ANIMATION, ANIMATION, dir, 13).setEndAnimation(Animation.RESET);
	}
}
