package content.region.tirranwn.handlers

import config.Items
import config.Scenery
import core.api.addItem
import core.api.inInventory
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

/**
 * Interaction listener for Isfandar area.
 */
class IsafdarListeners : InteractionListener {

    companion object {

        val outside: Location = Location.create(2312, 3217, 0)
        val inside: Location = Location.create(2314, 9624, 0)

        const val CAVE_ENTRANCE = Scenery.CAVE_ENTRANCE_4006
        const val CAVE_EXIT = Scenery.CAVE_EXIT_4007
        const val BOOKCASE = Scenery.BOOKCASE_8752
        const val PRIFDDINAS_BOOK = Items.PRIFDDINAS_HISTORY_6073
    }

    override fun defineListeners() {
        on(CAVE_ENTRANCE, IntType.SCENERY, "enter"){ player, _ ->
            player.teleport(inside)
            return@on true
        }

        on(CAVE_EXIT, IntType.SCENERY, "exit"){ player, _ ->
            player.teleport(outside)
            return@on true
        }

        //The World Gate is only mentioned briefly during the Plague City storyline.
        //It is spoken of in the book Prifddinas' history.

        on(BOOKCASE, IntType.SCENERY, "search") { player, _ ->
            if (!inInventory(player, PRIFDDINAS_BOOK)) {
                sendMessage(player, "You search the bookshelves and find a book named 'Prifddinas History'")
                addItem(player, PRIFDDINAS_BOOK)
            } else {
                sendMessage(player, "You search the bookshelves...")
                sendMessage(player, "You don't find anything interesting.")
            }
            return@on true
        }
    }
}