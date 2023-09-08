package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.tools.END_DIALOGUE

/**
 * Represents the Surok Magis dialogues plugin.
 */
class SurokMagisDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "Excuse me?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.HALF_GUILTY, "What do you want? ...Oh, wait. I know! You're", "probably just like all the others, aren't you? After some", "fancy spell or potion from me, I bet!").also { stage++ }
            1 -> player(FacialExpression.HALF_GUILTY, "No! at least, I don't think so. What sort of spells", "do you have?").also { stage++ }
            2 -> npc(FacialExpression.HALF_GUILTY, "Hah! I knew it! I expect you want my Aphro-Dizzy-", "Yak spell! Want someone to fall madly in love with you,", "eh?").also { stage++ }
            3 -> player(FacialExpression.HALF_GUILTY, "That spell sounds very interesting, but I didn't mean to", "disturb you!").also { stage++ }
            4 -> npc(FacialExpression.HALF_GUILTY, "Well, I see that you do have some manners. I'm glad", "to see that you use them.").also { stage++ }
            5 -> npc(FacialExpression.HALF_GUILTY, "Now, if it's all the same, I am very bust at the", "moment. Come back another time", "please and thank you.").also { stage++ }
            6 -> player(FacialExpression.HALF_GUILTY, "Yes, of course!").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return SurokMagisDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(
            NPCs.SUROK_MAGIS_5834,
            NPCs.SUROK_MAGIS_5835,
            NPCs.SUROK_MAGIS_7002,
            NPCs.SUROK_MAGIS_7136
        )
    }

}