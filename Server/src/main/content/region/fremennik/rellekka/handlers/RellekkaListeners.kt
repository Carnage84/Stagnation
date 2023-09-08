package content.region.fremennik.rellekka.handlers

import config.NPCs
import core.api.requireQuest
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

/**
 * File to be used for anything Rellekka related.
 * Handles the snow kebbit stairs up and down.
 */
class RellekkaListeners : InteractionListener {

    val UP1A = Location.create(2715, 3798, 0)
    val UP1B = Location.create(2716, 3798, 0)
    val UP2A = Location.create(2726, 3801, 0)
    val UP2B = Location.create(2727, 3801, 0)

    val DOWN1A = Location.create(2715, 3802, 1)
    val DOWN1B = Location.create(2716, 3802, 1)
    val DOWN2A = Location.create(2726, 3805, 1)
    val DOWN2B = Location.create(2727, 3805, 1)
    val STAIRS = intArrayOf(19690,19691)

    override fun defineListeners() {
        on(STAIRS, IntType.SCENERY, "ascend","descend"){ player, _ ->
            if(player.location.y < 3802){
                player.properties.teleportLocation = when(player.location.x){
                    2715 -> DOWN1A
                    2716 -> DOWN1B
                    2726 -> DOWN2A
                    2727 -> DOWN2B
                    else -> player.location
                }
            } else {
                player.properties.teleportLocation = when(player.location.x) {
                    2715 -> UP1A
                    2716 -> UP1B
                    2726 -> UP2A
                    2727 -> UP2B
                    else -> player.location
                }
            }
            return@on true
        }

        on(NPCs.MARIA_GUNNARS_5508, IntType.NPC, "ferry-neitiznot"){ player, _ ->
            if (!requireQuest(player, "Fremennik Trials",""))
                return@on true
            RellekkaUtils.sail(player, TravelDestination.RELLEKKA_TO_NEITIZNOT)
            return@on true
        }

        on(NPCs.MARIA_GUNNARS_5507, IntType.NPC, "ferry-rellekka"){ player, _ ->
            RellekkaUtils.sail(player, TravelDestination.NEITIZNOT_TO_RELLEKKA)
            return@on true
        }

        on(NPCs.MORD_GUNNARS_5481, IntType.NPC, "ferry-jatizso"){ player, _ ->
            if (!requireQuest(player, "Fremennik Trials",""))
                return@on true
            RellekkaUtils.sail(player, TravelDestination.RELLEKKA_TO_JATIZSO)
            return@on true
        }

        on(NPCs.MORD_GUNNARS_5482, IntType.NPC, "ferry-rellekka"){ player, _ ->
            RellekkaUtils.sail(player, TravelDestination.JATIZSO_TO_RELLEKKA)
            return@on true
        }
    }
}
