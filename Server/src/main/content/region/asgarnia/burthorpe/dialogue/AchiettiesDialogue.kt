package content.region.asgarnia.burthorpe.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Achietties dialogue plugin.
 */
@Initializable
class AchiettiesDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.FRIENDLY,"Greetings. Welcome to the Heroes' Guild.")
        stage = 99
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            99 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return AchiettiesDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ACHIETTIES_796)
    }
}