package content.region.asgarnia.burthorpe.dialogue

import config.NPCs
import content.quest.member.trollstronghold.dialogue.DenulthTSDialogue
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Denulth dialogue plugin.
 */
@Initializable
class DenulthDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player!!, DenulthTSDialogue())
        return true
    }
    override fun newInstance(player: Player?): DialoguePlugin {
        return DenulthDialogue(player)
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DENULTH_1060)
    }
}