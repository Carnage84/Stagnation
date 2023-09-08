package content.quest.member.holygrail.dialogue

import core.api.getQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.tools.END_DIALOGUE

/**
 * Represents King Percival dialogue file for Holy Grail quest.
 */
class KingPercivalHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
            questStage >= 60 -> {
                when (stage) {
                    0 -> npc(FacialExpression.AMAZED, "You missed all the excitement! I got here and agreed to take over duties as king here," + "then before my eyes the most miraculous changes occurred here..." + "grass and trees were growing outside before our very eyes!").also { stage++ }
                    1 -> npcl(FacialExpression.HAPPY, "Thank you very much for showing me the way home.").also { stage = END_DIALOGUE }
                }
            }
            else -> {
                when (stage) {
                    0 -> npcl(FacialExpression.NEUTRAL, "Hi").also { stage++ }
                    1 -> playerl(FacialExpression.NEUTRAL, "Hello").also { stage++ }
                    2 -> npcl(FacialExpression.NEUTRAL, "...").also { stage++ }
                    3 -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                    4 -> npcl(FacialExpression.HALF_ASKING, "See you later then?").also { stage++ }
                    5 -> playerl(FacialExpression.NEUTRAL, "Yes...").also { stage = END_DIALOGUE }
                }
            }
        }
    }
}
