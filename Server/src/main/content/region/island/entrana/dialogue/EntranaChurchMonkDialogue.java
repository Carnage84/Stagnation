package content.region.island.entrana.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Entrana Church Monk dialogue plugin.
 */
@Initializable
public final class EntranaChurchMonkDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code EntranaChurchMonk} {@code Object}.
	 */
	public EntranaChurchMonkDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code EntranaChurchMonk} {@code Object}.
	 * @param player the player.
	 */
	public EntranaChurchMonkDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new EntranaChurchMonkDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings traveller.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 222 };
	}
}
