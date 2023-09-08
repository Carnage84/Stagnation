package content.region.desert.pollnivneach.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Black Jack Seller dialogue plugin.
 */
@Initializable
public class BlackJackSellerDialogue extends DialoguePlugin {
    public BlackJackSellerDialogue(){
        /**
         * empty.
         */
    }
    public BlackJackSellerDialogue(Player player){super(player);}

    @Override
    public DialoguePlugin newInstance(Player player){return new BlackJackSellerDialogue(player);}

    @Override
    public boolean open(Object... args){
        npc("I'm not interested in selling to you. Not yet...");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int intefaceId, int objectId){
        switch(stage){
            case 0:
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {return new int[] {2548};}
}
