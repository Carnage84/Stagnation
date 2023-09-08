package content.quest.member.holygrail.dialogue

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents the Merlin dialogue file for Holy Grail quest.
 */
class MerlinHGDialogue : DialogueFile() {
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


//Hello. King Arthur has sent me on a quest for the Holy Grail. He thought you could offer some assistance.
//Ah yes... the Holy Grail... That is a powerful artefact indeed. Returning it here would help Camelot a lot.
//Due to its nature the Holy Grail is likely to reside in a holy place.
//Any suggestions?
//I believe there is a holy island somewhere not far away... I'm not entirely sure... I spent too long inside that
//crystal! Anyways, go and talk to someone over there. I suppose you could also try speaking to Sir Galahad?
//He returned from the quest many years after everyone else. He seems to know something about it, but he can only
//speak about those experiences cryptically.
//Where can I find Sir Galahad?
//Galahad now lives a life of religious contemplation. He lives somewhere west of McGrubor's Wood I think. Though
//I recommend speaking to someone on the holy island first.
//How goes the quest for the Holy Grail?
//I am to find an artifact in Draynor Manor
//I see
//How goes the quest for the Holy Grail?
//I am trying to find the son of the king of The Fisher Realm!
//That is a task indeed!
//I found the Holy Grail!
//REALLY!!?
//Yes!
//You really need to give The Holy Grail to King Arthur!
//I am amazed with you adventurer thank you for getting the Holy Grail back to Camelot!
//
