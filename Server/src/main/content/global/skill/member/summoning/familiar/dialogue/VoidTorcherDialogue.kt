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
 * Represents the Void Torcher familiar interaction dialogue.
 */
@Initializable
class VoidTorcherDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, VoidTorcherDialogueFile())
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.VOID_TORCHER_7351, NPCs.VOID_TORCHER_7352)
    }

}

class VoidTorcherDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.VOID_TORCHER_7351)
        if (randomConversation == 1) {
            when (stage) {
                0 -> playerl(FacialExpression.HALF_ASKING, "You okay there, spinner?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "I not spinner!").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Sorry, splatter?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "I not splatter either!").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "No, wait, I meant defiler.").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "I torcher!").also { stage++ }
                6 -> playerl(FacialExpression.NEUTRAL, "Hehe, I know. I was just messing with you.").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "Grr. Don't be such a pest.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "'T' is for torcher, that's good enough for me... 'T' is for torcher, I'm happy you can see.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "You're just a bit weird, aren't you?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Burn, baby, burn! Torcher inferno!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "*Wibble*").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "So hungry... must devour...").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "*Gulp* Er, yeah, I'll find you something to eat in a minute.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Is flesh-bag scared of torcher?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No, no. I, er, always look like this... honest.").also { stage = END_DIALOGUE }
            }
        }
    }
}