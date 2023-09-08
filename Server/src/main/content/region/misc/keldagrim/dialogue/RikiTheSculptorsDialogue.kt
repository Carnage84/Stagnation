package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Riki The Sculptors dialogue plugin.
 */
@Initializable
class RikiTheSculptorsDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.FRIENDLY, " I'm glad I don't have to talk to you anymore!")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.OLD_DEFAULT, "Hrm.").also { stage++ }
            1 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(
            NPCs.RIKI_THE_SCULPTORS_MODEL_2143,
            NPCs.RIKI_THE_SCULPTORS_MODEL_2144,
            NPCs.RIKI_THE_SCULPTORS_MODEL_2145,
            NPCs.RIKI_THE_SCULPTORS_MODEL_2146,
            NPCs.RIKI_THE_SCULPTORS_MODEL_2147,
            NPCs.RIKI_THE_SCULPTORS_MODEL_2148,
            NPCs.RIKI_THE_SCULPTORS_MODEL_2149,
            NPCs.RIKI_THE_SCULPTORS_MODEL_2150
        )
    }
}