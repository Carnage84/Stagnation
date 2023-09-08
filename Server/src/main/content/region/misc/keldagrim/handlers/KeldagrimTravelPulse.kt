package content.region.misc.keldagrim.handlers

import config.Components
import core.api.setAttribute
import core.game.component.Component
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.map.Location

/**
 * Handle Keldagrim Travel.
 */
class KeldagrimTravelPulse(val player: Player): Pulse(1){
    var counter = 0
    override fun pulse(): Boolean {
        when(counter++){
            0 -> player.lock().also { player.interfaceManager.open(Component(Components.FADE_TO_BLACK_120)) }
            3 -> player.properties.teleportLocation = Location.create(2888, 10225, 0)
            4 -> {
                player.interfaceManager.close(Component(Components.FADE_TO_BLACK_120))
                player.interfaceManager.open(Component(Components.FADE_FROM_BLACK_170))
            }
            6 -> player.unlock().also { player.interfaceManager.close(Component(Components.FADE_FROM_BLACK_170)); setAttribute(player, "/save:keldagrim-visited",true);  return true }
        }
        return false
    }
}