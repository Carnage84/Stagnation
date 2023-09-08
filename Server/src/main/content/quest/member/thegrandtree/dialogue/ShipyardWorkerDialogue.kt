package content.quest.member.thegrandtree.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents Shipyard Worker dialogue in the grand tree quest.
 */
@Initializable
class ShipyardWorkerDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
            1 -> npcl(FacialExpression.FRIENDLY, "Hello matey!").also { stage++ }
            2 -> playerl(FacialExpression.ASKING, "How are you?").also { stage++ }
            3 -> npcl(FacialExpression.NEUTRAL, "Tired!").also { stage++ }
            4 -> playerl(FacialExpression.FRIENDLY, "You shouldn't work so hard!").also { stage = END_DIALOGUE }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SHIPYARD_WORKER_675, NPCs.SHIPYARD_WORKER_38, NPCs.SHIPYARD_WORKER_39)
    }
}

