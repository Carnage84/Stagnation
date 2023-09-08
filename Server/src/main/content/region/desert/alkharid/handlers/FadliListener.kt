package content.region.desert.alkharid.handlers

import config.NPCs
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Represents Fadli "buy" interaction listener.
 */
class FadliListener : InteractionListener {
    override fun defineListeners() {
        on(NPCs.FADLI_958, IntType.NPC, "buy") { player, node ->
            if(node.asNpc().openShop(player)) {
                return@on true
            }
            return@on false
        }
    }
}