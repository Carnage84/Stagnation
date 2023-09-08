package content.region.misthalin.wiztower.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Dr Onglewip dialogue plugin.
 */
@Initializable
class DrOnglewipDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.ASKING, "Do you live here too?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.OLD_NORMAL, "Oh no, I come from Gnome Stronghold. I've been", "sent here by King Narnode to learn about human", "magics.").also { stage++ }
            1 -> player(FacialExpression.ASKING, "So where's this Gnome Stronghold?").also { stage++ }
            2 -> npc(FacialExpression.OLD_NORMAL, "It's in the North West of the continent - a long way", "away. You should visit us there some time. The food's", "great, and the company's delightful.").also { stage++ }
            3 -> player(FacialExpression.FRIENDLY, "I'll try and make time for it. Sounds like a nice place.").also { stage++ }
            4 -> npc(FacialExpression.OLD_NORMAL, "Well, it's full of gnomes. How much nicer could it be?").also { stage++ }
            5 -> end()
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return DrOnglewipDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.PROFESSOR_ONGLEWIP_4585)
    }

}