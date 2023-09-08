package content.quest.member.thelosttribe.dialogue

import content.quest.member.thelosttribe.handlers.GoblinFollower
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents Kazgar dialogue for Tribal totem quest.
 */
@Initializable
class KazgarLTDDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun newInstance(player: Player?): DialoguePlugin {
        return KazgarLTDDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = (args[0] as NPC).getShownNPC(player)
        npc(FacialExpression.OLD_NORMAL,"Hello, surface-dweller.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage) {
             0 -> options("Who are you?","Can you show me the way to the mine?").also { stage++ }
             1 -> when (buttonId) {
                    1 -> player("Who are you?").also { stage = 10 }
                    2 -> player("Can you show me the way to the mine?").also { stage = 20 }
                }
             10 -> npc(FacialExpression.OLD_NORMAL,"I'm Kazgar, I guide people through the mines.").also { stage = 1000 }
             20 -> npc(FacialExpression.OLD_NORMAL,"All right. Follow me.").also { stage++ }
             21 -> end().also {
                 GoblinFollower.sendToMines(player)
             }

            1000 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(2085)
    }

}