package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Scavvo dialogue plugin.
 */
@Initializable
class ScavvoDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.HALF_GUILTY, "'Ello matey! D'ya wanna buy some exiting new toys?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> player(FacialExpression.HALF_GUILTY, "Let's have a look then.").also { stage++ }
            1 -> {
                end()
                openNpcShop(player, NPCs.SCAVVO_537)
            }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return ScavvoDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SCAVVO_537)
    }

}