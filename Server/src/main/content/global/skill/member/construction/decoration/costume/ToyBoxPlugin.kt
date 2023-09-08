package content.global.skill.member.construction.decoration.costume

import content.global.handlers.item.toys.DiangoReclaimInterface
import core.cache.def.impl.SceneryDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles the Toy Box.
 */
@Initializable
class ToyBoxPlugin : OptionHandler() {
    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any?> {
        SceneryDefinition.forId(18802).handlers["option:open"] = this
        return this
    }

    override fun handle(player: Player, node: Node, option: String): Boolean {
        DiangoReclaimInterface.open(player)
        return true
    }
}