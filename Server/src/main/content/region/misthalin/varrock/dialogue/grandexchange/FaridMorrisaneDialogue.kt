package content.region.misthalin.varrock.dialogue.grandexchange

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.ge.GEGuidePrice
import core.game.ge.GEGuidePrice.GuideType
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Farid Morrisane dialogue plugin.
 */
@Initializable
class FaridMorrisaneDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "Hello, little boy.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.HALF_GUILTY, "I would prefer it if you didn't speak to me in such a", "manner. I'll have you know I'm an accomplished", "merchant.").also { stage++ }
            1 -> options("Calm down, junior.", "Can you help me out with the prices for ores?", "I best go and speak with someone my height.").also { stage++ }
            2 -> when (buttonId) {
                1 -> player(FacialExpression.HALF_GUILTY, "Calm down, junior.").also { stage++ }
                2 -> player(FacialExpression.HALF_GUILTY, "Can you help me out with the prices for ores?").also { stage = 16 }
                3 -> player(FacialExpression.HALF_GUILTY, "I best go and speak with someone more my height.").also { stage = 17 }
            }
            10 -> npc(FacialExpression.HALF_GUILTY, "Don't tell me to calm down! And don't call me 'junior'.").also { stage++ }
            11 -> npc(FacialExpression.HALF_GUILTY, "I'll have you know I am Farid Morrisane, son of Ali", "Morrisane, the worlds greatest merchant!").also { stage++ }
            12 -> player(FacialExpression.HALF_GUILTY, "Then why are you here and not him?").also { stage++ }
            13 -> npc(FacialExpression.HALF_GUILTY, "My dad has given me the responsibility of", "expanding our business here.").also { stage++ }
            14 -> player(FacialExpression.HALF_GUILTY, "And you're up to the task?").also { stage++ }
            15 -> player(FacialExpression.HALF_GUILTY, "What a grown up boy you are! Mummy and", "daddy must be very pleased!").also { stage = END_DIALOGUE }
            16 -> {
                end()
                GEGuidePrice.open(player, GuideType.ORES)
            }
            17 -> npc(FacialExpression.HALF_GUILTY, "Then I shall not stop you, mister. I've too", "much work to do.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return FaridMorrisaneDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FARID_MORRISANE_ORES_6523)
    }

}