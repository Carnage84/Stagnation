package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Tombar dialogue plugin.
 */
@Initializable
class TombarDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_ASKING,"Say, aren't you a bit tall for a dwarf?")
        stage = 1
        return true
    }
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            1 -> npc(FacialExpression.OLD_NORMAL,"Was there anything in particular you wanted?").also { stage++ }
            2 -> options("I'd like a quest please.", "No, I just like talking to strangers.").also { stage++ }
            3 -> when (buttonId) {
                1 -> player("I'd like a quest please.").also { stage++ }
                2 -> player("No, I just like talking to strangers.").also { stage = 5 }

            }
            4 -> npc(FacialExpression.OLD_NORMAL,"I have nothing to do for you, I'm afraid.","Ask around town, though, there are always people","who need some work done around here.").also { stage = 6 }
            5 -> npc(FacialExpression.OLD_NORMAL,"Well I don't.").also { stage++ }
            6 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.TOMBAR_2199)
    }
}