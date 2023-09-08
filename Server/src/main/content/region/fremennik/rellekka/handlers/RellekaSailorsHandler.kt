package content.region.fremennik.rellekka.handlers

import content.region.island.miscellania.dialogue.MiscellaniaSailorDialogue
import content.region.fremennik.rellekka.dialogue.RellekaSailorDialogue
import core.api.openDialogue
import core.api.requireQuest
import core.cache.def.impl.NPCDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles option for Sailors on Rellekka/Miscellania.
 */
@Initializable
class RellekaSailorsHandler : OptionHandler() {

    override fun newInstance(arg: Any?): Plugin<Any> {
        NPCDefinition.forId(1304).handlers["option:talk-to"] = this
        NPCDefinition.forId(1385).handlers["option:talk-to"] = this
        NPCDefinition.forId(1304).handlers["option:travel"] = this
        NPCDefinition.forId(1385).handlers["option:travel"] = this
        return this
    }
    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        when (node.id) {
            1304 -> if(option.toString() == "talk-to") {
                if (!requireQuest(player, "Fremennik Trials", "")) return false
                openDialogue(player, MiscellaniaSailorDialogue(false), node.asNpc())
            } else {
                openDialogue(player, MiscellaniaSailorDialogue(true), node.asNpc())
            }

            1385 -> if(option.toString() == "talk-to") {
                if (!requireQuest(player, "Fremennik Trials", "")) return false
                openDialogue(player, RellekaSailorDialogue(false), node.asNpc())
            } else {
                openDialogue(player, RellekaSailorDialogue(true), node.asNpc())
            }
        }
        return true
    }
}