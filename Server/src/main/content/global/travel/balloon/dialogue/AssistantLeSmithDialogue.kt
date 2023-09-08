package content.global.travel.balloon.dialogue

import config.Components
import config.NPCs
import core.api.isQuestComplete
import core.api.openInterface
import core.api.setComponentVisibility
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Assistant Lee Smith dialogue plugin used for Balloon traveling.
 */
@Initializable
class AssistantLeSmithDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when(stage) {
            START_DIALOGUE -> npcl(FacialExpression.OLD_NORMAL, "Do you want to use the balloon? Just so you know, some locations require special logs and high Firemaking skills.").also { stage++ }
            1 -> options("Yes.", "No.", "Who are you?").also { stage++ }
            2 -> when (buttonID) {
                1 -> if (!isQuestComplete(player, "Enlightened Journey")) {
                    npcl(FacialExpression.OLD_NORMAL, "You must complete Enlightened Journey before you can use it.").also { stage = END_DIALOGUE }
                } else {
                    player("Yes.").also { stage = 9 }
                }
                2 -> player("No.").also { stage = END_DIALOGUE }
                3 -> player("Who are you?").also { stage = 3 }
            }
            3 -> npcl(FacialExpression.OLD_NORMAL, "I am Assistant Le Smith. I used to work as a glider pilot, but they kicked me off.").also { stage++ }
            4 -> playerl(FacialExpression.FRIENDLY, "Why?").also { stage++ }
            5 -> npcl(FacialExpression.OLD_NORMAL, "They said I was too full of hot air.").also { stage++ }
            6 -> npcl(FacialExpression.OLD_NORMAL, "Do you want to use the balloon?").also { stage++ }
            7 -> options("Yes.", "No.").also { stage++ }
            8 -> when (buttonID) {
                1 -> if (!isQuestComplete(player, "Enlightened Journey")) {
                    npcl(FacialExpression.OLD_NORMAL, "You must complete Enlightened Journey before you can use it.").also { stage = END_DIALOGUE }
                } else {
                    player("Yes.").also { stage = 9 }
                }
                2 -> playerl(FacialExpression.NEUTRAL, "No.").also { stage = END_DIALOGUE }
            }
            9 -> {
                end()
                openInterface(player, Components.ZEP_BALLOON_MAP_469)
                setComponentVisibility(player,Components.ZEP_BALLOON_MAP_469,23, false)
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ASSISTANT_LE_SMITH_5056)
    }
}