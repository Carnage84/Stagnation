package content.quest.member.scorpioncatcher.util

import config.Items
import content.quest.member.scorpioncatcher.dialogue.SeersSCDialogue
import content.quest.member.scorpioncatcher.dialogue.ThormacSCDialogue
import core.api.*
import core.game.node.entity.player.Player
import core.game.system.task.Pulse

/**
 * Utils for Scorpion Catcher quest.
 */
object SCUtils {

    const val VARP_QUEST_SCORPION_CATCHER = 76

    const val SEER_MIRROR_ATTR = "/save:sc:start-talk"
    const val SECRET_ROOM_ATTR = "/save:sc:secret-room"
    const val NPCS_DIALOG_ATTR = "/save:sc:dialogues"
    const val GIVING_CAGE_ATTR = "/save:sc:cage-dialogue"

    const val PROGRESS_SC_ATTR_1 = "/save:sc:progress:1"
    const val PROGRESS_SC_ATTR_2 = "/save:sc:progress:2"
    const val PROGRESS_SC_ATTR_3 = "/save:sc:progress:3"

    val scorpionCage = intArrayOf(
        Items.SCORPION_CAGE_456,
        Items.SCORPION_CAGE_457,
        Items.SCORPION_CAGE_458,
        Items.SCORPION_CAGE_459
    )

    fun seerLookingAtMirror(player: Player) {
        submitIndividualPulse(player, object : Pulse() {
            var counter = 0
            override fun pulse(): Boolean {
                when (counter++) {
                    1 -> sendMessage(player, "The seer produces a small mirror.")
                    3 -> sendMessage(player, "The seer gazes into the mirror.")
                    6 -> sendMessage(player, "The seer smoothes his hair with his hand.")
                    9 -> {
                        setAttribute(player, SEER_MIRROR_ATTR, true)
                        setAttribute(player, SECRET_ROOM_ATTR, true)
                        openDialogue(player, SeersSCDialogue())
                        return true
                    }
                }
                return false
            }
        })
    }

    fun thormacGivingCage(player: Player) {
        submitIndividualPulse(player, object : Pulse() {
            var counter = 0
            override fun pulse(): Boolean {
                when (counter++) {
                    0 -> {
                        sendMessage(player, "Thormac gives you a cage.")
                        addItemOrDrop(player, Items.SCORPION_CAGE_456)
                    }
                    2 -> {
                        setAttribute(player, GIVING_CAGE_ATTR, true)
                        openDialogue(player, ThormacSCDialogue())
                        return true
                    }
                }
                return false
            }
        })
    }
}