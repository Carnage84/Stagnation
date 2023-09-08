package content.quest.member.holygrail.dialogue

import core.api.getQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.tools.END_DIALOGUE

/**
 * Represents the Fisherman dialogue file for Holy Grail quest.
 */
class FishermanHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
            (questStage == 0) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.HALF_ASKING,"Any idea how to get into the castle?").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY,"Why, that's easy! Just ring one of the bells outside.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY,"...I didn't see any bells.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY,"You must be blind then. There's ALWAYS bells there when I go to the castle.").also { stage = END_DIALOGUE }
                }
            }
        }
    }
}