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
 * Represents the Abyssal Parasite familiar interaction dialogue.
 */
@Initializable
class AbyssalParasiteDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, AbyssalParasiteDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ABYSSAL_PARASITE_6818, NPCs.ABYSSAL_PARASITE_6819)
    }
}

class AbyssalParasiteDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.ABYSSAL_PARASITE_6818)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Ongk n'hd?").also { stage++ }
                1 -> playerl(FacialExpression.HALF_WORRIED, "Oh, I'm not feeling so well.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Uge f't es?").also { stage++ }
                3 -> playerl(FacialExpression.SAD, "Please have mercy!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "F'tp ohl't?").also { stage++ }
                5 -> playerl(FacialExpression.AFRAID, "I shouldn't have eaten that kebab. Please stop talking!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Noslr'rh...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What's the matter?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Kdso Seo...").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Could you...could you mime what the problem is?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Yiao itl!").also { stage++ }
                5 -> playerl(FacialExpression.HALF_ASKING, "I want to help it but, aside from the language gap its noises make me retch!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Ace'e e ur'y!").also { stage++ }
                1 -> playerl(FacialExpression.HALF_WORRIED, "I think I'm going to be sick... The noises! Oh, the terrifying noises.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Tdsa tukk!").also { stage++ }
                1 -> playerl(FacialExpression.AFRAID, "Oh, the noises again.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Tdsa tukk!").also { stage++ }
                1 -> playerl(FacialExpression.AFRAID, "Oh, the noises again.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Hem s'htee?").also { stage = END_DIALOGUE }
            }
        }
    }
}