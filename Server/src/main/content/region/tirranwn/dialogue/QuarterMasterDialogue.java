package content.region.tirranwn.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Quarter Master dialogue plugin.
 */
@Initializable
public final class QuarterMasterDialogue extends DialoguePlugin {

	public QuarterMasterDialogue(Player player) {
		super(player);
	}

	public QuarterMasterDialogue() {}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new QuarterMasterDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("Hi, would you like to see my wares?");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
			case 0:
				player("Yes, please.");
				stage++;
				break;
			case 1:
				end();
				npc.openShop(player);
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1208 };
	}

}
