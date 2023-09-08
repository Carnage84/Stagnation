package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Ambassador Fernook dialogue plugin.
 */
@Initializable
class AmbassadorFernookDialogue(player: Player? = null) : DialoguePlugin(player) {

     /*
        Info: is the ambassador of King Narnode Shareen of the Gnome Stronghold to King Roald of Misthalin.
        He can be found on the 1st floor of Varrock Palace in Varrock.
        Location: -
     */
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "Hello Ambassador. Are you here visiting King Roald?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.HALF_GUILTY, "Well, in theory, but he always seems to be busy.").also { stage++ }
            1 -> player(FacialExpression.HALF_GUILTY, "You don't seem that upset by that, though...").also { stage++ }
            2 -> npc(FacialExpression.HALF_GUILTY, "Oh no, I like travelling, and if you become a diplomat", "patience is a vital skill.").also { stage++ }
            3 -> player(FacialExpression.HALF_GUILTY, "I'll try to remember that.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return AmbassadorFernookDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.AMBASSADOR_FERRNOOK_4582)
    }

}