package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Bull Ant familiar interaction dialogue.
 */
@Initializable
class BullAntDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, BullAntDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BULL_ANT_6867, NPCs.BULL_ANT_6868)
    }
}

class BullAntDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.BULL_ANT_6867)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "All right you worthless biped, fall in!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Sir, yes Sir!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "We're going to work you so hard your boots fall off, understood?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Sir, yes Sir!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Carry on Private!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Aten...hut!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Sir, Private Player reporting for immediate active duty, Sir!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "As you were, Private!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I can't believe they stuck me with you...").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Buck up, Sir, it's not that bad.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Stow that, Private, and get back to work!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Sir, yes Sir!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "What in the name of all the layers of the abyss do you think you're doing, biped?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Sir, nothing Sir!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Well double-time it, Private, whatever it is!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Sir, yes Sir!").also { stage = END_DIALOGUE }
            }
        }

        if (player!!.settings.runEnergy <= 0) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "What's the matter, Private? Not enjoying the run?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Sir...wheeze...yes Sir!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Not enjoying the run? You need more training biped?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Sir, no Sir! Sir, I'm enjoying the run a great deal, Sir!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Then hop to, Private!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Sir, yes Sir!").also { stage = END_DIALOGUE }
            }
        }
    }
}