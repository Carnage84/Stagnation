package content.region.misthalin.edgeville.handlers

import config.Scenery
import core.api.sendDialogue
import core.api.teleport
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

/**
 * Listener for Poster object.
 */
class PosterListener : InteractionListener {
    override fun defineListeners() {
        on(Scenery.POSTER_29586, IntType.SCENERY, "pull-back") { player, _ ->
            sendDialogue(player, "There appears to be a tunnel behind this poster.")
            teleport(player, Location.create(3140, 4230, 2))
            return@on true
        }
    }
}