package content.region.kandarin.ardougne.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Gem Merchant dialogue plugin.
 */
@Initializable
public final class GemMerchantDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GemMerchantDialogue} {@code Object}.
	 */
	public GemMerchantDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GemMerchantDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GemMerchantDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GemMerchantDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Here, look at my lovely gems.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			npc.openShop(player);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 570 };
	}
}
