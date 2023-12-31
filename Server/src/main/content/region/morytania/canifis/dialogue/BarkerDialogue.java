package content.region.morytania.canifis.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Barker dialogue plugin.
 */
@Initializable
public final class BarkerDialogue extends DialoguePlugin {
	public BarkerDialogue() {}
	public BarkerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BarkerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HAPPY, "Hello.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "You are looking for clothes, yes? You look at my", "products! I have very many nice clothes, yes?");
				stage = 1;
				break;
			case 1:
				interpreter.sendOptions("Select an Option", "Yes, please.", "No, thanks.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, FacialExpression.FRIENDLY, "Yes, please.");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.FRIENDLY, "No thanks.");
						stage = 15;
						break;
				}
				break;

			case 15:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Unfortunate for you, yes?", "Many bargains, won't find elsewhere!");
				stage = 20;
				break;
			case 20:
				end();
				break;
			case 10:
				end();
				npc.openShop(player);
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1039 };
	}
}
