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
 * Represents the Spirit Terrorbird familiar interaction dialogue.
 */
@Initializable
class SpiritTerrorbirdDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritTerrorbirdDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_TERRORBIRD_6794, NPCs.SPIRIT_TERRORBIRD_6795)
    }
}

class SpiritTerrorbirdDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_TERRORBIRD_6794)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "This is a fun little walk.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Why do I get the feeling you'll change your tune when I start loading you up with items?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I can keep this up for hours.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'm glad, as we still have plenty of time to go.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Are we going to visit a bank soon?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'm not sure, you still have plenty of room for more stuff.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Just don't leave it too long, okay?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Can we go to a bank now?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Just give me a little longer, okay?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "That's what you said last time!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Did I?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Yes!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Well, I mean it this time, promise.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "So...heavy...").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I knew you'd change your tune once you started carrying things.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Can we go bank this stuff now?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Sure. You do look like you're about to collapse.").also { stage = END_DIALOGUE }
            }
        }
    }
}