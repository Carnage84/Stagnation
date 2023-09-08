package content.region.asgarnia.taverley.handlers

import config.Items
import config.Scenery
import core.api.inInventory
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

class TaverleyDungeonListeners : InteractionListener {

    val BD_GATE = Scenery.GATE_2623

    override fun defineListeners() {

        on(BD_GATE, IntType.SCENERY, "open"){ player, node ->
            if(!inInventory(player, Items.DUSTY_KEY_1590)){
                sendMessage(player, "This gate seems to be locked.")
            } else {
                core.game.global.action.DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
            }
            return@on true
        }
    }
}