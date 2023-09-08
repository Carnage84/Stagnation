package content.region.misthalin.lumbridge.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Jimmy The Chisel dialogue plugin.
 */
@Initializable
class JimmyTheChiselDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.FRIENDLY,"Hello mate!").also { stage = 99 }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            99 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return JimmyTheChiselDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.JIMMY_THE_CHISEL_1718)
    }
}
