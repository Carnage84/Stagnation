package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Dreadfowl familiar interaction dialogue.
 */
@Initializable
class DreadfowlDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, DreadfowlDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DREADFOWL_6825, NPCs.DREADFOWL_6826)
    }
}

class DreadfowlDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.DREADFOWL_6825)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Attack! Fight! Annihilate!").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "It always worries me when you're so happy saying that.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Can it be fightin' time, please?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Look I'll find something for you to fight, just give me a second.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I want to fight something.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'll find something for you in a minute - just be patient.").also { stage = END_DIALOGUE }
            }
        }
    }
}