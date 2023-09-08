package content.quest.member.holygrail.dialogue.knightsoftheroundtable

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents Sir Pelleas dialogue file for Holy Grail quest.
 */
class SirPelleasHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
        }
    }
}

//Greetings to the court of King Arthur!
//Any suggestions on finding the Grail?
//My best guess would be some sort of spell. Merlin is our magic expert. Ask him? Although having said that,
//I believe Galahad found its location once.
//Really? Know where I can find him?
//I'm afraid not. He left here many moons ago and I know not where he went.
//Greetings to the court of King Arthur!
//Sure...
//Great job on the Holy Grail too!
//Thanks!