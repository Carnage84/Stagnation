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
 * Represents the Ibis familiar interaction dialogue.
 */
@Initializable
class IbisDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, IbisDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.IBIS_6991)
    }
}

class IbisDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.IBIS_6991)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I'm the best fisherman ever!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Where is your skillcape to prove it, then?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"At home...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I'll bet it is.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I like to fish!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I know!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I want to go fiiiish.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "We can't be fishing all the time you know.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Hey, where are we?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What do you mean?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"I just noticed we weren't fishing.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well, we can't fish all the time.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Can I look after those sharks for you?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I don't know. Would you eat them?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Yes! Ooops...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I think I'll hang onto them myself for now.").also { stage = END_DIALOGUE }
            }
        }
    }
}