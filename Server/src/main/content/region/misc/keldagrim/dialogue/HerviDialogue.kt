package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Hervi dialogue plugin.
 */
@Initializable
class HerviDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"Greetings, human... can I interest you in","some fine gems?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("Show me the wares!", "No thanks.").also { stage++ }
            1 -> when (buttonId) {
                1 -> player("Show me the wares!").also { stage = 3 }
                2 -> player("No thanks.").also { stage = 100 }
            }
            3 -> {
                end()
                openNpcShop(player, NPCs.HERVI_2157)
            }
            100 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HERVI_2157)
    }
}