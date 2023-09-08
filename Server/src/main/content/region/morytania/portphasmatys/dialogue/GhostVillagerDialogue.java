package content.region.morytania.portphasmatys.dialogue;

import content.region.morytania.portphasmatys.handlers.PhasmatysZone;
import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.tools.RandomFunction;

/**
 * Represents the Ghost Villager dialogue plugin.
 */
@Initializable
public final class GhostVillagerDialogue extends DialoguePlugin {

	public GhostVillagerDialogue() {}

	public GhostVillagerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GhostVillagerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (PhasmatysZone.hasAmulet(player)) {
			final int random = RandomFunction.random(10);
			switch (random) {
			default:
				npc("What do you want, mortal?");
				break;
			}
		} else {
			npc("Woooo wooo wooooo woooo");
			stage = 10;
		}

		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		case 10:
			interpreter.sendDialogue("You cannot understand the ghost.");
			stage++;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1697 };
	}

}
