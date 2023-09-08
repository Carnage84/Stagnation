package content.region.kandarin.seers.handlers

import config.NPCs
import config.Scenery
import core.api.face
import core.api.findNPC
import core.api.sendMessage
import core.api.sendNPCDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Handles both gates to MCGrubor Wood.
 */
class MCGruborGateHandler : InteractionListener {

    companion object {
        val GATES = intArrayOf(Scenery.GATE_52, Scenery.GATE_53)
    }
    override fun defineListeners() {
        on(GATES, IntType.SCENERY, "open") { player, _ ->
            face(findNPC(NPCs.FORESTER_231)!!, player, 3)
            sendNPCDialogue(player, NPCs.FORESTER_231, "Hey! You can't come through here! This is private land!")
            sendMessage(player, "There might be a gap in the fence somewhere where he wouldn't see you sneak in.")
            sendMessage(player, " You should look around.")
            return@on true
        }
    }
}