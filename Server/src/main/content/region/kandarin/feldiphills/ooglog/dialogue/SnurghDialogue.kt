package content.region.kandarin.feldiphills.ooglog.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.START_DIALOGUE

/**
 * Represents the Snurgh dialogue plugin.
 */
@Initializable
class SnurghDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
            when (stage) {
                START_DIALOGUE -> npcl(FacialExpression.CHILD_NORMAL, "Outta de way, human. Dis place not open yet!").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "That's not very friendly.").also { stage++ }
                2 -> npcl(FacialExpression.CHILD_NORMAL, "Me said OUTTA DE WAY! You no can sleep here!").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY,"Alright, alright! Keep your hat on! I never said I wanted to.").also { stage++ }
                4 -> end()
            }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SNURGH_7057)
    }
}