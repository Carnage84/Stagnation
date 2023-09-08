package content.region.asgarnia.portsarim.handlers;

import config.NPCs;
import core.game.interaction.Option;
import core.game.interaction.SpecialGroundInteraction;
import core.game.interaction.SpecialGroundItems;
import core.game.node.entity.player.Player;

import static core.api.ContentAPIKt.findLocalNPC;

/**
 * Handles Ahab's beer in Port Sarim.
 */
public class AhabBeerInteraction extends SpecialGroundInteraction {
    //The dialogue key
    public static final int DIALOGUE_KEY = 2692;

    @Override
    public void handle(final Player player, final Option option){
        configure();
        if(option.getName() == "take"){
            player.faceLocation(SpecialGroundItems.AHAB_BEER.getLocation());
            player.getDialogueInterpreter().open(DIALOGUE_KEY, findLocalNPC(player, NPCs.AHAB_2692), false);
        } else {
            player.debug("Unhandled option: " + option.getName());
        }
    }
}
