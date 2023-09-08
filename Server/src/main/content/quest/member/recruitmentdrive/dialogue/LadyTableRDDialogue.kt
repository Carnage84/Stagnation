package content.quest.member.recruitmentdrive.dialogue

import config.NPCs
import content.quest.member.recruitmentdrive.cutscene.LadyTableCutscene
import core.api.getQuestStage
import core.api.location
import core.api.submitWorldPulse
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.system.task.Pulse

/**
 * Represents the Lady Table dialogue file for Recruitment Drive quest.
 */
class LadyTableRDDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Recruitment Drive"
        val questStage = getQuestStage(player!!, questName)
        npc = NPC(NPCs.LADY_TABLE_2283)

        when {

            (questStage == 40) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Welcome, ${player!!.username}. This room will test your observation skills.").also { player!!.faceLocation(location(2458, 4980, 0)) }.also { stage = 1 }
                    1 -> npcl(FacialExpression.FRIENDLY, "Study the statues closely. There is one missing statue in this room.").also { stage = 2 }
                    2 -> npcl(FacialExpression.FRIENDLY, "We will also mix the order up a little, to make things interesting for you!").also { stage = 3 }
                    3 -> npcl(FacialExpression.FRIENDLY, "You have 10 seconds to memorise the statues... starting NOW!").also { player!!.unlock() }.also { stage = 4 }
                    4 -> {
                        end()
                        submitWorldPulse(object : Pulse() {
                            var counter = 0
                            override fun pulse(): Boolean {
                                when (counter++) {
                                    0 -> if (player!!.locks.isMovementLocked) {
                                        return false
                                    }
                                    5 -> player!!.lock()
                                    12 -> LadyTableCutscene(player!!).start().also { return true }
                                }
                                return false
                            }
                        })
                    }
                }
            }
        }
    }
}