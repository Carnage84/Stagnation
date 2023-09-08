package content.region.kandarin.ardougne.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Spice Seller dialogue plugin.
 */
@Initializable
public class SpiceSellerDialogue extends DialoguePlugin {

	public SpiceSellerDialogue() {

	}

	public SpiceSellerDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 572 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes.", "No.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thanks.");
				stage = 20;
				break;

			}
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SpiceSellerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Are you interested in buying or selling spice?");
		stage = 0;
		return true;
	}
}
