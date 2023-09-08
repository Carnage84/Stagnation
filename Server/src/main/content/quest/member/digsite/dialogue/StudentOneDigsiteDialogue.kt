package content.quest.member.digsite.dialogue

import config.Items
import config.NPCs
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Student One dialogue plugin for The Dig Site quest.
 */
@Initializable
class StudentOneDigsiteDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questName = "The Dig Site"
        val questStage = getQuestStage(player!!, questName)

        when {
            (questStage == 0) ->
                when(stage) {
                    0 -> sendDialogue(player!!,"He seems uninterested in talking.").also { stage = END_DIALOGUE }
                }
            // Start dialogue
            (questStage == 1) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Can you help me with the Earth Sciences exams at all?").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Well... Maybe I will if you help me with something.").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "What's that?").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "I have lost my recent good find.").also { stage++ }
                    5 -> playerl(FacialExpression.FRIENDLY, "What does it look like?").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "Err... Like an animal skull!").also { stage++ }
                    7 -> playerl(FacialExpression.FRIENDLY, "Well, that's not too helpful, there are lots of those around here; can you remember where you last had it?").also { stage++ }
                    8 -> npcl(FacialExpression.FRIENDLY, "It was around here for sure. Maybe someone picked it up?").also { stage++ }
                    9 -> player("Okay, I'll have a look for you.").also { stage = END_DIALOGUE }
                }
            }

            // Talk again without obtaining the skull
            (questStage == 3 && !inInventory(player!!, Items.TEDDY_673)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "How's the study going?").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Very well, thanks. Have you found my animal skull yet?").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "No, sorry, not yet.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Oh well, I am sure it's been picked up. Couldn't you try looking through some pockets?").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After obtaining the skull when giving him the skull
            (questStage == 3 && inInventory(player!!, Items.ANIMAL_SKULL_671)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> sendItemDialogue(
                        player!!,
                        Items.ANIMAL_SKULL_671,
                        "Is this your animal skull?"
                    ).also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Oh wow! You've found it! Thank you so much. I'll be glad to tell you what I know about the exam.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "The study of Earth Sciences is: The study of the earth, its contents and history.").also { stage++ }
                    4 -> playerl(FacialExpression.FRIENDLY, "Okay, I'll remember that.").also { stage = 5 }
                    5 -> {
                        end()
                        removeItem(player!!, Items.ANIMAL_SKULL_671)
                        setAttribute(player!!, "digsite:students", 1)
                    }
                }
            }

            // Talk after passing the first exam and before passing the second exam
            (questStage == 3 && player!!.getAttribute("digsite:students", 0) > 1) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How's it going?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I need more help with the exam.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Well, okay, this is what I have learned since I last spoke to you...").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Correct rock pick usage: Always handle with care; strike the rock cleanly on its cleaving point.").also { stage++ }
                    5 -> player("Okay, I'll remember that.").also { stage = END_DIALOGUE }
                }
            }

            // Again after passing the second exam and before passing the third exam
            (questStage == 6 && player!!.getAttribute("digsite:students", 0) == 4) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How's it going?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I need more help with the exam.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Well, okay, this is what I have learned since I last spoke to you...").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Specimen brush use: Brush carefully and slowly using short strokes.").also { stage++ }
                    5 -> player("Okay, I'll remember that. Thanks for all your help.").also { stage = 6 }
                    6 -> {
                        end()
                        setAttribute(player!!, "digsite:students", 7)
                    }
                }
            }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.STUDENT_615)
    }
}
