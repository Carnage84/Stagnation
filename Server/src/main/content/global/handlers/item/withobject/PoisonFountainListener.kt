package content.global.handlers.item.withobject

import config.Items
import config.Scenery
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.system.task.Pulse
import core.game.world.update.flag.context.Animation

/**
 * Listener for using poisoned fish food on Draynor Manor fountain.
 */
@Suppress("unused")
class PoisonFountainListener : InteractionListener {

    companion object {
        private val ANIMATION = Animation(881)
    }

    override fun defineListeners() {
        onUseWith(IntType.SCENERY, Items.POISONED_FISH_FOOD_274, Scenery.FOUNTAIN_153) { player, used, _ ->
            if (getAttribute(player, "piranhas-killed", false)) {
                sendMessage(player, "The piranhas are dead already.")
                return@onUseWith true
            }

            if (!removeItem(player, used)) {
                return@onUseWith false
            }

            lock(player, 5)
            animate(player, ANIMATION)
            sendMessage(player, "You pour the poisoned fish food into the fountain.")
            setAttribute(player, "/save:piranhas-killed", true)

            submitIndividualPulse(player, object : Pulse(1) {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        1 -> sendMessage(player, "The piranhas start eating the food...")
                        2 -> {
                            sendMessage(player, "... then die and float to the surface.")
                            unlock(player)
                            return true
                        }
                    }
                    return false
                }
            })

            return@onUseWith true
        }
    }

}