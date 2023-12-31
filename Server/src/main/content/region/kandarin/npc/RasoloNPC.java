package content.region.kandarin.npc;

import core.cache.def.impl.NPCDefinition;
import core.game.dialogue.DialoguePlugin;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Handles Rasolo NPC options.
 */
@Initializable
public class RasoloNPC extends OptionHandler {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        new RosaloDialouge().init();
        NPCDefinition.forId(1972).getHandlers().put("option:talk-to",this);
        NPCDefinition.forId(1972).getHandlers().put("option:trade",this);
        return null;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        if(option.equals("trade")){
            new NPC(1972).openShop(player);
        } else {
            player.getDialogueInterpreter().open(1972);
        }
        return false;
    }

    public class RosaloDialouge extends DialoguePlugin{
        public RosaloDialouge(){
            /**
             *
             */
        }
        public RosaloDialouge(Player player){super(player);}

        @Override
        public DialoguePlugin newInstance(Player player) {
           return new RosaloDialouge(player);
        }

        @Override
        public boolean open(Object... args){
            npc("Hello, would you like to see my wares?");
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch(stage){
                case 0:
                    player.getDialogueInterpreter().sendOptions("Select one", "Yes, please", "No, thanks");
                    stage++;
                    break;
                case 1:
                    switch(buttonId){
                        case 1:
                            end();
                            new NPC(1972).openShop(player);
                            break;
                        case 2:
                            player("No, thanks");
                            stage++;
                            break;
                    }
                    break;
                case 2:
                    end();
                    break;
            }
            return true;
        }
        @Override
        public int[] getIds() {return new int[] {1972};}
    }
}
