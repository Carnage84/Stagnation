package content.quest.member.recruitmentdrive

import config.NPCs
import content.quest.member.recruitmentdrive.dialogue.LadyTableRDDialogue
import content.quest.member.recruitmentdrive.dialogue.MissCheeversRDDialogue
import content.quest.member.recruitmentdrive.npc.SirKuamFerentseDialogue
import content.quest.member.recruitmentdrive.npc.SirSpishyusDialogue
import content.quest.member.recruitmentdrive.npc.SirTinleyDialogue
import content.quest.member.recruitmentdrive.util.RDUtils
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.global.action.DoorActionHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC
import core.game.system.task.Pulse
import core.tools.END_DIALOGUE

/**
 * Listener for interaction with doors between stages.
 */
class RDStagesListener : InteractionListener {
    override fun defineListeners() {
        class ExitPortalDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                npc = NPC(NPCs.SIR_TIFFY_CASHIEN_2290)
                when (stage) {
                    0 -> sendDialogue(player!!, "Quit the training grounds?").also { stage = 1 }
                    1 -> options("YES.", "NO.").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> {
                            end()
                            player!!.lock()
                            submitWorldPulse(object : Pulse(0) {
                                var counter = 0
                                override fun pulse(): Boolean {
                                    when (counter++) {
                                        0 -> player!!.inventory.clear().also { player!!.equipment.clear() }
                                        1 -> player!!.graphics(RDUtils.TP_OUT_GFX)
                                        2 -> teleport(player!!, location(2996, 3375, 0))
                                        4 -> npcl(
                                            FacialExpression.FRIENDLY,
                                            "Oh, jolly bad luck, what? Not quite the brainbox you thought you were, eh?"
                                        ).also {
                                            player!!.faceLocation(
                                                location(2997, 3373, 0)
                                            )
                                        }
                                        6 -> npcl(
                                            FacialExpression.FRIENDLY,
                                            "Well, never mind! You have an open invitation to join our organisation, so when you're feeling a little smarter, come back and talk to me again."
                                        )
                                        7 -> player!!.unlock().also { return true }
                                    }
                                    return false
                                }
                            })
                        }
                        2 -> playerl(FacialExpression.FRIENDLY, "No, I've changed my mind.").also {
                            stage = END_DIALOGUE
                        }
                    }
                }
            }
        }

        on(RDUtils.CLOSED_DOOR, IntType.SCENERY, "open") { player, _ ->
            sendMessage(player, "Nothing interesting happens.")
            return@on false
        }

        on(RDUtils.EXITPORTAL, IntType.SCENERY, "use") { player, _ ->
            openDialogue(player, ExitPortalDialogue())
            return@on true
        }

        on(RDUtils.DOOR_0, IntType.SCENERY, "open") { player, node ->
            if (player.questRepository.getStage("Recruitment Drive") in 30..34) {
                openInterface(player, 285)
            } else if (player.questRepository.getStage("Recruitment Drive") >= 35) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.pulseManager.run(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> player.lock()
                            1 -> teleport(player, location(2490, 4972, 0))
                            2 -> forceWalk(player, location(2489, 4972, 0), "SMART")
                            4 -> openDialogue(player, SirSpishyusDialogue())
                            5 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            }
            return@on true
        }

        on(RDUtils.DOOR_1, IntType.SCENERY, "open") { player, node ->
            if (player.questRepository.getStage("Recruitment Drive") >= 45) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.lock()
                player.pulseManager.run(object : Pulse(1) {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            1 -> teleport(player, location(2460, 4979, 0))
                            2 -> forceWalk(player, location(2459, 4979, 0), "SMART")
                            3 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else if (player.questRepository.getStage("Recruitment Drive") == 40) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.lock()
                player.pulseManager.run(object : Pulse(1) {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> setVarbit(player, 496, 3, true)
                            1 -> teleport(player, location(2460, 4979, 0))
                            2 -> forceWalk(player, location(2459, 4979, 0), "SMART")
                            4 -> openDialogue(player, LadyTableRDDialogue()).also { return true }
                        }
                        return false
                    }
                })
            }
            return@on true
        }

        on(RDUtils.DOOR_2, IntType.SCENERY, "open") { player, node ->
            if (player.questRepository.getStage("Recruitment Drive") in 55..100) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.lock()
                player.pulseManager.run(object : Pulse(1) {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            1 -> teleport(player, location(2471, 4956, 0))
                            2 -> forceWalk(player, location(2472, 4956, 0), "SMART")
                            3 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else if (player.questRepository.getStage("Recruitment Drive") == 45) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.pulseManager.run(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> player.lock()
                            1 -> teleport(player, location(2471, 4956, 0))
                            2 -> forceWalk(player, location(2472, 4956, 0), "SMART")
                            4 -> openDialogue(player, SirTinleyDialogue())
                            5 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            }
            return@on true
        }

        on(RDUtils.DOOR_3, IntType.SCENERY, "open") { player, node ->
            if (player.questRepository.getStage("Recruitment Drive") in 90..95) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.lock()
                player.pulseManager.run(object : Pulse(1) {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            1 -> teleport(player, location(2455, 4964, 0))
                            2 -> forceWalk(player, location(2456, 4964, 0), "SMART")
                            3 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else if (player.questRepository.getStage("Recruitment Drive") >= 85) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.pulseManager.run(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> player.lock()
                            1 -> teleport(player, location(2455, 4964, 0))
                            2 -> forceWalk(player, location(2456, 4964, 0), "SMART")
                            4 -> openDialogue(player, SirKuamFerentseDialogue())
                            5 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            }
            return@on true
        }

        on(RDUtils.DOOR_4, IntType.SCENERY, "open") { player, node ->
            if (player.questRepository.getStage("Recruitment Drive") in 75..100) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.lock()
                player.pulseManager.run(object : Pulse(1) {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            1 -> teleport(player, location(2467, 4940, 0))
                            2 -> forceWalk(player, location(2468, 4940, 0), "SMART")
                            3 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else if (player.questRepository.getStage("Recruitment Drive") >= 55) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                player.pulseManager.run(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> player.lock()
                            1 -> teleport(player, location(2467, 4940, 0))
                            2 -> forceWalk(player, location(2468, 4940, 0), "SMART")
                            4 -> openDialogue(player, MissCheeversRDDialogue())
                            5 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            }
            return@on true
        }
    }
}