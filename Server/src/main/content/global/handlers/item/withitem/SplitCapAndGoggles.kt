package content.global.handlers.item.withitem

import config.Items
import core.api.addItemOrDrop
import core.api.removeItem
import core.cache.def.impl.ItemDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles Split option for Bomber Cap & Gnome goggles.
 */
@Initializable
class SplitCapAndGoggles : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        ItemDefinition.forId(Items.CAP_AND_GOGGLES_9946).handlers["option:split"] = this
        return this
    }

    override fun handle(player: Player, node: Node?, option: String?): Boolean {
        if (removeItem(player, Items.CAP_AND_GOGGLES_9946)) {
            addItemOrDrop(player, Items.BOMBER_CAP_9945, 1)
            addItemOrDrop(player, Items.GNOME_GOGGLES_9472, 1)
        }
        return true
    }
}