package content.region.misthalin.lumbridge.handlers

import config.Scenery
import core.api.getUsedOption
import core.api.openBankAccount
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Handles the Culinomancer chest options.
 */
class CulinomancerChestListener : InteractionListener {
    companion object {
        private const val CULINO_CHEST = Scenery.CHEST_12309
    }

    override fun defineListeners() {
        on(CULINO_CHEST, IntType.SCENERY, "buy-items", "buy-food"){ player, _ ->
            CulinomancerShop.openShop(player, food = getUsedOption(player).lowercase() == "buy-food")
            return@on true
        }

        on(CULINO_CHEST, IntType.SCENERY, "bank"){ player, _ ->
            openBankAccount(player)
            return@on true
        }
    }
}