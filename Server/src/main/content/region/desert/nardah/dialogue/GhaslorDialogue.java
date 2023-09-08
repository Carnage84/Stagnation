package content.region.desert.nardah.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Ghaslor dialogue plugin.
 */
@Initializable
public class GhaslorDialogue extends DialoguePlugin {
    public GhaslorDialogue(){
        /**
         * empty
         */
    }
    public GhaslorDialogue(Player player){
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player){return new GhaslorDialogue(player);}

    @Override
    public boolean open(Object... args){
        String gender = player.isMale() ? "gentleman" : "lady";
        npc("Good day young " + gender);
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId){
        switch(stage) {
            case 0:
                end();
                break;
        }
        return true;
    }
    public int[] getIds() {return new int[] {3029};}
}
