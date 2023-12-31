package content.region.desert.nardah.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Seddu dialogue plugin.
 */
@Initializable
public class SedduDialogue extends DialoguePlugin {
    public SedduDialogue(){
        /**
         * Empty
         */
    }
    public SedduDialogue(Player player){
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player){return new SedduDialogue(player);}

    @Override
    public boolean open(Object... args){
        npc("I buy and sell adventurer's equipment, do you want to trade?");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId){
        switch(stage){
            case 0:
                interpreter.sendOptions("Select one","Yes, please","No, thanks");
                stage++;
                break;
            case 1:
                switch(buttonId){
                    case 1:
                        end();
                        NPC seddu = new NPC(3038);
                        seddu.openShop(player);
                        break;
                    case 2:
                        player("No, thanks.");
                        stage++;
                        break;
                    case 3:
                        end();
                        break;
                }
                break;
        }
        return true;
    }
    public int[] getIds(){return new int[] {3038};}
}
