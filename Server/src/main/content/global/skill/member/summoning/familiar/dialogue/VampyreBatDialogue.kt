package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.inBorders
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
 * Represents the Vampyre Bat familiar interaction dialogue.
 */
@Initializable
class VampyreBatDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, VampyreBatDialogueFile())
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.VAMPIRE_BAT_6835, NPCs.VAMPIRE_BAT_6836)
    }
}

class VampyreBatDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.VAMPIRE_BAT_6835)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You're vasting all that blood, can I have some?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "No!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Ven are you going to feed me?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well for a start, I'm not giving you any of my blood.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Ven can I eat somethink?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Just as soon as I find something to attack.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Ven can I eat somethink?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Just as soon as I find something to attack.").also { stage = END_DIALOGUE }
            }
        }

        if (inBorders(player!!, 3139, 9535, 3306, 9657)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Ze creatures ov ze dark; vat vonderful music zey make.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Riiight.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"I like it down here. Let's stay and eat moths!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I think I'll pass, thanks.").also { stage = END_DIALOGUE }
            }
        }
    }
}