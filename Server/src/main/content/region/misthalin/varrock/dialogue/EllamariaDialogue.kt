package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Ellamaria dialogue plugin.
 */
@Initializable
class EllamariaDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "What's going on here? I see a lot of farming patches", "with nothing growing in them.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.HALF_GUILTY, "No, one has just had them installed. One has had the", "most marvellous idea to bring renewed happiness to", "one's own deatest husband.").also { stage++ }
            1 -> player(FacialExpression.HALF_GUILTY, "One? I'm not sure I understand you-").also { stage++ }
            2 -> npc(FacialExpression.HALF_GUILTY, "Oh dear - the common classes, oh how they fill one with", "intolerable levels of exasperation, I swear to the most", "true.").also { stage++ }
            3 -> player(FacialExpression.HALF_GUILTY, "Ri-ight ... if you say so, my lady. I'll be off then, if you", "don't mind.").also { stage++ }
            4 -> npc(FacialExpression.HALF_GUILTY, "Yes, be off with you, before I call the guards.").also { stage++ }
            5 -> player(FacialExpression.HALF_GUILTY, "No problem, sorry to have bothered you.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return EllamariaDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ELLAMARIA_2581)
    }

}