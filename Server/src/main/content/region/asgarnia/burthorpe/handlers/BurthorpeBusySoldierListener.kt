package content.region.asgarnia.burthorpe.handlers

import config.NPCs
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Represents the InteractionListener used for the Burthorpe soldier messages.
 */
class BurthorpeBusySoldierListener: InteractionListener {
    override fun defineListeners() {
        // For sergeants.
        on(intArrayOf(NPCs.SERGEANT_1061, NPCs.SERGEANT_1062), IntType.NPC, "talk-to") { player, npc ->
            sendMessage(player, "The Sergeant is busy training the soldiers.")
            return@on true
        }

        // For soldiers training.
        on(intArrayOf(NPCs.SOLDIER_1063, NPCs.SOLDIER_1064), IntType.NPC, "talk-to") { player, npc ->
            sendMessage(player, "The soldier is busy training.")
            return@on true
        }

        // For soldiers sitting around the fire.
        on(intArrayOf(NPCs.SOLDIER_1066, NPCs.SOLDIER_1067, NPCs.SOLDIER_1068), IntType.NPC, "talk-to") { player, npc ->
            sendMessage(player, "The soldier is busy eating.")
            return@on true
        }

        // For archers at the castle.
        on(intArrayOf(NPCs.ARCHER_1073, NPCs.ARCHER_1074), IntType.NPC, "talk-to") { player, npc ->
            sendMessage(player, "The archer won't talk whilst on duty.")
            return@on true
        }

        // For soldiers at the castle.
        on(intArrayOf(NPCs.GUARD_1076, NPCs.GUARD_1077), IntType.NPC, "talk-to") { player, npc ->
            sendMessage(player, "The guard won't talk whilst on duty.")
            return@on true
        }
    }
}