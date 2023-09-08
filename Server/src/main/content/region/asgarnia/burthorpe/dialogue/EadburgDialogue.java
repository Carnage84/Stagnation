package content.region.asgarnia.burthorpe.dialogue;

import config.NPCs;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Eadburg dialogue plugin.
 */
@Initializable
public final class EadburgDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code EadburgDialogue} {@code Object}.
	 */
	public EadburgDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code EadburgDialogue} {@code Object}.
	 * @param player the player.
	 */
	public EadburgDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new EadburgDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		player(FacialExpression.HALF_GUILTY, "What's cooking, good looking?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc(FacialExpression.HALF_GUILTY, "The stew for the servant's main meal.");
			stage = 1;
			break;
		case 1:
			player(FacialExpression.HALF_GUILTY, "Um...er...see you later.");
			stage = 2;
			break;
		case 2:
			npc(FacialExpression.HALF_GUILTY, "Bye!");
			stage = 3;
			break;
		case 3:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { NPCs.EADBURG_1072 };
	}
}
