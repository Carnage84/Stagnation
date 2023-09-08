package content.quest.member.recruitmentdrive.dialogue

import config.NPCs
import core.api.getQuestStage
import core.api.location
import core.api.setQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC

/**
 * Represents the Miss Cheevers dialogue file for Recruitment Drive quest.
 */
class MissCheeversRDDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Recruitment Drive"
        val questStage = getQuestStage(player!!, questName)
        npc = NPC(NPCs.MISS_CHEEVERS_2288)

        when {

            (questStage == 55) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Greetings, ${player!!.username}. Welcome to my challenge.").also { player!!.faceLocation(location(2469, 4941, 0)) }.also { stage = 1 }
                    1 -> npcl(FacialExpression.FRIENDLY, "All you need to do is leave from the opposite door to where you came in by.").also { stage = 2 }
                    2 -> npcl(FacialExpression.FRIENDLY, "I will warn you that this is more complicated than it may at first appear.").also { stage = 3 }
                    3 -> npcl(FacialExpression.FRIENDLY, "I should also warn you that there are limited supplies of the items in this room, so think carefully before using them, you may find yourself stuck and have to leave to start again!").also { stage = 4 }
                    4 -> npcl(FacialExpression.FRIENDLY, "Best of luck!").also { stage = 5 }
                    5 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 60)
                    }
                }
            }

            (questStage == 60) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Can you give me any help?").also { player!!.faceLocation(location(2469, 4941, 0)) }.also { stage = 1 }
                    1 -> npcl(FacialExpression.FRIENDLY, "No, I am sorry, but that is forbidden by our rules.").also { stage = 2 }
                    2 -> npcl(FacialExpression.FRIENDLY, "If you are having a particularly tough time of it, I suggest you leave and come back later when you are in a more receptive frame of mind.").also { stage = 3 }
                    3 -> npcl(FacialExpression.FRIENDLY, "Sometimes a break from concentration will yield fresh insight. Our aim is to test you, but not to the point of frustration!").also { stage = 4 }
                    4 -> playerl(FacialExpression.FRIENDLY, "Okay, thanks!").also { stage = 5 }
                    5 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 65)
                    }
                }
            }

            (questStage == 65) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Please... I am REALLY stuck... Isn't there ANYTHING you can do to help me...?").also { player!!.faceLocation(location(2469, 4941, 0)) }.also { stage = 1 }
                    1 -> npcl(FacialExpression.FRIENDLY, "Well... Look, I really shouldn't say anything about this room, but...").also { stage = 2 }
                    2 -> npcl(FacialExpression.FRIENDLY, "When I was attempting to join the Temple Knights I myself had to do this puzzle myself.").also { stage = 3 }
                    3 -> npcl(FacialExpression.FRIENDLY, "It was slightly different, but the idea behind it was the same, and I left the notes I had made while doing it hidden in one of the bookcases.").also { stage = 4 }
                    4 -> npcl(FacialExpression.FRIENDLY, "If you look carefully you may find them, and they may be of some use to you.").also { stage = 5 }
                    5 -> npcl(FacialExpression.FRIENDLY, "I really can't be any more help than that I'm afraid, it is more than my job's worth to have given you the help I already have.").also { stage = 6 }
                    6 -> playerl(FacialExpression.FRIENDLY, "Okay, thanks a lot, you've been very helpful!").also { stage = 7 }
                    7 -> npcl(FacialExpression.FRIENDLY, "Best of luck with the test ${player!!.username}. I hope your application is successful.").also { stage = 8 }
                    8 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 70)
                    }
                }
            }
        }
    }
}