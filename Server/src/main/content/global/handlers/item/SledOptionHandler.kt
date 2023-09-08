package content.global.handlers.item

import config.Items
import core.api.equipSlot
import core.cache.def.impl.ItemDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.world.update.flag.context.Animation
import core.plugin.Initializable
import core.plugin.Plugin

@Initializable
class SledOptionHandler : OptionHandler() {

    private val ridingAnimation = Animation(1461)

    override fun newInstance(arg: Any?): Plugin<Any> {
        ItemDefinition.forId(4084).handlers["option:ride"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        when (option) {
            "ride" -> {
                equipSlot(Items.SLED_4084)
            }
        }
        return true
    }

}