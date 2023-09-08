package content.region.kandarin.ardougne.dialogue

import config.NPCs
import content.quest.member.clocktower.dialogue.BrotherKojoCTDialogueFile
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Brother Kojo dialogue plugin.
 */
@Initializable
class BrotherKojoDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        // ClockTower
        openDialogue(player!!, BrotherKojoCTDialogueFile(), npc)
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return BrotherKojoDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BROTHER_KOJO_223)
    }
}