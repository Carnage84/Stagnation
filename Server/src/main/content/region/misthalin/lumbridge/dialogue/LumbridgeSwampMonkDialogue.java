package content.region.misthalin.lumbridge.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Swamp Monks Lumbridge dialogue plugin.
 */
@Initializable
public final class LumbridgeSwampMonkDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LumbridgeSwampMonk} {@code Object}.
	 */
	public LumbridgeSwampMonkDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LumbridgeSwampMonk} {@code Object}.
	 * @param player the player.
	 */
	public LumbridgeSwampMonkDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LumbridgeSwampMonkDialogue(player);
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
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "None of your business. Get lost.");
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
		return new int[] { 651 };
	}

}
