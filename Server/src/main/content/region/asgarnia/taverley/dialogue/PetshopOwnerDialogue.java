package content.region.asgarnia.taverley.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.map.RegionManager;
import core.plugin.Initializable;

import java.util.List;

/**
 * Represents the Pet Shop Owner dialogue plugin.
 */
@Initializable
public final class PetshopOwnerDialogue extends DialoguePlugin {

	/**
	 * Represents the dog name.
	 */
	private String dogName;

	/**
	 * Represents the puppy.
	 */
	private Item puppy;

	/**
	 * Constructs a new {@code PetshopOwnerTaverly} {@code Object}.
	 */
	public PetshopOwnerDialogue() {
		/**
		 * emppty.
		 */
	}

	/**
	 * Constructs a new {@code PetshopOwnerTaverly} {@code Object}.
	 * @param player the player.
	 */
	public PetshopOwnerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PetshopOwnerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args.length > 1) {
			List<NPC> npcs = RegionManager.getLocalNpcs(player);
			for (NPC n : npcs) {
				if (n.getId() == 6893) {
					npc = n;
					break;
				}
			}
			dogName = (String) args[0];
			puppy = (Item) args[1];
			player("No, the " + dogName + ".");
			stage = 699;
			return true;
		}
		npc = (NPC) args[0];
		interpreter.sendOptions("Choose an option:", "Can I see your shop, please?", "How much is that puppy in the window?", "So, what sorts of pets are available?");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HAPPY, "Can I see your shop please?");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_ASKING, "How much is that puppy in the window?");
				stage = 200;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_ASKING, "So, what sorts of pets are available?");
				stage = 300;
				break;
			}
			break;
		case 100:
			end();
			npc.openShop(player);
			break;
		case 200:
			interpreter.sendDialogues(npc, FacialExpression.HALF_ASKING, "The one with the waggly tail?");
			stage = 201;
			break;
		case 201:
			end();
			player.getInterfaceManager().openChatbox(668);
			stage = 202;
			break;
		case 202:
			break;
		case 300:
			interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "Well, here we sell dogs, but we also have supplies for any", "other creatures you might want to raise.");
			stage = 301;
			break;
		case 301:
			interpreter.sendDialogues(player, FacialExpression.HALF_ASKING, "Such as?");
			stage = 302;
			break;
		case 302:
			interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "Well, we sell nuts. Those can be used to feed squirrels. If", "you want to capture a squirrel, you'll need to use the nuts", "on the trap you set, as the little scamps won't be fooled", "by anything else.");
			stage = 303;
			break;
		case 303:
			interpreter.sendDialogues(player, FacialExpression.FRIENDLY, "I'll bear that in mind.");
			stage = 304;
			break;
		case 304:
			interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "There are also a number of fabulous and exotic lizards in", "Karmja. Some can be caught easily in a box trap, while", "other will need to be raised from an egg.");
			stage = 305;
			break;
		case 305:
			interpreter.sendDialogues(player, FacialExpression.HAPPY, "Thank's alot! You've been very helpfull.");
			stage = 306;
			break;
		case 306:
			interpreter.sendDialogues(npc, FacialExpression.HAPPY, "It's always a pleasure to help a fellow animal-lover. Come", "back and visit soon.");
			stage = 307;
			break;
		case 307:
			end();
			break;
		case 700:
			player("Isn't that a little steep?");
			stage = 701;
			break;
		case 701:
			npc("Well, if we gave them away for free then people would", "just buy them and dump them without a care.");
			stage = 702;
			break;
		case 702:
			npc("Dogs are a big responsibility and should be cared for.");
			stage = 703;
			break;
		case 703:
			npc("If a person in unwilling to invest 500 coins, then they", "don't deserve to have the puppy in the first place.");
			stage = 704;
			break;
		case 704:
			npc("So, do you still want one?");
			stage = 705;
			break;
		case 705:
			options("Okay, I'll take the " + dogName + ".", "No thanks.");
			stage = 706;
			break;
		case 706:
			switch (buttonId) {
			case 1:
				player("Okay, I'll take the " + dogName + ".");
				stage = 707;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 707:
			if (player.getInventory().freeSlots() == 0) {
				npc("You don't have enough inventory space.");
				stage = 708;
				return true;
			}
			if (!player.getInventory().containsItem(new Item(995, 500))) {
				end();
				return true;
			}
			if (player.getInventory().remove(new Item(995, 500))) {
				player.getInventory().add(puppy);
				npc("There you go! I hope you two get on.");
				stage = 708;
			} else {
				player.getPacketDispatch().sendMessage("You don't the required coins in order to do this.");
				end();
			}
			break;
		case 708:
			end();
			break;
		case 699:
			npc("500 gold.");
			stage = 700;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6893 };
	}
}
