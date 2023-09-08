package content.quest.member.holygrail.dialogue.knightsoftheroundtable

import config.NPCs
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Sir Bedivere dialogue file for Holy Grail quest.
 */
class SirBedivereHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SIR_BEDIVERE_242)
        when (stage) {
            START_DIALOGUE -> npcl(FacialExpression.NEUTRAL, "May I help you? You are looking for the Grail now adventurer?").also { stage++ }
            1 -> playerl(FacialExpression.NEUTRAL, "Absolutely.").also { stage++ }
            2 -> npcl(FacialExpression.NEUTRAL, "The best of luck to you! Make the name of Camelot proud, and bring it back to us.").also { stage++ }
            3 -> npcl(FacialExpression.NEUTRAL, "You look in good spirits.").also { stage++ }
            4 -> npcl(FacialExpression.NEUTRAL, "Yup").also { stage++ }
            5 -> npcl(FacialExpression.NEUTRAL, "That Holy Grail...").also { stage++ }
            6 -> npcl(FacialExpression.NEUTRAL, "...").also { stage++ }
            7 -> npcl(FacialExpression.NEUTRAL, "I am glad you got it! You ought to be in good spirits").also { stage++ }
            8 -> npcl(FacialExpression.NEUTRAL, "I appreciate that").also { stage = END_DIALOGUE }
        }
    }
}
