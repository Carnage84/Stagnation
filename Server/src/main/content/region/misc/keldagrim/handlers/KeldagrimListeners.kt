package content.region.misc.keldagrim.handlers

import config.NPCs
import config.Scenery
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

class KeldagrimListeners : InteractionListener {
    override fun defineListeners() {
        on(Scenery.DOORWAY_23286, IntType.SCENERY, "enter") { player, _ ->
            player.properties.teleportLocation = Location.create(2941, 10179, 0)
            return@on true
        }
        on(Scenery.DOORWAY_23287, IntType.SCENERY, "enter") { player, _ ->
            player.properties.teleportLocation = Location.create(2435, 5535, 0)
            return@on true
        }
    }

    override fun defineDestinationOverrides() {
        setDest(IntType.NPC, intArrayOf(NPCs.INN_KEEPER_2176), "talk-to"){ _,_ ->
            return@setDest Location.create(2843, 10193, 1).transform(-2,-2,0)
        }
    }
}