package content.quest.member.recruitmentdrive.npc

import config.NPCs
import core.api.getQuestStage
import core.api.location
import core.api.setAttribute
import core.api.setQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Sir Ren Itchood dialogue file for Recruitment Drive quest.
 */
class SirRenItchoodDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Recruitment Drive"
        val questStage = getQuestStage(player!!, questName)
        npc = NPC(NPCs.SIR_REN_ITCHOOD_2287)

        when {

            (questStage in 20..34) -> {
                when (stage) {
                    0 -> npc(
                        FacialExpression.HALF_GUILTY,
                        "Greetings friend, and welcome here,",
                        "you'll find my puzzle not so clear.",
                        "Hidden amongst my words, it's true,",
                        "the password for the door as a clue."
                    ).also {
                        player!!.faceLocation(location(2443, 4956, 0))
                    }.also { setQuestStage(player!!, "Recruitment Drive", 30) }.also { stage = 1 }
                    1 -> options("Can I have the clue for the door?", "Can I have a different clue?").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> playerl(FacialExpression.ASKING, "Can I have the clue for the door?").also { stage = 4 }
                        2 -> playerl(FacialExpression.ASKING, "I don't get the riddle...").also { stage = 3 }
                    }
                    3 -> playerl(FacialExpression.HALF_GUILTY, "Can I have a different one?").also { stage = 4 }
                    4 -> {
                        val puzzleNumber = if (RandomFunction.getRandom(6) == 1) {
                            npc(
                                FacialExpression.HALF_GUILTY,
                                "Feel the aching of your mind",
                                "In puzzlement, confused.",
                                "See the clue hidden behind",
                                "His words, as you perused."
                            ).also { stage = END_DIALOGUE }
                            1
                        } else if (RandomFunction.getRandom(6) == 2) {
                            npc(
                                FacialExpression.HALF_GUILTY,
                                "Better than me, you'll not find",
                                "In rhyming and in puzzles.",
                                "This clue so clear will tax your mind",
                                "Entirely as it confuzzles!"
                            ).also { stage = END_DIALOGUE }
                            2
                        } else if (RandomFunction.getRandom(6) == 3) {
                            npc(
                                FacialExpression.HALF_GUILTY,
                                "Look closely at the words i speak;",
                                "And study closely every part.",
                                "See for yourself the word you seek",
                                "Trapped for you if you're smart."
                            ).also { stage = END_DIALOGUE }
                            3
                        } else if (RandomFunction.getRandom(6) == 4) {
                            npc(
                                FacialExpression.HALF_GUILTY,
                                "More than words, i have not for you",
                                "Except the things i say today.",
                                "Aware are you, this is a clue?",
                                "Take note of what i say!"
                            ).also { stage = END_DIALOGUE }
                            4
                        } else if (RandomFunction.getRandom(6) == 5) {
                            npc(
                                FacialExpression.HALF_GUILTY,
                                "Rare it is that you will see",
                                "A puzzle such as this!",
                                "In many ways it tickles me",
                                "Now, watching you hit and miss!"
                            ).also { stage = END_DIALOGUE }
                            5
                        } else {
                            npc(
                                FacialExpression.HALF_GUILTY,
                                "Twice it is now, i have stated",
                                "In a rhyme, what is the pass.",
                                "Maybe my words obfuscated",
                                "Entirely beyond your class."
                            ).also { stage = END_DIALOGUE }
                            6
                        }
                        when {
                            (puzzleNumber) == 1 -> setAttribute(player!!, "ComboLock", 1)// FISH
                            (puzzleNumber) == 2 -> setAttribute(player!!, "ComboLock", 2)// BITE
                            (puzzleNumber) == 3 -> setAttribute(player!!, "ComboLock", 3)// LAST
                            (puzzleNumber) == 4 -> setAttribute(player!!, "ComboLock", 4)// MEAT
                            (puzzleNumber) == 5 -> setAttribute(player!!, "ComboLock", 5)// RAIN
                            else -> setAttribute(player!!, "ComboLock", 6)// TIME
                        }
                    }
                }
            }
        }
    }
}