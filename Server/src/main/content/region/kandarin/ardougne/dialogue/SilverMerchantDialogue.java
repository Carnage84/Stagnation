package content.region.kandarin.ardougne.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

import static core.api.ContentAPIKt.openDialogue;

/**
 * Represents the Silver Merchant dialogue plugin.
 */
@Initializable
public class SilverMerchantDialogue extends DialoguePlugin {

	public SilverMerchantDialogue() {}

	public SilverMerchantDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SilverMerchantDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Silver! Silver! Best prices for buying and selling in all", "Kandarin!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
			case 0:
				if (player.getQuestRepository().hasStarted("Making History")) {
					interpreter.sendOptions("Select an Option", "Yes please.", "No, thank you.", "Ask about the outpost.");
					stage = 21;
					break;
				} else {
					interpreter.sendOptions("Select an Option", "Yes please.", "No, thank you.");
					stage = 1;
					break;
				}
			case 1:
				switch (buttonId) {
					case 1:
						end();
						npc.openShop(player);
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "No, thank you.");
						stage = 20;
						break;
				}
				break;
			case 20:
				end();
				break;
			case 21:
				switch (buttonId) {
					case 1:
						end();
						npc.openShop(player);
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "No, thank you.");
						stage = 20;
						break;
					case 3:
						openDialogue(player, new content.quest.member.makinghistory.dialogue.SilverMerchantMHDialogue());
						stage = 20;
						break;
				}
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 569 };
	}
}
