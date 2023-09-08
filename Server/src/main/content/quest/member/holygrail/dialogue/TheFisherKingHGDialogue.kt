package content.quest.member.holygrail.dialogue

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents the Fisher King dialogue file for Holy Grail quest.
 */
class TheFisherKingHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
            questStage == 0 -> when(stage){
                0 -> end()
            }
        }
    }
}
