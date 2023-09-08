package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Wolpertinger familiar interaction dialogue.
 */
@Initializable
class WolpertingerDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage) {
            0 -> {
                npcl(FacialExpression.OLD_NORMAL, "Raaar! Mewble, whurf whurf.").also {
                    end()
                    stage = END_DIALOGUE
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.WOLPERTINGER_6869, NPCs.WOLPERTINGER_6870)
    }

}