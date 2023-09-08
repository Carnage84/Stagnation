package content.region.desert.alkharid.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Dommik dialogue plugin.
 */
@Initializable
class DommikDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun newInstance(player: Player?): DialoguePlugin {
        return DommikDialogue(player)
    }

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        npcl(FacialExpression.HAPPY, "Would you like to buy some Crafting equipment?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("No, thanks, I've got all the Crafting equipment I need.", "Let's see what you've got, then.").also { stage = 1 }
            1 -> when (buttonId) {
                1 -> playerl(FacialExpression.FRIENDLY, "No, thanks, I've got all the Crafting equipment I need.").also { stage = 2 }
                2 -> {
                    end()
                    openNpcShop(player, NPCs.DOMMIK_545)
                }
            }
            2 -> npcl(FacialExpression.FRIENDLY, "Okay. Fare well on your travels.").also { stage = 3 }
            3 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DOMMIK_545)
    }
}
