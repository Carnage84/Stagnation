package content.global.handlers.item.withobject

import config.Items
import core.api.Container
import core.api.addItem
import core.api.removeItem
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Represents interaction listener for Sack On Hay.
 */
class SackOnHay : InteractionListener {
    val SACK = Items.EMPTY_SACK_5418
    val HAY = intArrayOf(36892, 36894, 36896, 300, 34593, 298, 299)

    override fun defineListeners() {
        onUseWith(IntType.SCENERY, SACK, *HAY){ player, used, _ ->
            if(removeItem(player, used.asItem(), Container.INVENTORY)){
                addItem(player, Items.HAY_SACK_6057, 1)
                sendMessage(player, "You fill the sack with hay.")
            }
            return@onUseWith true
        }
    }
}