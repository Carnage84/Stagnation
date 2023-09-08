package content.region.fremennik.rellekka.dialogue

import config.NPCs
import core.api.isQuestComplete
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Fishmonger Rellekka dialogue plugin.
 */
@Initializable
class FishmongerRellekkaDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (!isQuestComplete(player, "Fremennik Trials")) {
            npc(FacialExpression.ANNOYED, "I don't sell to outerlanders.").also { stage = END_DIALOGUE }
        } else {
            npcl(FacialExpression.FRIENDLY,"Hello there, ${player.getAttribute("fremennikname","fremmyname")}. Looking for fresh fish?").also { stage = 0 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> end().also { openNpcShop(player, NPCs.FISH_MONGER_1315) }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return FishmongerRellekkaDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FISH_MONGER_1315)
    }
}