package content.region.misthalin.varrock.handlers

import config.Scenery
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.plugin.Initializable

/**
 * Represents the plugin used to handle the interaction with the Champion's guild door.
 */
@Initializable
class ChampionsGuildDoor : InteractionListener {
    override fun defineListeners() {
        on(Scenery.DOOR_1805, IntType.SCENERY, "open") { player, node ->
            if (player.location.y > 3362 && player.questRepository.points < 32) {
                player.dialogueInterpreter.open(70099, "You have not proved yourself worthy to enter here yet.")
                player.packetDispatch.sendMessage("The door won't open - you need at least 32 Quest Points.")
            } else {
                if (player.location.x == 3191 && player.location.y == 3363) {
                    player.dialogueInterpreter.sendDialogues(198, null, "Greetings bold adventurer. Welcome to the guild of", "Champions.")
                }
                core.game.global.action.DoorActionHandler.handleAutowalkDoor(player, node as core.game.node.scenery.Scenery)
            }
            return@on true
        }
    }

}