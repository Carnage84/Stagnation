package content.global.travel.balloon

import config.Components
import config.NPCs
import core.api.isQuestComplete
import core.api.openInterface
import core.api.sendMessage
import core.api.setComponentVisibility
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC
import core.game.world.map.Location

/**
 * Listener for Assistants NPCs for Balloon travel.
 */
class AssistantsListener : InteractionListener {

    companion object {

        val assistantAuguste = NPC(NPCs.AUGUSTE_5050, Location.create(2938, 3424, 0))
        val assistantVarrock = NPC(NPCs.ASSISTANT_SERF_5053, Location.create(3298, 3484, 0))
        val assistantCastleWars = NPC(NPCs.ASSISTANT_MARROW_5055, Location.create(2462, 3108, 0))
        val assistantGrandTree = NPC(NPCs.ASSISTANT_LE_SMITH_5056, Location.create(2480, 3458, 0))
        val assistantTaverley = NPC(NPCs.ASSISTANT_STAN_5057, Location.create(2938, 3424, 0))

        val allAssistants = intArrayOf(
            NPCs.AUGUSTE_5049,
            NPCs.AUGUSTE_5050,
            NPCs.ASSISTANT_SERF_5053,
            NPCs.ASSISTANT_BROCK_5054,
            NPCs.ASSISTANT_MARROW_5055,
            NPCs.ASSISTANT_LE_SMITH_5056,
            NPCs.ASSISTANT_STAN_5057
        )
    }

    init {
        assistantTaverley.init()
        assistantAuguste.init()
        assistantCastleWars.init()
        assistantGrandTree.init()
        assistantVarrock.init()

        assistantTaverley.isWalks = true
        assistantAuguste.isWalks = true
        assistantCastleWars.isWalks = true
        assistantGrandTree.isWalks = true
        assistantVarrock.isWalks = true
    }

    override fun defineListeners() {

        on(allAssistants, IntType.NPC, "fly") { player, node ->
            if (!isQuestComplete(player, "Enlightened Journey")) {
                sendMessage(player, "You must complete Enlightened Journey before you can use it.")
            } else {
                openInterface(player, Components.ZEP_BALLOON_MAP_469).also {
                    for (i in allAssistants.indices) {
                        when (node.id) {
                            5049 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 12, false)
                            5050 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 22, false)
                            5053 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 21, false)
                            5054 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 20, false)
                            5055 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 24, false)
                            5056 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 23, false)
                            5057 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 22, false)
                        }
                    }
                }
            }
            return@on true
        }
    }
}