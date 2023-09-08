package content.global.travel

import core.api.hasTimerActive
import core.api.sendMessage
import core.cache.ServerStore.Companion.getArchive
import core.game.node.entity.impl.Animator
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics
import org.json.simple.JSONObject

/**
 * Represents the Lyre Teleport pulse.
 */
object LyreTeleport {

    @JvmStatic
    fun getStoreFile(): JSONObject {
        return getArchive("daily-lyre-teleport")
    }
    @JvmStatic fun getLyreTeleportFile(): JSONObject {
        return getArchive("daily-lyre-teleport")
    }

    fun teleport(player: Player) {
        if (hasTimerActive(player, "teleblock")) {
            sendMessage(player, "A magical force has stopped you from teleporting.")
        } else {
            GameWorld.Pulser.submit(LyreTeleportPulse(player))
            getLyreTeleportFile()[player.username.toLowerCase()] = true
        }
    }

    class LyreTeleportPulse(val player: Player) : Pulse(){
        var counter = 0
        override fun pulse(): Boolean{
            when (counter++){
                0 -> {
                    player.lock()
                    player.animator.animate(Animation(9600, Animator.Priority.VERY_HIGH), Graphics(1682))
                }
                6 -> player.properties.teleportLocation = Location(2663, 3646, 0)
                7 -> player.unlock().also { return true }
            }
            return false
        }
    }
}