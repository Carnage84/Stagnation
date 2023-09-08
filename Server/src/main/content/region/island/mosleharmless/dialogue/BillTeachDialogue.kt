package content.region.island.mosleharmless.dialogue

import config.Items
import config.NPCs
import core.api.addItemOrDrop
import core.api.inInventory
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Bill Teach dialogue plugin.
 */
@Initializable
class BillTeachDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        val pirateBook = inInventory(player,Items.BOOK_O_PIRACY_7144)
        npc = args[0] as NPC
        if(!pirateBook) {
            options("Speak about Rocking Out.", "Talk about something else")
            stage = 1
        } else {
            options("Speak about Rocking Out.")
            stage = 7
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        val pirateBook = inInventory(player,Items.BOOK_O_PIRACY_7144)
        when (stage) {
            1 -> when(buttonId) {
                1 -> end()
                2 -> playerl(FacialExpression.SAD, "Cap'n, I lost that book you gave me.").also { stage++ }
            }
            7 -> when (buttonId) {
                1 -> end()
            }
            2 -> npc(FacialExpression.NEUTRAL,"Ye'll notice I'm not fallin' to the floor in shock right","now.").also { stage++ }
            3 -> npc(FacialExpression.HALF_GUILTY, "Look, if I gives ye another copy will ye keep a better", "eye on it?").also { stage++ }
            4 -> player(FacialExpression.HAPPY,"Aye Cap'n!").also { stage++ }
            5 -> npc(FacialExpression.NEUTRAL,"Then here, have this other copy I dug up.").also { stage++ }
            6 -> {
                if(!pirateBook){
                    end()
                    addItemOrDrop(player, Items.BOOK_O_PIRACY_7144)
                } else {
                    end()
                    npc("If yer after a lift go to the ship and I'll take ye where ye"," want to go.")
                    stage = END_DIALOGUE
                }
            }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return BillTeachDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BILL_TEACH_3155)
    }
}