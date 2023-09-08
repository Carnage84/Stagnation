package content.region.asgarnia.falador.dialogue

import config.NPCs.SIR_VYVIN_605
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Sir Vyvin dialogue plugin.
 */
@Initializable
class SirVyvinDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player("Hello.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> {
                npc(FacialExpression.HALF_GUILTY, "Greetings traveller.")
                stage++
            }
            1 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return SirVyvinDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(SIR_VYVIN_605)
    }
}