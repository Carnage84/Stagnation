package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Sune dialogue plugin.
 */
@Initializable
class SuneDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            START_DIALOGUE -> npcl(FacialExpression.OLD_ANGRY1, "Can you leave me alone please? I'm trying to get a bit of rest.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return SuneDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SUNE_2191)
    }
}