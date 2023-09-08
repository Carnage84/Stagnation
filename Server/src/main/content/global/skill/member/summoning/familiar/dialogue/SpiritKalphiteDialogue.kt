package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.hasAnItem
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
 * Represents the Spirit Kalphite familiar interaction dialogue.
 */
@Initializable
class SpiritKalphiteDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritKalphiteDialogueFile())
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_KALPHITE_6994, NPCs.SPIRIT_KALPHITE_6995)
    }
}

class SpiritKalphiteDialogueFile : DialogueFile() {

    companion object {
        val KerisIds = intArrayOf(
            Items.KERIS_10581,
            Items.KERISP_10582,
            Items.KERISP_PLUS_10583,
            Items.KERISP_PLUS_PLUS_10584
        )
    }

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        val hasKerisItem = hasAnItem(player!!, *KerisIds).container == player!!.inventory
        npc = NPC(NPCs.SPIRIT_KALPHITE_6994)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "This activity is not optimal for us.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, you'll just have to put up with it for now.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "We would not have to 'put up' with this in the hive.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "We are growing infuriated. What is our goal?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, I haven't quite decided yet.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "There is no indecision in the hive.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Or a sense of humour or patience, it seems.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "We find this to be wasteful of our time.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Maybe I find you wasteful...").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "We would not face this form of abuse in the hive.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "We grow tired of your antics, biped.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What antics? I'm just getting on with my day.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "In an inefficient way. In the hive, you would be replaced.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "In the hive this, in the hive that...").also { stage = END_DIALOGUE }
            }
        }

        if (hasKerisItem) {
            when (stage) {
                0 -> playerl(FacialExpression.ASKING, "How dare I what?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "That weapon offends us!").also { stage++ }
                2 -> playerl(FacialExpression.HALF_ASKING, "What weapon?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh...").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Awkward.").also { stage = END_DIALOGUE }
            }
        }
    }
}