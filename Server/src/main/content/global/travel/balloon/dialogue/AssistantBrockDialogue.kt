package content.global.travel.balloon.dialogue

import config.Components
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
 * Represents the Assistant Brock dialogue plugin used for Balloon traveling.
 */
@Initializable
class AssistantBrockDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (stage) {
            START_DIALOGUE -> npcl(FacialExpression.FRIENDLY, "Do you want to use the balloon? Just so you know, some locations require special logs and high Firemaking skills.").also { stage++ }
            1 -> options("Yes.", "No.", "Who are you?").also { stage++ }
            2 -> when (buttonID) {
                1 -> if (!isQuestComplete(player, "Enlightened Journey")) {
                    npcl(FacialExpression.FRIENDLY, "You must complete Enlightened Journey before you can use it.").also { stage = END_DIALOGUE }
                } else {
                    player("Yes.").also { stage = 7 }
                }
                2 -> player("No.").also { stage = END_DIALOGUE }
                3 -> player("Who are you?").also { stage = 3 }
            }
            3 -> npcl(FacialExpression.FRIENDLY, "I am Assistant Brock. I serve under Auguste as his number two assistant.").also { stage++ }
            4 -> npcl(FacialExpression.FRIENDLY, "Do you want to use the balloon?").also { stage++ }
            5 -> options("Yes.", "No.").also { stage++ }
            6 -> when (buttonID) {
                1 -> if (!isQuestComplete(player, "Enlightened Journey")) {
                    npcl(FacialExpression.FRIENDLY, "You need complete the Enlightened Journey quest in order to use this.").also { stage = END_DIALOGUE }
                } else {
                    player("Yes.").also { stage = 7 }
                }
                2 -> playerl(FacialExpression.NEUTRAL, "No.").also { stage = END_DIALOGUE }
            }
            7 -> {
                end()
                openInterface(player, Components.ZEP_BALLOON_MAP_469)
                setComponentVisibility(player,Components.ZEP_BALLOON_MAP_469,20, false)
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(5065)
    }
}