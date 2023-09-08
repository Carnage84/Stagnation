package content.region.wilderness.handlers

import core.api.setAttribute
import core.game.component.Component
import core.game.interaction.InterfaceListener
import core.game.node.entity.player.Player
import core.game.world.GameWorld

/**
 * Handles the Corporeal Beast warning interface.
 */
class CorporealBeastWarningInterface : InterfaceListener {

    val COMPONENT_ID = 650

    override fun defineInterfaceListeners() {
        on(COMPONENT_ID,17){player,component,_,_,_,_ ->
            //if (!requireQuest(player, "Summer's End"))
            //    return@on true
            if(player.getAttribute("corp-beast-cave-delay",0) <= GameWorld.ticks) {
                player.properties.teleportLocation = player.location.transform(4, 0, 0).also { close(player,component) }
                setAttribute(player, "corp-beast-cave-delay", GameWorld.ticks + 5)
            } else {
                close(player,component)
            }
            return@on true
        }

        on(COMPONENT_ID){player, component, _, _, _, _ ->
            close(player,component)
            return@on true
        }
    }

    fun close(player: Player,component: Component){
        player.interfaceManager.close(component)
    }
}
