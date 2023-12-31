package core.game.dialogue;

import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents a simple dialogue.
 */
@Initializable
public class SimpleDialoguePlugin extends DialoguePlugin {

	public SimpleDialoguePlugin() {

	}

	public SimpleDialoguePlugin(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 70099 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		end();
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SimpleDialoguePlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		String[] messages = new String[args.length];
		for (int i = 0; i < messages.length; i++)
			messages[i] = (String) args[i];
		interpreter.sendDialogue(messages);
		return true;
	}

}
