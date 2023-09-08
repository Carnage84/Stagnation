package content.quest.member.olafquest

import config.Components
import config.Items
import core.api.openInterface
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.plugin.Initializable

/**
 * Handles interactions for Olaf's Quest quest.
 */
@Initializable
class OlafsQuestListeners : InteractionListener {

    //iface 252	olaf2_lock_gate
    //iface 253	olaf2_skull_puzzle
    override fun defineListeners() {
        val treasuremap = Items.SVENS_LAST_MAP_11034

        on(treasuremap, IntType.ITEM, "read"){ player, _ ->
            openInterface(player, Components.OLAF2_TREASUREMAP_254)
            return@on true
        }
    }

}