package content.region.misc.keldagrim.handlers

import config.NPCs
import core.cache.def.impl.NPCDefinition
import core.game.component.Component
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles Reinald's (right-click) option.
 */
@Initializable
class ReinaldOptionHandler : OptionHandler(){
    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player?.interfaceManager?.open(Component(593))
        return true
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        NPCDefinition.forId(NPCs.REINALD_2194).handlers["option:change-armguards"] = this
        return this
    }
}