package content.global.travel.balloon

import config.Components
import core.api.*
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.map.Location
import core.net.packet.PacketRepository
import core.net.packet.context.MinimapStateContext
import core.net.packet.out.MinimapState

/**
 * Balloon travel pulse.
 */
object BalloonTravel {

    @JvmStatic
    fun handleFlight(player: Player, flight: FlightDestination) {
        lock(player, 5)
        lockInteractions(player, 5)
        openInterface(player, Components.ZEP_BALLOON_MAP_469)
        PacketRepository.send(MinimapState::class.java, MinimapStateContext(player, 2))
        animateInterface(player, Components.ZEP_BALLOON_MAP_469, 1, flight.flyAnim)
        teleport(player, flight.flightDestination)
        val animDuration = animationDuration(getAnimation(flight.flyAnim))
        submitWorldPulse(object : Pulse(animDuration) {
            override fun pulse(): Boolean {
                PacketRepository.send(MinimapState::class.java, MinimapStateContext(player, 1))
                closeInterface(player)
                return true
            }
        })
    }
}

/**
 * Destination list.
 */
enum class FlightDestination(val areaName: String, val flightDestination: Location, val flyAnim: Int) {
    CASTLE_WARS("Castle Wars", Location.create(2462, 3108, 0), 1227),
    CRAFT_GUILD("Crafting Guild", Location.create(2924, 3303, 0), 1227),
    GRAND_TREE("Grand Tree", Location.create(2480, 3458, 0), 1227),
    VARROCK("Varrock", Location.create(3298, 3481, 0), 1227),
    TAVERLEY("Taverley", Location.create(2940, 3420, 0), 1227),
    ENTRANA("Entrana", Location.create(2809, 3356, 0), 1227)
}