package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Horvik dialogue plugin.
 */
@Initializable
class HorvikDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.HAPPY, "Hello, do you need any help?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("No, thanks. I'm just looking around.", "Do you want to trade?").also { stage++ }
            1 -> when (buttonId) {
                1 -> player(FacialExpression.FRIENDLY, "No, thanks. I'm just looking around.").also { stage++ }
                2 -> {
                    end()
                    openNpcShop(player, NPCs.HORVIK_549)
                }
            }
            2 -> npcl(FacialExpression.HAPPY, "Well, come and see me if you're ever in need of armour!").also { stage++ }
            3 -> end()
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return HorvikDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HORVIK_549)
    }

}