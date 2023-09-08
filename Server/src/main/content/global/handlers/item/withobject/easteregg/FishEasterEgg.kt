package content.global.handlers.item.withobject.easteregg

import config.Items
import content.global.skill.free.woodcutting.WoodcuttingNode
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Fish easter egg listener.
 */
class FishEasterEgg : InteractionListener {
    val TREE_IDs = WoodcuttingNode.values().map { it.id }.toIntArray()

    val fish = intArrayOf(Items.RAW_HERRING_345, Items.HERRING_347)
    val doors = intArrayOf(1967, 1968)

    override fun defineListeners() {
        onUseWith(IntType.SCENERY, fish, *TREE_IDs) { player, _, _ ->
            sendMessage(player, "This is not the mightiest tree in the forest.")
            return@onUseWith true
        }

        onUseWith(IntType.SCENERY, fish, *doors) { player, _, _ ->
            sendMessage(player, "It can't be done!")
            return@onUseWith true
        }
    }
}