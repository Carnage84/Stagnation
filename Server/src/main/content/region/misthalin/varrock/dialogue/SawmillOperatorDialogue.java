package content.region.misthalin.varrock.dialogue;

import core.game.component.Component;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Sawmill Operator dialogue plugin.
 */
@Initializable
public class SawmillOperatorDialogue extends DialoguePlugin {

	public SawmillOperatorDialogue() {

	}

	public SawmillOperatorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SawmillOperatorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NEUTRAL,"Do you want me to make some planks for you? Or", "would you be interested in some other housing supplies?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			options("Planks please!", "What kind of planks can you make?", "Can I buy some housing supplies?", "Nothing, thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HAPPY,"Planks please!");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.ASKING,"What kind of planks can you make?");
				stage = 20;
				break;
			case 3:
				end();
				npc.openShop(player);
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.FRIENDLY,"Nothing, thanks.");
				stage = 40;
				break;
			}
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.FRIENDLY,"Well come back when you want some. You can't get", "good quality planks anywhere but here!");
			stage = 999;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_ASKING,"What kind of planks do you want?");
			stage = 11;
			break;
		case 11:
			end();
			player.getInterfaceManager().open(new Component(403));
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NEUTRAL,"I can make planks from wood, oak, teak and mahogany.", "I don't make planks from other woods as they're no", "good for making furniture.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.NEUTRAL,"Wood and oak are all over the place, but teak and", "mahogany can only be found in a few places like", "Karamja and Etceteria.");
			stage = 999;
			break;
		case 999:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4250 };
	}
}
