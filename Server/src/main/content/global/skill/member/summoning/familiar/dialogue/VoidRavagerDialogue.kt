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
 * Represents the Void Ravager familiar interaction dialogue.
 */
@Initializable
class VoidRavagerDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, VoidRavagerDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.VOID_RAVAGER_7370, NPCs.VOID_RAVAGER_7371)
    }
}

class VoidRavagerDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.VOID_RAVAGER_7370)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You look delicious!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Don't make me dismiss you!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Take me to the rift!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'm not taking you there! Goodness knows what you'd get up to.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I promise not to destroy your world...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "If only I could believe you...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Pardon me. Could I trouble you for a moment?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Yeah, sure.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Oh, it's just a trifling thing. Mmm, trifle...you look like trifle...So, will you help?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Fire away!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Oh, just be honest. I just want a second opinion...Is this me? Mmm trifle...").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Huh?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Oh! The claws! The whiskers! The single, yellow eye! Oh! Is it me? Is it truly me?").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Erm...why yes...of course. It definitely reflects the inner you.").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL, "Oh, I knew it! You've been an absolute delight. An angel delight! And everyone said it was just a phase!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "How do you bear life without ravaging?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "It's not always easy.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I could show you how to ravage, if you like...").also { stage = END_DIALOGUE }
            }
        }
    }
}