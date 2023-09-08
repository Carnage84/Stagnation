package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Guidors Wife dialogue plugin.
 */
@Initializable
class GuidorsWifeDialogue(player: Player? = null) : DialoguePlugin(player) {

     /*
        Info: Wife of Elena's former teacher, Guidor.
        Location: 3281,3382
     */
    override fun open(vararg args: Any): Boolean {
        if (args.size == 2) {
            npc(FacialExpression.HALF_GUILTY, "Please leave my husband alone. He's very sick, and I don't", "want anyone bothering him.").also { stage = END_DIALOGUE }
            return true
        }
        player(FacialExpression.HALF_GUILTY, "Hello.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.HALF_GUILTY, "Oh hello, I can't chat now. I have to keep an eye on my", "husband. He's very ill!").also { stage++ }
            1 -> playerl(FacialExpression.HALF_GUILTY, "I'm sorry to hear that!").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return GuidorsWifeDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.GUIDORS_WIFE_342)
    }

}