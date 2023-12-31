package content.region.island.entrana.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Cone dialogue plugin.
 */
@Initializable
public final class ConeDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ConeDialogue} {@code Object}.
	 */
	public ConeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ConeDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ConeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ConeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello deary.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Um... hello.");
			stage = 1;
			break;
		case 1:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 217 };
	}
}
