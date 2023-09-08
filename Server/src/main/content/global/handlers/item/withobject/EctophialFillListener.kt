package content.global.handlers.item.withobject

import config.Items
import config.Scenery
import config.Sounds
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.system.task.Pulse
import core.game.world.update.flag.context.Animation

/**
 * Listener for filling empty Ectophial.
 */
@Suppress("unused")
class EctophialFillListener : InteractionListener {

    companion object {
        private val ANIMATION = Animation(1652)
        private val AUDIO = Sounds.FILL_ECTOPLASM_1132
    }

    override fun defineListeners() {
        onUseWith(IntType.SCENERY, Items.ECTOPHIAL_4252, Scenery.ECTOFUNTUS_5282) { player, used, _ ->
            lock(player, 5)
            animate(player, ANIMATION)
            playAudio(player, AUDIO)

            submitIndividualPulse(player, object : Pulse(3) {
                override fun pulse(): Boolean {
                    if (removeItem(player, used)) {
                        addItem(player, Items.ECTOPHIAL_4251)
                        sendMessage(player, "You refill the ectophial from the Ectofuntus.")
                        unlock(player)
                        return true
                    }
                    return false
                }
            })

            return@onUseWith true
        }
    }
}
