package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.api.openInterface
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Reinald dialogue plugin.
 */
@Initializable
class ReinaldDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.OLD_DEFAULT,"Hello, human! Would you like to browse", "my little shop of bracelets?").also { stage++ }
            1 -> options("Yes, please!", "No, thanks.").also { stage++ }
            2 -> when (buttonId) {
                1 -> end().also { openInterface(player, 593) }
                2 -> end()
            }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return ReinaldDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.REINALD_2194)
    }

}