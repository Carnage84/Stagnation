package content.global.travel.balloon

import config.Components
import core.api.*
import core.cache.def.impl.NPCDefinition
import core.cache.def.impl.SceneryDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.plugin.Plugin
import core.tools.DARK_RED

/**
 * Handle use options for baskets.
 */
@Initializable
class BalloonHandler : OptionHandler() {

    override fun newInstance(arg: Any?): Plugin<Any> {
        SceneryDefinition.forId(19128).handlers["option:use"] = this
        SceneryDefinition.forId(19129).handlers["option:use"] = this
        SceneryDefinition.forId(19133).handlers["option:use"] = this
        SceneryDefinition.forId(19135).handlers["option:use"] = this
        SceneryDefinition.forId(19143).handlers["option:use"] = this
        SceneryDefinition.forId(19141).handlers["option:use"] = this
        SceneryDefinition.forId(19137).handlers["option:use"] = this
        SceneryDefinition.forId(19139).handlers["option:use"] = this
        NPCDefinition.forId(5054).handlers["option:talk-to"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        when (node.id) {
            5054  -> player.dialogueInterpreter.open(5065)
            19128,
            19129,
            19133,
            19135,
            19143,
            19141,
            19137,
            19139 -> if (!isQuestComplete(player, "Enlightened Journey")) {
                sendMessage(player, DARK_RED + "You must complete Enlightened Journey before you can use it.")
            } else {
                openInterface(player, Components.ZEP_BALLOON_MAP_469).also {
                    when (node.asScenery().getWrapper().getId()) {
                        19128, 19133 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 12, false)
                        19135 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 22, false)
                        19137 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 24, false)
                        19139 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 23, false)
                        19141 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 20, false)
                        19143 -> setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 21, false)
                    }
                }
            }
        }
        return true
    }
}