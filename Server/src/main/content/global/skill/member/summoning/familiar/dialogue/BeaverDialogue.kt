package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.anyInInventory
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Beaver familiar interaction dialogue.
 */
@Initializable
class BeaverDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        val logs = intArrayOf(Items.LOGS_1511, Items.OAK_LOGS_1521, Items.WILLOW_LOGS_1519, Items.MAPLE_LOGS_1517, Items.YEW_LOGS_1515, Items.MAGIC_LOGS_1513)
        val randomTopic = RandomFunction.random(1,5)
        when(randomTopic) {
            1 -> when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Vot are you doing 'ere when we could be logging and building mighty dams, alors?").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Why would I want to build a dam again?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Why vouldn't you want to build a dam again?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I can't argue with that logic.").also { stage = END_DIALOGUE }
            }

            2 -> when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Pardonnez-moi - you call yourself a lumberjack?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "No").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Carry on zen.").also { stage = END_DIALOGUE }
            }

            3 -> when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Paul Bunyan 'as nothing on moi!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Except several feet in height, a better beard, and opposable thumbs.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "What was zat?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Nothing.").also { stage = END_DIALOGUE }
            }

            4 -> when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Zis is a fine day make some lumber.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "That it is!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "So why are you talking to moi? Get chopping!").also { stage = END_DIALOGUE }
            }

            5 -> if (anyInInventory(player, *logs)) {
                when (stage) {
                    0 -> npcl(FacialExpression.OLD_NORMAL, "'Ere, you 'ave ze logs, now form zem into a mighty dam!").also { stage++ }
                    1 -> playerl(FacialExpression.NEUTRAL, "Well, I was thinking of burning, selling, or fletching them.").also { stage++ }
                    2 -> npcl(FacialExpression.OLD_NORMAL, "Sacre bleu! Such a waste.").also { 
                    end()
                    stage = END_DIALOGUE }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BEAVER_6808)
    }

}