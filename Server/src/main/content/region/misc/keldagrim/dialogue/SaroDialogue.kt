package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the dialogue plugin used for the Saro NPC.
 * Quality armour shop in Keldagrim.
 */
@Initializable
class SaroDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"Welcome to my store, human! Are you interested","in buying anything?")
        stage = 1
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            1 -> options("Yes, I'm looking for some armour.", "No thanks.").also { stage++ }
            2 -> when (buttonId) {
                1 -> player(FacialExpression.FRIENDLY,"Yes, I'm looking for some armour.").also { stage++ }
                2 -> player(FacialExpression.NEUTRAL,"No thanks.").also { stage = 4 }
            }
            3 ->{
                end()
                openNpcShop(player, NPCs.SARO_2153)
            }
            4 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SARO_2153)
    }
}