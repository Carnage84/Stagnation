package content.minigame.magetrainingarea.dialogue;

import content.minigame.magetrainingarea.MageTrainingArenaPlugin;
import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;

/**
 * Represents the Rewards Guardian dialogue plugin for Mage Training Area.
 */
public class RewardsGuardianMTADialogue extends DialoguePlugin {

	public RewardsGuardianMTADialogue() {

	}

	public RewardsGuardianMTADialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new RewardsGuardianMTADialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args.length > 1) {
			npc("Have you spoken to my fellow guardian downstairs?");
			stage = 4;
			return true;
		}
		player("Hi.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (!player.getSavedData().getActivityData().isStartedMta()) {
			switch (stage) {
			case 0:
				npc("Greetings. Have you spoken to my fellow Guardian", "downstairs?");
				stage++;
				break;
			case 1:
				player("Nope.");
				stage++;
				break;
			case 2:
				npc("Well, you need to talk to him first.");
				stage++;
				break;
			case 3:
				end();
				break;
			case 4:
				player("Nope.");
				stage++;
				break;
			case 5:
				npc("Well, you need to talk to him first.");
				stage++;
				break;
			case 6:
				end();
				break;
			}
			return true;
		}
		switch (stage) {
		case 0:
			npc("Greetings. What wisdom do you seek?");
			stage++;
			break;
		case 1:
			options("Who are you?", "Can I trade my Pizazz Points please?", "Thanks, bye!");
			stage++;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				player("Who are you?");
				stage = 10;
				break;
			case 2:
				player("Can I trade my Pizazz Points please?");
				stage = 20;
				break;
			case 3:
				end();
				break;
			}
			break;
		case 10:
			npc("Me? I'm here to grant you rewards for any of the", "Pizazz Points you may have earned in this training", "arena. Like my fellow Guardians, I am part of the", "arena and live to ensure its safe running.");
			stage++;
			break;
		case 11:
			player("I see.");
			stage = 1;
			break;
		case 20:
			npc("Why of course.");
			stage++;
			break;
		case 21:
			end();
			MageTrainingArenaPlugin.SHOP.open(player);
			break;
		case 30:
			npc("Well, we do stock a special book that you may be", "interested in, which provides a comprehensive guide to", "this training arena. It costs 200gp. Would like", "one?");
			stage++;
			break;
		case 31:

			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3103 };
	}

}
