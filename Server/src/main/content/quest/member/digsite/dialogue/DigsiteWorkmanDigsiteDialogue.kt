package content.quest.member.digsite.dialogue

import config.NPCs
import core.api.getQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents Digsite Workman dialogue for The Dig Site quest.
 */
@Initializable
class DigsiteWorkmanDigsiteDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questName = "The Dig Site"
        val questStage = getQuestStage(player!!, questName)

        when {
            // Start
            (questStage == 0) -> {
                when (stage) {
                    0 -> player("Hello!").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Why hello there! What can I do you for?").also { stage++ }
                    2 -> options("What do you do here?", "I'm not sure.", "Can I dig around here?").also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "What do you do here?").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I'm not sure.").also { stage = 7 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Can I dig around here?").also { stage = 8 }
                    }

                    4 -> npcl(FacialExpression.FRIENDLY, "Well, my job involved digging for finds, cleaning them and transporting them for identification.").also { stage++ }
                    5 -> playerl(FacialExpression.FRIENDLY, "Sounds interesting.").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "I find it very interesting and very rewarding. So glad to see you're taking an interest in the digsite. Hope to see you out here digging sometime!").also {
                        stage = END_DIALOGUE
                    }

                    7 -> npcl(FacialExpression.FRIENDLY, "Well, let me know when you are and I'll do my very best to help you!").also {
                        stage = END_DIALOGUE
                    }

                    8 -> npcl(FacialExpression.FRIENDLY, "You can only use a site you have the appropriate exam level for.").also { stage++ }
                    9 -> playerl(FacialExpression.FRIENDLY, "Appropriate exam level?").also { stage++ }
                    10 -> npcl(FacialExpression.FRIENDLY, "Oh yes, you need to have been trained in the various techniques before you can be allowed to dig for artefacts.").also { stage++ }
                    11 -> playerl(FacialExpression.FRIENDLY, "Ah yes; I understand.").also { stage = END_DIALOGUE }
                }
            }

            // After passing the third exam
            (questStage == 1) -> {
                when (stage) {
                    0 -> player("Hello!").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Why hello there! What can I do you for?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Can I dig around here?").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "You can only use a site you have the appropriate exam level for.").also { stage++ }
                    4 -> playerl(FacialExpression.FRIENDLY, "I am already skilled in digging.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Really? What qualifications do you have?").also { stage++ }
                    6 -> playerl(FacialExpression.FRIENDLY, "I have passed all three Earth Sciences exams!").also { stage++ }
                    7 -> npcl(FacialExpression.FRIENDLY, "Well done! That means you can dig anywhere on this site, but you'll need to impress the examiner to use the winches.").also { stage++ }
                    8 -> playerl(FacialExpression.FRIENDLY, "Ok!").also { stage = END_DIALOGUE }
                }
            }

            // Attempting to operate the winch before giving the invitation letter to the digsite workman
            (questStage == 2) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Sorry, this area is private. The only way you'll get to use these is by impressing the archeological expert up at the centre.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Find something worthwhile and he might let you use the winches. Until then, get lost!").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Talk to Digsite Workman with the invitation letter in inventory
            (questStage == 3) -> {
                when (stage) {
                    0 -> player("Hello!").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Good day; what can I do for you?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Here, have a look at this...").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "I give permission... blah de blah... etc. Okay, that's all in order; you may use the mineshaft now. I'll hang onto this scroll, shall I?").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After quest
            (questStage == 100) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Oh wow! You're the archaeologist who found that altar in the mines aren't you!").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Um, yes.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "So glad to meet you! Well done!").also { stage = END_DIALOGUE }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DIGSITE_WORKMAN_613)
    }
}