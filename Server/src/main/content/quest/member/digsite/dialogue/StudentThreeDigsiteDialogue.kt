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
 * Represents the Student Three dialogue plugin for The Dig Site quest.
 */
@Initializable
class StudentThreeDigsiteDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questName = "The Dig Site"
        val questStage = getQuestStage(player!!, questName)

        when {
            (questStage == 0) ->
                when(stage) {
                    0 -> sendDialogue(player!!, "He seems uninterested in talking.").also { stage = END_DIALOGUE }
                }
            // Start
            (questStage == 1) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Can you help me with the Earth Sciences exams at all?").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "I can if can help me...").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "How can I do that?").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "I have lost me teddy bear. He was my lucky mascot.").also { stage++ }
                    5 -> playerl(FacialExpression.FRIENDLY, "Do you know where you dropped him?").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "Well, I was doing a lot of walking that day... Oh yes, that's right, we were studying ceramics in fact, near the edge of the digsite.").also { stage++ }
                    7 -> npcl(FacialExpression.FRIENDLY, "I found some pottery that seemed to match the design on those large urns.").also { stage++ }
                    8 -> npcl(FacialExpression.FRIENDLY, "I was in the process of checking this out, and when we got back to the centre my lucky mascot had gone!").also { stage++ }
                    9 -> playerl(FacialExpression.FRIENDLY, "Leave it to me. I'll find it.").also { stage++ }
                    10 -> npcl(FacialExpression.FRIENDLY, "Oh great! Thanks!").also { stage = END_DIALOGUE }
                }
            }

            // Without obtaining the teddy bear
            (questStage == 3 && !inInventory(player!!, Items.TEDDY_673)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "How's the study going?").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Very well thanks. Have you found my lucky mascot yet?").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "No, sorry, not yet.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "I'm sure it's just outside the site somewhere...").also { stage = END_DIALOGUE }
                }
            }

            // After obtaining the teddy bear
            (questStage == 3 && inInventory(player!!, Items.TEDDY_673)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Guess what I found.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Hey! My lucky mascot! That's ever so much. Let me help you with those questions now.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "The proper health and safety points are: Leather gloves and boots to be worn at all times; proper tools must be used.").also { stage++ }
                    4 -> playerl(FacialExpression.FRIENDLY, "Great, thanks for your advice.").also { stage = 5 }
                    5 -> {
                        end()
                        removeItem(player!!, Items.TEDDY_673)
                        setAttribute(player!!, "digsite:students", 3)
                    }
                }
            }

            // After obtaining the special cup and before passing the first exam
            (questStage == 3 && player!!.getAttribute("digsite:students", 0) == 3) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How's it going?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on some more exam questions.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Okay, I'll tell you my latest notes...").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "The proper health and safety points are: Proper tools must be used.").also { stage++ }
                    5 -> player("Great, thanks for your advice.").also { stage = END_DIALOGUE }
                }
            }

            // After passing the first exam and before passing the second exam
            (questStage == 6) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How's it going?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on some more exam questions.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Okay, I'll tell you my latest notes...").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Finds handling: Finds must be carefully handled.").also { stage++ }
                    5 -> player("Great, thanks for your advice.").also { stage = END_DIALOGUE }
                }
            }

            // After passing the second exam and before passing the third exam
            (questStage == 7 && player!!.getAttribute("digsite:students", 0) == 6) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "What, you want more help?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Err... Yes please!").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Well... it's going to cost you...").also { stage++ }
                    4 -> playerl(FacialExpression.FRIENDLY, "Oh, well how much?").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "I'll tell you what I would like: a precious stone. I don't find many of them. My favorite are opals; they are beautiful.").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "Just like me! Tee hee hee!").also { stage++ }
                    7 -> playerl(FacialExpression.FRIENDLY, "Err... OK I'll see what I can do, but I'm not sure where I'd get one.").also { stage++ }
                    7 -> npcl(FacialExpression.FRIENDLY, "Well, I have seen people get them from panning occasionally.").also { stage++ }
                    8 -> player("OK, I'll see what I can turn up for you.").also { stage = END_DIALOGUE }
                }
            }

            // Without a cut or uncut opal
            (questStage >= 7 && !inInventory(player!!, Items.UNCUT_OPAL_1625) || !inInventory(
                player!!,
                Items.OPAL_1609
            )) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Oh, hi again. Did you bring me the opal?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I haven't found one yet.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Oh, well, tell me when you do. Remember that they can be found around the site; perhaps try panning the river.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // With a cut or uncut opal
            (questStage == 8 && inInventory(player!!, Items.UNCUT_OPAL_1625) || inInventory(player!!, Items.OPAL_1609)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Would an opal look like this by any chance?").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Wow, great, you've found one. This will look beautiful set in my necklace. Thanks for that; now I'll tell you what I know... ").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Sample preparation: Samples cleaned, and carried only in specimen jars.").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "Great, thanks for your advice.").also { stage = END_DIALOGUE }
                }
            }

            // After giving her a cut or uncut opal before passing the third exam
            (questStage >= 9) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How's it going?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on some more exam questions.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Okay, I'll tell you my latest notes...").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Sample preparation: Samples cleaned, and carried only in specimen jars.").also { stage++ }
                    5 -> player("Great, thanks for your advice.").also { stage = 6 }
                    6 -> {
                        end()
                        setAttribute(player!!, "digsite:students", 9)
                    }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.STUDENT_617)
    }
}
