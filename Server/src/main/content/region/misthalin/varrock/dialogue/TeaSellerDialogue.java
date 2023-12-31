package content.region.misthalin.varrock.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Tea Seller dialogue plugin.
 */
@Initializable
public class TeaSellerDialogue extends DialoguePlugin {

	public TeaSellerDialogue() {

	}

	public TeaSellerDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 595 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thanks.", "What are you selling?");
			stage = 1;
			break;
		case 1:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, please.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thanks.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What are you selling?");
				stage = 30;
				break;
			}

			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Only the most delicious infusion of the leaves of the tea", "plant. Grown in the exotic regions of this world. Buy", "yourself a cup.");
			stage = 31;
			break;
		case 31:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, if you're sure. You know where to come if you do.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 10:
			npc.openShop(player);
			end();
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new TeaSellerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings! Are you in need of refreshment?");
		stage = 0;
		return true;
	}
}
