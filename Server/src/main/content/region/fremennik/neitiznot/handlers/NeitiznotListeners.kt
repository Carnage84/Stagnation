package content.region.fremennik.neitiznot.handlers

import config.NPCs
import core.api.addClimbDest
import core.api.registerMapZone
import core.api.sendMessage
import core.api.sendPlayerDialogue
import core.game.dialogue.FacialExpression
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.Node
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.world.map.Location
import core.game.world.map.zone.MapZone
import core.game.world.map.zone.ZoneBorders

/**
 * Listeners for Neitiznot area.
 */
class NeitiznotListeners : InteractionListener {
    val STUMP = 21305

    override fun defineListeners() {

        on(STUMP, IntType.SCENERY, "cut-wood"){ player, _ ->
            sendPlayerDialogue(player, "I should probably leave this alone.", FacialExpression.HALF_THINKING)
            return@on true
        }

        val zone = object : MapZone("Yakzone", true){
            override fun handleUseWith(player: Player, used: Item?, with: Node?): Boolean {
                if(with is NPC && with.id == NPCs.YAK_5529){
                    sendMessage(player, "The cow doesn't want that.")
                    return true
                }
                return false
            }
        }

        registerMapZone(zone, ZoneBorders(2313,3786,2331,3802))
        addClimbDest(Location.create(2363, 3799, 0), Location.create(2364, 3799, 2))
        addClimbDest(Location.create(2363, 3799, 2), Location.create(2362, 3799, 0))
    }
}