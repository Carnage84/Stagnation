package content.region.desert.handlers

import config.Items
import config.NPCs
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.Item

/**
 * Handles "buy-pass" option for Shantay NPC.
 */
class ShantayPassListener : InteractionListener {
    override fun defineListeners() {
        on(SHANTAY, IntType.NPC, "buy-pass") { player, node ->
            face(node.asNpc(),player,2)
            if (removeItem(player, Item(COINS, 5))) {
                addItemOrDrop(player, SHANTAYPASS)
                sendItemDialogue(player, SHANTAYPASS, "You purchase a Shantay Pass.")
            } else {
                sendPlayerDialogue(player, "Sorry, I don't seem to have enough money.")
            }
            return@on true
        }
    }
    companion object {
        private const val SHANTAY = NPCs.SHANTAY_836
        private const val SHANTAYPASS = Items.SHANTAY_PASS_1854
        private const val COINS = Items.COINS_995
    }
}