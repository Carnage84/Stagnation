package content.minigame.puropuro.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.player.Player;

/**
 * Represents the Wandering Impling dialogue plugin for Puro-Puro minigame.
 */
public final class WanderingImplingDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code WanderingImplingDialogue} {@code Object}.
	 */
	public WanderingImplingDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WanderingImplingDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WanderingImplingDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WanderingImplingDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		player("Hello. So, what is this place?");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc(FacialExpression.OLD_DEFAULT, "in your pockets? Something tasty?");
			stage++;
			break;
		case 1:
			player("Stay out of my pockets! I don't have anything that you", "want.");
			stage++;
			break;
		case 2:
			npc(FacialExpression.OLD_DEFAULT, "Ah, but do you have anything that *you* want?");
			stage++;
			break;
		case 3:
			player("Of course I do!");
			stage++;
			break;
		case 4:
			npc(FacialExpression.OLD_DEFAULT, "Then you have something that implings want.");
			stage++;
			break;
		case 5:
			player("Eh?");
			stage++;
			break;
		case 6:
			npc(FacialExpression.OLD_DEFAULT, "We want things you people want. They are tasty to us!", "The more you want them, the tastier they are!");
			stage++;
			break;
		case 7:
			player("So, you collect things that humans want? Interesting...", "So, what would happen if I caught an impling in a", "butterfly net?");
			stage++;
			break;
		case 8:
			npc(FacialExpression.OLD_DEFAULT, "Don't do that! That would be cruel. But chase us, yes!", "That is good. Implings that are not easy to catch. Especially", "ones with tasty food.");
			stage++;
			break;
		case 9:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6073 };
	}

}
