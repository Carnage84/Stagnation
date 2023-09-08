package content.quest.member.enlightenedjourney

import config.Items
import config.NPCs
import config.Scenery
import core.api.getQuestStage
import core.api.removeItem
import core.api.sendNPCDialogue
import core.api.setQuestStage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.Item
import core.plugin.Initializable

/**
 * Listeners for Enlightened Journey quest.
 */
@Initializable
class EnlightenedJourneyListeners : InteractionListener {

    companion object {
        val BASKET = Scenery.BASKET_19132
    }

    override fun defineListeners() {
        onUseWith(IntType.SCENERY, Items.WILLOW_BRANCH_5933, BASKET) { player, used, with ->
            if (getQuestStage(player, "Enlightened Journey") >= 7 && removeItem(player, Item(Items.WILLOW_BRANCH_5933, 12))) {
                sendNPCDialogue(player, NPCs.AUGUSTE_5049, "Great! Let me just put it together and we'll be ready to lift off! Speak to me again in a moment.")
                setQuestStage(player, "Enlightened Journey", 8)
            }
            return@onUseWith true
        }
    }
}