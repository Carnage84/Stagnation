package content.region.desert.nardah.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Awusah dialogue plugin.
 */
@Initializable
public class AwusahDialogue extends DialoguePlugin {
    public AwusahDialogue(){
        /**
         * Empty
         */
    }
    public AwusahDialogue(Player player){
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player){return new AwusahDialogue(player);}

    @Override
    public boolean open(Object... args){
        interpreter.sendDialogue("The mayor doesn't seem interested in talking to you right now.");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId){
        switch(stage){
            case 0:
                end();
                break;
        }
        return true;
    }
    public int[] getIds() {return new int[] {3040};}
}
