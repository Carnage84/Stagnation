package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.api.openNpcShop
import core.api.sendDialogueOptions
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Lowe dialogue plugin.
 */
@Initializable
class LoweDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.HAPPY, "Welcome to Lowe's Archery Emporium. Do you want", "to see my wares?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("Yes, please.", "No, I prefer to bash things close up.").also { stage++ }
            1 -> when (buttonId) {
                1 -> playerl(FacialExpression.FRIENDLY, "Yes, please.").also { stage = 2 }
                2 -> player(FacialExpression.EVIL_LAUGH, "No, I prefer to bash things close up.").also { stage = 3 }
            }
            2 -> {
                end()
                openNpcShop(player, NPCs.LOWE_550)
            }
            3 -> npc(FacialExpression.ANNOYED, "Humph, philistine.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return LoweDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.LOWE_550)
    }

}