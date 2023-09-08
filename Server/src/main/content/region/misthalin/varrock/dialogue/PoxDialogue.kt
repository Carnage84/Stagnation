package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Pox dialogue plugin.
 */
@Initializable
class PoxDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "Hi cat!")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.CHILD_FRIENDLY, "Miaooow!").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return PoxDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.POX_2943)
    }

}