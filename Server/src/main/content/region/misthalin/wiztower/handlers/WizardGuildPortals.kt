package content.region.misthalin.wiztower.handlers

import core.api.sendMessage
import core.api.teleport
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

/**
 * Listener for Runecrafting Guild portals.
 */
class WizardGuildPortals : InteractionListener {

    val WTOWER_PORTAL = 2156
    val DWTOWER_PORTAL = 2157
    val SORC_TOWER_PORTAL = 2158

    override fun defineListeners() {
        on(WTOWER_PORTAL, IntType.SCENERY, "enter"){ player, _ ->
            teleport(player, Location.create(3109, 3159, 0))
			sendMessage(player, "You enter the magic portal...")
			sendMessage(player, "You teleport to the Wizards' tower.")
            return@on true
        }

        on(DWTOWER_PORTAL, IntType.SCENERY, "enter"){ player, _ ->
            teleport(player, Location.create(2907, 3333, 0))
			sendMessage(player, "You enter the magic portal...")
			sendMessage(player, "You teleport to the Dark Wizards' tower.")
            return@on true
        }

        on(SORC_TOWER_PORTAL, IntType.SCENERY, "enter"){ player, _ ->
            teleport(player, Location.create(2703, 3406, 0))
			sendMessage(player, "You enter the magic portal...")
			sendMessage(player, "You teleport to Thormac the Sorceror's house.")
            return@on true
        }
    }
}