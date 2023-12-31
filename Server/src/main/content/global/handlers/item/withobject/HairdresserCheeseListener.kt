package content.global.handlers.item.withobject

import config.Items
import config.Scenery
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.link.diary.DiaryType

/**
 * Listener for using cheese on Ridgeley on the treadmill.
 */
@Suppress("unused")
class HairdresserCheeseListener : InteractionListener {

    override fun defineListeners() {
        onUseWith(IntType.SCENERY, Items.CHEESE_1985, Scenery.TREADMILL_11677) { player, used, _ ->
            lock(player, 5)

            var isTaskSuccessful = false
            runTask(player, 3, 1) {
                if (!removeItem(player, used)) {
                    return@runTask
                }

                sendMessage(player, "You throw the cheese to Ridgeley, for which he appears grateful.")
                player.achievementDiaryManager.finishTask(player, DiaryType.FALADOR, 0, 6)
                unlock(player)

                isTaskSuccessful = true
            }

            return@onUseWith isTaskSuccessful
        }
    }

}