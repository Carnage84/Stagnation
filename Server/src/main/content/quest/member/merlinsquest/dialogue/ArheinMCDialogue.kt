package content.quest.member.merlinsquest.dialogue

import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Arhein dialogue plugin for Merlin Crystal quest.
 */
class ArheinMCDialogue (val questStage: Int) : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when (stage) {
            START_DIALOGUE -> playerl(FacialExpression.NEUTRAL, "Can you drop me off on the way down please?").also { stage++ }
            1 -> {
                npcl(FacialExpression.ANNOYED,"I don't think Sir Mordred would like that. He wants as few outsiders visiting as possible. I wouldn't want to lose his business.")
                val quest = player!!.questRepository.getQuest("Merlin's Crystal")
                player!!.questRepository.setStage(quest, 40)
                stage = END_DIALOGUE
            }
        }
    }

}