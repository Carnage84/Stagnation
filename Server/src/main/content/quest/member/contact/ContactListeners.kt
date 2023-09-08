package content.quest.member.contact

import config.Components
import config.Items
import core.api.openInterface
import core.api.setInterfaceText
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Contact quest listeners.
 */
class ContactListeners : InteractionListener {

    companion object {
        private val BLOODY_PARCHMENT = Items.PARCHMENT_10585
        private val BLOODY_SCROLL = Components.CONTACT_SCROLL_BLOOD_498
    }

    private val PARCHMENT_TEXT = arrayOf(
        "",
        "Kaleef",
        "Your mission is to contact our agent inside Menaphos.",
        "Use the tunnels beneath the temple of the lesser gods.",
        "Be vigilant of traps and the hostile natives.",
        "They're about as welcoming as the Menaphites only",
        "slightly better looking.",
        "",
        "Os"
    )

    override fun defineListeners() {
        on(BLOODY_PARCHMENT, IntType.ITEM, "Read"){ player, _ ->
            openInterface(player, BLOODY_SCROLL).also {
                setInterfaceText(player, PARCHMENT_TEXT.joinToString("<br>"), BLOODY_SCROLL, 1)
            }
            return@on true
        }
    }
}