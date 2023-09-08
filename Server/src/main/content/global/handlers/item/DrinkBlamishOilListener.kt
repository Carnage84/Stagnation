package content.global.handlers.item

import config.Items
import core.api.sendPlayerDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Listener for Blamish Oil "drinkable item".
 */
class DrinkBlamishOilListener : InteractionListener {
    override fun defineListeners() {
        on(Items.BLAMISH_OIL_1582, IntType.ITEM, "drink"){ player, _ ->
            sendPlayerDialogue(player, "You know... I'd really rather not.")
            return@on true
        }
    }
    
}