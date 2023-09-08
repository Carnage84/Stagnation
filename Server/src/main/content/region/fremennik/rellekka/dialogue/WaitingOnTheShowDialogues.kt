package content.region.fremennik.rellekka.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the dialogue plugin used for the:
 * Fridgeir, Ospak, Styrmir NPC.
 */
@Initializable
class WaitingOnTheShowDialogues(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.ANNOYED, "Shhh! I'm waiting for the show!").also { stage = END_DIALOGUE }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return WaitingOnTheShowDialogues(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FRIDGEIR_1277, NPCs.OSPAK_1274, NPCs.STYRMIR_1275)
    }
}
