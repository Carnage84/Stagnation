package content.region.desert.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Camel dialogue plugin.
 */
@Initializable
public final class CamelDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CamelDialoguePlugin} {@code Object}.
	 */
	public CamelDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CamelDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public CamelDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CamelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "If I go near that camel, it'll probably bite my hand off.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			player.getPacketDispatch().sendMessage("The camel spits at you, and you jump back hurriedly.");
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2813 };
	}
}
