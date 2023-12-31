package content.global.handlers.item.scroll

import config.Items
import core.api.openInterface
import core.api.setInterfaceText
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * The Crumpled scroll handler for 'Shilo Village' quest.
 */
class CrumpledScroll : InteractionListener {
    companion object {
        private const val MESSAGE_SCROLL = 222
        val CRUMPLED_SCROLL_TEXT = arrayOf(
            "Rashiliyia's rage went unchecked. She killed",
            "",
            "without mercy for revenge of her son's life. Like",
            "",
            "a spectre through the night she entered houses",
            "",
            "and one by one quietly strangled life from the",
            "",
            "occupants. It is said that only a handful survived,",
            "",
            "protected by the necklace wards to keep the Witch",
            "",
            "Queen at bay."
        )
    }

    override fun defineListeners() {
        on(Items.CRUMPLED_SCROLL_608, IntType.ITEM, "read") { player, _ ->
            openInterface(player, MESSAGE_SCROLL)
            setInterfaceText(player, CRUMPLED_SCROLL_TEXT.joinToString("<br>"),
                MESSAGE_SCROLL, 2)
            return@on true
        }
    }
}