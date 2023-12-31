package content.region.asgarnia.burthorpe.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Lidio dialogue plugin.
 */
@Initializable
public final class LidioDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LidioDialogue} {@code Object}.
	 */
	public LidioDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LidioDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LidioDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LidioDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings, warrior, how can I fill your stomach today?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "With food preferrable.");
			stage = 1;
			break;
		case 1:
			end();
			npc.openShop(player);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4293 };
	}
}
