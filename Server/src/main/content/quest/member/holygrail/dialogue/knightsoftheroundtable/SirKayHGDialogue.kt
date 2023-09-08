package content.quest.member.holygrail.dialogue.knightsoftheroundtable

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents Sir Kay dialogue file for Holy Grail quest.
 */
class SirKayHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
        }
    }
}
//Good day (player.isMale() ? "Sir, " : "Madam, ")! I hear you are questing for the Holy Grail?
//That's right. Any hints?
//Unfortunately not.
//You got the Holy Grail!
//Yes, I did...