package content.region.misthalin.edgeville.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Initializable;

/**
 * Represents the Monk dialogue plugin used in Edgeville.
 */
@Initializable
public final class EdgevilleMonkDialogue extends DialoguePlugin {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(710);

	/**
	 * Represents the graphics to use.
	 */
	private static final Graphics GRAPHIC = new Graphics(84);

	public EdgevilleMonkDialogue() {

	}

	public EdgevilleMonkDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new EdgevilleMonkDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args.length >= 1) {
			if (args[0] instanceof NPC) {
				npc = (NPC) args[0];
			} else {
				interpreter.sendDialogues(7727, null, "Only members of our order can go up there. You'll", "need to talk to Abbot Langley if you want to explore", "the monastery further.");
				stage = 21;
				return true;
			}
		}
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings traveller.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Can you heal me? I'm injured.", "Isn't this place built a bit out of the way?", "How do I get further into the monastery?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you heal me? I'm injured.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Isn't this place built a bit out of the way?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How do i get farther into the monastery?");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ok.");
			stage = 11;
			break;
		case 11:
			end();
			npc.animate(ANIMATION);
			npc.graphics(GRAPHIC);
			player.getPacketDispatch().sendMessage("You feel a little better.");
			player.getSkills().heal(((int) (player.getSkills().getStaticLevel(Skills.HITPOINTS) * 0.20)));
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "We like it that way actually! We get disturbed less. We still", "get rather a large amount of travellers looking for", "sanctuary and healing here as it is!");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You'll need to talk to Abbot Langley about that. He's", "usually to be found walking the halls of the monastery.");
			stage = 21;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7727 };
	}
}
