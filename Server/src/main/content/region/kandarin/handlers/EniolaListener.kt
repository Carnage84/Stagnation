package content.region.kandarin.handlers

import config.Components
import config.NPCs
import core.api.openInterface
import core.api.restrictForIronman
import core.api.setAttribute
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.link.IronmanMode

/**
 * Handles special banking interactions for Eniola at ZMI altar area.
 */
class EniolaListener : InteractionListener {
    override fun defineListeners() {
        on(NPCs.ENIOLA_6362, IntType.NPC, "bank") { player, _ ->
            restrictForIronman(player, IronmanMode.ULTIMATE) {
                setAttribute(player, "zmi:bankaction", "open")
                openInterface(player, Components.BANK_CHARGE_ZMI_619)
            }

            return@on true
        }

        on(NPCs.ENIOLA_6362, IntType.NPC, "collect") { player, _ ->
            restrictForIronman(player, IronmanMode.ULTIMATE) {
                setAttribute(player, "zmi:bankaction", "collect")
                openInterface(player, Components.BANK_CHARGE_ZMI_619)
            }

            return@on true
        }
    }
}