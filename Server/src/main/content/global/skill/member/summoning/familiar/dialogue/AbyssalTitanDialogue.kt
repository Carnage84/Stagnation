package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Abyssal Titan familiar interaction dialogue.
 */
@Initializable
class AbyssalTitanDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage) {
            0 -> {
                npcl(FacialExpression.OLD_NORMAL, "Scruunt, scraaan.").also {
                    end()
                    stage = END_DIALOGUE
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ABYSSAL_TITAN_7349, NPCs.ABYSSAL_TITAN_7350)
    }

}