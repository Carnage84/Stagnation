package content.quest.member.icthlarinslittlehelper.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Wanderer dialogue plugin for Icthlarin's Little Helper quest.
 */
@Initializable
public class WandererILHDialogue extends DialoguePlugin {

    public WandererILHDialogue() {
    }

    public WandererILHDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new WandererILHDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        player ("Good day, wanderer.");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage){
            case 0:
                npc ("Hello to you too adventurer.");
                next();
                break;
            case 1:
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] {};


    }
}
