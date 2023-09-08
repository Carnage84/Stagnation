package content.quest.member.monksfriend.npc

import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.tools.END_DIALOGUE

/**
 * Represents the Monastery Monk dialogue file.
 */
class MonasteryMonkMFDialogue : DialogueFile() {
    override fun handle(interfaceId: Int, buttonId: Int) {
        var questStage = player!!.questRepository.getStage("Monk's Friend")
        if (questStage == 0) {
            when (stage) {
                0 -> npcl(FacialExpression.NEUTRAL, "Peace brother.").also { stage = END_DIALOGUE }
            }
        } else if (questStage < 100) {
            when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "*yawn*").also { stage = END_DIALOGUE }
            }
        } else {
            when (stage) {
                0 -> npcl(FacialExpression.HAPPY, "Can't wait for the party!").also { stage = END_DIALOGUE }
            }
        }
    }
}