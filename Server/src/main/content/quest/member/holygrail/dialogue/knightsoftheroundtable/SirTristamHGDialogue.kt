package content.quest.member.holygrail.dialogue.knightsoftheroundtable

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents Sir Tristam dialogue file for Holy Grail quest.
 */
class SirTristamHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
        }
    }
}
//Hail Arthur, King of the Britons!
//Um... Hello. I am seeking the Grail.
//Good luck with that!
//Hail Arthur, King of the Britons!
//Oh and good job getting the Holy Grail!
//Thanks.