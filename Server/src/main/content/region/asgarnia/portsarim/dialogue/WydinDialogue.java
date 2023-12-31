package content.region.asgarnia.portsarim.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the dialogue that handles the Wydin npc.
 */
@Initializable
public final class WydinDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code WydinDialogue} {@code Object}.
	 */
	public WydinDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WydinDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WydinDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WydinDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		boolean door = false;
		if (args.length == 2)
			door = (boolean) args[1];
		if (door) {
			if (player.getSavedData().getGlobalData().isWydinEmployee()) {
				interpreter.sendDialogues(557, FacialExpression.HALF_GUILTY, "Can you put your white apron on before going in there", "please.");
				stage = 100;
			} else {
				interpreter.sendDialogues(557, FacialExpression.ANGRY, "Hey, you can't go in there. Only employees of the", "grocery store can go in.");
				stage = 100;
			}
			return true;
		} else {
			npc = (NPC) args[0];
		}
		if (player.getSavedData().getGlobalData().isWydinEmployee()) {
			interpreter.sendDialogues(557, FacialExpression.ASKING, "Is it nice and tidy round the back now?");
			stage = 0;
		} else {
			interpreter.sendDialogues(557, FacialExpression.HAPPY, "Welcome to my food store! Would you like to buy", "anything?");
			stage = 0;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (player.getSavedData().getGlobalData().isWydinEmployee()) {
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "Yes, can I work out front now?", "Yes, are you going to pay me yet?", "No, it's a complete mess.", "Can I buy something please?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.ASKING, "Yes, can I work out front now?");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.ASKING, "Yes, are you going to pay me yet?");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, it's a complete mess.");
					stage = 30;
					break;
				case 4:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I buy something please?");
					stage = 40;
					break;

				}
				break;
			case 10:
				interpreter.sendDialogues(557, FacialExpression.NEUTRAL, "No, I'm the one who works here.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				interpreter.sendDialogues(557, FacialExpression.THINKING, "Umm... No, not yet.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 30:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, it's a complete mess.");
				stage = 31;
				break;
			case 31:
				interpreter.sendDialogues(557, FacialExpression.THINKING, "Ah well, it'll give you something to do, won't it.");
				stage = 32;
				break;
			case 32:
				end();
				break;
			case 40:
				interpreter.sendDialogues(557, FacialExpression.HAPPY, "Yes, ofcourse.");
				stage = 41;
				break;
			case 41:
				end();
				npc.openShop(player);
				break;
			case 100:
				end();
				break;
			}
		} else {
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "Yes please.", "No, thank you.", "What can you recommend?", "Can I get a job here?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HAPPY, "Yes please.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thank you.");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.ASKING, "What can you recommend?");
					stage = 30;
					break;
				case 4:
					interpreter.sendDialogues(player, FacialExpression.ASKING, "Can I get a job here?");
					stage = 40;
					break;
				}
				break;
			case 10:
				end();
				npc.openShop(player);
				break;
			case 20:
				end();
				break;
			case 30:
				interpreter.sendDialogues(557, FacialExpression.HAPPY, "We have this really exotic fruit all the way from", "Karamja. It's called a banana.");
				stage = 31;
				break;
			case 31:
				interpreter.sendOptions("Select an Option", "Hmm, I think I'll try one.", "I don't like the sound of that.");
				stage = 32;
				break;
			case 32:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.FRIENDLY, "Hmm, I think I'll try one.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I don't like the sound of that.");
					stage = 100;
					break;
				}
				break;
			case 40:
				interpreter.sendDialogues(557, FacialExpression.HAPPY, "Well, you're keen, I'll give you that. Okay, I'll give you", "a go. Have you got your own white apron?");
				stage = 41;
				break;
			case 41:
				if (!player.getInventory().contains(1005, 1) && !player.getEquipment().contains(1005, 1) && !player.getBank().contains(1005, 1)) {
					interpreter.sendDialogues(player, FacialExpression.SAD, "No, I haven't.");
					stage = 42;
				} else {
					interpreter.sendDialogues(player, FacialExpression.HAPPY, "Yes, I have one.");
					stage = 50;
				}
				break;
			case 42:
				interpreter.sendDialogues(557, FacialExpression.FRIENDLY, "Well, you can't work here unless you have a white", "apron. Health and safety regulations, you understand.");
				stage = 43;
				break;
			case 43:
				interpreter.sendDialogues(player, FacialExpression.ASKING, "Where can I get one of those?");
				stage = 44;
				break;
			case 44:
				interpreter.sendDialogues(557, FacialExpression.FRIENDLY, "Well, I get all of mine over at the clothing shop in", "Varrock. They sell them cheap there.");
				stage = 45;
				break;
			case 45:
				interpreter.sendDialogues(557, FacialExpression.FRIENDLY, "Oh, and I'm sure that I've seen a spare one over in", "Gerrant's fish store somewhere. It's the little place just", "north of here.");
				stage = 46;
				break;
			case 46:
				end();
				break;
			case 50:
				player.getSavedData().getGlobalData().setWydinEmployee(true);
				interpreter.sendDialogues(557, FacialExpression.HAPPY, "Wow - you are well prepared! You're hired. Go through", "to the back and tidy up for me, please.");
				stage = 100;
				break;
			case 100:
				end();
				break;
			}
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 557 };
	}
}
