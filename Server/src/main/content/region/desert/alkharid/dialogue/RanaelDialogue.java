package content.region.desert.alkharid.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Ranael dialogue plugin.
 */
@Initializable
public class RanaelDialogue extends DialoguePlugin {

	public RanaelDialogue() {

	}

	public RanaelDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 544 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you, that's not my scene.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thank you, that's not my scene.");
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

		return new RanaelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Do you want to buy any armoured skirts? Designed", "especially for ladies who like to fight.");
		stage = 0;
		return true;
	}
}
