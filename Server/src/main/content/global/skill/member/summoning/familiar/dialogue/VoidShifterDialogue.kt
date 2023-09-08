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
 * Represents the Void Shifter familiar interaction dialogue.
 */
@Initializable
class VoidShifterDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, VoidShifterDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.VOID_SHIFTER_7367, NPCs.VOID_SHIFTER_7368, NPCs.VOID_SHIFTER_7369)
    }
}

class VoidShifterDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.VOID_SHIFTER_7367)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "What a splendid day, " + (if (player!!.isMale) "sir" else "madam") + "!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Yes, it is!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "It could only be marginally improved, perhaps, by tea and biscuits.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "What a marvellous idea!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I'm sorry to bother you, but could you assist me briefly?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I suppose so.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I was wondering, briefly, if perchance you might care to dance?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Dance? With a pest?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Well, you see, I'm dreadfully out of practice and now I can barely leap, let alone teleport.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "I'm not going to help you remember how to destroy the world!").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "What a beastly world we live in where one gentleman/lady will not aid a pest in need...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "How do you do?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Okay, I suppose.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Marvellous, simply marvellous!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Lets go and see to those cads and bounders!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Which 'cads and bounders' did you mean, exactly?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Why, the ones with no honour, of course.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I don't think he knows what pests do...").also { stage = END_DIALOGUE }
            }
        }
    }
}