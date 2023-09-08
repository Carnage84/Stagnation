package content.quest.member.recruitmentdrive.npc

import config.NPCs
import content.quest.member.recruitmentdrive.dialogue.SirTiffyCashienRDDialogue
import content.quest.member.recruitmentdrive.npc.SirLeyeNPC.Companion.spawnSirLeye
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.system.task.Pulse
import core.game.world.update.flag.context.Graphics

/**
 * Represents the Sir Kuam Ferentse dialogue file for Recruitment Drive quest.
 */
class SirKuamFerentseDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Recruitment Drive"
        val questStage = getQuestStage(player!!, questName)
        val tpGFX = Graphics(110, 150)
        npc = NPC(NPCs.SIR_KUAM_FERENTSE_2284)

        when {
            (questStage == 85) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Ah, ${player!!.username}, You are finally here.").also { player!!.faceLocation(location(2457, 4966, 0)) }.also { stage = 1 }
                    1 -> npcl(FacialExpression.FRIENDLY, "Your task for this room is to defeat Sir Leye. He has been blessed by Saradomin to be undefeatable by any man, so it should be quite the challenge for you.").also { stage = 2 }
                    2 -> npcl(FacialExpression.FRIENDLY, "If you are having problems, remember; A true warrior uses his wits as much as his brawn. Fight smarter, not harder.").also { stage = 3 }
                    3 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 90)
                        spawnSirLeye(player!!)
                    }
                }
            }

            (questStage == 95) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Excellent work! You have passed all five of the required tests! Please accept my congratulations!").also { player!!.faceLocation(location(2457, 4966, 0)) }.also { stage = 1 }
                    1 -> {
                        end()
                        player!!.lock()
                        setQuestStage(player!!, "Recruitment Drive", 99)
                        submitWorldPulse(object : Pulse(1) {
                            var counter = 0
                            override fun pulse(): Boolean {
                                when (counter++) {
                                    1 -> player!!.graphics(tpGFX)
                                    2 -> teleport(player!!, location(2996, 3375, 0))
                                    4 -> openDialogue(player!!, SirTiffyCashienRDDialogue())
                                    6 -> player!!.unlock().also { return true }
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