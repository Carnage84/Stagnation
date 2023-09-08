package content.region.island.miscellania.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Greengrocer dialogue plugin.
 */
@Initializable
class GreengrocerMiscDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.FRIENDLY,"Welcome, Sir.", "I sell only the finest and freshest vegetables!").also { stage = 0 }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage){
            0 -> end().also { openNpcShop(player, NPCs.GREENGROCER_1394) }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return GreengrocerMiscDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.GREENGROCER_1394)
    }
}