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
 * Represents the Fur Trader dialogue plugin.
 */
@Initializable
class FurTraderDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
       if (!isQuestComplete(player, "Fremennik Trials")) {
           npc(FacialExpression.ANNOYED, "I don't sell to outerlanders.").also { stage = END_DIALOGUE }
       } else {
           npcl(FacialExpression.FRIENDLY,"Welcome back, ${player.getAttribute("fremennikname","fremmyname")}. Have you seen the furs I have today?").also { stage = 10 }
       }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            10 -> end().also { openNpcShop(player, NPCs.FUR_TRADER_1316) }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return FurTraderDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FUR_TRADER_1316)
    }
}
