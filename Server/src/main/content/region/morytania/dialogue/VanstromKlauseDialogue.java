package content.region.morytania.dialogue;

import content.quest.member.insearchofthemyreque.dialogue.VanstromKlauseQuestDialogue;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Vanstrom Klause dialogue plugin.
 */
@Initializable
public class VanstromKlauseDialogue extends DialoguePlugin {

	public VanstromKlauseDialogue() {}

	public VanstromKlauseDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new VanstromKlauseDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there, how goes it stranger?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Quite well thanks for asking, how about you?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Quite well my self.");
			stage = 2;
			break;
		case 2:
			if(player.getQuestRepository().hasStarted("In Search of the Myreque")) {
				player.getDialogueInterpreter().open(new VanstromKlauseQuestDialogue());
				stage = 3;
				break;
			} else {
				stage = 3;
				break;
			}
			case 3:
				end();
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2020 };
	}
}
