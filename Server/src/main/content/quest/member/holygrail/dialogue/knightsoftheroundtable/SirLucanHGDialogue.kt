package content.quest.member.holygrail.dialogue.knightsoftheroundtable
import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents Sir Lucan dialogue file for Holy Grail quest.
 */
class SirLucanHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
        }
    }
}
//Hello there adventurer.
//I seek the Grail of legend!
//I'm afraid I don't have any suggestions.
//Thanks. I'll try and find someone who does.
//Thank you for getting the Holy Grail!
//You are welcome.