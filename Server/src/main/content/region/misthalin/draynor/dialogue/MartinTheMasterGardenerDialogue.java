package content.region.misthalin.draynor.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.global.Skillcape;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.plugin.Initializable;

/**
 * Represents the Martin The Master Gardener dialogue plugin.
 */
@Initializable
public final class MartinTheMasterGardenerDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MartinTheMasterGardener} {@code Object}.
	 */
	public MartinTheMasterGardenerDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MartinTheMasterGardener} {@code Object}.
	 * @param player the player.
	 */
	public MartinTheMasterGardenerDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is that cape that you're wearing?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I can't chat now, I have too many things to worry", "about.");
				stage = 20;
				break;
			}
			break;
		case 20:
			end();
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is a Skillcape of Farming, isn't it incredbile? It's a", "symbol of my ability as the finest farmer in the land!");
			stage = 11;
			break;
		case 11:
			if (player.getSkills().getStaticLevel(Skills.FARMING) == 99) {
				npc("Ah! I see you have mastered the skill of Farming,", "would you like to purchase a Farming cape for", "a fee of 99000 coins?");
				stage = 12;
			} else {
				end();
			}
			break;
		case 12:
			options("Yes, please.", "No, thanks.");
			stage = 13;
			break;
		case 13:
			switch (buttonId) {
			case 1:
				player("Yes, please.");
				stage = 14;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 14:
			if (Skillcape.purchase(player, Skills.FARMING)) {
				npc("Have fun with it.");
				stage = 15;
			}
			stage = 15;
			break;
		case 15:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MartinTheMasterGardenerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Skillcape of Farming", "Quest.");
		stage = 0;
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3299 };
	}
}
