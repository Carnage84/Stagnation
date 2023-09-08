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
 * Represents the Bloated Leech familiar interaction dialogue.
 */
@Initializable
class BloatedLeechDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, BloatedLeechDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BLOATED_LEECH_6843, NPCs.BLOATED_LEECH_6844)
    }
}

class BloatedLeechDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.BLOATED_LEECH_6843)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I'm afraid it's going to have to come off, ${player!!.username}.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What is?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Never mind. Trust me, I'm almost a doctor.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I think I'll get a second opinion.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"You're in a critical condition.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Is it terminal?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Not yet. Let me get a better look and I'll see what I can do about it.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "There are two ways to take that...and I think I'll err on the side of caution.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Let's get a look at that brain of yours.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What? My brains stay inside my head, thanks.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"That's ok, I can just drill a hole.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "How about you don't and pretend you did?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I think we're going to need to operate.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I think we can skip that for now.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Who's the doctor here?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Not you.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"I may not be a doctor, but I'm keen. Does that not count?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "In most other fields, yes; in medicine, no.").also { stage = END_DIALOGUE }
            }
        }
    }
}