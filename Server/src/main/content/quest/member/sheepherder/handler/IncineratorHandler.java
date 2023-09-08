package content.quest.member.sheepherder.handler;

import content.quest.member.sheepherder.SheepHerder;
import core.game.interaction.*;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.scenery.Scenery;
import core.game.world.update.flag.context.Animation;
import core.plugin.Initializable;
import core.plugin.Plugin;

import java.util.Objects;

import static core.api.ContentAPIKt.setAttribute;

@Initializable
public class IncineratorHandler extends PluginInteraction {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        setIds(new int[] {SheepHerder.RED_SHEEP_BONES.getId(), SheepHerder.GREEN_SHEEP_BONES.getId(), SheepHerder.BLUE_SHEEP_BONES.getId(),SheepHerder.YELLOW_SHEEP_BONES.getId()});
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.USEWITH);
        return this;
    }

    @Override
    public boolean handle(Player player, NodeUsageEvent event) {
        Node n = event.getUsedWith();
        if(n instanceof Scenery){
            Scenery obj = (Scenery) n;
            if (n.getId() == 165) {
                player.getPulseManager().run(new MovementPulse(player, DestinationFlag.OBJECT.getDestination(player,obj)) {
                    @Override
                    public boolean pulse() {
                        player.lock(2);
                        player.getInventory().remove(event.getUsedItem());
                        player.getAnimator().reset();
                        player.getAnimator().animate(new Animation(3243));
                        switch (Objects.requireNonNull(event.getUsedItem()).getId()) {
                            case 280:
                                setAttribute(player, "/save:sheep_herder:red_dead", true);
                                break;
                            case 281:
                                setAttribute(player, "/save:sheep_herder:green_dead", true);
                                break;
                            case 282:
                                setAttribute(player, "/save:sheep_herder:blue_dead", true);
                                break;
                            case 283:
                                setAttribute(player, "/save:sheep_herder:yellow_dead", true);
                                break;
                        }
                        if (player.getAttribute("sheep_herder:red_dead", false) && player.getAttribute("sheep_herder:green_dead", false) && player.getAttribute("sheep_herder:blue_dead", false) && player.getAttribute("sheep_herder:yellow_dead", false)) {
                            setAttribute(player, "/save:sheep_herder:all_dead", true);
                        }
                        return true;
                    }
                });
                return true;
            }
        }
        return false;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
