package content.region.misc.keldagrim.dialogue.workers

import config.NPCs
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Factory Worker 2 dialogue plugin.
 */
@Initializable
class FactoryWorkerDialogue2(player: Player? = null) : core.game.dialogue.DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "Are you okay?").also { stage++ }
            1 -> npcl(FacialExpression.OLD_ANGRY1, "Don't I look okay?").also { stage++ }
            2 -> playerl(FacialExpression.FRIENDLY, "If you were any shorter you wouldn't exist.").also { stage++ }
            3 -> npcl(FacialExpression.OLD_ANGRY1, "Very funny, human.").also {
                stage = END_DIALOGUE
            }
        }
        return true
    }

    override fun newInstance(player: Player?): core.game.dialogue.DialoguePlugin {
        return FactoryWorkerDialogue2(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FACTORY_WORKER_2173)
    }
}