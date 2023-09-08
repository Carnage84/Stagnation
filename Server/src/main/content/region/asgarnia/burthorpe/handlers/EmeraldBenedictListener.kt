package content.region.asgarnia.burthorpe.handlers

import config.NPCs
import core.api.openBankAccount
import core.api.openGrandExchangeCollectionBox
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Handles Emerald Benedict's only available interaction.
 */
class EmeraldBenedictListener : InteractionListener {
    override fun defineListeners() {
        on(NPCs.EMERALD_BENEDICT_2271, IntType.NPC, "bank") { player, _ ->
            openBankAccount(player)
            return@on true
        }

        on(NPCs.EMERALD_BENEDICT_2271, IntType.NPC, "collect") { player, _ ->
            openGrandExchangeCollectionBox(player)
            return@on true
        }
    }
}