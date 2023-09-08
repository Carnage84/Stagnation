package content.region.misthalin.draynor.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.Initializable;

/**
 * Represents the Veronica dialogue plugin.
 */
@Initializable
public class VeronicaDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code VeronicaDialogue} {@code Object}.
	 */
	public VeronicaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code VeronicaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public VeronicaDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 285 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Ernest the Chicken");
		switch (quest.getStage(player)) {
		case 0:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "Aha, sounds like a quest. I'll help.", "No, I'm looking for something to kill.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Aha, sounds like a quest. I'll help.");
					stage = 4;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I'm looking for something to kill.");
					stage = 2;
					break;
				}
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oooh, you violent person you.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			case 4:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes yes, I suppose it is a quest. My fiance Ernest and", "I came upon this house.");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Seeing as we were a little lost Ernest decided to go in", "and ask for direction.");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That was an hour ago. That house looks spooky, can", "you go and see if you can find him for me?");
				stage = 7;
				break;
			case 7:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, I'll see what I can do.");
				stage = 8;
				break;
			case 8:
				quest.start(player);
				player.getQuestRepository().syncronizeTab(player);
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thank you, thank you. I'm very grateful.");
				stage = 9;
				break;
			case 9:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 9:
				end();
				break;
			case 0:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, not yet.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, he's a chicken.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I know he's not exactly brave but I think you're being", "a bit harsh.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No no, he's been turned into an actual chicken by a", "mad scientist.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Eeeeeek!");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "My poor darling, why must these things happen to us.");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well I'm doing my best to turn him back.");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well be quick, I'm sure being a chicken can't be good", "for him.");
				stage = 7;
				break;
			case 7:
				end();
				break;
			}
			break;
		case 100:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where is he now?");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh he went off to talk to some green warty guy. I'm", "sure he'll be back soon.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new VeronicaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		final Quest quest = player.getQuestRepository().getQuest("Ernest the Chicken");
		switch (quest.getStage(player)) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_ASKING, "Can you please help me? I'm in a terrible spot of", "trouble.");
			stage = 0;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_ASKING, "Have you foudn my sweetheart yet?");
			stage = 0;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_ASKING, "Have you foudn my sweetheart yet?");
			stage = 0;
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_ASKING, "Thank you for resucing Ernest.");
			stage = 0;
			break;
		}
		return true;
	}
}
