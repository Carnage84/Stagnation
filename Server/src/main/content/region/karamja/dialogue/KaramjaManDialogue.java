package content.region.karamja.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Karamja Man dialogue plugin.
 */
@Initializable
public final class KaramjaManDialogue extends DialoguePlugin {

	public KaramjaManDialogue() {

	}

	public KaramjaManDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KaramjaManDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HAPPY, "Hello, how's it going?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Not too bad, but I'm a little worried about the increase", "of goblins these days.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HAPPY, "Don't worry, I'll kill them.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3915 };
	}
}
