package content.quest.member.onesmallfavour

import config.Items
import core.api.sendDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Listener for One Small Favour quest.
 */
class OneSmallFavourListeners : InteractionListener {

    val WEATHER_REPORT = Items.WEATHER_REPORT_4435
    override fun defineListeners() {
        on(WEATHER_REPORT, IntType.ITEM, "read"){player, node ->
            sendDialogue(player, "Generally quite changeable weather, perhaps starting quite sunny with some chances of rain, snow, or hail, and a large possibility of a thunderstorm or clear skies")
            return@on true
        }
    }
}