package content.region.fremennik.rellekka.dialogue;

import content.region.fremennik.rellekka.handlers.RellekkaUtils;
import content.region.fremennik.rellekka.handlers.TravelDestination;
import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;

import static core.api.ContentAPIKt.requireQuest;

/**
 * Represents the Maria Gunnars dialogue plugin.
 */
public class MariaGunnarsDialogue extends DialoguePlugin {
	public MariaGunnarsDialogue() {
	}

	public MariaGunnarsDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MariaGunnarsDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("Welcome, Talvald. Do you have any questions?");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			options("Can you ferry me to " + (npc.getId() == 5508 ? "Neitiznot?" : "Relleka") + "?", "I just stopped to say 'hello'.");
			stage++;
			break;
		case 1:
			if (buttonId == 1) {
				player("Can you ferry me to " + (npc.getId() == 5508 ? "Neitiznot?" : "Relleka") + "?");
				stage++;
			} else {
				player("I just stopped to say 'hello'.");
				stage = 4;
			}
			break;
		case 2:
			npc("Let's set sail then.");
			stage++;
			break;
		case 3:
			end();
			if (!requireQuest(player, "Fremennik Trials", ""))
				break;
			if (npc.getId() == 5508) {
				RellekkaUtils.sail(player, TravelDestination.RELLEKKA_TO_NEITIZNOT);
			} else {
				RellekkaUtils.sail(player, TravelDestination.NEITIZNOT_TO_RELLEKKA);
			}
			break;
		case 4:
			npc("Thanks!");
			stage++;
			break;
		case 5:
			player("I may be back later.");
			stage++;
			break;
		case 6:
			end();
			npc.sendChat("Bye");
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5508, 5507 };
	}

}
