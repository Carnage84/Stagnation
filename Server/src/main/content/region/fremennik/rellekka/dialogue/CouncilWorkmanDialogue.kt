package content.region.fremennik.rellekka.dialogue

import config.NPCs
import content.global.diary.dialogue.CouncilWorkmanDiaryDialogue
import content.quest.member.thefremenniktrials.dialogue.CouncilWorkerFTDialogue
import core.api.getQuestStage
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Council Workman dialogue plugin.
 */
@Initializable
class CouncilWorkmanDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if(getQuestStage(player, "Fremennik Trials") in 1..99){
            player.dialogueInterpreter.open((CouncilWorkerFTDialogue(1)))
        }
        else {
            end()
            openDialogue(player, CouncilWorkmanDiaryDialogue(), npc)
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return CouncilWorkmanDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.COUNCIL_WORKMAN_1287)
    }

}