package content.region.asgarnia.rimmington.dialogue

import config.NPCs
import core.api.sendMessage
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.START_DIALOGUE

/**
 * Represents the Hops dialogue plugin.
 */
@Initializable
class HopsDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun newInstance(player: Player?): DialoguePlugin {
        return HopsDialogue(player)
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            START_DIALOGUE -> {
                sendMessage(player, "He isn't in a fit state to talk.")
                end()
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HOPS_340)
    }

}