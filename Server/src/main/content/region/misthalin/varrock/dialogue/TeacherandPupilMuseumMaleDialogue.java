package content.region.misthalin.varrock.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Handles the Teacher and Pupil Museum Male dialogue plugin.
 */
@Initializable
public class TeacherandPupilMuseumMaleDialogue extends DialoguePlugin {

    public TeacherandPupilMuseumMaleDialogue() {

    }

    public TeacherandPupilMuseumMaleDialogue(Player player) {
        super(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{5944};
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendDialogues(5948, FacialExpression.HALF_GUILTY, "I told you to go before we got here.");
                stage = 1;
                break;

            case 1:
                interpreter.sendDialogues(5949, FacialExpression.CHILD_GUILTY, "But sir, I didn't need to go then!");
                stage = 2;
                break;

            case 2:
                interpreter.sendDialogues(5948, FacialExpression.HALF_GUILTY, "Alright, come on then.");
                stage = 99;
                break;

            case 99:
                end();
                break;
        }

        return true;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new TeacherandPupilMuseumMaleDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        interpreter.sendDialogues(5949, FacialExpression.CHILD_GUILTY, "Teacher! Sir! I need the toilet!");
        stage = 0;
        return true;
    }
}
