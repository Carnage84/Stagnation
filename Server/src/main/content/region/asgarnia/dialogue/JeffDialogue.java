package content.region.asgarnia.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Jeff dialogue plugin.
 */
@Initializable
public final class JeffDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code JeffDialogue} {@code Object}.
	 */
	public JeffDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code JeffDialogue} {@code Object}.
	 * @param player the player.
	 */
	public JeffDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new JeffDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Tell me, is the guard still watching us?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Why would you care if there's a guard watching you?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh, forget it.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3240 };
	}
}
