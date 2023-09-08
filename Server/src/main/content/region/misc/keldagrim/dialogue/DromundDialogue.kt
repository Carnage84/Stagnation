package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Dromund dialogue plugin.
 */
@Initializable
class DromundDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,
            "Someone stole my beautiful boots...",
            "I had to buy some crummy replicas,",
            "the real boots were one of a kind."
        )
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> {
                player(FacialExpression.FRIENDLY,"Don't worry, if I find out who did it", "I'll take care of them.")
                stage++
            }

            1 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DROMUND_2169)
    }
}