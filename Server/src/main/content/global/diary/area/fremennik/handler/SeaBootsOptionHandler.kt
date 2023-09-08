package content.global.diary.area.fremennik.handler

import config.Items
import core.api.openDialogue
import core.cache.def.impl.ItemDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * handler for the Fremennik sea boots.
 */
@Initializable
class SeaBootsOptionHandler : OptionHandler() {

    override fun newInstance(arg: Any?): Plugin<Any> {
        ItemDefinition.forId(14571).handlers["option:operate"] = this
        ItemDefinition.forId(14572).handlers["option:operate"] = this
        ItemDefinition.forId(14573).handlers["option:operate"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        when (node.id) {
            // https://runescape.wiki/w/Fremennik_sea_boots_1?oldid=917535
            Items.FREMENNIK_SEA_BOOTS_1_14571 -> openDialogue(player, FremennikSeaBootsOption(), node.asItem())
            // https://runescape.wiki/w/Fremennik_sea_boots_2?oldid=1850881
            Items.FREMENNIK_SEA_BOOTS_2_14572 -> openDialogue(player, FremennikSeaBootsOption(), node.asItem())
            // https://runescape.wiki/w/Fremennik_sea_boots_3?oldid=826531
            Items.FREMENNIK_SEA_BOOTS_3_14573 -> openDialogue(player, FremennikSeaBootsOption(), node.asItem())
        }
        return true
    }
}