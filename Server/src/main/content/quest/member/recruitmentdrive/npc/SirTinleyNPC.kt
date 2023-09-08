package content.quest.member.recruitmentdrive.npc

import config.NPCs
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.system.task.Pulse
import core.game.world.map.Location
import core.tools.END_DIALOGUE

/**
 * Represents the Sir Tinley dialogue file for Recruitment Drive quest.
 */
class SirTinleyDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {

        val questName = "Recruitment Drive"
        val questStage = getQuestStage(player!!, questName)
        npc = NPC(NPCs.SIR_TINLEY_2286)

        when {

            (questStage == 45) -> {
                when (stage) {
                    0 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Ah, ${player!!.username}, you have arrived. Speak to me to begin your task."
                    ).also { player!!.faceLocation(location(2476, 4958, 0)) }.also { stage = 1 }
                    1 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 46)
                        stage = END_DIALOGUE
                    }
                }
            }

            (questStage == 46) -> {
                when (stage) {
                    0 -> npcl("Ah, ${player!!.username}, I have but one clue for you to pass this room's puzzle: 'Patience'.").also {
                        stage = 1
                    }
                    1 -> {
                        end()
                        stage = END_DIALOGUE
                        player!!.locks.lockMovement(10)
                        setQuestStage(player!!, "Recruitment Drive", 50)
                        submitWorldPulse(object : Pulse() {
                            var counter = 0
                            override fun pulse(): Boolean {
                                when (counter++) {
                                    9 -> if (player!!.locks.isMovementLocked) {
                                        setQuestStage(player!!, "Recruitment Drive", 55)
                                        npcl(
                                            FacialExpression.FRIENDLY,
                                            "Excellent work, Player. Please step through the portal to meet your next challenge."
                                        )
                                        stage = END_DIALOGUE
                                    } else {
                                        runTask(player!!, 1) {
                                            teleport(player!!, Location.create(2996, 3375, 0))
                                            npcl(
                                                FacialExpression.FRIENDLY,
                                                "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future."
                                            )
                                            stage = END_DIALOGUE
                                        }.also { return true }
                                    }
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