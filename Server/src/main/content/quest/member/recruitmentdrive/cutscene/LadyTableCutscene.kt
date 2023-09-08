package content.quest.member.recruitmentdrive.cutscene

import core.api.runTask
import core.api.sendNPCDialogue
import core.api.setVarbit
import core.game.activity.Cutscene
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.net.packet.PacketRepository
import core.net.packet.context.MinimapStateContext
import core.net.packet.out.MinimapState

/**
 * Cutscene for stage with statues in Recruitment Drive quest.
 */
class LadyTableCutscene(player: Player) : Cutscene(player) {
    override fun setup() {
        setExit(player.location.transform(0, 0, 0))
        loadRegion(9805)
    }

    override fun runStage(stage: Int) {
        when (stage) {
            0 -> {
                PacketRepository.send(MinimapState::class.java, MinimapStateContext(player, 2))
                sendNPCDialogue(player, 2283, "We will now dim the lights and bring the missing statue back in.")
                timedUpdate(1)
            }
            1 -> {
                end {
                    dialogueUpdate(2283, FacialExpression.FRIENDLY, "Please touch the statue you think has been added.")
                }
                runTask(player, 3) {
                    setVarbit(player, 496, 3, true)
                }
            }
        }
    }
}