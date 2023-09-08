package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.api.sendDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Giant Ent familiar interaction dialogue.
 */
@Initializable
class GiantEntDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, GiantEntDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.GIANT_ENT_6800, NPCs.GIANT_ENT_6801)
    }
}

class GiantEntDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.GIANT_ENT_6800)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Creeeeeeeeeeeak..... (I.....)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "Yes?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, ".....").also { stage++ }
                3 -> sendDialogue(player!!, "After a while you realise that the ent has finished speaking for the moment.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Creak..... Creaaaaaaaaak..... (Am.....)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "Yes?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, ".....").also { stage++ }
                3 -> sendDialogue(player!!, "After a while you realise that the ent has finished speaking for the moment.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Grooooooooan..... (Feeling.....)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "Yes? We almost have a full sentence now - the suspense is killing me!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, ".....").also { stage++ }
                3 -> sendDialogue(player!!, "After a while you realise that the ent has finished speaking for the moment.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Groooooooooan..... (Sleepy.....)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "I'm not sure if that was worth all the waiting.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Grooooooan.....creeeeeeeak (Restful.....)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "I'm not sure if that was worth all the waiting.").also { stage = END_DIALOGUE }
            }
        }


        if (randomConversation == 6) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Grrrrooooooooooooooan..... (Achey.....)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "I'm not sure if that was worth all the waiting.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 7) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Creeeeeeeegroooooooan..... (Goood.....)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "I'm not sure if that was worth all the waiting.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 8) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Creeeeeeeeeeeeeaaaaaak..... (Tired.....)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "I'm not sure if that was worth all the waiting.").also { stage = END_DIALOGUE }
            }
        }
    }
}