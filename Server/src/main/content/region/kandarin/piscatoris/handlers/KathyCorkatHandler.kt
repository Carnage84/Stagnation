package content.region.kandarin.piscatoris.handlers

import config.Components
import config.NPCs
import core.api.*
import core.cache.def.impl.NPCDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles travel option for Kathy Corkat NPC.
 */
@Initializable
class KathyCorkatHandler : OptionHandler() {

    override fun newInstance(arg: Any?): Plugin<Any> {
        NPCDefinition.forId(3831).handlers["option:travel"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        when (node.id) {
            NPCs.KATHY_CORKAT_3831 -> {
                lock(player, 10000)
                GameWorld.Pulser.submit(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> openInterface(player, Components.FADE_TO_BLACK_120)
                            1 -> sendDialogue(player,"Kathy Corkat rows you up the river...")
                            3 -> teleport(player, location(2369, 3484, 0))
                            4 -> openInterface(player, Components.FADE_FROM_BLACK_170)
                            6 -> closeInterface(player).also {
                                unlock(player)
                                return true
                            }
                        }
                        return false
                    }
                })
            }
        }
        return true
    }
}