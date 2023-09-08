package content.global.handlers.item

import config.Items
import core.api.sendItemDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * handler for the Cadava Potion.
 */
class CadavaPotionListener : InteractionListener {

    var POTION = Items.CADAVA_POTION_756

    override fun defineListeners() {
        on(POTION, IntType.ITEM, "drink") { player, node ->
            sendItemDialogue(player, POTION, "You dare not drink.")
            return@on true
        }
        on(POTION, IntType.ITEM, "look-at") { player, node ->
            sendItemDialogue(player, POTION, "This looks very colourful.")
            return@on true
        }
    }

}