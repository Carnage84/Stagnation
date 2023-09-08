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
 * Represents the Abyssal Lurker familiar interaction dialogue.
 */
@Initializable
class AbyssalLurkerDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, AbyssalLurkerDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ABYSSAL_LURKER_6820, NPCs.ABYSSAL_LURKER_6821)
    }
}

class AbyssalLurkerDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.ABYSSAL_LURKER_6820)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Djrej gf'ig sgshe...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What? Are we in danger, or something?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
                when (stage) {
                    0 -> npcl(FacialExpression.OLD_NORMAL, "To poshi v'kaa!").also { stage++ }
                    1 -> playerl(FacialExpression.HALF_ASKING, "What? Is that even a language?").also { stage = END_DIALOGUE }
                }
            }

        if (randomConversation == 3) {
            when (stage) {
                        0 -> npcl(FacialExpression.OLD_NORMAL, "G-harrve shelmie?").also { stage++ }
                        1 -> playerl(FacialExpression.HALF_ASKING, "What? Do you want something?").also { stage = END_DIALOGUE }
                    }
                }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Jehifk i'ekfh skjd.").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What? Is there somebody down an old well, or something?").also { stage = END_DIALOGUE }
            }
        }
    }
}