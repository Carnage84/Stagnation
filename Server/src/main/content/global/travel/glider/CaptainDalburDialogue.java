package content.global.travel.glider;

import config.NPCs;
import content.quest.member.thegrandtree.TheGrandTree;
import core.game.component.Component;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

import static core.api.ContentAPIKt.isQuestComplete;
import static core.tools.DialogueConstKt.END_DIALOGUE;

/**
 * Represents the dialogue plugin used for the captain dalbur npc.
 */
@Initializable
public final class CaptainDalburDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CaptainDalburDialogue} {@code Object}.
	 */
	public CaptainDalburDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CaptainDalburDialogue.java} {@code Object}.
	 * @param player the player.
	 */
	public CaptainDalburDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CaptainDalburDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.OLD_DEFAULT, "What do you want human?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "May you fly me somewhere on your glider?");
				stage = 1;
				break;
			case 1:
				if(!isQuestComplete(player, TheGrandTree.questName)){
					interpreter.sendDialogues(npc, FacialExpression.OLD_ANGRY3, "I only fly friends of the gnomes!");
					stage = END_DIALOGUE;
				}
				else {
					npc(FacialExpression.OLD_DEFAULT,"If you wish.");
					stage++;
				}
				break;
			case 2:
				end();
				player.getInterfaceManager().open(new Component(138));
				Gliders.sendConfig(npc, player);
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] {NPCs.CAPTAIN_DALBUR_3809, NPCs.CAPTAIN_BLEEMADGE_3810, NPCs.CAPTAIN_KLEMFOODLE_3812, 3813 };
	}
}
