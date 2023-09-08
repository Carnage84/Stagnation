package content.region.misthalin.lumbridge.handlers

import config.Scenery
import core.api.sendDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Represents the Gnome Copter Sign Listener.
 */
class GnomeCopterSignListener : InteractionListener {
    val SIGN = Scenery.ADVERTISEMENT_30037
    override fun defineListeners() {
        on(SIGN, IntType.SCENERY, "read") { player, _ ->
            sendDialogue(player, "Come check our gnome copters up north! Disclaimer: EXTREMELY WIP")
            return@on true
        }
    }
}