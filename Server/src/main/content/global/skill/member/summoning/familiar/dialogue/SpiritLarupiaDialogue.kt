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
 * Represents the Spirit Larupia familiar interaction dialogue.
 */
@Initializable
class SpiritLarupiaDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritLarupiaDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_LARUPIA_7337, NPCs.SPIRIT_LARUPIA_7338)
    }
}

class SpiritLarupiaDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_LARUPIA_7337)
        if (randomConversation == 1) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Kitty cat!").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "What is your wish master?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Have you ever thought about doing something other than hunting and serving me?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "You mean, like stand-up comedy, master?").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Umm...yes, like that.").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "No, master.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Hello friend!").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "'Friend', master? I do not understand this word.").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Friends are people, or animals, who like one another. I think we are friends.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "Ah, I think I understand friends, master.").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Great!").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "A friend is someone who looks tasty, but you don't eat.").also { stage++ }
                6 -> playerl(FacialExpression.NEUTRAL, "!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "What are we doing today, master?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I don't know, what do you want to do?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I desire only to hunt and to serve my master.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Err...great! I guess I'll decide then.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Master, do you ever worry that I might eat you?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "No, of course not! We're pals.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "That is good, master.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Should I?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Of course not, master.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Oh. Good.").also { stage = END_DIALOGUE }
            }
        }
    }
}