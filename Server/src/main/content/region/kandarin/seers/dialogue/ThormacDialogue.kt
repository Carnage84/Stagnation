package content.region.kandarin.seers.dialogue

import config.NPCs
import content.quest.member.scorpioncatcher.dialogue.ThormacSCDialogue
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.START_DIALOGUE

/**
 * Represents the Thormac dialogue plugin.
 */
@Initializable
class ThormacDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            START_DIALOGUE -> openDialogue(player, ThormacSCDialogue())
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return ThormacDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.THORMAC_389)
    }
}