package content.region.misthalin.draynor.handlers

import config.Scenery
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Represents the Deposit boxes listener at Gnomecopter.
 */
class DepositBoxesListener : InteractionListener {

    val GNOMECOPTER_DEPOSIT_BOXES = Scenery.DEPOSIT_BOX_30044
    override fun defineListeners() {
        on(GNOMECOPTER_DEPOSIT_BOXES, IntType.SCENERY, "deposit"){player, node ->
            player.bank.openDepositBox()
            player.bank::refreshDepositBoxInterface
            return@on true
        }
    }
}