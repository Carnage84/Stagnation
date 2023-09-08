package content.region.misc.keldagrim.dialogue.directors

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the dialogue plugin used for the Blue Opal Director in Keldagrim.
 */
@Initializable
class BlueOpalDirector(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        sendDialogue("is too important to talk to you.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BLUE_OPAL_DIRECTOR_2101)
    }
}