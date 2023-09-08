package content.quest.member.thegiantdwarf

import config.Scenery
import core.api.location
import core.api.setQuestStage
import core.api.setVarbit
import core.api.teleport
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * The Keldagrim entrance listener.
 */
class TheGiantDwarfListener : InteractionListener {

    private val WALL = intArrayOf(Scenery.CAVE_ENTRANCE_5973, Scenery.ENTRANCE_5998)

    override fun defineListeners() {
        on(WALL, IntType.SCENERY, "go-through"){player, node ->
            when (node.id) {
                Scenery.CAVE_ENTRANCE_5973 -> teleport(player, location(2838, 10125,0))
                Scenery.ENTRANCE_5998 -> teleport(player, location(2780, 10161,0))
            }
            setQuestStage(player, "The Giant Dwarf", 2)
            setVarbit(player, 571, 1, true)
            return@on true

        }
    }
}