package content.region.misthalin.lumbridge.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Lumbridge Swamp Wizard dialogue plugin.
 */
@Initializable
public final class LumbridgeSwampWizardDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LumbridgeSwampWizard} {@code Object}.
	 */
	public LumbridgeSwampWizardDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LumbridgeSwampWizard} {@code Object}.
	 * @param player the player.
	 */
	public LumbridgeSwampWizardDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Why are all of you standing around here?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hahaha you dare talk to a mighty wizard such as", "myself? I bet you can't even cast windstrike yet", "amateur!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "...You're an idiot.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LumbridgeSwampWizardDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 652 };
	}

}
