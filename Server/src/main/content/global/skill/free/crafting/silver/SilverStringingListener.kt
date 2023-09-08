package content.global.skill.free.crafting.silver

import config.Items
import core.api.addItem
import core.api.removeItem
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.Node
import core.game.node.entity.player.Player

/**
 * Handles ball of wool <-> stringable silver items interaction.
 */
class SilverStringingListener : InteractionListener {
    companion object {
        private val STRINGABLE_PRODUCTS = intArrayOf(
            Items.UNSTRUNG_SYMBOL_1714,
            Items.UNSTRUNG_EMBLEM_1720
        )
    }

    private fun stringSilverProduct(player: Player, used: Node, with: Node): Boolean {
        SilverProduct.forProductID(with.id)?.let {
            if (removeItem(player, with.id) && removeItem(player, used.id)) {
               addItem(player, it.strungId)
            }
        }

        return true
    }

    override fun defineListeners() {
        onUseWith(IntType.ITEM, Items.BALL_OF_WOOL_1759, *STRINGABLE_PRODUCTS, handler = ::stringSilverProduct)
    }
}