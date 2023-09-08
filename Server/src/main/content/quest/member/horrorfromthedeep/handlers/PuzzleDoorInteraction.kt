package content.quest.member.horrorfromthedeep.handlers

import config.Components
import config.Items
import config.Sounds
import content.quest.member.horrorfromthedeep.HFTDUtils
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.node.item.Item

/**
 * Puzzle door.
 */
class PuzzleDoorInteraction : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when (stage) {
            0 -> {
                sendDialogue(player!!, "I don't think I'll get that back if I put it in there.")
                stage++
            }
            1 -> {
                setComponentVisibility(player!!, Components.MULTI2_228, 6, true)
                setComponentVisibility(player!!, Components.MULTI2_228, 9, false)
                sendDialogueOptions(player!!, "Really place the rune into the door?", "Yes", "No")
                stage++
            }

            2 -> when (buttonID) {
                1 -> if (getAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, -1) == 0){
                        end()
                        removeItem(player!!, Item(Items.BRONZE_ARROW_882, 1))
                        sendMessage(player!!, "you place an arrow into the slot in the wall.")
                        setAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, 1)
                    } else if (getAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, -1) == 1){
                        end()
                        removeItem(player!!, Item(Items.BRONZE_SWORD_1277, 1))
                        sendMessage(player!!, "you place an a sword into the slot in the wall.")
                        setAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, 2)
                    } else if (getAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, -1) == 2){
                        end()
                        removeItem(player!!, Item(Items.AIR_RUNE_556, 1))
                        sendMessage(player!!, "you place an air rune into the slot in the wall.")
                        setAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, 3)
                    } else if (getAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, -1) == 3){
                        end()
                        removeItem(player!!, Item(Items.FIRE_RUNE_554, 1))
                        sendMessage(player!!, "you place a fire rune into the slot in the wall.")
                        setAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, 4)
                    } else if (getAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, -1) == 4){
                        end()
                        removeItem(player!!, Item(Items.EARTH_RUNE_557, 1))
                        sendMessage(player!!, "you place an earth rune into the slot in the wall.")
                        setAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, 5)
                    } else if (getAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, -1) == 5) {
                    removeItem(player!!, Item(Items.WATER_RUNE_555, 1))
                    end()
                    lock(player!!, 2)
                    sendMessage(player!!, "you place a water rune into the slot in the wall.")
                    setAttribute(player!!, HFTDUtils.PUZZLE_PROGRESS, 6)
                    runTask(player!!, 1) {
                        sendMessage(player!!, "You hear the sound of something moving within the wall.")
                        setQuestStage(player!!, "Horror from the Deep", 50)
                        playAudio(player!!, Sounds.STRANGEDOOR_SOUND_1627)
                        setComponentVisibility(player!!, Components.HORROR_METALDOOR_142, 10, false)
                    }
                }
                2 -> end()
            }
        }

    }
}