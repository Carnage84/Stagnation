package content.global.random.event.kissthefrog

import config.Items
import config.NPCs
import core.api.*
import core.game.node.entity.player.Player
import core.game.world.map.Location

/**
 * Utils for Kiss the Frog random event.
 */
object FrogUtils {
    const val frogTask = "frog:task"
    const val frogLocation = "frog:location"
    const val frogLogout = "frog:logout"
    const val frogFail = "frog:fail"

    const val frogToken = Items.FROG_TOKEN_6183

    const val FROG_PRINCE_NPC = NPCs.FROG_PRINCE_2474
    const val FROG_PRINCESS_NPC = NPCs.FROG_PRINCESS_2475

    const val TRANSFORMATION = 2377
    const val GET_BACK_ANIM_1 = 17080
    const val GET_BACK_GFX = 3220
    const val BLOW_KISS_ANIM = 1374
    const val BLOW_KISS_GFX = 1702
    const val FROG_APPEARANCE = 2473

    fun teleport(player: Player){
        setAttribute(player,frogLocation, player.location)
        registerLogoutListener(player,frogLogout){p->
            p.location = getAttribute(p,frogLocation,p.location)
        }

        player.properties.teleportLocation = Location.create(2463, 4781, 0)
    }
    fun cleanup(player: Player){
        player.properties.teleportLocation = getAttribute(player, frogLocation,null)
        clearLogoutListener(player, frogLogout)
        player!!.appearance.transformNPC(-1)
        player!!.interfaceManager.restoreTabs()
        removeAttributes(player, frogTask, frogFail, frogLocation, frogLogout)
    }
}
