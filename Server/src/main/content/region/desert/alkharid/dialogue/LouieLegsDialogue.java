package content.region.desert.alkharid.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Louie Legs dialogue plugin.
 */
@Initializable
public final class LouieLegsDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LouiLegsDialogue} {@code Object}.
	 */
	public LouieLegsDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LouiLegsDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LouieLegsDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LouieLegsDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Hey, wanna buy some armour?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "What have you got?", "No, thank you.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.THINKING, "What have you got?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.FRIENDLY, "No, thank you.");
				stage = 20;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HAPPY, "I provide items to help you keep your legs!");
			stage = 11;
			break;
		case 11:
			end();
			npc.openShop(player);
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 542 };
	}
}
