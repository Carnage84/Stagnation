package content.global.handlers.item.withobject

import core.api.EquipmentSlot
import core.api.sendDialogue
import core.cache.def.impl.ItemDefinition
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Represents interaction listener for Hat Stand.
 */
class HatStand : InteractionListener {

    val hats = ItemDefinition.getDefinitions().values.filter { it.getConfiguration("equipment_slot",0) == EquipmentSlot.HEAD.ordinal }.map { it.id }.toIntArray()
    val hat_stand = 374

    override fun defineListeners() {
        onUseWith(IntType.SCENERY, hats, hat_stand){ player, used, with ->
            sendDialogue(player, "It'd probably fall off if I tried to do that.")
            return@onUseWith true
        }
    }

}