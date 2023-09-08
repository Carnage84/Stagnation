package content.region.asgarnia.burthorpe.dialogue;

import config.NPCs;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.Initializable;

/**
 * Represents the Gam Fred dialogue plugin.
 */
@Initializable
public final class GamFredDialoue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GamFredDialoue} {@code Object}.
	 */
	public GamFredDialoue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GamFredDialoue} {@code Object}.
	 * @param player the player.
	 */
	public GamFredDialoue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GamFredDialoue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length > 1) {
			player(FacialExpression.ASKING, "May I have a shield please?");
			stage = 13;
			return true;
		}
		npc(FacialExpression.CHILD_NORMAL, "Ello there. I'm Gamfred, the engineer in this here guild.", "Have you seen my catapult?");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "That's not a catapult, it's a large crossbow.", "Yes, beautiful piece of enigneering.", "No, where is it?", "May I claim my tokens please?", "Bye!");
			stage = 1;
			break;
		case 1:
			if (buttonId == 1) {
				player( "That's not a catapult, it's a large crossbow.");
				stage = 2;
			} else if (buttonId == 2) {
				player("Yes, beautiful piece of engineering.");
				stage = 4;
			} else if (buttonId == 3) {
				player("No, where is it?");
				stage = 15;
			} else if (buttonId == 4) {
				end();
				player.getDialogueInterpreter().open("wg:claim-tokens", npc.getId());
			} else if (buttonId == 5) {
				player("Bye!");
				stage = 16;
			}
			break;
		case 2:
			npc(FacialExpression.CHILD_SAD, "WHAT!? I'll have you know that is the finest piece of", "dwarven engineering for miles around! How DARE you", "insult my work!");
			stage = 3;
			break;
		case 3:
			end();
			break;
		case 4:
			npc(FacialExpression.CHILD_NORMAL, "Nice to meet someone who appreicates fine work, have", "you tried it out yet?");
			stage = 5;
			break;
		case 5:
			interpreter.sendOptions("Select an Option", "Yes", "No, how do I do that?");
			stage = 6;
			break;
		case 6:
			if (buttonId == 1) {
				player("Yes.");
				stage = 7;
			} else if (buttonId == 2) {
				player("No, how do I do that?");
				stage = 17;
			}
			break;
		case 7:
			npc(FacialExpression.CHILD_NORMAL, "What did you think?");
			stage = 8;
			break;
		case 8:
			interpreter.sendOptions("Select an Option", "It was ok I guess.", "It was fun!", "I didn't like it.", "May i have a shield please?");
			stage = 9;
			break;
		case 9:
			switch (buttonId) {
			case 1:
				player("It was ok I guess.");
				stage = 10;
				break;
			case 2:
				player("It was fun!");
				stage = 11;
				break;
			case 3:
				player("I didn't like it.");
				stage = 12;
				break;
			case 4:
				player("May i have a shield please?");
				stage = 13;
				break;
			}
			break;
		case 10:
			npc(FacialExpression.CHILD_NORMAL, "Well I guess not everyone will like it.");
			stage = 3;
			break;
		case 11:
			npc(FacialExpression.CHILD_NORMAL, "Glad to hear it. Try it again sometime. We have more", "tests to run.");
			stage = 3;
			break;
		case 12:
			npc(FacialExpression.CHILD_NORMAL, "Well I guess not everyone will like it. But give it another", " chance before you go.");
			stage = 3;
			break;
		case 13:
			if (player.getBank().contains(8856, 1) || player.getInventory().contains(8856, 1) || player.getEquipment().contains(8856, 1)) {
				npc(FacialExpression.CHILD_NORMAL, "Silly muffin, you have one already!");
				stage = 3;
			} else {
				npc(FacialExpression.CHILD_NORMAL, "ofcourse!");
				stage = 14;
			}
			break;
		case 14:
			if (player.getInventory().hasSpaceFor(new Item(8856))) {
				player.getInventory().add(new Item(8856));
				interpreter.sendItemMessage(8856, "The dwarf hands you a large shield.");
				stage = 3;
			} else {
				npc(FacialExpression.CHILD_NORMAL, "Muffin make some room in your inventory first!");
				stage = 3;
			}
			break;
		case 15:
			npc(FacialExpression.CHILD_NORMAL, "Are ye blind lad? Tis over there in the next room with me", "assistant working it!");
			stage = 3;
			break;
		case 16:
			npc(FacialExpression.CHILD_NORMAL, "Come back soon! My catapult needs more test subjects.");
			stage = 3;
			break;
		case 17:
			npc(FacialExpression.CHILD_NORMAL, "Well ye take the big defence shield in both hands and", "watch the catapult. My assitant will fire different things", "at you and you need to defend against them. To see", "what might be comming your way and wich defensive");
			stage = 18;
			break;
		case 18:
			npc(FacialExpression.CHILD_NORMAL, "mode to choose, see the poster on the wall.");
			stage = 19;
			break;
		case 19:
			interpreter.sendOptions("Select an Option", "May I have a shield please?", "Sounds boring.");
			stage = 20;
			break;
		case 20:
			switch (buttonId) {
			case 1:
				player("May I have a shield please?");
				stage = 13;
				break;
			case 2:
				player("Sounds boring.");
				stage = 21;
				break;
			}
			break;
		case 21:
			npc(FacialExpression.CHILD_NORMAL, "Your loss...");
			stage = 3;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { NPCs.GAMFRED_4287 };
	}
}
