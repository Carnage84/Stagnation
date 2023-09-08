package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Philop dialogue plugin.
 */
@Initializable
class PhilopDialogue(player: Player? = null) : DialoguePlugin(player) {

     /*
        Info: One of four children belonging to Gertrude
        Location: 3150,3406
     */
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        playerl(FacialExpression.HALF_GUILTY, "Hello, what's your name?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.CHILD_NORMAL, "Gwwrr!").also { stage++ }
            1 -> playerl(FacialExpression.HALF_GUILTY, "Err, hello there. What's that you have there?").also { stage++ }
            2 -> npc(FacialExpression.CHILD_NORMAL, "Gwwwrrr! Dwa-gon Gwwwwrrrr!").also { stage++ }
            3 -> npc(FacialExpression.CHILD_NORMAL, "Enjoy playing with your dragon, then.").also { stage++ }
            4 -> npc(FacialExpression.CHILD_NORMAL, "Gwwwrrr!").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return PhilopDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.PHILOP_782)
    }

}