package content.quest.member.holygrail.dialogue.knightsoftheroundtable

import config.NPCs
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Sir Gawain dialogue file for Holy Grail quest.
 */
class SirGawainHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SIR_GAWAIN_240)
        when (stage) {
            START_DIALOGUE -> npcl(FacialExpression.NEUTRAL, "Good day to you ${if (player!!.isMale) "Sir" else "Madam"}!").also { stage++ }
            1 -> npcl(FacialExpression.NEUTRAL, "I seek the Grail in the name of Camelot!").also { stage++ }
            2 -> npcl(FacialExpression.NEUTRAL, "The Grail? That is truly a noble quest indeed. None but Galahad have come close.").also { stage++ }
            3 -> npcl(FacialExpression.NEUTRAL, "Galahad? Who is he?").also { stage++ }
            4 -> npcl(FacialExpression.NEUTRAL, "He used to be one of the Knights of the Round Table, but he mysteriously disappeared many years ago.").also { stage++ }
            5 -> npcl(FacialExpression.NEUTRAL, "Why would he quit being a Knight?").also { stage++ }
            6 -> npcl(FacialExpression.NEUTRAL, "That is a good question. I'm afraid I don't have the answer.").also { stage++ }
            7 -> npcl(FacialExpression.NEUTRAL, "Getting the Holy Grail was a major victory for us, thank you.").also { stage++ }
            8 -> npcl(FacialExpression.NEUTRAL, "You are welcome.").also { stage = END_DIALOGUE }
        }
    }
}
