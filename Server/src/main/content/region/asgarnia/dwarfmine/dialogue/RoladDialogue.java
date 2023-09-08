package content.region.asgarnia.dwarfmine.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Rolad dialogue plugin.
 */
@Initializable
public class RoladDialogue extends DialoguePlugin {

	public RoladDialogue() {

	}

	public RoladDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 1841 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.OLD_NORMAL, "Ehm... well... my name is " + player.getUsername() + ", if that rings any bell?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.OLD_NORMAL, "No, never heard of you.");
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

		return new RoladDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.OLD_NORMAL, "Oh, hello... do I know you?");
		stage = 0;
		return true;
	}
}
