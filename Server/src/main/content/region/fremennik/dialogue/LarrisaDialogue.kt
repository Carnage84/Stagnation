package content.region.fremennik.dialogue

import config.NPCs
import content.quest.member.horrorfromthedeep.dialogue.LarrisaHFTDDialogue
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.START_DIALOGUE

/**
 * Represents the Larrisa dialogue plugin.
 */
@Initializable
class LarrisaDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            START_DIALOGUE -> openDialogue(player, LarrisaHFTDDialogue(), npc)
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.LARRISSA_1336)
    }
}