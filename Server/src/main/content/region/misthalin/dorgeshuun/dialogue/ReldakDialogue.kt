package content.region.misthalin.dorgeshuun.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Reldak dialogue plugin.
 */
@Initializable
class ReldakDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            START_DIALOGUE -> npcl(FacialExpression.OLD_NORMAL,"Do you want to buy some leather armour?").also { stage++ }
            1 -> options("Yes please.", "No thanks.").also { stage++ }
            2 -> when(buttonId){
                1 -> playerl(FacialExpression.FRIENDLY, "Yes please.").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "No thanks.").also { stage = 4 }
            }
            3 -> openNpcShop(player, NPCs.RELDAK_5780).also { end(); stage = END_DIALOGUE }
            4 -> npcl(FacialExpression.OLD_NORMAL,"Have a good day!").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.RELDAK_5780)
    }
}