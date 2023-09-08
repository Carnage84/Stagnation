package content.region.morytania.portphasmatys.dialogue;

import content.region.morytania.portphasmatys.handlers.PhasmatysZone;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Ghost Shopkeeper dialogue plugin.
 */
@Initializable
public final class GhostShopkeeperDialogue extends DialoguePlugin {

	public GhostShopkeeperDialogue() {}

	public GhostShopkeeperDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GhostShopkeeperDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (PhasmatysZone.hasAmulet(player)) {
			interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "Can I help you at all?");
			stage = 0;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "Woooo wooo wooooo woooo");
			stage = 10;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
			case 0:
			interpreter.sendOptions("Select an Option", "Yes, please. What are you selling?", "How should I use your shop?", "No, thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "I'm glad you ask! You can buy as many of the items", "stocked as you wish. You can also sell most items to the", "shop.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			}
			break;
		case 10:
			interpreter.sendDialogue( "You cannot understand the ghost.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1699 };
	}
}
