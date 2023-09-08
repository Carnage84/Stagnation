package content.quest.member.recruitmentdrive.npc

import config.NPCs
import content.quest.member.recruitmentdrive.util.RDUtils
import core.api.getQuestStage
import core.api.location
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC

/**
 * Represents the Sir Spishyus dialogue file for Recruitment Drive quest.
 */
class SirSpishyusDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Recruitment Drive"
        val questStage = getQuestStage(player!!, questName)
        npc = NPC(NPCs.SIR_SPISHYUS_2282)

        when {

            (questStage == 35) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Ah, welcome ${player!!.username}.").also { player!!.faceLocation(location(2488, 4973, 0)) }.also { stage = 1 }
                    1 -> playerl(FacialExpression.FRIENDLY, "Hello there." + " What am I supposed to be doing in this room?").also { stage = 2 }
                    2 -> npcl(FacialExpression.FRIENDLY, "Well, your task is to take this fox, this chicken and this bag of grain across that bridge there to the other side of the room.").also { stage = 3 }
                    3 -> npcl(FacialExpression.FRIENDLY, "When you have done that, your task is complete.").also { stage = 4 }
                    4 -> playerl(FacialExpression.FRIENDLY, "Is that it?").also { stage = 5 }
                    5 -> npcl(FacialExpression.FRIENDLY, "Well, it is not quite as simple as that may sound.").also { stage = 6 }
                    6 -> npcl(FacialExpression.FRIENDLY, "Firstly, you may only carry one of the objects across the room at a time, for the bridge is old and fragile.").also { stage = 7 }
                    7 -> npcl(FacialExpression.FRIENDLY, "Secondly, the fox wants to eat the chicken, and the chicken wants to eat the grain. Should you ever leave the fox unattended with the chicken, or the grain unattended with the chicken, then").also { stage = 8 }
                    8 -> npcl(FacialExpression.FRIENDLY, "one of them will be eaten, and you will be unable to complete the test.").also { stage = 9 }
                    9 -> playerl(FacialExpression.FRIENDLY, "Okay, I'll see what I can do.").also { stage = 10 }
                    10 -> {
                        end()
                        RDUtils.puzzleRoom(player!!)
                        // setQuestStage(player!!, "Recruitment Drive", 25)
                    }
                }
            }
        }
    }
}