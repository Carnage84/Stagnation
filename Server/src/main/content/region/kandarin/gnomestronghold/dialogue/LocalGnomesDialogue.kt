package content.region.kandarin.gnomestronghold.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Local gnomes dialogue plugin.
 */
@Initializable
class LocalGnomesDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        playerl(FacialExpression.FRIENDLY,"Hello little man.").also { stage++ }
        return true
    }

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when(stage) {
            1 -> npcl(FacialExpression.FRIENDLY,"Little man stronger than big man. Hee hee, lardi dee, lardi da.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.LOCAL_GNOME_484)
    }
}