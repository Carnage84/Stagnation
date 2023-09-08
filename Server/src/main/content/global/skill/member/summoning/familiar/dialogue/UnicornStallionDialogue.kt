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
 * Represents the Unicorn Stallion familiar interaction dialogue.
 */
@Initializable
class UnicornStallionDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, UnicornStallionDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.UNICORN_STALLION_6822, NPCs.UNICORN_STALLION_6823)
    }
}

class UnicornStallionDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.UNICORN_STALLION_6822)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Neigh neigh neighneigh snort? (Isn't everything so awesomely wonderful?)").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "Err...yes?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Whicker whicker snuffle. (I can see you're not tuning in, Player name.)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No, no, I'm completely at one with...you know...everything.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Whicker! (Cosmic.)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Whicker whicker. Neigh, neigh, whinny. (I feel so, like, enlightened. Let's meditate and enhance our auras.)").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "I can't do that! I barely even know you.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Whicker... (Bipeds...)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Whinny whinny whinny. (I think I'm astrally projecting.)").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Okay... Hang on. Seeing as I summoned you here, wouldn't that mean you are physically projecting instead?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Whicker whicker whicker. (You're, like, no fun at all, man.)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Whinny, neigh! (Oh, happy day!)").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Happy day? Is that some sort of holiday or something?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Snuggle whicker (Man, you're totally, like, uncosmic, ${player!!.username}.)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Whicker snort! Whinny whinny whinny. (You're hurt! Let me try to heal you.)").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Yes, please do!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Snuffle whicker whicker neigh neigh... (Okay, we'll begin with acupuncture and some reiki, then I'll get my crystals...)").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "Or you could use some sort of magic...like the other unicorns...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Whicker whinny whinny neigh. (Yes, but I believe in alternative medicine.)").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY, "Riiight. Don't worry about it, then; I'll be fine.").also { stage = END_DIALOGUE }
            }
        }
    }
}