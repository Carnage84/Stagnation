package content.region.kandarin.ardougne.dialogue;

import content.global.travel.ship.Ships;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.Initializable;

/**
 * Represents the Captain Barnaby dialogue plugin.
 */
@Initializable
public final class CaptainBarnabyDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 30);

	/**
	 * Constructs a new {@code CaptainBarnabyDialogue} {@code Object}.
	 */
	public CaptainBarnabyDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CaptainBarnabyDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CaptainBarnabyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CaptainBarnabyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do you want to go on a trip to Brimhaven?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The trip will cost you 30 coins.");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Yes please.", "No, thank you.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes please.");
				stage = 10;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 10:
			if (!player.getInventory().containsItem(COINS)) {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough coins.");
				stage = 220;
				return true;
			}
			if (player.getInventory().remove(COINS)) {
				end();
				player.getPacketDispatch().sendMessage("You pay 30 coins and board the ship.");
				Ships.ARDOUGNE_TO_BRIMHAVEN.sail(player);
			}
			break;
		case 220:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 381 };
	}
}
