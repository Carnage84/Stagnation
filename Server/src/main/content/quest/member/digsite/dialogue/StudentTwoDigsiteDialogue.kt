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
 * Represents the Student Two dialogue plugin for The Dig Site quest.
 */
@Initializable
class StudentTwoDigsiteDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questName = "The Dig Site"
        val questStage = getQuestStage(player!!, questName)

        when {
            (questStage == 0) ->
                when(stage) {
                    0 -> sendDialogue(player!!, "He seems uninterested in talking.")
                        .also { stage = END_DIALOGUE }
                }
            // Start
            (questStage == 1) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Can you help me with the Earth Sciences exams at all?").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "I can't do anything unless I find my special cup.").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "Your what?").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "My special cup. I won it for a particularly good find last month.").also { stage++ }
                    5 -> playerl(FacialExpression.FRIENDLY, "Oh, right. So if I find it you'll help me?").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "I sure will!").also { stage++ }
                    7 -> playerl(FacialExpression.FRIENDLY, "Any ideas where it may be?").also { stage++ }
                    8 -> npcl(FacialExpression.FRIENDLY, "All I remember is that I was working near the tents when I lost it.").also { stage++ }
                    9 -> player("Okay, I'll see what I can do.").also { stage = END_DIALOGUE }
                }
            }

            // Without obtaining the special cup
            (questStage >= 3 && !inInventory(player!!, Items.SPECIAL_CUP_672)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "How's the study going?").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "I'm getting there. Have you found my special cup yet?").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "No, sorry, not yet.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Oh dear, I hope it didn't fall into the river. I might never find it again.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After obtaining the special cup
            (questStage >= 3 && inInventory(player!!, Items.SPECIAL_CUP_672)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Look what I found!").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Excellent! I'm so happy. Let me now help you with your exams... ").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "The people eligible to use the digsite are: All that have passed the appropriate Earth Sciences exams.").also { stage++ }
                    4 -> playerl(FacialExpression.FRIENDLY, "Thanks for the information.").also { stage = 5 }
                    5 -> {
                        end()
                        removeItem(player!!, Items.SPECIAL_CUP_672)
                        setAttribute(player!!, "digsite:students", 2)
                    }
                }
            }

            // After obtaining the special cup and before passing the first exam
            (questStage == 3 && player!!.getAttribute("digsite:students", 0) > 2) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How's it going?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "There are more exam questions I'm stuck on.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Hey, I'll tell you what I've learned. That may help.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "The people eligible to use the site are: All that have passed the appropriate Earth Sciences exams.").also { stage++ }
                    5 -> player("Thanks for the information.").also { stage = END_DIALOGUE }
                }
            }

            // After passing the first exam and before passing the second exam
            (questStage == 5) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How's it going?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "There are more exam questions I'm stuck on.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Hey, I'll tell you what I've learned. That may help.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Correct sample transportation: Samples taken in rough form; kept only in sealed containers.").also { stage++ }
                    5 -> player("Thanks for the information.").also { stage = END_DIALOGUE }
                }
            }

            // After passing the second exam and before passing the third exam
            (questStage == 6 && player!!.getAttribute("digsite:students", 0) == 5) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How's it going?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "There are more exam questions I'm stuck on.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Hey, I'll tell you what I've learned. That may help.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "The proper technique for handling bones is: Handle bones carefully and keep them away from other samples.").also { stage++ }
                    5 -> player("Thanks for the information.").also { stage = 6 }
                    6 -> {
                        end()
                        setAttribute(player!!, "digsite:students", 8)
                    }
                }
            }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.STUDENT_616)
    }
}