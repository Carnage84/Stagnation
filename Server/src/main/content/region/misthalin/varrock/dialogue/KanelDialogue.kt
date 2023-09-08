package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Kanel dialogue plugin.
 */
@Initializable
class KanelDialogue(player: Player? = null) : DialoguePlugin(player) {

     /*
        Info: One of the youngest of Gertrude's four children.
        Location: -
     */
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "Hello there.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.CHILD_SUSPICIOUS, "Hel-lo?").also { stage++ }
            1 -> player(FacialExpression.HALF_GUILTY, "Right. Goodbye.").also { stage++ }
            2 -> npc(FacialExpression.CHILD_NORMAL, "Bye?").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return KanelDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.KANEL_784)
    }

}