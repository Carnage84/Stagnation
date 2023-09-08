package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Vigr dialogue plugin.
 */
@Initializable
class VigrDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"What do you want, human?")
        stage = 0
        return true
    }
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> player(FacialExpression.HALF_ASKING,"Ehm, anything on offer?").also { stage++ }
            1 -> npc(FacialExpression.OLD_NORMAL,"Can you wield a warhammer?", "If not, then go away.").also { stage++ }
            2 -> options("Of course I can.", "I can, but I won't.").also { stage++ }
            3 -> when (buttonId) {
                1 -> player(FacialExpression.FRIENDLY,"Of course I can.").also { stage++ }
                2 -> player(FacialExpression.NEUTRAL,"I can, but I won't.").also { stage = 5 }
            }
            4 -> openNpcShop(player, NPCs.VIGR_2151).also { stage++ }
            5 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.VIGR_2151)
    }
}