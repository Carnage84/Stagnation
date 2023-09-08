package content.quest.member.thelosttribe.handlers

import config.Items
import core.api.*
import core.cache.def.impl.SceneryDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.node.item.GroundItemManager
import core.game.node.item.Item
import core.plugin.Initializable
import core.plugin.Plugin


/**
 * Handles sigmund's chest
 */
@Initializable
class SigmundChestHandler : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        SceneryDefinition.forId(6910).handlers["option:open"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        if(getQuestStage(player,"Lost Tribe") == 47 && inInventory(player,Items.KEY_423,1)){
            removeItem(player, Item(Items.KEY_423))
            for(item in arrayOf(Items.HAM_ROBE_4300, Items.HAM_SHIRT_4298, Items.HAM_HOOD_4302).map { Item(it) }){
                if(!player.inventory.add(item)){
                    GroundItemManager.create(item,player)
                }
            }
            setQuestStage(player,"Lost Tribe", 48)
        } else {
            sendMessage(player,"This chest requires a key.")
        }
        return true
    }
}