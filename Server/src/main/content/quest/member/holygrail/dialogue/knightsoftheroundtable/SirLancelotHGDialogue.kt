package content.quest.member.holygrail.dialogue.knightsoftheroundtable

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents Sir Lancelot dialogue file for Holy Grail quest.
 */
class SirLancelotHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
        }
    }
}

//Greetings! I am Sir Lancelot, the greatest Knight in the land! What do you want?
//I am questing for the Holy Grail.
//The Grail? Ha! Frankly, little man, you're not in that league.
//Why do you say that?
//You got lucky with freeing Merlin but there's no way a puny wannabe like you is going to find the Holy Grail
//where so many others have failed.
//We'll see about that.
//There is no way a wannabe like you got the Holy Grail without help. You lucky knight.
//I am sure you would have gotten it if you tried...
//Yup...
