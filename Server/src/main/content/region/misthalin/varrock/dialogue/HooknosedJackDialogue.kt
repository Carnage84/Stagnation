package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Hooknosed Jack dialogue plugin.
 */
@Initializable
class HooknosedJackDialogue(player: Player? = null) : DialoguePlugin(player) {

     /*
        Info: Jack Tylner cat owners. He is located just outside of
        Varrock's Dancing Donkey Inn along with his cat Pox.
        Location: 3265,3403
     */
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "Hello.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.HALF_GUILTY, "What?").also { stage++ }
            1 -> npc(FacialExpression.HALF_GUILTY, "Actually I've got no time for this. I don't want to talk to", "you.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return HooknosedJackDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HOOKNOSED_JACK_2948)
    }

}