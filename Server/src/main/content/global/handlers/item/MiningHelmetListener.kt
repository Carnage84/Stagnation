package content.global.handlers.item

import config.Items
import core.api.produceGroundItem
import core.api.removeItem
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Handles the Mining Helmet.
 */
class MiningHelmetListener : InteractionListener {
    override fun defineListeners() {
        on(Items.MINING_HELMET_5013, IntType.ITEM, "drop") { player, _ ->
            val removed = removeItem(player, Items.MINING_HELMET_5013)
            if (removed) produceGroundItem(player, Items.MINING_HELMET_5014)
            sendMessage(player, "The helmet goes out as you drop it.")
            return@on true
        }
    }

}