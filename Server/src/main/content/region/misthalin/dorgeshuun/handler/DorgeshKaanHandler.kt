package content.region.misthalin.dorgeshuun.handler

import core.api.location
import core.api.teleport
import core.cache.def.impl.NPCDefinition
import core.cache.def.impl.SceneryDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles Dorgesh-Kaan area.
 */
@Initializable
class DorgeshKaanHandler : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        SceneryDefinition.forId(32952).handlers["option:open"] = this // Mines -> Dorgesh-Kaan.
        SceneryDefinition.forId(32953).handlers["option:open"] = this // Mines -> Dorgesh-Kaan.
        SceneryDefinition.forId(22945).handlers["option:open"] = this // Dorgesh-Kaan -> Mines.
        NPCDefinition.forId(5863).handlers["option:talk-to"] = this // Special handle for Ambassador Alvijar dialogue.
        return this

    }
    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        when(node.id) {
            32952 -> teleport(player, location(2747, 5374, 0))
            32953 -> teleport(player, location(2748, 5374, 0))
            22945 -> teleport(player, location(3318, 9602, 0))
            5863 -> player.dialogueInterpreter.open(5887)
        }
        return true
    }
}
