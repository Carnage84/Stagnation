package content.global.random.event.supriseexam

import core.game.dialogue.DialogueFile
import core.tools.END_DIALOGUE

/**
 * Handles door interaction dialogues in Suprise Exam random event.
 */
class SEDoorDialogue(val preExam: Boolean) : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        stage = if(preExam){
            dialogue("I should probably speak with Mr. Mordaut first.")
            END_DIALOGUE
        } else {
            dialogue("The door won't budge. Perhaps I should","ask for directions.")
            END_DIALOGUE
        }
    }
}