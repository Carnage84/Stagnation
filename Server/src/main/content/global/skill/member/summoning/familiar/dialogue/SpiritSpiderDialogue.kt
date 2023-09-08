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
 * Represents the Spirit Spider familiar interaction dialogue.
 */
@Initializable
class SpiritSpiderDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritSpiderDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_SPIDER_6841, NPCs.SPIRIT_SPIDER_6842)
    }
}

class SpiritSpiderDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_SPIDER_6841)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Where are we going?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I've not decided yet.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Fine, don't tell me...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh, okay, well, we are going...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Don't want to know now.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Siiiigh...spiders.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Who is that?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Who?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "The two-legs over there.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I can't see who you mean...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Never mind...").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Can you describe them a little better...").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "It doesn't matter now.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Siiiigh...spiders.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "What are you doing?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Nothing that you should concern yourself with.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I see, you don't think I'm smart enough to understand...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "That's not it at all! Look, I was...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Don't wanna know now.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Siiiigh...spiders.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Sigh...").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What is it now?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Nothing really.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh, well that's a relief.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "So, do I get any of those flies?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I don't know, I was saving these for a pet.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I see...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Look, you can have some if you want.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Oh, don't do me any favours.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Look, here, have some!").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Don't want them now.")
                7 -> playerl(FacialExpression.NEUTRAL, "Siiiigh...spiders.").also { stage = END_DIALOGUE }
            }
        }
    }
}