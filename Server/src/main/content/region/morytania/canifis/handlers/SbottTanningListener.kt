package content.region.morytania.canifis.handlers

import config.NPCs
import content.global.skill.free.crafting.TanningProduct
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Handles Tanning for Sbott NPC.
 */
class SbottTanningListener : InteractionListener {

    private val SBOTT = NPCs.SBOTT_1041
    override fun defineListeners() {
        on(SBOTT, IntType.NPC, "trade") { player, _ ->
            TanningProduct.open(player, SBOTT)
            return@on true
        }
    }
}