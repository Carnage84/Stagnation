package content.region.kandarin.feldiphills.ooglog.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Tyke dialogue plugin.
 */
@Initializable
class TykeDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            START_DIALOGUE -> npcl(FacialExpression.CHILD_NORMAL, "Hi, human.").also { stage++ }
            1 -> playerl(FacialExpression.HALF_ASKING, "Hi, ogre! How are you today, little ogre?").also { stage++ }
            2 -> npcl(FacialExpression.CHILD_NORMAL, "Hey, human. What did you bring me?").also { stage++ }
            3 -> playerl(FacialExpression.THINKING, "Hmm, let me think carefully about this. Oh, yes. I remember, now! Absolutely nothing.").also { stage++ }
            4 -> npcl(FacialExpression.OLD_SAD, "Aw, shucks.").also { stage = END_DIALOGUE }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.TYKE_7068)
    }
}