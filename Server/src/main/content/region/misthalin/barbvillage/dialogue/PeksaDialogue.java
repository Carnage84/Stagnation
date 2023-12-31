package content.region.misthalin.barbvillage.dialogue;

import content.quest.member.scorpioncatcher.dialogue.PeksaSCDialogue;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Peksa dialogue plugin.
 */
@Initializable
public final class PeksaDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code PeksaDialogue} {@code Object}.
	 */
	public PeksaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code PeksaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public PeksaDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PeksaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Are you interested in buying or selling a helmet?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if(player.getQuestRepository().hasStarted("Scorpion Catcher")){
				interpreter.sendOptions("Select an Option", "I could be, yes.", "No, I'll pass on that.", "I've heard you have a small scorpion in your possession.");
				stage = 1;
			} else {
				interpreter.sendOptions("Select an Option", "I could be, yes.", "No, I'll pass on that.");
				stage = 1;
			}
			break;
		case 1:
			switch (buttonId) {
				case 1:
					end();
					npc.openShop(player);
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I'll pass on that.");
					stage = 20;
					break;
				case 3:
					end();
					player.getDialogueInterpreter().open(new PeksaSCDialogue());
					break;
			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, come back if you change your mind.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 538 };
	}
}
