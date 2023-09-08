package content.region.misthalin.varrock.handlers

import content.region.misthalin.varrock.dialogue.GrandExchangeClerkDialogue
import core.api.openDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

/**
 * Handles the Grand Exchange Clerk options.
 */
class GrandExchangeClerksHandler : InteractionListener {
    companion object {
        private const val CLERK_1 = 6528
        private const val CLERK_2 = 6529
        private const val CLERK_3 = 6530
        private const val CLERK_4 = 6531

        private val GE_CLERKS = intArrayOf(CLERK_1, CLERK_2, CLERK_3, CLERK_4)
    }
    override fun defineListeners() {
        on(GE_CLERKS, IntType.NPC, "talk-to") { player, npc ->
            openDialogue(player, GrandExchangeClerkDialogue(), npc)
            return@on true
        }
    }
    override fun defineDestinationOverrides() {
        setDest(IntType.NPC, intArrayOf(CLERK_1), "talk-to") { _, _ -> return@setDest Location.create(3165, 3492, 0) }
        setDest(IntType.NPC, intArrayOf(CLERK_2), "talk-to") { _, _ -> return@setDest Location.create(3164, 3487, 0) }
        setDest(IntType.NPC, intArrayOf(CLERK_3), "talk-to") { _, _ -> return@setDest Location.create(3164, 3492, 0) }
        setDest(IntType.NPC, intArrayOf(CLERK_4), "talk-to") { _, _ -> return@setDest Location.create(3165, 3487, 0) }
    }
}