package content.quest.member.horrorfromthedeep.handlers

import config.Components
import config.Sounds
import content.quest.member.horrorfromthedeep.HFTDUtils
import core.api.*
import core.game.global.action.DoorActionHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Listener for Puzzle door in Lighthouse basement.
 */
class PuzzleDoorListener : InteractionListener {

    override fun defineListeners() {

        on(HFTDUtils.PUZZLE_DOORS, IntType.SCENERY, "study") { player, _ ->
            when(player.location.y){
                4626 -> openInterface(player, Components.HORROR_METALDOOR_142)
                4627 -> sendMessage(player, "You cannot see anything unusual about the wall from this side.")
            }
            return@on true
        }

        onUseWith(IntType.SCENERY, HFTDUtils.QUEST_ITEMS, *HFTDUtils.PUZZLE_DOORS) { player, _, _ ->
            openDialogue(player, PuzzleDoorInteraction())
            return@onUseWith true
        }

        on(HFTDUtils.STRANGE_DOORS, IntType.SCENERY, "open") { player, node ->
            if (getQuestStage(player, "Horror from the Deep") >= 50) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                playAudio(player, Sounds.STRANGEDOOR_OPEN_1626)
                setQuestStage(player, "Horror from the Deep", 55)
                runTask(player, 2) { playAudio(player, Sounds.STRANGEDOOR_CLOSE_1625) }
            } else {
                when(player.location.y){
                    4626 -> sendMessage(player, "You cannot see any way to move this part of the wall....")
                    4627 -> sendMessage(player, "You cannot see anything unusual about the wall from this side.")
                }
            }
            return@on true
        }
    }
}