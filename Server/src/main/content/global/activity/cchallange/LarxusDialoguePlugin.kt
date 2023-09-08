package content.global.activity.cchallange

import config.NPCs
import core.api.face
import core.api.findNPC
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the dialogue plugin used for the larxus npc.
 */
@Initializable
class LarxusDialoguePlugin(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        npc = NPC(NPCs.LARXUS_3050)
        val challengeStart: Boolean = false
        if (challengeStart) {
            openDialogue(player, LarxusDialogue(false), npc)
        } else {
            when (stage) {
                START_DIALOGUE -> {
                    face(findNPC(NPCs.LARXUS_3050)!!, player!!, 1)
                    npcl(FacialExpression.NEUTRAL, "Is there something I can help you with?").also { stage = 1 }
                }
                1 -> options("I was given a challenge, what now?", "What is this place?", "Nothing thanks.").also { stage = 2 }
                2 -> when (buttonId) {
                    1 -> playerl(FacialExpression.HALF_ASKING, "I was given a challenge, what now?").also { stage = 3 }
                    2 -> playerl(FacialExpression.HALF_ASKING,"What is this place?").also { stage = 4 }
                    3 -> playerl(FacialExpression.HALF_ASKING,"Nothing thanks.").also { stage = END_DIALOGUE }
                }
                3 -> npcl(FacialExpression.NEUTRAL,"Well pass it here and we'll get you started.").also { stage = END_DIALOGUE }
                4 -> npcl(FacialExpression.NEUTRAL,"This is the champions' arena, the champions of various, races use it to duel those they deem worthy of the, honour.").also { stage = END_DIALOGUE }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.LARXUS_3050)
    }
}