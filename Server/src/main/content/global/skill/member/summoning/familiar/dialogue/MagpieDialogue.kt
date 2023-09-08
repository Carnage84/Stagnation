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
 * Represents the Magpie familiar interaction dialogue.
 */
@Initializable
class MagpieDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, MagpieDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MAGPIE_6824)
    }
}

class MagpieDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.MAGPIE_6824)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"There's nowt gannin on here...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Err...sure? Maybe?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "It seems upset, but what is it saying?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Howway, let's gaan see what's happenin' in toon.").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What? I can't understand what you're saying.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Are we gaan oot soon? I'm up fer a good walk me.").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "That...that was just noise. What does that mean?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Ye' been plowdin' i' the claarts aall day.").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What? That made no sense.").also { stage = END_DIALOGUE }
            }
        }
    }
}