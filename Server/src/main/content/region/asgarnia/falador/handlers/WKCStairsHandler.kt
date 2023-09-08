package content.region.asgarnia.falador.handlers

import config.Scenery
import core.api.inBorders
import core.api.teleport
import core.cache.def.impl.SceneryDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles the stairs in White Knights' Castle.
 */
@Initializable
class WKCStairsHandler : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        SceneryDefinition.forId(Scenery.STAIRCASE_11729).handlers["option:climb-up"] = this
        SceneryDefinition.forId(Scenery.STAIRCASE_11731).handlers["option:climb-down"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        when (node!!.id) {

            Scenery.STAIRCASE_11729 -> if(inBorders(player, 2954, 3334, 2963, 3343)) {
                when (player.location.z) {
                    0 -> teleport(player, Location(2956, 3338, 1))
                    1 -> teleport(player, Location(2959, 3339, 2))
                    2 -> teleport(player, Location(2959, 3338, 3))
                }
            }

            Scenery.STAIRCASE_11731 -> if(inBorders(player, 2954, 3334, 2963, 3343)) {
                when (player.location.z) {
                    3 -> teleport(player, Location(2959, 3338, 2))
                    2 -> teleport(player, Location(2959, 3339, 1))
                    1 -> teleport(player, Location(2956, 3338, 0))
                }
            }
        }
        return true
    }
}