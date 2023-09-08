package content.region.asgarnia.dialogue

import config.NPCs
import content.global.skill.free.crafting.TanningProduct
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Tanner dialogue plugin.
 */
@Initializable
class TannerDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?) : Boolean {
        npc = args[0] as NPC
        player.inventory.refresh()
        val items = player.inventory.toArray()
        for (i in items.indices) {
            if (items[i] == null) {
                continue
            }
            if (TanningProduct.forItemId(items[i]!!.id) != null) {
                npcl(FacialExpression.HALF_ASKING, "I see you have brought me some hides. Would you like me to tan them for you?").also { stage = 24 }
                return true
            }
        }
        npcl(FacialExpression.NEUTRAL, "Greetings friend. I am a manufacturer of leather.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int) : Boolean {
        when(stage) {
            0 -> options("Can I buy some leather then?", "Leather is rather weak stuff.").also{ stage++ }
            1 -> when(buttonId) {
                1 -> playerl(FacialExpression.ASKING,"Can I buy some leather then?").also{ stage = 10 }
                2 -> playerl(FacialExpression.SUSPICIOUS, "Leather is rather weak stuff.").also { stage = 20 }
            }
            10 -> npcl(FacialExpression.FRIENDLY, "I make leather from animal hides. Bring me some cowhides and one gold coin per hide, and I'll tan them into soft leather for you.").also{ stage = END_DIALOGUE }
            20 -> npcl(FacialExpression.NOD_YES, "Normal leather may be quite weak, but it's very cheap - I make it from cowhides for only 1 gp per hide - and it's so easy to craft that anyone can work with it.").also{ stage++ }
            21 -> npcl(FacialExpression.HALF_THINKING, "Alternatively you could try hard leather. It's not so easy to craft, but I only charge 3 gp per cowhide to prepare it, and it makes much sturdier armour.").also{ stage++ }
            22 -> npcl(FacialExpression.FRIENDLY, "I can also tan snake hides and dragonhides, suitable for crafting into the highest quality armour for rangers.").also{ stage++ }
            23 -> player(FacialExpression.FRIENDLY,"Thanks; I'll bear it in mind.").also { stage = END_DIALOGUE }
            24 -> options("Yes please.", "No thanks.").also { stage++ }
            25 -> when(buttonId){
                1 -> {
                    end()
                    TanningProduct.open(player, NPCs.TANNER_804).also { stage = END_DIALOGUE }
                }
                2 -> playerl(FacialExpression.NEUTRAL, "Thanks, I'll bear it in mind.").also { stage = END_DIALOGUE }
            }
        }
        return true
    }
    override fun getIds() : IntArray {
        return intArrayOf(NPCs.TANNER_804)
    }
}