package content.region.asgarnia.falador.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the White Knight dialogue plugin.
 */
@Initializable
public final class WhiteKnightDialogue extends DialoguePlugin {

	/**
	 * The NPC ids that use this dialogue plugin.
	 */
	private static final int[] NPC_IDS = { 660 };

	/**
	 * Constructs a new {@code WhiteKnightDialoguePlugin} {@code Object}.
	 */
	public WhiteKnightDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WhiteKnightDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public WhiteKnightDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		interpreter.sendDialogue("He is too busy dancing to talk!");
		return true;
	}

	@Override
	public int[] getIds() {
		return NPC_IDS;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WhiteKnightDialogue(player);
	}

}
