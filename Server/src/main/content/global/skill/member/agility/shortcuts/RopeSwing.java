package content.global.skill.member.agility.shortcuts;

import content.global.skill.member.agility.AgilityHandler;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.scenery.Scenery;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Handles the Rope Swing.
 */
@Initializable
public class RopeSwing extends UseWithHandler {
    @Override
    public boolean handle(NodeUsageEvent event) {

        if(event.getUsedWith() instanceof Scenery){
            Scenery object = event.getUsedWith().asScenery();
            int objId = object.getId();

            assert event.getUsedItem() != null;
            int usedId = event.getUsedItem().getId();

            Player player = event.getPlayer();

            if(objId == 2327 && usedId == 954){
                if (!player.getLocation().withinDistance(object.getLocation(), 2)) {
                    player.sendMessage("I can't reach that.");
                    return true;
                }
                player.getPacketDispatch().sendSceneryAnimation(object, Animation.create(497), true);
                AgilityHandler.forceWalk(player, 0, player.getLocation(), Location.create(2505, 3087, 0), Animation.create(751), 50, 22, "You skillfully swing across.", 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        addHandler(2327,OBJECT_TYPE,this);
        return this;
    }
}
