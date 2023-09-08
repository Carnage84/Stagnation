package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the dialogue plugin used for the Stonemason.
 * Construction supplies.
 * TODO: check content.global.skill.member.construction.npc.StonemasonPlugin
 */
@Initializable
class StonemasonDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL, "Do you want to buy any stone for building?","Or gold leaf?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("Yes please", "No thanks", "What kind of stone do you sell?").also { stage++ }
            1 -> when (buttonId) {
                1 -> player(FacialExpression.FRIENDLY,"Yes please").also { stage = 3 }
                2 -> player(FacialExpression.NEUTRAL,"No thanks.").also { stage = 4 }
                3 -> player(FacialExpression.HALF_ASKING,"What kind of stone do you sell?").also { stage = 5 }
            }
            3 -> {
                end()
                openNpcShop(player, NPCs.STONEMASON_4248)
            }
            4 -> end()
            5 -> npc(FacialExpression.OLD_NORMAL,"A few kinds. Limestone, of course. Here in Keldagrim we're","surrounded by the stuff so I can practically give it away!","You'll be able to build some kinds of furniture out of that.").also { stage++ }
            6 -> npc(FacialExpression.OLD_NORMAL,"Marble is much better, of course, but it costs a lot for me","to get hold of it. You can make some beautiful ","pieces of furniture out of that.").also { stage++ }
            7 -> npc(FacialExpression.OLD_NORMAL,"I also sell gold leaf, which can be used to decorate furniture","made of marble or mahogany.").also { stage++ }
            8 -> npc(FacialExpression.OLD_NORMAL,"Then for the very special furniture I sell these","magic stones. Only the most skilled builders can work them,","but they can create a few amazing pieces of","furniture with them.").also { stage++ }
            9 -> npc(FacialExpression.OLD_NORMAL,"So, do you want to buy anything?").also { stage = 0 }

        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.STONEMASON_4248)
    }
}