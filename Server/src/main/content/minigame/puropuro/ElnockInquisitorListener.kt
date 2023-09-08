package content.minigame.puropuro

import config.Items
import config.NPCs
import core.api.openNpcShop
import core.api.sendNPCDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.Item

/**
 * Represents the option listener for Elnock Inquisitor NPC in Puro-Puro minigame.
 */
class ElnockInquisitorListener : InteractionListener {

    companion object{
        private val IMPLING_SCROLL = Items.IMPLING_SCROLL_11273
        private val ELNOCK_INQUISITOR = NPCs.ELNOCK_INQUISITOR_6070
    }
    override fun defineListeners() {
        // Elnock Exchange
        on(ELNOCK_INQUISITOR, IntType.NPC, "trade") { player, node ->
            openNpcShop(player, ELNOCK_INQUISITOR)
            return@on true
        }

        // Quick-start option
        on(ELNOCK_INQUISITOR, IntType.NPC, "quick-start") { player, _ ->
            if (!player.savedData.activityData.isElnockSupplies) {
                player.savedData.activityData.isElnockSupplies = true
                player.inventory.add(Item(10010), player)
                player.inventory.add(Item(11262, 1), player)
                player.inventory.add(Item(11260, 6), player)
                sendNPCDialogue(player,ELNOCK_INQUISITOR,"Here you go!")
            } else {
                sendNPCDialogue(player,ELNOCK_INQUISITOR,"Since I have already given you some equipment for free, I'll be willing to sell you some now.")
            }
            return@on true
        }
    }
}