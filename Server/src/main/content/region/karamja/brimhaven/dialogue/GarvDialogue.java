package content.region.karamja.brimhaven.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Garv dialogue plugin.
 */
@Initializable
public final class GarvDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GarvDialogue} {@code Object}.
	 */
	public GarvDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GarvDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GarvDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GarvDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello. What do you want?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Can I go in there?", "I want for nothing!");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I go in there?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I want for nothing!");
				stage = 20;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No. In there is private.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You're one of a very lucky few then.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 788 };
	}
}
