package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Vermundi dialogue plugin.
 */
@Initializable
class VermundiDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_DEFAULT, "Welcome to my clothes stall, can I help you", "with anything?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage) {
            0 -> options("Yes, what clothes do you have in stock?", "No, I'm just browsing.").also { stage++ }
            1 -> when(buttonId) {
                1 -> player("Yes, what clothes do you have in stock?").also { stage = 3 }
                2 -> player("No, I'm just browsing.").also { stage = 6 }
            }
            3 -> npc(FacialExpression.OLD_NORMAL, "Not a lot, I'm afraid, most of what I produce goes to my","sister. Her shop is in Keldagrim-West.").also { stage++ }
            4 -> player("Well, show me what you do have then.").also { stage++ }
            5 -> {
                end()
                openNpcShop(player, NPCs.VERMUNDI_2162)
            }
            6 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.VERMUNDI_2162)
    }
}