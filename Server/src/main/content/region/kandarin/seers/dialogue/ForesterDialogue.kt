package content.region.kandarin.seers.dialogue

import config.NPCs
import core.api.sendMessage
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Forester dialogue plugin.
 */
@Initializable
class ForesterDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        sendMessage(player, "He doesn't seem interested in talking to you.")
        stage = 1
        return true
    }
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            1 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FORESTER_231)
    }
}

