package content.quest.member.holygrail.dialogue.knightsoftheroundtable

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents Sir Palomedes dialogue file for Holy Grail quest.
 */
class SirPalomedesHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
        }
    }
}

//Hello there adventurer, what do you want of me?
//I'd like some advice on finding the Grail.
//Sorry, I cannot help you with that.
//Amazing job getting the Holy Grail!
//Thank you.
