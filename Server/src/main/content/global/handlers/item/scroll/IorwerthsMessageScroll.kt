package content.global.handlers.item.scroll

import config.Components
import config.Items
import core.api.openInterface
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Represents the Iorwerths message scroll listener for the Regicide quest.
 */
class IorwerthsMessageScroll : InteractionListener {

    // Receive from the Lord Iorwerth after
    // killing King Tyras in Regicide quest.

    override fun defineListeners() {
        val kingsmessage = Components.KINGS_LETTER_V2_463
        val iorwerthsscroll = Items.IORWERTHS_MESSAGE_3207
        on(iorwerthsscroll, IntType.ITEM, "read") { player, _ ->
            openInterface(player, kingsmessage)
            player.packetDispatch.sendString("Your Majesty King Lathas<br>Your man did well. The path is now open for the dark lord to enter this realm. You will yet live to see Camelot crushed under foot.", kingsmessage, 1)
            player.packetDispatch.sendString("Warmaster Iorwerth", kingsmessage, 2)
            return@on true
        }
    }
}