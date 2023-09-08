package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Macaw familiar dialogue.
 */

class MacawDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.MACAW_6851)
        if (randomConversation == 1) {
            when (stage) {
                0 ->npcl(FacialExpression.OLD_NORMAL, "Awk! Gimme the rum! Gimme the rum!").also { stage++ }
                1 ->playerl(FacialExpression.NEUTRAL, "I don't think you'll like the stuff. Besides, I think there is a law about feeding birds alcohol.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 ->npcl(FacialExpression.OLD_NORMAL, "Awk! I'm a pirate! Awk! Yo, ho ho!").also { stage++ }
                1 ->playerl(FacialExpression.NEUTRAL, "I'd best not keep you around any customs officers!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 ->npcl(FacialExpression.OLD_NORMAL, "Awk! Caw! Shiver me timbers!").also { stage++ }
                1 ->playerl(FacialExpression.HALF_ASKING, "I wonder where you picked up all these phrases?").also { stage = END_DIALOGUE }
            }
        }
    }
}