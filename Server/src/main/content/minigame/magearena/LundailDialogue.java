package content.minigame.magearena;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;

/**
 * Represents the Lundail dialogue plugin.
 */
public final class LundailDialogue extends DialoguePlugin {

	public LundailDialogue() {

	}

	public LundailDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LundailDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("Hello sir.");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			npc("How can I help you, brave adventurer?");
			stage++;
			break;
		case 2:
			options("What are you selling?", "What's that big old building above us?");
			stage++;
			break;
		case 3:
			switch (buttonId) {
			case 1:
				player("What are you selling?");
				stage = 10;
				break;
			case 2:
				player("What's that big old building above us?");
				stage = 20;
				break;
			}
			break;
		case 10:
			npc("I sell rune stones. I've got some good stuff, some really", "powerful little rocks. Take a look.");
			stage++;
			break;
		case 11:
			npc.openShop(player);
			end();
			break;
		case 20:
			npc("That, my friend is the mage battle arena. Top mages", "come from all over " + GameWorld.getSettings().getName() + " to compete in the arena.");
			stage++;
			break;
		case 21:
			player("Wow.");
			stage++;
			break;
		case 22:
			npc("Few return, most get fried, hence the smell.");
			stage++;
			break;
		case 23:
			player("Hmmm... I did notice.");
			stage++;
			break;
		case 24:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 903 };
	}

}
