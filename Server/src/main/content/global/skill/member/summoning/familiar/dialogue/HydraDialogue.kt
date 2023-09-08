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
 * Represents the Hydra familiar interaction dialogue.
 */
@Initializable
class HydraDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, HydraDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HYDRA_6811, NPCs.HYDRA_6812)
    }
}

class HydraDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.HYDRA_6811)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Raaaspraaasp? (Isn't it hard to get things done with just one head?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Not really!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Raaasp raaaaap raaaasp? (Well I suppose you work with what you got, right?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL," Raaaaaasp raaaasp raaaasp. (At least he doesn't have someone whittering in their ear all the time.)").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Raaaaaaasp! (Quiet, you!)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Raaaasp raaaasp! (Man, I feel good!)").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL," Raaasp ssssss raaaasp. (That's easy for you to say.)").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "What's up?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL," Raaa.... (well...)").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Raaaaasp sss rassssp. (Don't pay any attention, they are just feeling whiny.)").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "But they're you, aren't they?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Raaaasp raasp rasssp! (Don't remind me!)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Rassssp rasssssp! (You know, two heads are better than one!)").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL," Raaaasp rassssp sssssp.... (Unless you're the one doing all the heavy thinking....)").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "I think I'll stick to one for now, thanks.").also { stage = END_DIALOGUE }

            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL," Raaaaaaasp. (Siiiigh.)").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL,"Raasp raasp raaaaasp? (What's up this time?)").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Can I help?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL,"Rasssp ssssssp? raaaaasp raaaasp. (Do you mind? This is a private conversation.)").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Well, excu-u-use me.").also { stage = END_DIALOGUE }
            }
        }
    }
}